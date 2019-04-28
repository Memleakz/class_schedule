/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Business_Logic.Common.Period;
import Business_Logic.Courses.Course;
import Business_Logic.Locations.Room;
import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.LocationInterface;
import Business_Logic.Responsible.Lecturer;
import Business_Logic.Participants.ClassOfTheStudents;
import Business_Logic.IServices.StudentsInterface;
import Business_Logic.IServices.TeacherInterface;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lawar15
 */
public class ClassShedule {

   /* public static void main(String[] args) {
	String tek = new String("TEK");
	String b44 = new String("B44");
	ArrayList<String> Areas = new ArrayList<String>();
	Room roomie = new Room(1, "U1", tek, Areas, new ArrayList<BookingLocationsInterface>(), 200);

	StudentsInterface software = new ClassOfTheStudents("001", "SE", 80, new ArrayList<Period>());
	StudentsInterface it = new ClassOfTheStudents("002", "IT", 15, new ArrayList<Period>());

	List<StudentsInterface> studentsForMaintance = new ArrayList<StudentsInterface>();

	studentsForMaintance.add(it);
	studentsForMaintance.add(software);

	TeacherInterface adam = new Lecturer("00001", "Adam Adamsen", new ArrayList<Period>(), new ArrayList<CourseInterface>());
	List<TeacherInterface> lecturersForMaintance = new ArrayList<TeacherInterface>();
	lecturersForMaintance.add(adam);

	Course sM = new Course("0001", "Software Maintance", studentsForMaintance, 48, 4, 1);
	Course sM1 = new Course("0001", "Software Maintance2", studentsForMaintance, 48, 4, 1);
	Course sM2 = new Course("0001", "Software Maintance3", studentsForMaintance, 48, 4, 1);
	Course sM3 = new Course("0001", "Software Maintance4", studentsForMaintance, 48, 4, 1);
	
	Period f19 = new Period("01/02/2019 08:00:00", "31/05/2019 22:00:00");
	// lists of objects
	List<StudentsInterface> allstudents = new ArrayList<StudentsInterface>();
	allstudents.add(it);
	allstudents.add(software);
	List<CourseInterface> allcourses = new ArrayList<CourseInterface>();
	allcourses.add(sM);
	allcourses.add(sM1);
	allcourses.add(sM2);
	allcourses.add(sM3);
	List<LocationInterface> allrooms = new ArrayList<LocationInterface>();
	allrooms.add(roomie);
	
	BookingSys sys = new BookingSys();
	for(LocationInterface r : allrooms)
	{
	    sys.addNewRoom(r);
	}
	for(CourseInterface c : allcourses)
	{
	    c.setDesiredDaysBetweenLectures(2);
	    sys.addNewCourse(c);
	}
	List<CourseInterface> booked_courses = sys.AssignRoomsForCourses();
	sys.print_table();
	CourseInterface c = booked_courses.get(0);
	CourseInterface c1 = booked_courses.get(1);
	BookingLocationsInterface cancel_me = c.getBookedRooms().get(0).getBookings().get(c).get(0);
	sys.cancelBooking(cancel_me, true);
	sys.print_table();
	
	BookingLocationsInterface swap1 = c.getBookedRooms().get(0).getBookings().get(c).get(0);
	BookingLocationsInterface swap2 = c1.getBookedRooms().get(0).getBookings().get(c1).get(0);
	sys.swapCourseBookings(swap1, swap2);
	sys.print_table();
    }*/
}
