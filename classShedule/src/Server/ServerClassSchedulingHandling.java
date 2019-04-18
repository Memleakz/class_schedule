/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Business_Logic.Bookings.BookingFactory;
import Business_Logic.Common.Period;
import Business_Logic.Days.ClasificationFactory;
import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.ClasificationInterface;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.LocationInterface;
import Business_Logic.IServices.TeacherInterface;
import Server.database.DatabaseManager;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author lawar15
 */
public class ServerClassSchedulingHandling {
DatabaseManager dbm = DatabaseManager.getInstance();
    private List<CourseInterface> Courses;
    List<LocationInterface> Rooms;
    List<Period> specialClosePeriod;
    //String SemesterStart = "01/02/2019 08:00:00";
    //String SemesterEnd = "31/05/2019 22:00:00";
    //TeacherInterface adam = new Lecturer("00001", "Adam Adamsen", new ArrayList<Period>(), new ArrayList<CourseInterface>());
public void addSpecialClosePeriod(String start,String end)
    {
	
    }
    public void addNewRoom(LocationInterface r)
    {
	this.Rooms.add(r);
    }
    public void addNewCourse(CourseInterface c)
    {
	this.Courses.add(c);
    }
    public List<CourseInterface> AssignRoomsForCourses(String SemesterStart,String SemesterEnd) {
	List<CourseInterface> bookedCourses = new ArrayList<CourseInterface>();
	List<CourseInterface> BookingFails = new ArrayList<CourseInterface>();
	Period BookingPeriod = new Period(SemesterStart,SemesterEnd);
	for (CourseInterface c : this.Courses) {
	    //Find best match room ?
	    List<LocationInterface> allPossibleRooms = findAllPossibleRoomsToCourse(c, this.Rooms);
	    int Total_needed_bookings = c.getNumberOfLessons() / c.getNumberOfHourstogether();

	    List<BookingLocationsInterface> bookings = new ArrayList<BookingLocationsInterface>();
	    List<BookingLocationsInterface> known_bookings = null;
	    Map<LocationInterface, List<BookingLocationsInterface>> l = new HashMap<LocationInterface, List<BookingLocationsInterface>>();
	    boolean BookingDone = false;
	    
	    for (LocationInterface r : allPossibleRooms) {
		known_bookings = l.get(r);
		List<BookingLocationsInterface> abooks = AttemBookCourseTimes(null,c, r, "bedst",Total_needed_bookings,BookingPeriod);
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
		    List<BookingLocationsInterface> abooks = AttemBookCourseTimes(null,c, r, "medium",Total_needed_bookings,BookingPeriod);
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
		    List<BookingLocationsInterface> abooks = AttemBookCourseTimes(null,c, r, "emergency",Total_needed_bookings,BookingPeriod);
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
		CourseInterface finalizedCourse = finalizeBookings(c,l);
		bookedCourses.add(finalizedCourse);		
	    }
	    //place booking
	}
	return bookedCourses;
    }
    private CourseInterface finalizeBookings(CourseInterface c,Map<LocationInterface, List<BookingLocationsInterface>> bookings)
    {
	List<LocationInterface> bookedRooms = new ArrayList<LocationInterface>();
	for (Map.Entry<LocationInterface, List<BookingLocationsInterface>> entry : bookings.entrySet())
	{
	    LocationInterface r = entry.getKey();
	    List<BookingLocationsInterface> booklist = entry.getValue();
	    for(BookingLocationsInterface b : booklist)
	    {
		r.addNewBooking(b,c);
	    }
	    bookedRooms.add(r);
	    
	}
	c.setBookedRooms(bookedRooms);
	return c;
    }
    public int helperCountBookings(Map<LocationInterface, List<BookingLocationsInterface>> bookings)
    {
	int totalBookings = 0;
	for (Map.Entry<LocationInterface, List<BookingLocationsInterface>> entry : bookings.entrySet())
	{
	    List<BookingLocationsInterface> booklist = entry.getValue();
	    totalBookings+= booklist.size();
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

    public List<LocationInterface> findAllPossibleRoomsToCourse(CourseInterface c, List<LocationInterface> rooms) {
	List<LocationInterface> possible_rooms = new ArrayList<LocationInterface>();
	for (LocationInterface r : rooms) {
	    if (r.getNumberOfPlaces() >= c.getNumberOfParticipants()) {
		possible_rooms.add(r);
	    }

	}
	return possible_rooms; // no rooms matched
    }

    public  List<BookingLocationsInterface> AttemBookCourseTimes(TeacherInterface adam,CourseInterface c, LocationInterface r, String target_periode,int Total_needed_bookings,Period BookingPeriod) {
	
	List<BookingLocationsInterface> return_data = new ArrayList<BookingLocationsInterface>();
	while (Total_needed_bookings != 0) {
	    
	    List<ClasificationInterface> possibleDays = ClasificationFactory.getPossibleDays(BookingPeriod); //this should come from course ? 
	    int desiredDaysBetweenBooking = 0;
	    for (ClasificationInterface d : possibleDays) {
		if(desiredDaysBetweenBooking != 0)
		{
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
		    if(desiredDaysBetweenBooking != 0)
		    {
			continue;
		    }
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		    String start_string = d.getDateOfTheDay().withHour(start_hour + (block_booking_size * (i))).format(formatter);
		    int new_end_time = start_hour + (block_booking_size * (i + 1));
		    String end_string = d.getDateOfTheDay().withHour(new_end_time).format(formatter);
		    BookingLocationsInterface b = BookingFactory.getBookingOfTheRoom(start_string, end_string,c, adam, c.getParticipants());
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
    public void print_table()
    {
	System.out.print("##");
	Map<String,List<BookingLocationsInterface>> hour_sortet_bookings = new TreeMap<String,List<BookingLocationsInterface>>();
	List<List<BookingLocationsInterface>> room_bookings = new ArrayList<List<BookingLocationsInterface>>();
	for(LocationInterface r : this.Rooms)
	{
	    System.out.print(r.getNameOfTheLocation() + " | ");
	    List<BookingLocationsInterface> b = r.getAllBookings();
	    for(BookingLocationsInterface br : b)
	    {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String key = br.getPeriodOfBooking().getStartDate().format(formatter);
		List<BookingLocationsInterface> known = hour_sortet_bookings.get(key);
		if(known != null)
		{
		    known.add(br);
		    hour_sortet_bookings.put(key, known);
		}
		else
		{
		    known = new ArrayList<BookingLocationsInterface>();
		    known.add(br);
		    hour_sortet_bookings.put(key, known);
		}
	    }
	    
	    //room_bookings.add(b);
	    
	}
	System.out.println();
		
	for (Map.Entry<String,List<BookingLocationsInterface>> entry : hour_sortet_bookings.entrySet())
	{
	    List<BookingLocationsInterface> booklist = entry.getValue();
	    System.out.println(entry.getKey() + "  ");
	    for(BookingLocationsInterface br : booklist)
	    {
		System.out.println(br.getPeriodOfBooking().getStartDate().getHour() + ": "+br.getCourse().getNameOfTheCourse());
	    }
	    System.out.println();
	}
    }
    private boolean isSpecialCloseDay(BookingLocationsInterface b)
    {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	for(Period p : this.specialClosePeriod)
	{
	    String f = p.getStartDate().format(formatter);
	    if(f.equals(b.getPeriodOfBooking().getStartDate().format(formatter)))
	    {
		return true;
	    }
	}
	return false;
    }
    ///Cancel a couse and attempt to find a new date.
    public int cancelBooking(BookingLocationsInterface booking,boolean attemptRebook, String SemesterEnd)
    {
	for(LocationInterface r : this.Rooms)
	{
	    if(r.cancelBooking(booking))
	    {
		break;
	    }
	}
	if(attemptRebook)
	{
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    List<LocationInterface> allPossibleRooms = findAllPossibleRoomsToCourse(booking.getCourse(), this.Rooms);
	    Period booking_period = new Period(booking.getPeriodOfBooking().getStartDate().plusDays(1).format(formatter),SemesterEnd);
	    for(LocationInterface r : allPossibleRooms)
	    {
		//we just need to book 1..
		List<BookingLocationsInterface> bookings = AttemBookCourseTimes(booking.getTeacher(),booking.getCourse(),r,"bedst",1,booking_period);
		if(bookings.size() == 1)
		{
		    r.addNewBooking(bookings.get(0), booking.getCourse());
		    return 1;
		}
	    }
	}
	return 0;
    }
    public boolean swapCourseBookings(BookingLocationsInterface booking1,BookingLocationsInterface booking2)
    {
	//Check if a valid swap ? room size , lecture and more ?
	
	//do swap
	CourseInterface c1= booking1.getCourse();
	CourseInterface c2 = booking2.getCourse();
	//Forslag: Booking1.setCourse(c2)
	booking1.setCourse(c2);
	booking2.setCourse(c1);
	return true;
    }
    
}
