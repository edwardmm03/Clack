package data;

import java.util.Date;
public abstract class ClackData
{
    //private variables
    private String username;
    private int type;
    private Date date;

    //public constants
    final int CONSTANT_LISTUSERS =0;
    final int CONSTANT_LOGOUT =1;
    final int CONSTANT_SENDMESSAGE=2;
    final int CONSTANT_SENDFILE =3;

    //constructor for username and type
    public ClackData(String u, int t)
    {
        username = u;
        type =t;
        this.date = new Date();
    }

    //Constructor for type
    public ClackData(int t)
    {
        this("Anon", t);
    }

    //Default Constructor
    public ClackData()
    {
        this("Anon",0);
    }

    //returns type
    public int getType()
    {
        return type;
    }

    //returns username
    public String getUsername()
    {
        return username;
    }

    //returns date
    public Date getDate()
    {
        return date;
    }

    public abstract String getData();
}