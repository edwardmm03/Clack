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
    }

    //overrides hash code
    public int hashCode()
    {
        return Objects.hash(fileName) + Objects.hash(fileContents);
    }

    //overrides equals
    public boolean equals (FileClackData f)
    {
        return (this.fileName == f.fileName) && (this.fileContents == f.fileContents);
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
