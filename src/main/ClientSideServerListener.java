package main;

import data.ClackData;

public class ClientSideServerListener implements Runnable{
    private ClackClient client = new ClackClient();
    ClientSideServerListener(ClackClient client){this.client = client;}
    @Override
    public void run()
    {
        System.out.println("sad");
        try{
            while (!client.getCloseConnection())
            {
                System.out.println("in da while");
                client.receiveData();
                System.out.println("received");
                client.printData();
            }
        }
        catch(Exception e)
        {
            System.err.println("Exception occurred");
        }
    }

}
