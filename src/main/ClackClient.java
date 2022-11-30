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
private static ClackData dataToRecieveFromServer;
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
    dataToRecieveFromServer = null;
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
        //No clue how to pass the client over with the correct info
        Thread clientThread = new Thread(new ClientSideServerListener(new ClackClient()));
        while (!closeConnection)
        {
            readClientData();
            sendData();
            if (closeConnection) {
                break;
            }
            clientThread.run();
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
            dataToSendToServer = new MessageClackData(userName, nextToken, key,
                    ClackData.CONSTANT_LOGOUT);
        }
        else if (nextToken.equals("SENDFILE"))
        {
            String filename = inFromStd.next();
            dataToSendToServer = new FileClackData(userName, filename, ClackData.CONSTANT_SENDFILE);
            ((FileClackData) dataToSendToServer).readFileContents(key);

        }
        else if (nextToken.equals("LISTUSERS"))
        {
            // Does nothing for now. Eventually, this will return a list of users.
            // For Part 2, do not test LISTUSERS; otherwise, it may generate an error.
        }
        else
        {
            String message = nextToken + inFromStd.nextLine();
            dataToSendToServer = new MessageClackData(userName, message, key,
            ClackData.CONSTANT_SENDMESSAGE);
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
}
public static void receiveData()
{
    try
    {
        dataToRecieveFromServer = (ClackData) inFromServer.readObject();
    }
    catch(Exception e)
    {
        System.err.println("IO Exception occurred");
    }
}
    public static void printData() {
        if (dataToRecieveFromServer != null) {
            System.out.println("From: " + dataToRecieveFromServer.getUsername());
            System.out.println("Date: " + dataToRecieveFromServer.getDate());
            System.out.println("Data: " + dataToRecieveFromServer.getData(key));
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
    return port == that.port && Objects.equals(userName, that.userName) && Objects.equals(hostName, that.hostName) && Objects.equals(closeConnection, that.closeConnection) && Objects.equals(dataToSendToServer, that.dataToSendToServer) && Objects.equals(dataToRecieveFromServer, that.dataToRecieveFromServer);
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
            ", dataToRecieveFromServer=" + dataToRecieveFromServer +
            '}';
    }

}
