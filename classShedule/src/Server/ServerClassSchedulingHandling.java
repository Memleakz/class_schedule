/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Business_Logic.Bookings.BookingFactory;
import Business_Logic.Common.Period;
import Business_Logic.Courses.CourseFactory;
import Business_Logic.Days.ClasificationFactory;
import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.ClasificationInterface;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.LocationInterface;
import Business_Logic.IServices.StudentsInterface;
import Business_Logic.IServices.TeacherInterface;
import Business_Logic.Locations.RoomFactory;
import Business_Logic.Participants.StudentsFactory;
import Business_Logic.Responsible.LecturerFactory;
import Business_Logic.scheldue_result.Scheldue_result;
import static Server.ServerUserHandling.changeDataPickersToPeriod;
import Server.database.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lawar15
 */
public class ServerClassSchedulingHandling {

    static DatabaseManager dbm = DatabaseManager.getInstance();

    private List<CourseInterface> Courses;
    List<LocationInterface> Rooms;
    static List<Period> specialClosePeriod = new ArrayList<Period>();

    public void addSpecialClosePeriod(String start, String end) {

    }

    public void addNewRoom(LocationInterface r) {
	this.Rooms.add(r);
    }

    public void addNewCourse(CourseInterface c) {
	this.Courses.add(c);
    }

    static Scheldue_result AssignRoomsForCourses(String SemesterStart, String SemesterEnd, List<LocationInterface> Rooms, List<CourseInterface> Courses) {
	List<CourseInterface> bookedCourses = new ArrayList<CourseInterface>();
	List<CourseInterface> BookingFails = new ArrayList<CourseInterface>();

	Period BookingPeriod = new Period(SemesterStart, SemesterEnd);
	for (CourseInterface c : Courses) {
	    //Find best match room ?
	    List<LocationInterface> allPossibleRooms = findAllPossibleRoomsToCourse(c, Rooms);
	    int Total_needed_bookings = c.getNumberOfLessons() / c.getNumberOfHourstogether();

	    List<BookingLocationsInterface> bookings = new ArrayList<BookingLocationsInterface>();
	    List<BookingLocationsInterface> known_bookings = null;
	    Map<LocationInterface, List<BookingLocationsInterface>> l = new HashMap<LocationInterface, List<BookingLocationsInterface>>();
	    boolean BookingDone = false;

	    for (LocationInterface r : allPossibleRooms) {
		known_bookings = l.get(r);
		List<BookingLocationsInterface> abooks = AttemBookCourseTimes(null, c, r, "bedst", Total_needed_bookings, BookingPeriod);
		if (known_bookings == null) {
		    l.put(r, abooks);
		} else {
		    known_bookings.addAll(abooks);
		    l.put(r, known_bookings);
		}

		if (helperCountBookings(l) == Total_needed_bookings) {
		    //we booked all the rooms we needed.
		    BookingDone = true;
		    break;
		}
	    }
	    if (BookingDone != true) {
		for (LocationInterface r : allPossibleRooms) {
		    known_bookings = l.get(r);
		    List<BookingLocationsInterface> abooks = AttemBookCourseTimes(null, c, r, "medium", Total_needed_bookings, BookingPeriod);
		    if (known_bookings == null) {
			l.put(r, abooks);
		    } else {
			known_bookings.addAll(abooks);
			l.put(r, known_bookings);
		    }
		    if (helperCountBookings(l) == Total_needed_bookings) {
			//we booked all the rooms we needed.
			BookingDone = true;
			break;
		    }
		}
	    }
	    if (BookingDone != true) {
		for (LocationInterface r : allPossibleRooms) {
		    known_bookings = l.get(r);
		    //List<BookingLocationsInterface> abooks = AttemBookCourseTimes(null, c, r, "emergency", Total_needed_bookings, BookingPeriod,bestTimeForDay,mediumTimeForDay,emergencyTimeForDay);
		    List<BookingLocationsInterface> abooks = AttemBookCourseTimes(null, c, r, "emergency", Total_needed_bookings, BookingPeriod);
		    if (known_bookings == null) {
			l.put(r, abooks);
		    } else {
			known_bookings.addAll(abooks);
			l.put(r, known_bookings);
		    }
		    if (helperCountBookings(l) == Total_needed_bookings) {
			//we booked all the rooms we needed.
			BookingDone = true;
			break;
		    }
		}
	    }

	    if (helperCountBookings(l) != Total_needed_bookings) {
		//We failed to book all lessons for the this
		// log error with course and bookings and the bookings possible for later display.
		System.out.println("Failed to book lessons for course: " + c.getNameOfTheCourse());
		BookingFails.add(c);
	    } else {
		System.out.println("Booked all lessons for course: " + c.getNameOfTheCourse());
		CourseInterface finalizedCourse = finalizeBookings(c, l);
		bookedCourses.add(finalizedCourse);
	    }
	    //place booking
	}
	Scheldue_result ret_obj = new Scheldue_result();
	ret_obj.setBookingFails(BookingFails);
	ret_obj.setBookedCourses(bookedCourses);

	return ret_obj;
    }

