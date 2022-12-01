package main;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import java.io.*;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Objects;

import java.util.Scanner;

public class ClackClient
{
private static String userName;
private static String hostName;
private int port;
private static Boolean closeConnection = false;
private static ClackData dataToSendToServer;
private static ClackData dataToReceiveFromServer;
private static Scanner inFromStd;
private static final int defaultPort  = 7000;

private static final String key = "TIME";

private static ObjectInputStream inFromServer;
private static ObjectOutputStream outToServer;

public ClackClient(String userName, String hostName, int port)  throws IllegalArgumentException
{
    this.userName = userName;
    this.hostName = hostName;
    this.port = port;
    dataToSendToServer = null;
    dataToReceiveFromServer = null;
    inFromServer = null;
    outToServer = null;
}
public ClackClient(String userName, String hostName) throws IllegalArgumentException
{
    this(userName, hostName, defaultPort);
}
public ClackClient(String userName) 
{
    this(userName, "localhost");
}
public ClackClient() throws IllegalArgumentException
{
    this("Anon");
}

public static void main(String [] args)
{
    String input = "";
    String uName = "";
    String hName = "";
    int pNumber = 0;
    ClackClient cTest;

    for(String i : args)
    {
        input += i;
    }

    if(input.contains("@") && input.contains(":"))
    {
        uName = input.substring(0,input.indexOf('@'));
        hName = input.substring(input.indexOf('@')+1,input.indexOf(':'));
        pNumber=Integer.parseInt((input.substring(input.indexOf(':')+1)));
        cTest = new ClackClient(uName,hName,pNumber);
    }
    else if(input.contains("@"))
    {
        uName = input.substring(0,input.indexOf('@'));
        hName = input.substring(input.indexOf('@')+1);
        cTest = new ClackClient(uName,hName);
    }
    else if(!input.equals(""))
    {
        uName = input;
        cTest= new ClackClient(uName);
    }
    else
    {
       cTest = new ClackClient();
    }

    cTest.start();
}

public static void start() {

    try
    {
        Socket skt = new Socket(hostName, defaultPort);
        outToServer = new ObjectOutputStream(skt.getOutputStream());
        inFromServer = new ObjectInputStream(skt.getInputStream());
        inFromStd = new Scanner(System.in);
        Thread clientThread = new Thread(new ClientSideServerListener(new ClackClient()));
        while (!closeConnection)
        {
            readClientData();
            sendData();
            if (closeConnection) {
                break;
            }
            clientThread.start();
        }

        inFromStd.close();
        skt.close();
    }
    catch(IOException ioe)
    {
        System.err.println("IO Exception occurred");
    }

}
public static void readClientData()
{
    String nextToken = inFromStd.next();

        if (nextToken.equals("DONE"))
        {
            closeConnection = true;
            dataToSendToServer = new MessageClackData(userName, nextToken, key, ClackData.CONSTANT_LOGOUT);
        }
        else if (nextToken.equals("SENDFILE"))
        {
            String filename = inFromStd.next();
            dataToSendToServer = new FileClackData(userName, filename, ClackData.CONSTANT_SENDFILE);
            ((FileClackData) dataToSendToServer).readFileContents(key);

        }
        else if (nextToken.equals("LISTUSERS")) {
            dataToSendToServer = new MessageClackData(userName, nextToken, key, ClackData.CONSTANT_LISTUSERS);
        }
        else
        {
            String message = nextToken + inFromStd.nextLine();
            dataToSendToServer = new MessageClackData(userName, message, key,
            ClackData.CONSTANT_SENDMESSAGE);
        }
}public void receiveData() {
        try {
            this.dataToReceiveFromServer = (ClackData) this.inFromServer.readObject();
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
public static void sendData()
{
    try
    {
        outToServer.writeObject(dataToSendToServer);
    }
    catch(IOException ioe)
    {
        System.err.println("IO Exception occurred");
    }
}public void printData() {
        if (this.dataToReceiveFromServer != null) {
            System.out.println("From: " + this.dataToReceiveFromServer.getUsername());
            System.out.println("Date: " + this.dataToReceiveFromServer.getDate());
            System.out.println("Data: " + this.dataToReceiveFromServer.getData(key));
            System.out.println();
        }
    }

public String getUserName()
{
    return userName;
}
public String getHostName()
{
    return hostName;
}
public int getPort()
{
    return port;
}
public boolean getCloseConnection(){return closeConnection;}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ClackClient that = (ClackClient) o;
    return port == that.port && Objects.equals(userName, that.userName) && Objects.equals(hostName, that.hostName) && Objects.equals(closeConnection, that.closeConnection) && Objects.equals(dataToSendToServer, that.dataToSendToServer) && Objects.equals(dataToReceiveFromServer, that.dataToReceiveFromServer);
}
@Override
public int hashCode() {
    return Objects.hash(userName) / Objects.hash(hostName) + Objects.hash(port);
}
@Override
public String toString() {
    return "main.ClackClient{" +
            "userName='" + userName + '\'' +
            ", hostName='" + hostName + '\'' +
            ", port=" + port +
            ", closeConnection=" + closeConnection +
            ", dataToSendToServer=" + dataToSendToServer +
            ", dataToRecieveFromServer=" + dataToReceiveFromServer +
            '}';
    }

}
