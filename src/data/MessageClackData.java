package data;

import java.util.Objects;

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

    public MessageClackData(String u, String m, String k, int t)
    {
        super(u,t);
        message= encrypt(m,k);
    }

    public String getData()
    {
        return message;
    }

    //overrides the hashcode
    public int hashCode()
    {
        return Objects.hash(message) ;
    }

    //overrides the equals method
    public boolean equals (MessageClackData m)
    {
        return this.message == m.message;
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