    private static boolean addNewBookingsToRoomToDb(LocationInterface room, List<BookingLocationsInterface> bookingsForRoom) {
	int roomsId = room.getId();
	for (BookingLocationsInterface onebooking : bookingsForRoom) {
	    LocalDateTime startdate = onebooking.getPeriodOfBooking().getStartDate();
	    LocalDateTime enddate = onebooking.getPeriodOfBooking().getEndDate();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    String formattedStartDate = startdate.format(formatter);
	    String formattedFinishDate = enddate.format(formatter);
	    int idOfPeriod = getPeriodsIdByStartAndFinishDate(formattedStartDate, formattedFinishDate);
	    int bookingsId = onebooking.getId();
	    String coursesId = onebooking.getCourse().getId();
	    String newcourseInRoomQuery = "INSERT INTO \"BookedRoomsForCourse\" (\"courseInRoom\",\"id_bookedRoomsCourse\",\"fk_room\") VALUES ('" + coursesId + "',DEFAULT,'" + roomsId + "')";
	    dbm.execute(newcourseInRoomQuery);
	    String newbookingsInRoomQuery = "INSERT INTO \"RoomsAndBookings\" (\"id_rooms_for_bookings\",\"room_for_courses\",\"bookingsToRooms\") VALUES (DEFAULT,'" + roomsId + "','" + bookingsId + "')";
	    dbm.execute(newbookingsInRoomQuery);
	    String newBookedPeriodsInRoomQuery = "INSERT INTO \"BookedPeriodsForRoom\" (\"id_booked_period\",\"room_for_period\",\"period_for_booking\") VALUES (DEFAULT,'" + roomsId + "','" + idOfPeriod + "')";
	    dbm.execute(newBookedPeriodsInRoomQuery);
	    return true;
	}

	return false;
    }

    private static CourseInterface finalizeBookings(CourseInterface c, Map<LocationInterface, List<BookingLocationsInterface>> bookings) {
	List<LocationInterface> bookedRooms = new ArrayList<LocationInterface>();
	for (Map.Entry<LocationInterface, List<BookingLocationsInterface>> entry : bookings.entrySet()) {
	    LocationInterface r = entry.getKey();
	    List<BookingLocationsInterface> booklist = entry.getValue();
	    for (BookingLocationsInterface b : booklist) {
		AddNewBookingToDb(b);
		r.addNewBooking(b, c);
	    }
	    addNewBookingsToRoomToDb(r, booklist);
	    bookedRooms.add(r);

	}
	c.setBookedRooms(bookedRooms);
	return c;
    }

    public static int helperCountBookings(Map<LocationInterface, List<BookingLocationsInterface>> bookings) {
	int totalBookings = 0;
	for (Map.Entry<LocationInterface, List<BookingLocationsInterface>> entry : bookings.entrySet()) {
	    List<BookingLocationsInterface> booklist = entry.getValue();
	    totalBookings += booklist.size();
	}
	return totalBookings;
    }

    public LocationInterface findBedstMatchRoom(CourseInterface c, List<LocationInterface> rooms) {
	LocationInterface selected_room = null;
	for (LocationInterface r : rooms) {
	    if (r.getNumberOfPlaces() >= c.getNumberOfParticipants() && selected_room == null) {
		selected_room = r;
	    }
	    if (r.getNumberOfPlaces() >= c.getNumberOfParticipants() && r.getNumberOfPlaces() < selected_room.getNumberOfPlaces()) {
		selected_room = r;
	    }
	}
	return selected_room; // no rooms matched
    }

    public static List<LocationInterface> findAllPossibleRoomsToCourse(CourseInterface c, List<LocationInterface> rooms) {
	List<LocationInterface> possible_rooms = new ArrayList<LocationInterface>();
	for (LocationInterface r : rooms) {
	    if (r.getNumberOfPlaces() >= c.getNumberOfParticipants()) {
		possible_rooms.add(r);
	    }

	}
	return possible_rooms; // no rooms matched
    }

