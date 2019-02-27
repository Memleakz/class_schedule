/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Services.factories.BookingFactory;
import Services.factories.DayFactory;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author lawar15
 */
public class ClassShedule {

    public static void main(String[] args) {
	Area tek = new Area("1", "TEK");
	Area b44 = new Area ("2", "B44");
	ArrayList<Area> Areas  = new ArrayList<Area>();
	Room roomie = new Room("01", "U1", tek, Areas, new ArrayList <BookingOfTheRoom>(), 200);
    	
	ClassOfTheStudents software = new ClassOfTheStudents("001", "SE", 80, new ArrayList<Period> ());
	ClassOfTheStudents it = new ClassOfTheStudents("002", "IT", 15, new ArrayList<Period> ());
	
	List<ClassOfTheStudents> studentsForMaintance =new ArrayList<ClassOfTheStudents>();
	
	studentsForMaintance.add(it);
	studentsForMaintance.add(software);
	
	Lecturer adam = new Lecturer("00001", "Adam Adamsen", new ArrayList<Period>());
	List<Lecturer> lecturersForMaintance =new ArrayList<Lecturer>();
	lecturersForMaintance.add(adam);
	
	Course sM = new Course("0001", "Software Maintance",studentsForMaintance, lecturersForMaintance, 48, 4, 1);
	Course sM1 = new Course("0001", "Software Maintance2",studentsForMaintance, lecturersForMaintance, 48,4, 1);
	
	Period f19 = new Period("01/02/2019 08:00:00","31/05/2019 22:00:00");
	// lists of objects
	List<ClassOfTheStudents> allstudents = new ArrayList<ClassOfTheStudents>();
	allstudents.add(it);
	allstudents.add(software);
	List<Course> allcourses = new ArrayList<Course>();
	allcourses.add(sM);
	allcourses.add(sM1);
	List<Room> allrooms = new ArrayList<Room>();
	allrooms.add(roomie);
        List<Day> possibleDays= DayFactory.getPossibleDays(f19);
	AssignRoomsForCourses(allcourses,allrooms);
	for(Course template_course : allcourses ){
	   int requiredSizeOfTheRoom= template_course.getNumberOfParticipants();
	   for (Room template_room : allrooms){
	       if(template_room.getNumberOfPlaces()>=requiredSizeOfTheRoom){
		   for(Day day: possibleDays){
		   }
		   BookingOfTheRoom b = BookingFactory.getBookingOfTheRoom("01/02/2019 08:00:00", "31/05/2019 22:00:00", adam, allstudents);
		   if(template_room.isAvailable(b))
		   {
		    template_room.addNewBooking(b);
		   }
		   else
		   {
		       System.out.println("Booking already taken");
		   }
		   break;
		 //Lecturer lecturer, List<ClassOfTheStudents> students, Room bookedRoom, Period periodOfBooking
	       }
	   }
	}
    }
    public static void AssignRoomsForCourses(List<Course> courses,List<Room> rooms)
    {
	for(Course c : courses)
	{
	    //Find best match room ?
	    Room matched_room = findBedstMatchRoom(c,rooms);
	    //find available time slot ?
	    int Total_needed_bookings = c.numberOflessons/c.numberOfHourstogether;
	    List<BookingOfTheRoom> bookings = AttemBookCourseTimes(c,matched_room,"bedst");
	    if(bookings.size() != Total_needed_bookings)
	    {
		c.numberOflessons = c.numberOflessons-(bookings.size()*c.numberOfHourstogether);
		List<BookingOfTheRoom> more_bookings = AttemBookCourseTimes(c,matched_room,"medium");
		bookings.addAll(more_bookings);
		if(bookings.size() != Total_needed_bookings)
		{
		    c.numberOflessons = c.numberOflessons-(bookings.size()*c.numberOfHourstogether);
		    more_bookings = AttemBookCourseTimes(c,matched_room,"emergency");
		    bookings.addAll(more_bookings);
		}
	    }
	    if(bookings.size() != Total_needed_bookings)
	    {
		//We failed to book all lessons for the this ? what todo ?!
		System.out.println("Failed to book lessons for course: " + c.nameOfTheCourse);
	    }
	    else
	    {
		System.out.println("Booked all lessons for course: " + c.nameOfTheCourse);
		for(BookingOfTheRoom b : bookings)
		{
		    matched_room.addNewBooking(b);
		}
	    }
	    //place booking
	}
    }
    public static Room findBedstMatchRoom(Course c,List<Room> rooms)
    {
	Room selected_room = null;
	for(Room r : rooms)
	{
	    if(r.getNumberOfPlaces() >= c.getNumberOfParticipants() && selected_room == null)
	    {
		selected_room = r;
	    }
	    if(r.getNumberOfPlaces() >= c.getNumberOfParticipants() && r.getNumberOfPlaces() < selected_room.getNumberOfPlaces())
	    {
		selected_room = r;
	    }
	}
	return selected_room; // no rooms matched
    }
    public static List<BookingOfTheRoom> AttemBookCourseTimes(Course c,Room r,String target_periode)
    {
	int Total_needed_bookings = c.numberOflessons/c.numberOfHourstogether;
	List<BookingOfTheRoom> return_data = new ArrayList<BookingOfTheRoom>();
	while(Total_needed_bookings != 0)
	{
	    	Period f19 = new Period("01/02/2019 08:00:00","31/05/2019 22:00:00");
		List<Day> possibleDays= DayFactory.getPossibleDays(f19); //this should come from course ? 
		for(Day d : possibleDays)
		{
		    Lecturer adam = new Lecturer("00001", "Adam Adamsen", new ArrayList<Period>());
		    Period bedst_booking = null;
		    if(target_periode.compareTo("emergency") == 0)
		    {
			bedst_booking = d.getEmergencyPeriod();
		    }
		    if(target_periode.compareTo("medium") == 0)
		    {
			bedst_booking = d.getMediumPeriod();
		    }
		    if(target_periode.compareTo("bedst") == 0)
		    {
			bedst_booking = d.getBestPeriod();
		    }
		    if(bedst_booking == null)
		    {
			continue;
		    }
		    int block_booking_size = c.numberOfHourstogether;

		    int start_hour = bedst_booking.getStartDate().getHour();
		    int end_hour = bedst_booking.getEndDate().getHour();
		    long possible_booking_block_count = Math.round((end_hour-start_hour)/block_booking_size);
		    for(int i = 0;i < possible_booking_block_count;i++)
		    {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			String start_string = d.getDateOfTheDay().withHour(start_hour+(block_booking_size*(i))).format(formatter);
			int new_end_time =start_hour+(block_booking_size*(i+1));
			String end_string = d.getDateOfTheDay().withHour(new_end_time).format(formatter);
			BookingOfTheRoom b = BookingFactory.getBookingOfTheRoom(start_string, end_string, adam, c.students);
			if(r.isAvailable(b))
			{
			    //break;
			    return_data.add(b);
			    Total_needed_bookings--;
			    if(Total_needed_bookings == 0)
			    {
				return return_data;
			    }
			    continue;
			}
			if(new_end_time >= bedst_booking.finishTime.getHour())
			{
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
}


