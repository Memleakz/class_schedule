/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Business_Logic.Common.Period;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.ServerInterface;
import Business_Logic.IServices.TeacherInterface;
import Business_Logic.User.User;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;

/**
 *
 * @author lawar15
 */
public class ClientController {
    ServerInterface serverController;
    private User currentUser= null;
    
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
	
	public boolean isThisATeacher(String login, String password){
	    return ClientUserHandling.isUserATeacher(serverController, login, password);
	}

public void handlesignoutAction(){
    currentUser = null;
}
    public boolean handleTeachersAbsence(String startDateOfAbsence, String finishDateOfAbsence) {
	TeacherInterface teacherForAbsence= null;
	String usersId = currentUser.getTeachersID();
	teacherForAbsence =ClientUserHandling.handleTeachersAbsence(serverController, usersId , startDateOfAbsence, finishDateOfAbsence);
	    if(teacherForAbsence !=null){
		return true;
	    }
	    return false;
    }
    public String getUsersNameByLogin(String login){
	return currentUser.getname();
	
    }
    public Map<Integer,Map<Period,CourseInterface>> getClassScheduleForTeacher(String teachersID){
	return ClientUserHandling.getClassScheduleForTeacher(serverController, teachersID);
    }
    public String getTeachersIdByName(String name){
	return ClientUserHandling.getUsersIdByName(serverController, name);
    }
}

