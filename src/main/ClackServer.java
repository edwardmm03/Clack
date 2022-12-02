package main;

import data.ClackData;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClackServer {
    private static final int DEFAULT_PORT = 7000;  // The default port number
    private int port;  // An integer representing the port number on the server connected to
    private static boolean closeConnection;  // A boolean representing whether the connection is closed or not
    public static ArrayList<ServerSideClientIO> serverSideClientIOList; //ArrayList of ServerSideIO Objects
    /**
     * The constructor that sets the port number.
     * Sets dataToReceiveFromClient and dataToSendToClient as null.
     *
     * @param port an int representing the port number on the server connected to
     */
    public ClackServer(int port) throws IllegalArgumentException {
        if (port < 1024) {
            throw new IllegalArgumentException("The port cannot be less than 1024.");
        }
        this.port = port;
        this.closeConnection = false;
        serverSideClientIOList = new ArrayList<ServerSideClientIO>();
    }
    /**
     * The default constructor that sets the port to the default port number 7000.
     * The default port number should be set up as constant (e.g., DEFAULT_PORT).
     * This constructor should call another constructor.
     */
    public ClackServer() throws IllegalArgumentException {
        this(DEFAULT_PORT);
    }
    public static void main(String[] args){
        ClackServer runner;
        if (args.length == 0) {runner = new ClackServer();}
        else {
            try {
                int port = Integer.parseInt(args[0]);
                runner = new ClackServer(port);
            } catch (NumberFormatException nfe) {
                System.err.println("Input an int as argument");
                runner = new ClackServer();
            }
        }
        runner.start();
    }
    /**
     * Starts the server.
     * Does not return anything.
     * Creates a server socket and a client socket to facilitate input and output
     * runs a loop to echo data until closeConnection is set to true
     */
    public static void start() {
        try {
            ServerSocket sskt = new ServerSocket(DEFAULT_PORT);
            while(!closeConnection){
                Socket cskt = sskt.accept();
                ServerSideClientIO acceptedClient = new ServerSideClientIO(this,cskt);
                serverSideClientIOList.add(acceptedClient);
                Thread clientInstance= new Thread(acceptedClient);
                clientInstance.start();
            }
            sskt.close();
        }
        catch (IOException ioe) {System.err.print("IO Exception Occurred");}
    }
    public synchronized void broadcast(ClackData dataToBroadcastToClients){
        for(Iterator<ServerSideClientIO> it = serverSideClientIOList.iterator(); it.hasNext();){
            ServerSideClientIO temp = it.next();
            temp.setDataToSendToClient(dataToBroadcastToClients);
            temp.sendData();
        }
    }
    public synchronized void remove(ServerSideClientIO serverSideClientToRemove){
        serverSideClientIOList.remove(serverSideClientToRemove);
    }
    /**
     * Returns the port.
     *
     * @return this.port.
     */
    public int getPort() {
        return this.port;
    }
    public String listUsers(){
        String users = null;
        for(Iterator<ServerSideClientIO> it = serverSideClientIOList.iterator(); it.hasNext();){
            ServerSideClientIO temp = it.next();
            users += temp.getUserName();
        }
        return users;
    }


    @Override
    public int hashCode() {
        // The following is only one of many possible implementations to generate the hash code.
        // See the hashCode() method in other classes for some different implementations.
        // It is okay to select only some of the instance variables to calculate the hash code
        // but must use the same instance variables with equals() to maintain consistency.
        return Objects.hash(this.port, this.closeConnection);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClackServer)) {
            return false;
        }

        // Casts other to be a ClackServer to access its instance variables.
        ClackServer otherClackServer = (ClackServer) other;

        // Compares the selected instance variables of both ClackServer objects that determine uniqueness.
        // It is okay to select only some of the instance variables for comparison but must use the same
        // instance variables with hashCode() to maintain consistency.
        return this.port == otherClackServer.port
                && this.closeConnection == otherClackServer.closeConnection;
    }
    @Override
    public String toString() {
        // Should return a full description of the class with all instance variables.
        return "This instance of ClackServer has the following properties:\n"
                + "Port number: " + this.port + "\n"
                + "Connection status: " + (this.closeConnection ? "Closed" : "Open") + "\n";
    }
}
