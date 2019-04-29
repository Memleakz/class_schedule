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
import Business_Logic.User.User;
import Business_Logic.scheldue_result.scheldue_result;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lawar15
 */
public class ClientController {

    ServerInterface serverController;
    private User currentUser = null;
    private List<TeacherInterface> allTeachers = new ArrayList<TeacherInterface>();
    private List<TeacherInterface> choosedTeachers = new ArrayList<TeacherInterface>();
    private List<CourseInterface> choosedCourses = new ArrayList<CourseInterface>();
    private List<CourseInterface> allCourses = new ArrayList<CourseInterface>();
    private List<LocationInterface> choosedRooms = new ArrayList<LocationInterface>();
    private List<LocationInterface> allRooms = new ArrayList<LocationInterface>();

    public ClientController() throws RemoteException {
	String hostname = "localhost";
	try {
	    Registry registry = LocateRegistry.getRegistry(hostname, 1812);
	    serverController = (ServerInterface) registry.lookup("serverController");
	} catch (RemoteException | NotBoundException ex) {
	    ex.printStackTrace();
	}
    }

    public boolean signIn(String login, String password) {
	currentUser = ClientUserHandling.signIn(serverController, login, password);
	if (currentUser != null) {
	    return true;
	}
	return false;
    }

    public boolean isThisATeacher(String login, String password) {
	return ClientUserHandling.isUserATeacher(serverController, login, password);
    }

    public boolean preparePaneToMakingNewSchedule() {
	allCourses = ClientClassSchedulingHandling.getAllCourses(serverController);
	allTeachers = ClientClassSchedulingHandling.getAllTeachers(serverController);
	allRooms = ClientClassSchedulingHandling.getAllRooms(serverController);
	if (allCourses != null && allTeachers != null && allRooms != null) {
	    return true;
	}
	return false;
    }

    public String makeDatePickerDateToString(LocalDate startdate, boolean isStartDate) {
	String time = "";
	if (isStartDate == true) {
	    time = " 00:00:01";
	} else {
	    time = " 23:59:59";
	}
	String returstring = "";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	String formattedString = startdate.format(formatter);
	String newString = formattedString + time;
	returstring = newString;
	return returstring;
    }

    public List<CourseInterface> getAllCourses() {
	//check if prepared.
	return this.allCourses;
    }

    public List<TeacherInterface> getAllTeachers() {
	//check if prepared.
	return ClientClassSchedulingHandling.getAllTeachers(serverController);
    }

    public List<LocationInterface> getAllRooms() {
	//check if prepared.
	return this.allRooms;
    }

    public void handlesignoutAction() {
	currentUser = null;
    }

    public boolean handleTeachersAbsence(String startDateOfAbsence, String finishDateOfAbsence,TeacherInterface lecturer) {
	/*TeacherInterface teacherForAbsence= null;
	String usersId = currentUser.getTeachersID();
	teacherForAbsence =ClientUserHandling.handleTeachersAbsence(serverController, usersId , startDateOfAbsence, finishDateOfAbsence);
	    if(teacherForAbsence !=null){
		return true;
	    }
	    return false;*/

	//GEt retardo lecturer obj
	if(lecturer == null)
	{
	    List<TeacherInterface> Teachers = ClientClassSchedulingHandling.getAllTeachers(serverController);
	    for (TeacherInterface t : Teachers) {
		if (t.getTeachersId().equals(this.currentUser.getTeachersID())) {
		    lecturer = t;
		    break;
		}
	    }
	}
	//Get teachers courses.
	Period p = new Period(startDateOfAbsence, finishDateOfAbsence);
	List<CourseInterface> tcourses = new ArrayList<CourseInterface>();
	List<CourseInterface> courses = ClientClassSchedulingHandling.getAllCourses(serverController);
	/*for(CourseInterface c : courses)
	    {
		if(c.getAssignedLecturer().getTeachersId().equals(currentUser.getTeachersID()))
		{
		    tcourses.add(c);
		}
	    }*/
	//Find affected bookings
	scheldue_result active = ClientClassSchedulingHandling.getCurrentActiveScheldue(serverController);
	List<BookingLocationsInterface> bookings = new ArrayList<BookingLocationsInterface>();
	List<LocationInterface> rooms = new ArrayList<LocationInterface>();
	List<CourseInterface> bookedcourses = active.getBookedCourses();
	//replace this with a server call to get all rooms , once scheldue is saved.
	for (CourseInterface c : bookedcourses) {
	    List<LocationInterface> b = c.getBookedRooms();
	    boolean match_found = false;
	    for (LocationInterface broom : b) {
		for (LocationInterface r : rooms) {
		    if (r.getNameOfTheLocation().compareTo(broom.getNameOfTheLocation()) == 0) {
			match_found = true;
			break;
		    }
		}
		if (match_found == false) {
		    rooms.add(broom);
		}
	    }
	}

	for (LocationInterface r : rooms) {
	    List<BookingLocationsInterface> affected_bookings = r.getAllBookingsForTeacher(lecturer, p);
	    bookings.addAll(affected_bookings);
	}

	ClientClassSchedulingHandling.AttemptreBookBooking(serverController, finishDateOfAbsence, bookings);
	return true;
    }

    public String getUsersNameByLogin(String login) {
	return currentUser.getname();

    }

    public Map<Integer, Map<Period, CourseInterface>> getClassScheduleForTeacher(String teachersID) {
	return ClientUserHandling.getClassScheduleForTeacher(serverController, teachersID);
    }

    public String getTeachersIdByName(String name) {
	return ClientUserHandling.getUsersIdByName(serverController, name);
    }

    public void addNewTeacher(String login, String password, String name, String id, ArrayList<BookingLocationsInterface> arrayList, List<CourseInterface> teachersCourses) {
	ClientClassSchedulingHandling.addNewTeacherToDb(serverController, login, password, name, id, arrayList, teachersCourses);
    }

    public scheldue_result createNewScheldue(String semesterStart, String semesterEnd, ArrayList<LocationInterface> Rooms, ArrayList<CourseInterface> Courses) {
	return ClientClassSchedulingHandling.createNewScheldue(serverController, semesterStart, semesterEnd, Rooms, Courses);
    }

    public scheldue_result getCurrentScheldue() {
	return ClientClassSchedulingHandling.getCurrentActiveScheldue(serverController);
    }

    public List<BookingLocationsInterface> AttemptreBookBooking(String startBookingFrom, List<BookingLocationsInterface> booking) {
	return ClientClassSchedulingHandling.AttemptreBookBooking(serverController, startBookingFrom, booking);
    }
}