    public static List<BookingLocationsInterface> AttemBookCourseTimes(TeacherInterface adam, CourseInterface c, LocationInterface r, String target_periode, int total_needed_bookings, Period BookingPeriod) {

	List<BookingLocationsInterface> return_data = new ArrayList<BookingLocationsInterface>();
	while (total_needed_bookings != 0) {

	    List<ClasificationInterface> possibleDays = ClasificationFactory.getPossibleDays(BookingPeriod, getBestTimeForDay(), getMediumTimeForDay(), getEmergencyTimeForDay());
	    int desiredDaysBetweenBooking = 0;
	    for (ClasificationInterface d : possibleDays) {
		if (desiredDaysBetweenBooking != 0) {
		    desiredDaysBetweenBooking--;
		    continue;
		}

		Period bedst_booking = null;
		if (target_periode.compareTo("emergency") == 0) {
		    bedst_booking = d.getEmergencyPeriod();
		}
		if (target_periode.compareTo("medium") == 0) {
		    bedst_booking = d.getMediumPeriod();
		}
		if (target_periode.compareTo("bedst") == 0) {
		    bedst_booking = d.getBestPeriod();
		}
		if (bedst_booking == null) {
		    continue;
		}
		int block_booking_size = c.getNumberOfHourstogether();

		int start_hour = bedst_booking.getStartDate().getHour();
		int end_hour = bedst_booking.getEndDate().getHour();
		long possible_booking_block_count = Math.round((end_hour - start_hour) / block_booking_size);
		for (int i = 0; i < possible_booking_block_count; i++) {
		    if (desiredDaysBetweenBooking != 0) {
			continue;
		    }
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		    String start_string = d.getDateOfTheDay().withHour(start_hour + (block_booking_size * (i))).format(formatter);
		    int new_end_time = start_hour + (block_booking_size * (i + 1));
		    String end_string = d.getDateOfTheDay().withHour(new_end_time).format(formatter);
		    BookingLocationsInterface b = BookingFactory.getBookingOfTheRoom(start_string, end_string, c);
		    boolean isTeacherAvailableToBooking = b.getTeacher().isTeacherAvailable(start_string, end_string);
		    if (!isSpecialCloseDay(b) && r.isAvailable(b) && isTeacherAvailableToBooking == true) {
			//break;
			return_data.add(b);
			c.getAssignedLecturer().addbookedPeriods(b.getPeriodOfBooking());
			total_needed_bookings--;
			desiredDaysBetweenBooking = c.getDeseiredDaysBetweenLectures();
			if (total_needed_bookings == 0) {

			    return return_data;
			}
			continue;
		    }
		    if (new_end_time >= bedst_booking.getEndDate().getHour()) {
			//we failed to get a booking for our course on this day.
			break;
		    }
		}

	    }
	    break;
	}
	//did we manage to book all the lessons ? 
	return return_data;
    }

    private static boolean AddNewBookingToDb(BookingLocationsInterface booking) {
	String teachersId = booking.getTeacher().getTeachersId();
	LocalDateTime startdate = booking.getPeriodOfBooking().getStartDate();
	LocalDateTime enddate = booking.getPeriodOfBooking().getEndDate();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	String formattedStartDate = startdate.format(formatter);
	String formattedFinishDate = enddate.format(formatter);
	String courseId = booking.getCourse().getId();
	List<StudentsInterface> students = booking.getCourse().getParticipants();
	List<String> classesIds = new ArrayList<String>();
	for (StudentsInterface s : students) {
	    String studentsId = s.getId();
	    classesIds.add(studentsId);
	}

	if (teachersId != null && startdate != null && enddate != null && courseId != null && classesIds != null) {
	    String newPeriodQuery = "INSERT INTO \"Periods\" (\"StartDate\",\"FinishDate\",\"periods_id\") VALUES ('" + formattedStartDate + "','" + formattedFinishDate + "'," + "DEFAULT)";
	    dbm.execute(newPeriodQuery);
	    int idOfBookingsPeriod = getPeriodsIdByStartAndFinishDate(formattedStartDate, formattedFinishDate);
	    String newBookingTeacherQuery = "INSERT INTO \"BookedPeriodsForTeachers\" (\"periods_bookings_teacher\",\"teacher_of_booking\",\"bookings_teachers_id\") VALUES ('" + idOfBookingsPeriod + "','" + teachersId + "'," + "DEFAULT)";
	    dbm.execute(newBookingTeacherQuery);
	    String newBookingQuery = "INSERT INTO \"Bookings\" (\"teacher_for_booking\",\"course_for_booking\",\"bookings_id\",\"period_for_booking\",\"studentsForBooking\") VALUES ('" + teachersId + "','" + courseId + "'," + "DEFAULT, '" + idOfBookingsPeriod + "',null)";
	    dbm.execute(newBookingQuery);

	    for (String string : classesIds) {
		String newStudentsQuery = "INSERT INTO \"BookedPeriodsForClassesOfStudents\" (\"bookingsPeriod\",\"classOfStudents\",\"id_booking_students\") VALUES ('" + idOfBookingsPeriod + "','" + string + "'," + "DEFAULT)";
		dbm.execute(newStudentsQuery);
		int bookingsId = getBookingsIDByCourseAndPeriod(idOfBookingsPeriod, courseId);
		String newStudentsToBooking = "INSERT INTO \"StudentsForBookings\" (\"id\",\"classOfStudents\",\"bookingForstudents\") VALUES (DEFAULT,'" + string + "','" + bookingsId + "')";
		dbm.execute(newStudentsToBooking);
	    }

	    return true;
	}
	return false;
    }

