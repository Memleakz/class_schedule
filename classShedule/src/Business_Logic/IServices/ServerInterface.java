/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.IServices;

import Business_Logic.Common.Period;
import Business_Logic.Common.Schedule_result;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lawar15
 */
public interface ServerInterface  extends Remote {

	//User functions
	public UserInterface signUp(UserInterface user, String password) throws RemoteException;

	public UserInterface signIn(String username, String password) throws RemoteException;

	public void signOut() throws RemoteException;

	public UserInterface updateUser(int currentUserID) throws RemoteException;
	
	public TeacherInterface handleTeachersAbsence(String teacherID, String startDateOfAbsence, String finishDateOfAbsence) throws RemoteException;
	public Map<Integer,Map<Period,CourseInterface>> getClassScheduleForTeacher(String teachersID)throws RemoteException;

	public String getIdOfTeacherByName(String name)throws RemoteException;
	public List<TeacherInterface> getAllTeachers()throws RemoteException;
	public int addTeacher(String login, String password, String name, String id, ArrayList<BookingLocationsInterface> arrayList, List<CourseInterface> teachersCourses) throws RemoteException;
	public List<CourseInterface> getAllCourses()throws RemoteException;
	public List<LocationInterface> getAllRooms()throws RemoteException;
	public Schedule_result getNewSchelue(String semesterStart,String semesterEnd,List<LocationInterface> Rooms,List<CourseInterface> Courses)throws RemoteException;
	public List<BookingLocationsInterface> AttemptreBookBooking(String startBookingFrom,List<BookingLocationsInterface> bookings)throws RemoteException;
	public Schedule_result getCurrentSchelue() throws RemoteException;
	public boolean addPossibleTimesToBook(Map<String, String[]> besttimes, Map<String, String[]> mediumtimes, Map<String, String[]> emergencytimes)throws RemoteException;
}
