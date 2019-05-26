/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Business_Logic.Common.Schedule_result;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.LocationInterface;
import Client.ClientController;
import Server.ServerControllerImplementation;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpeedTest {
    private static final int REGISTRY_PORT = 1812;
    private static final String REMOTE_OBJECT_NAME = "serverController";
    ServerControllerImplementation serverController = null;
    Registry registry = null;
    
    public SpeedTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    public void scheldueGenerationSpeedTest() throws RemoteException {
        long timer_start = System.currentTimeMillis();
        //execute logic in between
        
        ClientController cc = new ClientController();
	boolean isprepaingsuccessfull =cc.preparePaneToMakingNewSchedule();
	Assert.assertTrue(isprepaingsuccessfull);
     
	ArrayList<CourseInterface> courses=(ArrayList<CourseInterface>) cc.getAllCourses();
	ArrayList<LocationInterface> rooms = (ArrayList<LocationInterface>) cc.getAllRooms();
	String start = "01/01/2019 08:00:00";
	String finish = "31/12/2019 23:59:59";
	Schedule_result sr =cc.createNewScheldue(start, finish, rooms, courses);
	       
        
        long timer_end = System.currentTimeMillis();
        System.out.println("DEBUG: Logic A took " + (timer_end - timer_start) + " MilliSeconds");
    }
}