    private static int getBookingsIDByCourseAndPeriod(int periodOfBookingId, String courseOfBookingId) {
	int idToReturn = 0;
	String query = "SELECT \"bookings_id\" FROM \"Bookings\" WHERE \"period_for_booking\" = \'" + periodOfBookingId + "\' AND \"course_for_booking\" = \'" + courseOfBookingId + "\';";
	try {
	    ResultSet bookingsSearch = dbm.executeQuery(query);

	    while (bookingsSearch.next()) {
		idToReturn = bookingsSearch.getInt("bookings_id");
	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return idToReturn;
    }

    private static boolean isSpecialCloseDay(BookingLocationsInterface b) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	for (Period p : specialClosePeriod) {
	    String f = p.getStartDate().format(formatter);
	    if (f.equals(b.getPeriodOfBooking().getStartDate().format(formatter))) {
		return true;
	    }
	}
	return false;
    }

    ///Cancel a couse and attempt to find a new date.
    public int cancelBooking(BookingLocationsInterface booking, boolean attemptRebook, String SemesterEnd, Map<String, String[]> bestTimeForDay, Map<String, String[]> mediumTimeForDay, Map<String, String[]> emergencyTimeForDay) {
	for (LocationInterface r : this.Rooms) {
	    if (r.cancelBooking(booking)) {
		break;
	    }
	}
	if (attemptRebook) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    List<LocationInterface> allPossibleRooms = findAllPossibleRoomsToCourse(booking.getCourse(), this.Rooms);
	    Period booking_period = new Period(booking.getPeriodOfBooking().getStartDate().plusDays(1).format(formatter), SemesterEnd);
	    for (LocationInterface r : allPossibleRooms) {
		//we just need to book 1..
		//List<BookingLocationsInterface> bookings = AttemBookCourseTimes(booking.getTeacher(), booking.getCourse(), r, "bedst", 1, booking_period,bestTimeForDay,mediumTimeForDay,emergencyTimeForDay);
		List<BookingLocationsInterface> bookings = AttemBookCourseTimes(booking.getTeacher(), booking.getCourse(), r, "bedst", 1, booking_period);
		if (bookings.size() == 1) {
		    r.addNewBooking(bookings.get(0), booking.getCourse());
		    return 1;
		}
	    }
	}
	return 0;
    }

    public boolean swapCourseBookings(BookingLocationsInterface booking1, BookingLocationsInterface booking2) {
	//Check if a valid swap ? room size , lecture and more ?

	//do swap
	CourseInterface c1 = booking1.getCourse();
	CourseInterface c2 = booking2.getCourse();
	booking1.setCourse(c2);
	booking2.setCourse(c1);
	return true;
    }

    public BookingLocationsInterface scheldueRebookBooking(CourseInterface c, BookingLocationsInterface b, Period LockedPeriod) {
	//Find next available date for the booking.
	LocationInterface room = c.getRoomReferencedByBooking(b);
	return null;
    }

