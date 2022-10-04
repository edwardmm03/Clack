package test;
import data.*;

public class TestClackData
{
    public static void main (String args [])
    {
        //testing data.MessageClackData class methods
        ClackData message1 = new MessageClackData("Megan","Hi", 0);
        ClackData message2 = new MessageClackData();
        ClackData message3 = new MessageClackData("Kell", ":)", -1);

        System.out.println("Message 1 says: " + message1.getData());
        System.out.println(message2);

        System.out.println(message1==message2);

        System.out.println(message3);

        System.out.println(message3.hashCode());

        //testing data.FileClackData
        FileClackData file1 = new FileClackData("Megan", "Hw1", 0);
        FileClackData file2 = new FileClackData();
        FileClackData file3 = new FileClackData();

        file2.setFileName("List of Favorite Books");
        System.out.println(file2);

        file1.getFileName();
        System.out.println("The first file's name is: " + file1.getFileName());

        System.out.println(file2 == file3);
        System.out.println(file1.hashCode());
        System.out.print(file2.hashCode());
    }
}
