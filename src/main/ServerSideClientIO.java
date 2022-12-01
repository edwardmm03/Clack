package main;

import data.ClackData;

import java.io.*;
import java.net.*;

public class ServerSideClientIO implements Runnable {

    private boolean closeConnection = false;
    ClackData dataToReceiveFromClient;
    ClackData dataToSendToClient;
    ObjectInputStream inFromClient;
    ObjectOutputStream outToClient;
    ClackServer server;
    Socket clientSocket;

    ServerSideClientIO(ClackServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        closeConnection = false;
        dataToReceiveFromClient = null;
        dataToSendToClient = null;
        inFromClient = null;
        outToClient = null;
    }

    @Override
    public void run() {
        try {
            this.inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            this.outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            while (!closeConnection) {
                receiveData();
                if(closeConnection){break;}

            }
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void receiveData() {
        try {
            this.dataToReceiveFromClient = (ClackData) this.inFromClient.readObject();
            if (this.dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                this.closeConnection = true;
            }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("ClassNotFoundException thrown in receiveData(): " + cnfe.getMessage());
        } catch (InvalidClassException ice) {
            System.err.println("InvalidClassException thrown in receiveData(): " + ice.getMessage());
        } catch (StreamCorruptedException sce) {
            System.err.println("StreamCorruptedException thrown in receiveData(): " + sce.getMessage());

        } catch (OptionalDataException ode) {
            System.err.println("OptionalDataException thrown in receiveData(): " + ode.getMessage());

        } catch (IOException ioe) {
            System.err.println("IOException thrown in receiveData(): " + ioe.getMessage());
        }
    }
    public void sendData() {
        try {this.outToClient.writeObject(this.dataToSendToClient);}
        catch (InvalidClassException ice) {
            System.err.println("InvalidClassException thrown in sendData(): " + ice.getMessage());
        } catch (NotSerializableException nse) {
            System.err.println("NotSerializableException thrown in sendData(): " + nse.getMessage());
        } catch (IOException ioe) {
            System.err.println("IOException thrown in sendData(): " + ioe.getMessage());
        }
    }
    void setDataToSendToClient(ClackData dataToSendToClient){this.dataToSendToClient = dataToSendToClient;}
}

