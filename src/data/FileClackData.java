package data;

import java.util.Objects;

public class FileClackData extends ClackData
{
    //private variables
    private String fileName;
    private String fileContents;

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
        //currently does not return anything
    }

    public void writeFileContents()
    {
        //currently does not return anything
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