    static List<TeacherInterface> getAllTeachers() {
	List<TeacherInterface> allteachers = new ArrayList<TeacherInterface>();
	String query = "SELECT \"teachers_id\" FROM \"Teachers\";";
	String id = "";
	List<String> teachersIDs = new ArrayList<String>();
	try {
	    ResultSet teacherRs = dbm.executeQuery(query);

	    while (teacherRs.next()) {
		id = teacherRs.getString("teachers_id");
		teachersIDs.add(id);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	for (String teacherID : teachersIDs) {
	    TeacherInterface teacher = getTeacher(teacherID);
	    allteachers.add(teacher);
	}
	return allteachers;
    }

    static List<LocationInterface> getAllRooms() {
	List<LocationInterface> allrooms = new ArrayList<LocationInterface>();

	String query = "SELECT \"rooms_id\" FROM \"Rooms\";";
	try {
	    ResultSet roomsRs = dbm.executeQuery(query);

	    while (roomsRs.next()) {
		int roomid = roomsRs.getInt("rooms_id");
		LocationInterface roomToList = getRoomById(roomid);
		allrooms.add(roomToList);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return allrooms;
    }

    static List<CourseInterface> getAllCourses() {
	List<String> coursesIds = new ArrayList<String>();
	List<CourseInterface> courses = new ArrayList<CourseInterface>();
	String query = "SELECT \"courses_id\" FROM \"Courses\";";
	try {
	    ResultSet coursess = dbm.executeQuery(query);

	    while (coursess.next()) {
		String courseId = coursess.getString("courses_id");
		coursesIds.add(courseId);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	for (String someID : coursesIds) {
	    CourseInterface coursaeToList = getCourseById(someID);
	    courses.add(coursaeToList);
	}
	return courses;
    }

    static LocationInterface getRoomById(int rooms_id) {
	LocationInterface room;
	//String query = "SELECT * FROM \"Rooms\" NATURAL JOIN \"NeighborAreasForRoom\" WHERE \"rooms_id\" = \'" + rooms_id + "\' ;";
	String query = "SELECT * FROM \"Rooms\" JOIN \"NeighborAreasForRoom\" on \"Rooms\".rooms_id = \"NeighborAreasForRoom\".room_for_areas WHERE \"Rooms\".Rooms_id = " + rooms_id;
	List<String> closeNeighborAreas = new ArrayList<String>();
	List<BookingLocationsInterface> bookings = new ArrayList<BookingLocationsInterface>();

	int id = 0;
	String name = "";
	String area = "";
	int numberOfplaces = 0;
	try {
	    ResultSet roomsRs = dbm.executeQuery(query);

	    while (roomsRs.next()) {
		name = roomsRs.getString("rooms_name");
		id = roomsRs.getInt("rooms_id");
		area = roomsRs.getString("area");
		numberOfplaces = roomsRs.getInt("numberOfPlaces");
		String neighboer = roomsRs.getString("areas_for_room");
		closeNeighborAreas.add(neighboer);
	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	bookings = getBookingsForRoom(id);
	room = RoomFactory.getRoom(id, name, area, closeNeighborAreas, bookings, numberOfplaces);
	return room;
    }

    private static List<BookingLocationsInterface> getBookingsForRoom(int room_id) {
	List<BookingLocationsInterface> bookings = new ArrayList<BookingLocationsInterface>();
	int bookingID = 0;
	List<Integer> bookingsIds = new ArrayList<Integer>();
	String query = "SELECT * FROM \"RoomsAndBookings\" WHERE \"room_for_courses\" = \'" + room_id + "\' ;";

	try {
	    ResultSet coursesRs = dbm.executeQuery(query);

	    while (coursesRs.next()) {
		bookingID = coursesRs.getInt("bookingsToRooms");

		bookingsIds.add(bookingID);
	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}

	for (int i : bookingsIds) {
	    BookingLocationsInterface bookingToList = getBookingById(i);
	    if(bookingToList != null)
	    {
		bookings.add(bookingToList);
	    }
	}
	return bookings;
    }

    private static BookingLocationsInterface getBookingById(int bookings_id) {
	String query = "SELECT * FROM \"Bookings\" WHERE \"bookings_id\" = \'" + bookings_id + "\' ;";
	BookingLocationsInterface booking = null;
	String teachersid = "";
	String studentsId = "";
	String coursesId = "";
	int bookingId = 0;
	int periodsId = 0;

	try {
	    ResultSet teacherRs = dbm.executeQuery(query);

	    while (teacherRs.next()) {
		teachersid = teacherRs.getString("teacher_for_booking");
		coursesId = teacherRs.getString("course_for_booking");
		bookingId = teacherRs.getInt("bookings_id");
		periodsId = teacherRs.getInt("period_for_booking");
		Period periodforbooking = getPeriodById(periodsId);
		CourseInterface course = getCourseById(coursesId);
		TeacherInterface teacherforbooking = getTeacher(teachersid);
		List<StudentsInterface> studentsbooking = getClassForCourse(coursesId);
		DateTimeFormatter outformatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		booking = BookingFactory.getBookingOfTheRoom(periodforbooking.getStartDate().format(outformatter), periodforbooking.getEndDate().format(outformatter), course);

	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return booking;
    }

    private static Period getPeriodById(int periodsID) {
	Period period = null;
	String query = "SELECT * FROM \"Periods\" WHERE \"periods_id\" = \'" + periodsID + "\' ;";
	try {
	    ResultSet periods = dbm.executeQuery(query);

	    while (periods.next()) {
		String startOfPeriod = periods.getString("StartDate");
		String finishOfPeriod = periods.getString("FinishDate");
		DateTimeFormatter informatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter outformatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime replaceStringStart = LocalDateTime.parse(startOfPeriod,informatter);
		LocalDateTime replaceStringFinish = LocalDateTime.parse(finishOfPeriod,informatter);
	
		period = new Period(replaceStringStart.format(outformatter), replaceStringFinish.format(outformatter));

	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return period;
    }

    private static int getPeriodsIdByStartAndFinishDate(String startDate, String finishDate) {
	int id = 0;
	String query = "SELECT \"periods_id\" FROM \"Periods\" WHERE \"StartDate\" = \'" + startDate + "\' AND \"FinishDate\" = \'" + finishDate + "\' ;";
	try {
	    ResultSet periods = dbm.executeQuery(query);

	    while (periods.next()) {
		id = periods.getInt("periods_id");
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return id;
    }

    private static List<StudentsInterface> getStudentsForCourse(String coursesId) {
	List<StudentsInterface> students = new ArrayList<StudentsInterface>();
	String query = "SELECT * FROM \"StudentsForCourses\" NATURAL JOIN \"ClassesOfStudents\" WHERE \"course\" = \'" + coursesId + "\' ;";
	String fieldOfStudy = "";
	int numberOfStudents = 0;
	String classesId = "";
	try {
	    ResultSet studentsRs = dbm.executeQuery(query);

	    while (studentsRs.next()) {
		fieldOfStudy = studentsRs.getString("field_of_study");
		numberOfStudents = studentsRs.getInt("numberOfMembers");
		classesId = studentsRs.getString("classes_id");
		List<Period> bookedPeriodsForClass = getBookedPeriodsForClass(classesId);

		StudentsInterface student = StudentsFactory.getClassOfStudents(classesId, fieldOfStudy, numberOfStudents, bookedPeriodsForClass);
		students.add(student);

	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return students;
    }

    private static TeacherInterface getTeacher(String teacherID) {
	TeacherInterface teacher;
	String query = "SELECT * FROM \"Teachers\" WHERE \"teachers_id\" = \'" + teacherID + "\' ;";
	List<CourseInterface> possibleCcourses = new ArrayList<CourseInterface>();
	String id = "";
	String name = "";

	try {
	    ResultSet teacherRs = dbm.executeQuery(query);

	    while (teacherRs.next()) {
		name = teacherRs.getString("teachersName");
		id = teacherRs.getString("teachers_id");
	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	possibleCcourses = getCoursesForTeacher(teacherID);
	for (CourseInterface c : possibleCcourses) {
	    List<StudentsInterface> classOfStudents = getClassForCourse(c.getId());
	    c.getParticipants().addAll(classOfStudents);

	}
	List<Period> teachersBookedPeriods = getBookedPeriodsForTeacher(teacherID);

	teacher = LecturerFactory.getTeacher(id, name, teachersBookedPeriods, possibleCcourses);
	return teacher;
    }

    private static List<Period> getBookedPeriodsForTeacher(String teachersID) {
	List<Period> teachersBookedPeriods = new ArrayList<Period>();
	//Bad join!
	//String query5 = "SELECT \"StartDate\", \"FinishDate\" FROM \"BookedPeriodsForTeachers\" NATURAL JOIN \"Periods\" WHERE \"teacher_of_booking\" = \'" + teachersID + "\' ;";
	String query5 = "SELECT \"StartDate\",\"FinishDate\" FROM \"BookedPeriodsForTeachers\" LEFT JOIN \"Periods\" on \"BookedPeriodsForTeachers\".periods_bookings_teacher::int8 = \"Periods\".periods_id WHERE \"teacher_of_booking\" = '"+teachersID+"' ;";
	try {
	    ResultSet periodsteachers = dbm.executeQuery(query5);

	    while (periodsteachers.next()) {
		String tStart = periodsteachers.getString("StartDate");
		String tFinish = periodsteachers.getString("FinishDate");
		Period newPeriod = changeDataPickersToPeriod(tStart, tFinish);
		teachersBookedPeriods.add(newPeriod);

	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return teachersBookedPeriods;
    }

    private static int setNewBookedPeriodForTeacher(String teachersID, String startdate, String finishdate) {
	Period newPeriod = changeDataPickersToPeriod(startdate, finishdate);
	if (newPeriod == null || newPeriod.getStartDate() == null || newPeriod.getEndDate() == null) {
	    throw new IllegalArgumentException("Period is null or empty.");
	}
	String teachersId = "null";
	if (teachersID != null && !teachersID.isEmpty()) {
	    teachersId = "'" + teachersID + "'";
	}
	String periodQuery = "INSERT INTO \"Periods\" (\"StartDate\",\"FinishDate\",\"periods_id\") VALUES ( " + newPeriod.getStartDate() + ", " + newPeriod.getEndDate() + ", " + "DEFAULT" + "?)";
	int periodsid = dbm.executeInsertAndGetId(periodQuery);
	String periodTeacherQuery = "INSERT INTO \"BookedPeriodsForTeachers\" (\"bookings_teachers_id\", \"periods_bookings_teacher\", \"teacher_of_booking\") VALUES (DEFAULT, " + periodsid + ", /" + teachersID + "\' ;";
	return dbm.executeInsertAndGetId(periodTeacherQuery);
    }

    private static List<CourseInterface> getCoursesForTeacher(String teachersID) {
	List<CourseInterface> possibleCoursesss = new ArrayList<CourseInterface>();
	String cName = "";
	int cParticipants = 0;
	int cNumberLessons = 0;
	int cNumberHoursTogether = 0;
	int cMaximalWeek = 0;
	int cDesiredDays = 0;
	String cId = "";
	String query2 = "SELECT \"courseName\", \"courses_id\", \"numberOfParticipants\", \"numberOfLessons\", \"numberOfHoursTogether\",\"maximalTimesOfWeek\",\"assigned_teacher\",\"desiredDaysBetweenLectures\" FROM \"Courses\" NATURAL JOIN \"PossibleCoursesForTeacher\" WHERE \"teacher_to_possible\" = \'" + teachersID + "\' ;";
	try {
	    ResultSet coursess = dbm.executeQuery(query2);

	    while (coursess.next()) {
		cName = coursess.getString("courseName");
		cNumberLessons = coursess.getInt("numberOfLessons");
		cNumberHoursTogether = coursess.getInt("numberOfHoursTogether");
		cMaximalWeek = coursess.getInt("maximalTimesOfWeek");
		cId = coursess.getString("courses_id");

		TeacherInterface t = null;
		int desiredDaysBetweenLectures = coursess.getInt("desiredDaysBetweenLectures");
		CourseInterface course = CourseFactory.getCourse(cId, cName, new ArrayList<StudentsInterface>(), cNumberLessons, cNumberHoursTogether, cMaximalWeek, t, desiredDaysBetweenLectures);
		possibleCoursesss.add(course);
	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return possibleCoursesss;
    }

    private static List<StudentsInterface> getClassForCourse(String coursesID) {
	List<StudentsInterface> classesOfStudents = new ArrayList<StudentsInterface>();
	String query3 = "SELECT distinct(\"classes_id\"), \"field_of_study\", \"numberOfMembers\" FROM \"ClassesOfStudents\" NATURAL JOIN \"StudentsForCourses\" WHERE \"course\" = \'" + coursesID/*c.getId()*/ + "\' ;";

	try {
	    ResultSet studentss = dbm.executeQuery(query3);

	    while (studentss.next()) {
		String studentsid = studentss.getString("classes_id");
		String studentsfield = studentss.getString("field_of_study");
		int studentsNumber = studentss.getInt("numberOfMembers");
		List<Period> bookedPeriodsForClass = getBookedPeriodsForClass(studentsid);

		StudentsInterface classOfStudents = StudentsFactory.getClassOfStudents(studentsid, studentsfield, studentsNumber, bookedPeriodsForClass);
		classesOfStudents.add(classOfStudents);

	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return classesOfStudents;
    }

    private static List<Period> getBookedPeriodsForClass(String classesID) {
	List<Period> studentsbookedperiods = new ArrayList<Period>();
	//String query4 = "SELECT \"StartDate\", \"FinishDate\" FROM \"Periods\" NATURAL JOIN \"BookedPeriodsForClassesOfStudents\" WHERE \"classOfStudents\" = \'" + classesID + "\' ;";
	String query4 = "SELECT * FROM \"Periods\" INNER JOIN \"BookedPeriodsForClassesOfStudents\" ON \"BookedPeriodsForClassesOfStudents\".id_booking_students = \"Periods\".periods_id WHERE \"classOfStudents\" = '"+classesID+"' ;";
	try {
	    ResultSet periodssforss = dbm.executeQuery(query4);

	    while (periodssforss.next()) {
		String start = periodssforss.getString("StartDate");
		String finish = periodssforss.getString("FinishDate");
		Period period = changeDataPickersToPeriod(start,finish);
		studentsbookedperiods.add(period);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return studentsbookedperiods;
    }

    static Map<LocationInterface, List<BookingLocationsInterface>> getClassSchedule() {
	Map<LocationInterface, List<BookingLocationsInterface>> schedule = new TreeMap<LocationInterface, List<BookingLocationsInterface>>();
	String query = "SELECT * FROM \"RoomsAndBookings\";";
	try {
	    ResultSet scheduleee = dbm.executeQuery(query);

	    while (scheduleee.next()) {
		int roomToBookingID = scheduleee.getInt("room_for_courses");
		int bookingToRoomID = scheduleee.getInt("bookingsToRooms");
		BookingLocationsInterface bookingToRoom = getBookingById(bookingToRoomID);
		LocationInterface roomToBooking = getRoomById(roomToBookingID);
		List<BookingLocationsInterface> bookings = schedule.get(roomToBooking);
		if (bookings != null) {
		    bookings.add(bookingToRoom);
		    schedule.put(roomToBooking, bookings);
		} else {
		    List<BookingLocationsInterface> newList = new ArrayList<BookingLocationsInterface>();
		    newList.add(bookingToRoom);
		    schedule.put(roomToBooking, newList);
		}
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return schedule;  
    }

    static CourseInterface getCourseById(String courseId) {
	CourseInterface coursewithId = null;
	String query = "SELECT * FROM \"Courses\" WHERE \"courses_id\" = \'" + courseId + "\' ;";
	try {
	    ResultSet courses = dbm.executeQuery(query);

	    while (courses.next()) {
		String nameOfTheCourse = courses.getString("courseName");
		int numberLessons = courses.getInt("numberOfLessons");
		int numberhoursTogether = courses.getInt("numberOfHoursTogether");
		int maxLecturesInWeek = courses.getInt("maximalTimesOfWeek");
		int desiredDaysBetweenLectures = courses.getInt("desiredDaysBetweenLectures");
		String teachersID = courses.getString("assigned_teacher");
		TeacherInterface teacher = getTeacher(teachersID);
		List<StudentsInterface> studentsinCourse = getClassForCourse(courseId);
		CourseInterface course = CourseFactory.getCourse(courseId, nameOfTheCourse, studentsinCourse, numberLessons, numberhoursTogether, maxLecturesInWeek, teacher, desiredDaysBetweenLectures);
		coursewithId = course;
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return coursewithId;

    }

    static int setTeacherInDb(String login, String password, String name, String id, ArrayList<BookingLocationsInterface> arrayList, List<CourseInterface> teachersCourses) {

	if (login != null && password != null && id != null && teachersCourses != null) {
	    String newTeacerQuery = "INSERT INTO \"Teachers\" (\"teachers_id\",\"teachersName\") VALUES ( '" + id + "', '" + name + "')";
	    String newUserQuery = "INSERT INTO \"Users\" (\"name\",\"users_teacher\",\"isClassScheduler\",\"Login\",\"Password\") VALUES ( '" + name + "', '" + id + "', " + false + ", '" + login + "', '" + password + "')";
	    dbm.execute(newTeacerQuery);
	    dbm.execute(newUserQuery);

	    return 1;

	}
	return -1;
    }

    static Map<String, String[]> getBestTimeForDay() {
	Map<String, String[]> ret = new TreeMap<String, String[]>();
	String query = "select * from \"DaysBookingPeriodes\" where daysbookinperiodename = 'best';";
	try {
	    ResultSet optimalBookingTimes = dbm.executeQuery(query);

	    while (optimalBookingTimes.next()) {
		String day = optimalBookingTimes.getString("day");
		String starthour = optimalBookingTimes.getString("starthour");
		String endhour = optimalBookingTimes.getString("endhour");
		String[] strvalues = new String[2];
		strvalues[0] = starthour;
		strvalues[1] = endhour;

		ret.put(day, strvalues);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return ret;
    }

    static Map<String, String[]> getMediumTimeForDay() {
	Map<String, String[]> ret = new TreeMap<String, String[]>();
	String query = "select * from \"DaysBookingPeriodes\" where daysbookinperiodename = 'medium';";
	try {
	    ResultSet optimalBookingTimes = dbm.executeQuery(query);

	    while (optimalBookingTimes.next()) {
		String day = optimalBookingTimes.getString("day");
		String starthour = optimalBookingTimes.getString("starthour");
		String endhour = optimalBookingTimes.getString("endhour");
		String[] strvalues = new String[2];
		strvalues[0] = starthour;
		strvalues[1] = endhour;

		ret.put(day, strvalues);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return ret;
    }

    static Map<String, String[]> getEmergencyTimeForDay() {
	Map<String, String[]> ret = new TreeMap<String, String[]>();
	String query = "select * from \"DaysBookingPeriodes\" where daysbookinperiodename = 'emergency';";
	try {
	    ResultSet optimalBookingTimes = dbm.executeQuery(query);

	    while (optimalBookingTimes.next()) {
		String day = optimalBookingTimes.getString("day");
		String starthour = optimalBookingTimes.getString("starthour");
		String endhour = optimalBookingTimes.getString("endhour");
		String[] strvalues = new String[2];
		strvalues[0] = starthour;
		strvalues[1] = endhour;

		ret.put(day, strvalues);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return ret;
    }

    static boolean setPossibleTimesOfDayInDb(Map<String, String[]> besttimes, Map<String, String[]> mediumtimes, Map<String, String[]> emergencytimes) {
	String[] daysOfWeek = {"monday", "tuesday", "wednesday", "thursday", "saturday", "sunday"};
	if (emergencytimes != null && mediumtimes != null && besttimes != null) {
	    for (int i = 0; i <= besttimes.size(); i++) {
		String[] periods = besttimes.get(daysOfWeek[i]);
		String startperiod = periods[0];
		String endperiod = periods[1];
		String newDayQuery = "INSERT INTO \"DaysBookingPeriodes\" (\"DaysBookinPeriodId\",\"daysbookinperiodename\",\"starthour\",\"endhour\",\"day\") VALUES (DEFAULT, 'best','" + startperiod + "','" + endperiod + "', '" + daysOfWeek[i] + "')";
		dbm.execute(newDayQuery);
		return true;
	    }
	    for (int i = 0; i <= mediumtimes.size(); i++) {
		String[] periods = mediumtimes.get(daysOfWeek[i]);
		String startperiod = periods[0];
		String endperiod = periods[1];
		String newDayQuery = "INSERT INTO \"DaysBookingPeriodes\" (\"DaysBookinPeriodId\",\"daysbookinperiodename\",\"starthour\",\"endhour\",\"day\") VALUES (DEFAULT, 'medium','" + startperiod + "','" + endperiod + "', '" + daysOfWeek[i] + "')";
		dbm.execute(newDayQuery);
		return true;
	    }
	    for (int i = 0; i <= emergencytimes.size(); i++) {
		String[] periods = emergencytimes.get(daysOfWeek[i]);
		String startperiod = periods[0];
		String endperiod = periods[1];
		String newDayQuery = "INSERT INTO \"DaysBookingPeriodes\" (\"DaysBookinPeriodId\",\"daysbookinperiodename\",\"starthour\",\"endhour\",\"day\") VALUES (DEFAULT, 'medium','" + startperiod + "','" + endperiod + "', '" + daysOfWeek[i] + "')";
		dbm.execute(newDayQuery);

	    }
	    return true;
	}
	return false;
    }
}
