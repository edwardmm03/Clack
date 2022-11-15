package main;

import data.ClackData;
import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * The ClackServer class is a blueprint for a ClackServer object that contains information about the
 * port number that clients connect to, a boolean representing whether the server needs to be
 * closed or not, and ClackData objects representing data sent to and received from the client. The
 * server class does not need to know the host name (as the server program runs on its own computer),
 * it just needs to know what port the clients connect to. In our application, all clients will connect
 * to a single port.
 *
 * @author xinchaosong
 */
public class ClackServer {
    private static final int DEFAULT_PORT = 7000;  // The default port number
    private int port;  // An integer representing the port number on the server connected to
    private static boolean closeConnection;  // A boolean representing whether the connection is closed or not
    private static ClackData dataToReceiveFromClient;  // A ClackData object representing the data received from the client
    private static ClackData dataToSendToClient;  // A ClackData object representing the data sent to client
    private static ObjectInputStream inFromClient;  // An ObjectInputStream to manage input from the client
    private static ObjectOutputStream outToClient; // An ObjectOutputStream to send data to the client

    /**
     * The constructor that sets the port number.
     * Should set dataToReceiveFromClient and dataToSendToClient as null.
     *
     * @param port an int representing the port number on the server connected to
     */
    public ClackServer(int port) throws IllegalArgumentException {
        if (port < 1024) {
            throw new IllegalArgumentException("The port cannot be less than 1024.");
        }
        this.port = port;
        this.closeConnection = false;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
        this.inFromClient = null;
        this.outToClient = null;
    }

    /**
     * The default constructor that sets the port to the default port number 7000.
     * The default port number should be set up as constant (e.g., DEFAULT_PORT).
     * This constructor should call another constructor.
     */
    public ClackServer() throws IllegalArgumentException {
        this(DEFAULT_PORT);
    }
    public static void main(String [] args){
        String input = null;
        int temp = '\0';
        Scanner console = new Scanner(System.in);
        System.out.println("Enter a port number to connect to");
        input = console.nextLine();
        if (!input.equals(null))
        {
            temp = Integer.parseInt(input);
            ClackServer runner = new ClackServer(temp);
        }
        else {ClackServer runner;}
        console.close();
        start();
    }
    /**
     * Starts the server.
     * Does not return anything.
     * For now, it should have no code, just a declaration.
     */
    public static void start() {
        try {
            ServerSocket sskt = new ServerSocket(DEFAULT_PORT);
            Socket cskt = sskt.accept();
            inFromClient = new ObjectInputStream(cskt.getInputStream());
            outToClient = new ObjectOutputStream(cskt.getOutputStream());
            while(!closeConnection == true) {
                receiveData();
                dataToSendToClient = dataToReceiveFromClient;
                sendData();
            }
            inFromClient.close();
            outToClient.close();
            cskt.close();
            sskt.close();
        }
        catch (IOException ioe) {System.err.print("IO Exception Occurred");}
    }

    /**
     * Receives data from client.
     * Does not return anything.
     * For now, it should have no code, just a declaration.
     */
    public static void receiveData() {
        try {
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();
        }
        catch (IOException ioe) {System.err.println("IO Exception Occurred");}
        catch (ClassNotFoundException cnfn) {System.err.println("Class was not found");}
        if (dataToReceiveFromClient.getType() == 1)
        {
        closeConnection = true;
        }
        }

    /**
     * Sends data to client.
     * Does not return anything.
     * For now, it should have no code, just a declaration.
     */
    public static void sendData() {
            try {
                outToClient.writeObject(dataToSendToClient);
            }
            catch (IOException ioe) {System.err.print("IO Exception Occurred");}
    }

    /**
     * Returns the port.
     *
     * @return this.port.
     */
    public int getPort() {
        return this.port;
    }


    @Override
    public int hashCode() {
        // The following is only one of many possible implementations to generate the hash code.
        // See the hashCode() method in other classes for some different implementations.
        // It is okay to select only some of the instance variables to calculate the hash code
        // but must use the same instance variables with equals() to maintain consistency.
        return Objects.hash(this.port, this.closeConnection, this.dataToReceiveFromClient, this.dataToSendToClient);
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
                && this.closeConnection == otherClackServer.closeConnection
                && Objects.equals(this.dataToReceiveFromClient, otherClackServer.dataToReceiveFromClient)
                && Objects.equals(this.dataToSendToClient, otherClackServer.dataToSendToClient);
    }

    @Override
    public String toString() {
        // Should return a full description of the class with all instance variables.
        return "This instance of ClackServer has the following properties:\n"
                + "Port number: " + this.port + "\n"
                + "Connection status: " + (this.closeConnection ? "Closed" : "Open") + "\n"
                + "Data to receive from the client: " + this.dataToReceiveFromClient + "\n"
                + "Data to send to the client: " + this.dataToSendToClient + "\n";
    }
}
