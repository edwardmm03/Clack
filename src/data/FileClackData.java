package data;

import java.util.Objects;
import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class FileClackData extends ClackData
{
    //private variables
    private String fileName;
    private String fileContents = "";

    //Constructor for username, filename, and type
    public FileClackData (String u, String fn, int t)
    {
        super(u,t);
        fileName = fn;
    }

    //Default constructor
    public FileClackData()
    {
        super("Anon", 0);
        fileName = null;
    }

    //sets the file name
    public void setFileName(String fn)
    {
        fileName = fn;
    }

    public void setFileContents(String fc){fileContents = fc;}

    //retruns the file name
    public String getFileName()
    {
        return fileName;
    }

    public String getData()
    {
        return fileContents;
    }

    public void readFileContents()
    {
        //reads from a file and saves the contents to the instance variable fileContents
        try
        {
            File file = new File (this.fileName);
            Scanner sc = new Scanner(file);

            while(sc.hasNext())
            {
                fileContents += sc.nextLine();
            }
        }
        catch(FileNotFoundException fnfe)
        {
            System.err.println("File does not exist");
        }
        catch(InputMismatchException ime)
        {
            System.err.println("Mismatch input type");
        }
        catch(IOException ioe)
        {
            System.err.println("IO Exception occured");
        }

    }

    public void readFileContents(String key)
    {
        //reads from the file and sends encrypted contents to fileContents variable

        String tempC ="";

        try
        {
            File file = new File (this.fileName);
            Scanner sc = new Scanner(file);

            while(sc.hasNext())
            {
                tempC += sc.nextLine();
            }
            fileContents = encrypt(tempC,key);
        }
        catch(FileNotFoundException fnfe)
        {
            System.err.println("File does not exist");
        }
        catch(InputMismatchException ime)
        {
            System.err.println("Mismatch input type");
        }
        catch(IOException ioe)
        {
            System.err.println("IO Exception occured");
        }
    }
    public void writeFileContents()
    {

        File file = new File(this.fileName);
        try
        {
            BufferedWriter f_writer = new BufferedWriter(new FileWriter(file));
            f_writer.write(fileContents);
            f_writer.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.err.println("File does not exist");
        }
        catch(IOException ioe)
        {
            System.err.println("Exception occured");
        }
    }

    public void writeFileContents (String key)
    {
        //decrypt contents of fileContents and write them to the file
        File file = new File(this.fileName);
        try
        {
            BufferedWriter f_writer = new BufferedWriter(new FileWriter(file));

            String fileContentsD = decrypt(fileContents,key);
            f_writer.write(fileContentsD);
            f_writer.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.err.println("File does not exist");
        }
        catch(IOException ioe)
        {
            System.err.println("Exception occured");
        }
    }

    public int hashCode(){
        return 5*(this.fileName.hashCode());
    }

    public boolean equals(Object other) {
        if (this == other) {return true;}
        else {return false;}
    }
    //returns a string describing the object
    public String toString()
    {
        return "This class is a subclass for the data.ClackData class and creates objects" +
                "for files sent in clack. " +
                "File name currently is: " + fileName+ " " +
                "File contents are: " + fileContents+ " " +
                "Username is: " + getUsername() + " " +
                "The type is: " + getType() + " " +
                "The date is: " + getDate();
    }
}
