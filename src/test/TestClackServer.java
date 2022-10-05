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
        System.out.println(test1.getPort());
        System.out.println(test2.getPort());
        //equals
        System.out.println(test1.equals(test2));
        System.out.println(test1.equals(test1c));
        //hashCode
        System.out.println(test2.hashCode());
        //toString
        System.out.print(test1.toString());
    }
}
