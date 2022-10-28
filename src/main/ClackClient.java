package main;

import data.ClackData;
import data.FileClackData;

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
private static final int defaultPort  = 7000;
public ClackClient(String userName, String hostName, int port)
{
    this.userName = userName;
    this.hostName = hostName;
    this.port = port;
    dataToSendToServer = null;
    dataToRecieveFromServer = null;
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
void start(){
    Scanner inFromStd = new Scanner(System.in);
}
void readClientData(){
    switch(inFromStd.nextLine()) {
        case "DONE":
            closeConnection = True;
            break;
        case "SENDFILE":
            FileClackData dataToSendToServer(this.userName, inFromStd.nextLine(), 3);

    }
}
void sendData(){}
void receiveData(){}
void printData(){}
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
