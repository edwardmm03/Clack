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

public ClackClient(String userName, String hostName, int port)
{
    this.userName = userName;
    this.hostName = hostName;
    this.port = port;
    dataToSendToServer = null;
    dataToRecieveFromServer = null;
    inFromServer = null;
    outToServer = null;
}
public ClackClient(String userName, String hostName)
{
    this(userName, hostName, defaultPort);
}
public ClackClient(String userName)
{
    this(userName, "localhost");
}
public ClackClient()
{
    this("Anon");
}

public static void main(String [] args)
{
    //uses command line arguements(idk what that means)
}

public void start(){

    try
    {
        Socket skt = new Socket(hostName, defaultPort);
        PrintWriter out = new PrintWriter( outToServer);
        inFromStd = new Scanner(System.in);
        Scanner in = new Scanner(inFromServer);

        while (!closeConnection)
        {
            sendData();
            receiveData();
            printData();
        }

        inFromStd.close();
        skt.close();
    }
    catch(Exception e)
    {
        System.err.println(e.getMessage());
    }
}
private void readClientData(){
    System.out.println("Input command");
    String input = inFromStd.next();
    input = input.toUpperCase();
    switch(input) {
        case "DONE":
            closeConnection = true;
            break;
        case "SENDFILE":
            input = inFromStd.next();
            System.out.println(input);
            dataToSendToServer = new FileClackData(this.userName, input, 3);
            try
            {
                String temp = "";
                File file = new File (input);
                Scanner sc = new Scanner(file);

                while(sc.hasNext())
                {
                    temp += sc.nextLine();
                }
            }
            catch(FileNotFoundException fnfe)
            {
                System.err.println("File does not exist");
                dataToSendToServer = null;
            }
            catch(InputMismatchException ime)
            {
                System.err.println("Mismatch input type");
                dataToSendToServer = null;
            }
            catch(IOException ioe)
            {
                System.err.println("IO Exception occured");
                dataToSendToServer = null;
            }
            break;
        case "LISTUSERS":
            break;
        default:
            dataToSendToServer = new MessageClackData(userName, input, 2);
            break;
    }
}
private void sendData()
{
    try
    {
        readClientData();
    }
    catch (Exception e)
    {
        System.err.println(e.getMessage());
    }
}
private void receiveData()
{
    try
    {
       //im confused here
    }
    catch(Exception e)
    {
        System.err.println(e.getMessage());
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
