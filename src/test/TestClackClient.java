package test;
import  main.*;
public class TestClackClient
{
    public static void main(String args []){
        //Test clackClient constructors
        ClackClient test1 = new ClackClient("Kell", ":(", 1);
        ClackClient test1c = new ClackClient("Kell", ":(", 1);
        ClackClient test2 = new ClackClient("Megan", ":)");
        ClackClient test3 = new ClackClient("uhhhhummmuhhhhmmmmmm");
        ClackClient test4 = new ClackClient();

        //getUserName
        test1.getUserName();
        test4.getUserName();
        //getHostName
        test2.getHostName();
        test4.getHostName();
        //getPort
        test1.getPort();
        test2.getPort();
        //equals
        test1.equals(test2);
        test1.equals(test1c);
        //hashCode
        test3.hashCode();
        //toString
        test2.toString();
    }
}
