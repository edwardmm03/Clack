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
        ClackData message4 = new MessageClackData("Megan", "BRAVENEWWORLD", "TIME",0);

        System.out.println("Message 1 says: " + message1.getData());
        System.out.println(message1.getType());

        System.out.println(message2);
        System.out.println(message2.getDate());

        System.out.println(message1.equals(message2));

        System.out.println(message3);
        System.out.println(message3.hashCode());

        System.out.println(message4.getData());

        //testing data.FileClackData
        FileClackData file1 = new FileClackData("Megan", "Hw1", 0);
        FileClackData file2 = new FileClackData();
        FileClackData file3 = new FileClackData();
        FileClackData file4 = new FileClackData("Megan", "testFile",0);
        FileClackData file5 = new FileClackData("Kell","testingSecureFile",0);

        file2.setFileName("List of Favorite Books");
        System.out.println(file2);

        file1.getFileName();
        System.out.println("The first file's name is: " + file1.getFileName());

        System.out.println(file2.equals(file3));
        System.out.println(file1.hashCode());
        System.out.print(file2.hashCode());

        System.out.println(file1.getType());
        System.out.println(file1.getDate());

        //testing read and write on unsecure files
        file4.readFileContents();
        System.out.println(file4.getData());
        file4.setFileContents("Testing Write");
        file4.writeFileContents();
        System.out.println(file4.getData());

        file1.readFileContents();
        file1.getData();
        file1.setFileContents("Trying something new");
        file1.writeFileContents();
        System.out.println(file1.getData());


        file5.readFileContents("time");
        System.out.println(file5.getData());
        file5.writeFileContents("time");
    }
}
