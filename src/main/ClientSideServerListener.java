package main;

import data.ClackData;

public class ClientSideServerListener implements Runnable{
    private ClackClient client = new ClackClient();
    ClientSideServerListener(ClackClient client){this.client = client;}
    @Override
    public void run(){
        try{
        while (client.getCloseConnection() == false) {

        }
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
}
