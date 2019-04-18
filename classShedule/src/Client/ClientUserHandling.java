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
import java.rmi.RemoteException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lawar15
 */
class ClientUserHandling {

    static User signIn(ServerInterface serverController, String login, String password) {
	try {
	    return (User) serverController.signIn(login, password);
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }

    static boolean isUserATeacher(ServerInterface serverController, String login, String password) {
	try {
	    User u = (User) serverController.signIn(login, password);
	    return u.isTeacher();
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	    return false;
	}
    }

    static TeacherInterface handleTeachersAbsence(ServerInterface serverController, String teacherID, String startDateOfAbsence, String finishDateOfAbsence) {
	try {
	    return serverController.handleTeachersAbsence(teacherID, startDateOfAbsence, finishDateOfAbsence);
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }

    static Map<Integer, Map<Period, CourseInterface>> getClassScheduleForTeacher(ServerInterface serverController, String teachersID) {
	try {
	    return serverController.getClassScheduleForTeacher(teachersID);
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }

    static String getUsersIdByName(ServerInterface serverController, String name) {
	try {
	    return serverController.getIdOfTeacherByName(name);
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	    return null;
	}
    }

}
