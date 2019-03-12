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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author lawar15
 */
public class ClassShedule {

    public static void main(String[] args) {
	Area tek = new Area("1", "TEK");
	Area b44 = new Area("2", "B44");
	ArrayList<Area> Areas = new ArrayList<Area>();
	Room roomie = new Room("01", "U1", tek, Areas, new ArrayList<BookingOfTheRoom>(), 200);

	ClassOfTheStudents software = new ClassOfTheStudents("001", "SE", 80, new ArrayList<Period>());
	ClassOfTheStudents it = new ClassOfTheStudents("002", "IT", 15, new ArrayList<Period>());

	List<ClassOfTheStudents> studentsForMaintance = new ArrayList<ClassOfTheStudents>();

	studentsForMaintance.add(it);
	studentsForMaintance.add(software);

	Lecturer adam = new Lecturer("00001", "Adam Adamsen", new ArrayList<Period>());
	List<Lecturer> lecturersForMaintance = new ArrayList<Lecturer>();
	lecturersForMaintance.add(adam);

	Course sM = new Course("0001", "Software Maintance", studentsForMaintance, lecturersForMaintance, 48, 4, 1);
	Course sM1 = new Course("0001", "Software Maintance2", studentsForMaintance, lecturersForMaintance, 48, 4, 1);
	Course sM2 = new Course("0001", "Software Maintance3", studentsForMaintance, lecturersForMaintance, 48, 4, 1);
	Course sM3 = new Course("0001", "Software Maintance4", studentsForMaintance, lecturersForMaintance, 48, 4, 1);
	
	Period f19 = new Period("01/02/2019 08:00:00", "31/05/2019 22:00:00");
	// lists of objects
	List<ClassOfTheStudents> allstudents = new ArrayList<ClassOfTheStudents>();
	allstudents.add(it);
	allstudents.add(software);
	List<Course> allcourses = new ArrayList<Course>();
	allcourses.add(sM);
	allcourses.add(sM1);
	allcourses.add(sM2);
	allcourses.add(sM3);
	List<Room> allrooms = new ArrayList<Room>();
	allrooms.add(roomie);
	
	BookingSys sys = new BookingSys();
	for(Room r : allrooms)
	{
	    sys.addNewRoom(r);
	}
	for(Course c : allcourses)
	{
	    c.setDesiredDaysBetweenLectures(2);
	    sys.addNewCourse(c);
	}
	List<Course> booked_courses = sys.AssignRoomsForCourses();
	sys.print_table();
	Course c = booked_courses.get(0);
	Course c1 = booked_courses.get(1);
	BookingOfTheRoom cancel_me = c.getBookedRooms().get(0).bookings.get(c).get(0);
	sys.cancelBooking(cancel_me, true);
	sys.print_table();
	
	BookingOfTheRoom swap1 = c.getBookedRooms().get(0).bookings.get(c).get(0);
	BookingOfTheRoom swap2 = c1.getBookedRooms().get(0).bookings.get(c1).get(0);
	sys.swapCourseBookings(swap1, swap2);
	sys.print_table();
    }
}
