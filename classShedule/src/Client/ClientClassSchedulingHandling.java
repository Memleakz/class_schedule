/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Business_Logic.Common.Period;
import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.LocationInterface;
import Business_Logic.IServices.ServerInterface;
import Business_Logic.IServices.TeacherInterface;
import Business_Logic.scheldue_result.scheldue_result;
import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
	try{
	    serverController.addTeacher(login, password, name, id, arrayList, teachersCourses);
	}
	catch (RemoteException ex) {
	    Logger.getLogger(ClientUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	    return;
	}
    }
    static scheldue_result createNewScheldue(ServerInterface serverController,String semesterStart,String semesterEnd,ArrayList<LocationInterface> Rooms,ArrayList<CourseInterface> Courses)
    {
	try {
	    scheldue_result scheldue = serverController.getNewSchelue(semesterStart, semesterEnd, Rooms, Courses);
	    return scheldue;
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientClassSchedulingHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }
    static List<BookingLocationsInterface> AttemptreBookBooking(ServerInterface serverController,String startBookingFrom,List<BookingLocationsInterface> bookings)
    {
	try {
	    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    //Period targetPeriode = new Period(booking.getPeriodOfBooking().getStartDate().format(formatter),"31/05/2019 22:00:00");
	    List<BookingLocationsInterface> newBookings = serverController.AttemptreBookBooking(startBookingFrom,bookings) ;
	    return newBookings;
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientClassSchedulingHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }
    static scheldue_result getCurrentActiveScheldue(ServerInterface serverController)
    {
	try {
	    return serverController.getCurrentSchelue();
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientClassSchedulingHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }

}
