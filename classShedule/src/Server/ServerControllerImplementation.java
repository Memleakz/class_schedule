/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Business_Logic.Common.Period;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.ServerInterface;
import Business_Logic.IServices.TeacherInterface;
import Business_Logic.IServices.UserInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

/**
 *
 * @author lawar15
 */
public class ServerControllerImplementation extends UnicastRemoteObject implements ServerInterface{
    public ServerControllerImplementation()  throws RemoteException {

	}

    @Override
    public UserInterface signUp(UserInterface user, String password) throws RemoteException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserInterface signIn(String username, String password) throws RemoteException {
	return ServerUserHandling.signIn(username, password);
    }

    @Override
    public void signOut() throws RemoteException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserInterface updateUser(int currentUserID) throws RemoteException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TeacherInterface handleTeachersAbsence(String teacherID, String startDateOfAbsence, String finishDateOfAbsence) throws RemoteException {
	return ServerUserHandling.handleTeachersAbsence(teacherID, startDateOfAbsence, finishDateOfAbsence);
    }
    @Override
    public Map<Integer,Map<Period,CourseInterface>> getClassScheduleForTeacher(String teachersID)throws RemoteException {
	return ServerUserHandling.getClassScheduleForTeacher(teachersID);
    }

    @Override
    public String getIdOfTeacherByName(String name)throws RemoteException {
    return ServerUserHandling.getTeachersIDByName(name);
    }




    
}
