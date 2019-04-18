/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.IServices;

import Business_Logic.Common.Period;
import java.rmi.Remote;
import java.rmi.RemoteException;
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
    
}
