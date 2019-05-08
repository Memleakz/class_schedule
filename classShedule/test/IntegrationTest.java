/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Business_Logic.Common.Schedule_result;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.LocationInterface;
import Client.ClientController;
import Server.ClassSchedulingServer;
import Server.ServerControllerImplementation;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author lawar15
 */
public class IntegrationTest {

    private static final int REGISTRY_PORT = 1812;
    private static final String REMOTE_OBJECT_NAME = "serverController";
    ServerControllerImplementation serverController = null;
    Registry registry = null;

    @BeforeClass
    public static void oneTimeSetUp() {
	// one-time initialization code        
    }

    @AfterClass
    public static void oneTimeTearDown() {
	// one-time cleanup code
    }

    @Before
    public void setUp() {
	try {
	    if(this.serverController == null)
	    {
		this.serverController = new ServerControllerImplementation();
		this.registry = LocateRegistry.createRegistry(REGISTRY_PORT);
		registry.bind(REMOTE_OBJECT_NAME, serverController);
		System.out.println("Server is running");
	    }
	} catch (RemoteException ex) {
	    Logger.getLogger(ClassSchedulingServer.class.getName()).log(Level.SEVERE, null, ex);
	} catch (AlreadyBoundException ex) {
	    Logger.getLogger(ClassSchedulingServer.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
public String hashPassword(String password){
    if (password == null) {
	    return null;
	}

	byte[] hashBytes = null;
	// Salting the password
	password += password.substring(0, 4);
	MessageDigest md;
	try {
	    md = MessageDigest.getInstance("SHA-256");
	    md.update(password.getBytes());
	    hashBytes = md.digest();
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}

	return String.format("%064x", new java.math.BigInteger(1, hashBytes)).toLowerCase();
}
    @After
    public void tearDown() throws RemoteException {

    }

    @Test
    public void testSignIn() throws RemoteException {
	ClientController cc = new ClientController();
	String login = "jan";
	String password = "corfixen";
	boolean signInBoolean = cc.signIn(login, hashPassword(password));
	Assert.assertTrue(signInBoolean);

    }
    @Test
    public void testMakeNewSchedule() throws RemoteException {
	
	ClientController cc = new ClientController();
	boolean isprepaingsuccessfull =cc.preparePaneToMakingNewSchedule();
	Assert.assertTrue(isprepaingsuccessfull);
	ArrayList<CourseInterface> courses=(ArrayList<CourseInterface>) cc.getAllCourses();
	ArrayList<LocationInterface> rooms = (ArrayList<LocationInterface>) cc.getAllRooms();
	String start = "01/01/2016 08:00:00";
	String finish = "31/12/2016 23:59:59";
	Schedule_result sr =cc.createNewScheldue(start, finish, rooms, courses);
	List<CourseInterface> bookedCourses = sr.getBookedCourses();
	Assert.assertEquals(courses.size(),bookedCourses.size());
	
    }
}
