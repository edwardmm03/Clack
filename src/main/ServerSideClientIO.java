package main;

import data.ClackData;
import data.MessageClackData;

import java.io.*;
import java.net.*;

public class ServerSideClientIO implements Runnable {
    private boolean closeConnection = false;
    public ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ClackServer server;
    private Socket clientSocket;

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
            System.out.println("Starting thread");
            this.inFromClient = new ObjectInputStream(this.clientSocket.getInputStream());
            this.outToClient = new ObjectOutputStream(this.clientSocket.getOutputStream());
            while (!this.closeConnection) {
                receiveData();
                if(this.closeConnection){break;}
                this.server.broadcast(this.dataToReceiveFromClient);
            }
            this.clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void receiveData() {
        try {
            this.dataToReceiveFromClient = (ClackData) this.inFromClient.readObject();
            if (this.dataToReceiveFromClient.getType() == ClackData.CONSTANT_LOGOUT) {
                this.server.remove(new ServerSideClientIO(this.server, this.clientSocket));
                this.closeConnection = true;
            }
            if (this.dataToReceiveFromClient.getType() == ClackData.CONSTANT_LISTUSERS){
                dataToReceiveFromClient = new MessageClackData(dataToReceiveFromClient.getUsername(), server.listUsers(),0);
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
        try {outToClient.writeObject(dataToSendToClient);}
        catch (InvalidClassException ice) {
            System.err.println("InvalidClassException thrown in sendData(): " + ice.getMessage());
        } catch (NotSerializableException nse) {
            System.err.println("NotSerializableException thrown in sendData(): " + nse.getMessage());
        } catch (IOException ioe) {
            System.err.println("IOException thrown in sendData(): " + ioe.getMessage());
        }
    }
    public String getUserName(){return this.dataToReceiveFromClient.getUsername();}
    public void setDataToSendToClient(ClackData dataToSendToClient){this.dataToSendToClient = dataToSendToClient;}
}

