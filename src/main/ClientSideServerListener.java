package main;

import data.ClackData;

public class ClientSideServerListener implements Runnable{
    private ClackClient client = new ClackClient();
    ClientSideServerListener(ClackClient client){this.client = client;}
    @Override
    public void run()
    {
        try{
            while (!client.getCloseConnection())
            {
                client.receiveData();
                client.printData();
            }
        }
        catch(Exception e)
        {
            System.err.println("Exception occurred");
        }
    }

}
