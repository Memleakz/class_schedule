/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.LocationInterface;
import Business_Logic.IServices.ServerInterface;
import Business_Logic.IServices.TeacherInterface;
import Business_Logic.scheldue_result.Scheldue_result;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lawar15
 */
public class ClientClassSchedulingHandling {

    static List<TeacherInterface> getAllTeachers(ServerInterface serverController) {
	try {
	    return (List<TeacherInterface>) serverController.getAllTeachers();
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }

    static List<CourseInterface> getAllCourses(ServerInterface serverController) {
	try {
	    return (List<CourseInterface>) serverController.getAllCourses();
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }

    static List<LocationInterface> getAllRooms(ServerInterface serverController) {
	try {
	    return (List<LocationInterface>) serverController.getAllRooms();
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }

    static void addNewTeacherToDb(ServerInterface serverController, String login, String password, String name, String id, ArrayList<BookingLocationsInterface> arrayList, List<CourseInterface> teachersCourses) {
	try {
	    serverController.addTeacher(login, password, name, id, arrayList, teachersCourses);
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	    return;
	}
    }

    static Scheldue_result createNewScheldue(ServerInterface serverController, String semesterStart, String semesterEnd, ArrayList<LocationInterface> Rooms, ArrayList<CourseInterface> Courses) {
	try {
	    Scheldue_result scheldue = serverController.getNewSchelue(semesterStart, semesterEnd, Rooms, Courses);
	    return scheldue;
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientClassSchedulingHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    static List<BookingLocationsInterface> AttemptreBookBooking(ServerInterface serverController, String startBookingFrom, List<BookingLocationsInterface> bookings) {
	try {
	    List<BookingLocationsInterface> newBookings = serverController.AttemptreBookBooking(startBookingFrom, bookings);
	    return newBookings;
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientClassSchedulingHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    static Scheldue_result getCurrentActiveScheldue(ServerInterface serverController) {
	try {
	    return serverController.getCurrentSchelue();
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientClassSchedulingHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

    static boolean addPossibleTimesToBook(ServerInterface serverController, Map<String, String[]> besttimes, Map<String, String[]> mediumtimes, Map<String, String[]> emergencytimes) {
	try {
	    return serverController.addPossibleTimesToBook(besttimes, mediumtimes, emergencytimes);
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientClassSchedulingHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return false;
    }

    static String[] getNamesOfAllDaysOfWeek() {
	String[] daysOfWeek = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
	return daysOfWeek;
    }

    static String[] getPossibleTimeStamps() {
	String[] timestamp = new String[48];
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:SS");
	LocalDateTime dateTime = LocalDateTime.now();
	dateTime.withHour(0);
	dateTime.withMinute(0);
	dateTime.withSecond(0);
	for(int i=0;i<=timestamp.length;i++){
	    String formattedDateTime = dateTime.format(formatter);
	    timestamp[i]=formattedDateTime;
	    dateTime.plusMinutes(30);
	}
	return timestamp;
    }
        static List<String> getPossibleTimeStampsAfterTime(String time) {	    
	List <String> timestamp = new ArrayList<String>();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:SS");
	LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
	//Add First to list..so we include it.
	timestamp.add(dateTime.format(formatter));
	dateTime.plusMinutes(30);
	while(dateTime.getHour() != 0 && dateTime.getMinute() != 0){
	    String formattedDateTime = dateTime.format(formatter);
	    timestamp.add(formattedDateTime);
	    dateTime.plusMinutes(30);
	}
	return timestamp;
    }
    

}
