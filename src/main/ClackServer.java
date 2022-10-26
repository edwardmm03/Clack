package main;

import data.ClackData;

import java.util.Objects;

public class ClackServer {
    private int port;
    private boolean closeConnection;
    private ClackData dataToRecieveFromClient;
    private ClackData dataToSendToClient;
    private static final int defaultPort = 7000;

    public ClackServer(int port)
    {
        this.port = port;
        this.dataToRecieveFromClient = null;
        this.dataToSendToClient = null;
    }
    public ClackServer()
    {
        this(defaultPort);
    }
    void start(){}
    void recieveData(){}
    void sendData(){}
    public int getPort() {
        return port;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClackServer that = (ClackServer) o;
        return port == that.port && closeConnection == that.closeConnection && Objects.equals(dataToRecieveFromClient, that.dataToRecieveFromClient) && Objects.equals(dataToSendToClient, that.dataToSendToClient);
    }
    @Override
    public int hashCode() {
        return Objects.hash(port, closeConnection, dataToRecieveFromClient, dataToSendToClient);
    }
    @Override
    public String toString() {
        return "main.ClackServer{" +
                "port=" + port +
                ", closeConnection=" + closeConnection +
                ", dataToRecieveFromClient=" + dataToRecieveFromClient +
                ", dataToSendToClient=" + dataToSendToClient +
                '}';
    }
}