package test;
import main.*;
public class TestClackServer
{
    public static void main(String args []){
        //Test clackServer constructors
        ClackServer test1 = new ClackServer(20);
        ClackServer test1c = new ClackServer(20);
        ClackServer test2 = new ClackServer();

        //getPort
        test1.getPort();
        test2.getPort();
        //equals
        test1.equals(test2);
        test1.equals(test1c);
        //hashCode
        test2.hashCode();
        //toString
        test1.toString();
    }
}
