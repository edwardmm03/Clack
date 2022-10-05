package test;
import  main.*;
public class TestClackClient
{
    public static void main(String args []){
        //Test clackClient constructors
        ClackClient test1 = new ClackClient("Kell", ":(", -1);
        ClackClient test1c = new ClackClient("Kell", ":(", 1);
        ClackClient test2 = new ClackClient("Megan", ":)");
        ClackClient test3 = new ClackClient("uhhhhummmuhhhhmmmmmm");
        ClackClient test4 = new ClackClient();

        //getUserName
        System.out.print(test1.getUserName());
        System.out.println(test4.getUserName());
        //getHostName
        System.out.println(test2.getHostName());
        System.out.println(test4.getHostName());
        //getPort
        System.out.println(test1.getPort());
        System.out.println(test2.getPort());
        //equals
        System.out.println(test1.equals(test2));
        System.out.println(test1.equals(test1c));
        //hashCode
        System.out.println(test3.hashCode());
        //toString
        System.out.println(test2.toString());
    }
}
