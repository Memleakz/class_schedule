/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lawar15
 */
public class ClassSchedulingServer {

    private static final int REGISTRY_PORT = 1812;
    private static final String REMOTE_OBJECT_NAME = "serverController";

    public static void main(String[] args) {

        try {
            ServerControllerImplementation serverController = new ServerControllerImplementation();
            Registry registry = LocateRegistry.createRegistry(REGISTRY_PORT);
            registry.bind(REMOTE_OBJECT_NAME, serverController);
            System.out.println("Server is running");
        } catch (RemoteException ex) {
            Logger.getLogger(ClassSchedulingServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(ClassSchedulingServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
