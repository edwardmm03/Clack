package data;

public class MessageClackData extends ClackData
{
    //private variables
    private String message;

    //Constructor for username, message, and type
    public MessageClackData(String u, String m, int t)
    {
        super(u,t);
        message = m;
    }

    //Default Constructor
    public MessageClackData()
    {
        this("Anon", " ", 0);
    }

    public String getData()
    {
        return message;
    }

    //overrides the hashcode
    public int hashCode()
    {
        return 0x100;
    }

    //overrides the equals method
    public boolean equals (String m1, String m2)
    {
        return m1 == m2;
    }

    //returns a string describing the object
    public String toString()
    {
        return "This class is a subclass for the data.ClackData class and creates objects" +
                "for messages sent in clack. " +
                "Message currently is: " + message + " " +
                "Username is: " + getUsername() + " " +
                "The type is: " + getType() + " " +
                "The date is: " + getDate();
    }

}
