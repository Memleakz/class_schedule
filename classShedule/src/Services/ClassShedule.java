/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Services.factories.BookingFactory;
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
	
	Course sM = new Course("0001", "Software Maintance",studentsForMaintance, lecturersForMaintance);
	Course sM1 = new Course("0001", "Software Maintance2",studentsForMaintance, lecturersForMaintance);
	
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

	
	for(Course template_course : allcourses ){
	   int requiredSizeOfTheRoom= template_course.getNumberOfParticipants();
	   for (Room template_room : allrooms){
	       if(template_room.getNumberOfPlaces()>=requiredSizeOfTheRoom){
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
}


