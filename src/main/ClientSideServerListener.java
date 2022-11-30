package main;

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
}
