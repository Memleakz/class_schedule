/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Business_Logic.Common.Period;
import Business_Logic.Common.Schedule;
import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.LocationInterface;
import Business_Logic.IServices.ServerInterface;
import Business_Logic.IServices.TeacherInterface;
import Business_Logic.IServices.UserInterface;
import Business_Logic.Common.Schedule_result;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lawar15
 */
public class ServerControllerImplementation extends UnicastRemoteObject implements ServerInterface {

    Schedule_result current_scheldue = null;

    public ServerControllerImplementation() throws RemoteException {
        //Load current active scheldue from db , we need this for making changes.

        List<LocationInterface> Rooms = ServerClassSchedulingHandling.getAllRooms();
        List<CourseInterface> Courses = ServerClassSchedulingHandling.getAllCourses();
        Schedule_result tmp = new Schedule_result();
        List<CourseInterface> Courses_with_bookings = new ArrayList<CourseInterface>();
        for (CourseInterface c : Courses) {
            if (c.getBookedRooms() != null && c.getBookedRooms().size() != 0) {
                Courses_with_bookings.add(c);
            }
        }
        if (Courses_with_bookings.size() != 0) {
            tmp.setBookedCourses(Courses);

            this.current_scheldue = tmp;
        }

    }

    @Override
    public UserInterface signIn(String username, String password) throws RemoteException {
        return ServerUserHandling.signIn(username, password);
    }

    @Override
    public TeacherInterface handleTeachersAbsence(String teacherID, String startDateOfAbsence, String finishDateOfAbsence) throws RemoteException {
        return ServerUserHandling.handleTeachersAbsence(teacherID, startDateOfAbsence, finishDateOfAbsence);
    }

    @Override
    public Map<Integer, Map<Period, CourseInterface>> getClassScheduleForTeacher(String teachersID) throws RemoteException {
        return ServerUserHandling.getClassScheduleForTeacher(teachersID);
    }

    @Override
    public String getIdOfTeacherByName(String name) throws RemoteException {
        return ServerUserHandling.getTeachersIDByName(name);
    }

    @Override
    public List<TeacherInterface> getAllTeachers() throws RemoteException {
        return ServerClassSchedulingHandling.getAllTeachers();
    }

    @Override
    public List<CourseInterface> getAllCourses() throws RemoteException {
        return ServerClassSchedulingHandling.getAllCourses();
    }

    @Override
    public List<LocationInterface> getAllRooms() throws RemoteException {
        return ServerClassSchedulingHandling.getAllRooms();
    }

    @Override
    public Schedule_result getNewSchelue(String semesterStart, String semesterEnd, List<LocationInterface> Rooms, List<CourseInterface> Courses) throws RemoteException {
        this.current_scheldue = ServerClassSchedulingHandling.AssignRoomsForCourses(semesterStart, semesterEnd, Rooms, Courses);
        return this.current_scheldue;
    }

    @Override
    public Schedule_result getCurrentSchelue() throws RemoteException {
        return this.current_scheldue;
    }

    @Override
    public List<BookingLocationsInterface> AttemptreBookBooking(String startBookingFrom, List<BookingLocationsInterface> bookings) {
        List<BookingLocationsInterface> new_booking_result = new ArrayList<BookingLocationsInterface>();
        List<CourseInterface> update_me = new ArrayList<CourseInterface>();
        for (BookingLocationsInterface b : bookings) {
            Schedule schedule = b.getScheduleOfBooking();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            Period targetPeriode = new Period(startBookingFrom, schedule.getPeriodOfTerm().getEndDate().format(formatter));
            schedule.setPeriodOfTerm(targetPeriode);
            //Get a new date
            CourseInterface c = this.current_scheldue.getBookedCourse(b.getCourse().getNameOfTheCourse());
            LocationInterface cr = b.getCourse().getRoomReferencedByBooking(b);
            LocationInterface room = null;
            for (LocationInterface lroom : c.getBookedRooms()) {
                if (lroom.getNameOfTheLocation().equals(cr.getNameOfTheLocation())) {
                    room = lroom;
                }
            }
            List<BookingLocationsInterface> new_Booking = ServerClassSchedulingHandling.AttemBookCourseTimes(null, c, room, "bedst", 1, schedule);
            if (new_Booking.size() != 0) {
                b.setCourse(c);
                room.cancelBooking(b); // remove old or mark as cancelled
                room.addNewBooking(new_Booking.get(0), c); // add new
            }
        }

        return new_booking_result;
    }

    @Override
    public int addTeacher(String login, String password, String name, String id, ArrayList<BookingLocationsInterface> arrayList, List<CourseInterface> teachersCourses) throws RemoteException {
        return ServerClassSchedulingHandling.setTeacherInDb(login, password, name, id, arrayList, teachersCourses);

    }

    @Override
    public boolean addPossibleTimesToBook(Map<String, String[]> besttimes, Map<String, String[]> mediumtimes, Map<String, String[]> emergencytimes) throws RemoteException {
        return ServerClassSchedulingHandling.setPossibleTimesOfDayInDb(besttimes, mediumtimes, emergencytimes);
    }

}
