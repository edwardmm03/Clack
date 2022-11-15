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
private String userName;
private String hostName;
private int port;
private Boolean closeConnection;
private ClackData dataToSendToServer;
private ClackData dataToRecieveFromServer;
private Scanner inFromStd;
private static final int defaultPort  = 7000;

private static final String key = "TIME";

private ObjectInputStream inFromServer;
private ObjectOutputStream outToServer;

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

public void main(String [] args)
{
    String input;
    String uName = null;
    String hName = null;
    int pNumber = defaultPort;
    Scanner console = new Scanner(System.in);

    System.out.println("Please enter the info of the server you want to connect to");
    input = console.nextLine();
    console.close();

    if(input.contains("@")&&input.contains(":"))
    {
        uName= input.substring(0,input.indexOf('@'));
        hName = input.substring(input.indexOf('@')+1,input.indexOf(':'));
        pNumber = Integer.parseInt((input.substring(input.indexOf(':')+1)));
    }
    else if(input.contains("@"))
    {
        uName= input.substring(0,input.indexOf('@'));
        hName = input.substring(input.indexOf('@')+1);
    }
    else if(!input.equals(null))
    {
        uName=input;
    }

    ClackClient cTest = new ClackClient(uName,hName,pNumber);
    start();
}

public void start() {

    try
    {
        Socket skt = new Socket(hostName, defaultPort);
        outToServer = new ObjectOutputStream(skt.getOutputStream());
        inFromServer = new ObjectInputStream(skt.getInputStream());
        inFromStd = new Scanner(System.in);

        while (!closeConnection)
        {
            readClientData();
            sendData();
            receiveData();
            printData();
        }

        inFromStd.close();
        skt.close();
    }
    catch(IOException ioe)
    {
        System.err.println("IO Exception occurred");
    }

}
private void readClientData()
{
    String nextToken = this.inFromStd.next();

        if (nextToken.equals("DONE"))
        {
            this.closeConnection = true;
            this.dataToSendToServer = new MessageClackData(this.userName, nextToken, key,
                    ClackData.CONSTANT_LOGOUT);
        }
        else if (nextToken.equals("SENDFILE"))
        {
            String filename = this.inFromStd.next();
            this.dataToSendToServer = new FileClackData(this.userName, filename, ClackData.CONSTANT_SENDFILE);
            ((FileClackData) this.dataToSendToServer).readFileContents(key);

        }
        else if (nextToken.equals("LISTUSERS"))
        {
            // Does nothing for now. Eventually, this will return a list of users.
            // For Part 2, do not test LISTUSERS; otherwise, it may generate an error.
        }
        else
        {
            String message = nextToken + this.inFromStd.nextLine();
            this.dataToSendToServer = new MessageClackData(this.userName, message, key,
            ClackData.CONSTANT_SENDMESSAGE);
        }
}

private void sendData()
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
private void receiveData()
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
public void printData(){
    if (dataToRecieveFromServer == null) {return;}
    System.out.println(dataToRecieveFromServer.getDate());
    System.out.println(dataToRecieveFromServer.getType());
    System.out.println(dataToRecieveFromServer.getUsername());
    System.out.println(dataToRecieveFromServer.getData());
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
