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
import Business_Logic.scheldue_result.scheldue_result;
import static Server.ServerUserHandling.changeDataPickersToPeriod;
import Server.database.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    //String SemesterStart = "01/02/2019 08:00:00";
    //String SemesterEnd = "31/05/2019 22:00:00";
    //TeacherInterface adam = new Lecturer("00001", "Adam Adamsen", new ArrayList<Period>(), new ArrayList<CourseInterface>());

    public void addSpecialClosePeriod(String start, String end) {

    }

    public void addNewRoom(LocationInterface r) {
	this.Rooms.add(r);
    }

    public void addNewCourse(CourseInterface c) {
	this.Courses.add(c);
    }

    static scheldue_result AssignRoomsForCourses(String SemesterStart, String SemesterEnd, List<LocationInterface> Rooms, List<CourseInterface> Courses) {
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

		//bookings.addAll();
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
	scheldue_result ret_obj = new scheldue_result();
	ret_obj.BookingFails = BookingFails;
	ret_obj.bookedCourses = bookedCourses;
	
	//print_table(); //debug
	return ret_obj;
    }

    private static CourseInterface finalizeBookings(CourseInterface c, Map<LocationInterface, List<BookingLocationsInterface>> bookings) {
	List<LocationInterface> bookedRooms = new ArrayList<LocationInterface>();
	for (Map.Entry<LocationInterface, List<BookingLocationsInterface>> entry : bookings.entrySet()) {
	    LocationInterface r = entry.getKey();
	    List<BookingLocationsInterface> booklist = entry.getValue();
	    for (BookingLocationsInterface b : booklist) {
		r.addNewBooking(b, c);
	    }
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

    public static List<BookingLocationsInterface> AttemBookCourseTimes(TeacherInterface adam, CourseInterface c, LocationInterface r, String target_periode, int Total_needed_bookings, Period BookingPeriod) {

	List<BookingLocationsInterface> return_data = new ArrayList<BookingLocationsInterface>();
	while (Total_needed_bookings != 0) {

	    List<ClasificationInterface> possibleDays = ClasificationFactory.getPossibleDays(BookingPeriod); //this should come from course ? 
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
		    BookingLocationsInterface b = BookingFactory.getBookingOfTheRoom(start_string, end_string, c, c.getAssignedLecturer(), c.getParticipants());
		    if (!isSpecialCloseDay(b) && r.isAvailable(b)) {
			//break;
			return_data.add(b);
			Total_needed_bookings--;
			desiredDaysBetweenBooking = c.getDeseiredDaysBetweenLectures();
			if (Total_needed_bookings == 0) {
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

    public void print_table() {
	System.out.print("##");
	Map<String, List<BookingLocationsInterface>> hour_sortet_bookings = new TreeMap<String, List<BookingLocationsInterface>>();
	List<List<BookingLocationsInterface>> room_bookings = new ArrayList<List<BookingLocationsInterface>>();
	for (LocationInterface r : this.Rooms) {
	    System.out.print(r.getNameOfTheLocation() + " | ");
	    List<BookingLocationsInterface> b = r.getAllBookings();
	    for (BookingLocationsInterface br : b) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String key = br.getPeriodOfBooking().getStartDate().format(formatter);
		List<BookingLocationsInterface> known = hour_sortet_bookings.get(key);
		if (known != null) {
		    known.add(br);
		    hour_sortet_bookings.put(key, known);
		} else {
		    known = new ArrayList<BookingLocationsInterface>();
		    known.add(br);
		    hour_sortet_bookings.put(key, known);
		}
	    }

	    //room_bookings.add(b);
	}
	System.out.println();

	for (Map.Entry<String, List<BookingLocationsInterface>> entry : hour_sortet_bookings.entrySet()) {
	    List<BookingLocationsInterface> booklist = entry.getValue();
	    System.out.println(entry.getKey() + "  ");
	    for (BookingLocationsInterface br : booklist) {
		System.out.println(br.getPeriodOfBooking().getStartDate().getHour() + ": " + br.getCourse().getNameOfTheCourse());
	    }
	    System.out.println();
	}
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
    public int cancelBooking(BookingLocationsInterface booking, boolean attemptRebook, String SemesterEnd) {
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
	//Forslag: Booking1.setCourse(c2)
	booking1.setCourse(c2);
	booking2.setCourse(c1);
	return true;
    }
    public BookingLocationsInterface scheldueRebookBooking(CourseInterface c,BookingLocationsInterface b,Period LockedPeriod)
    {
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
	String query = "SELECT * FROM \"Rooms\" NATURAL JOIN \"NeighborAreasForRoom\" WHERE \"rooms_id\" = \'" + rooms_id + "\' ;";
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
	String query = "SELECT * FROM \"BookedCoursesForRoom\" NATURAL JOIN \"BookedRoomsForCourse\" WHERE \"fk_room\" = \'" + room_id + "\' ;";

	try {
	    ResultSet coursesRs = dbm.executeQuery(query);

	    while (coursesRs.next()) {
		bookingID = coursesRs.getInt("bookings_for_room");

		bookingsIds.add(bookingID);
	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}

	for (int i : bookingsIds) {
	    BookingLocationsInterface bookingToList = getBookingById(i);
	    bookings.add(bookingToList);
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
		teachersid = teacherRs.getString("teachersName");
		coursesId = teacherRs.getString("course_for_booking");
		bookingId = teacherRs.getInt("bookings_id");
		periodsId = teacherRs.getInt("period_for_booking");
		Period periodforbooking = getPeriodById(periodsId);
		CourseInterface course = getCourseById(coursesId);
		TeacherInterface teacherforbooking = getTeacher(teachersid);
		List<StudentsInterface> studentsbooking = getClassForCourse(coursesId);
		booking = BookingFactory.getBookingOfTheRoom(periodforbooking.getStartDate().toString(), periodforbooking.getEndDate().toString(), course, teacherforbooking, studentsbooking);

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
		Date startOfPeriod = periods.getDate("StartDate");
		Date finishOfPeriod = periods.getDate("FinishDate");
		period = new Period(startOfPeriod.toString(), finishOfPeriod.toString());

	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return period;
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
	String query5 = "SELECT \"StartDate\", \"FinishDate\" FROM \"BookedPeriodsForTeachers\" NATURAL JOIN \"Periods\" WHERE \"teacher_of_booking\" = \'" + teachersID + "\' ;";
	try {
	    ResultSet periodsteachers = dbm.executeQuery(query5);

	    while (periodsteachers.next()) {
		String tStart = periodsteachers.getString("StartDate");
		String tFinish = periodsteachers.getString("FinishDate");
		Period newPeriod = new Period(tStart, tFinish);
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
		
		TeacherInterface t= null;
		int desiredDaysBetweenLectures = coursess.getInt("desiredDaysBetweenLectures");
		CourseInterface course = CourseFactory.getCourse(cId, cName, new ArrayList<StudentsInterface>(), cNumberLessons, cNumberHoursTogether, cMaximalWeek,t,desiredDaysBetweenLectures);
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
	String query4 = "SELECT \"StartDate\", \"FinishDate\" FROM \"Periods\" NATURAL JOIN \"BookedPeriodsForClassesOfStudents\" WHERE \"classOfStudents\" = \'" + classesID + "\' ;";
	try {
	    ResultSet periodssforss = dbm.executeQuery(query4);

	    while (periodssforss.next()) {
		String start = periodssforss.getString("StartDate");
		String finish = periodssforss.getString("FinishDate");
		Period period = new Period(start, finish);
		studentsbookedperiods.add(period);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return studentsbookedperiods;
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
		CourseInterface course = CourseFactory.getCourse(courseId, nameOfTheCourse, studentsinCourse, numberLessons, numberhoursTogether, maxLecturesInWeek,teacher,desiredDaysBetweenLectures);
		coursewithId = course;
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return coursewithId;

    }

    static int setTeacherInDb(String login, String password, String name, String id, ArrayList<BookingLocationsInterface> arrayList, List<CourseInterface> teachersCourses) {

	if (login != null && password != null && id != null && teachersCourses != null) {
	    String newTeacerQuery = "INSERT INTO \"Teachers\" NATURAL JOIN \"Users\" (\"teachers_id\",\"teachersName\",\"name\",\"users_teacher\",\"isClassScheduler\",\"Login\",\"Password\") VALUES ( " + id + ", " + name + ", " + name + ", " + id + ", " + false + ", " + login + ", " + password + "?)";
	    return dbm.executeInsertAndGetId(newTeacerQuery);
	}
	return -1;
    }

    /*
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
	}*/
}
