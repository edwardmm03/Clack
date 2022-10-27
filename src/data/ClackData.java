package data;

import java.util.Date;

public abstract class ClackData
{
    //private variables
    private String username;
    private int type;
    private Date date;

    //public constants
    final static int CONSTANT_LISTUSERS =0;
    final static int CONSTANT_LOGOUT =1;
    final static int CONSTANT_SENDMESSAGE=2;
    final static int CONSTANT_SENDFILE =3;

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

    public String encrypt(String inputStringToEncrypt, String key)
    {
        inputStringToEncrypt.toUpperCase();
        key.toUpperCase();
        String encrypted = " ";
        String repeatedKey = key;
        char [] alphabet= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        int i =0;
        while(key.length() < inputStringToEncrypt.length())
        {
            if(i >= key.length())
            {
                i=0;
            }
            repeatedKey += key.charAt(i);
            i ++;
        }

        for(int x =0; x < inputStringToEncrypt.length(); x++)
        {
            char nextLetter;
            int newSpot;

            newSpot = findIndex(alphabet,inputStringToEncrypt.charAt(x)) + findIndex(alphabet,repeatedKey.charAt(x));
            if(newSpot > 25)
            {
                int temp = newSpot-25;
                newSpot = temp -1;
            }

            nextLetter = alphabet[newSpot];
            encrypted += nextLetter;
        }

        return encrypted;
    }

    private int findIndex(char arr[], char n)
    {
        int y =0;
        while(y < arr.length)
        {
            if(arr[y] == n)
            {
                return y;
            }
            else
            {
                y++;
            }
        }

        //if char not found in the array
        return -1;
    }
}
