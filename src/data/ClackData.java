package data;

import java.io.Serializable;
import java.util.Date;

public abstract class ClackData implements Serializable
{
    //private variables
    private String username;
    private int type;
    private Date date;
    private char [] alphabet= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

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

    protected String encrypt(String inputStringToEncrypt, String key) {
        //corrected from solution
        if (inputStringToEncrypt == null) {
            return null;
        }

        final int keyLen = key.length();
        int keyIndex = 0;
        StringBuilder stringEncrypted = new StringBuilder();

        for (int i = 0; i < inputStringToEncrypt.length(); i++) {
            char inputCharToEncrypt = inputStringToEncrypt.charAt(i);
            char inputCharEncrypted;

            if (Character.isLowerCase(inputCharToEncrypt)) {
                char keyChar = Character.toLowerCase(key.charAt(keyIndex));
                inputCharEncrypted = (char) (((inputCharToEncrypt - 'a') + (keyChar - 'a')) % 26 + 'a');
                keyIndex = (keyIndex + 1) % keyLen;

            } else if (Character.isUpperCase(inputCharToEncrypt)) {
                char keyChar = Character.toUpperCase(key.charAt(keyIndex));
                inputCharEncrypted = (char) (((inputCharToEncrypt - 'A') + (keyChar - 'A')) % 26 + 'A');
                keyIndex = (keyIndex + 1) % keyLen;

            } else {
                inputCharEncrypted = inputCharToEncrypt;
            }

            stringEncrypted.append(inputCharEncrypted);
        }

        return stringEncrypted.toString();
    }

    protected String decrypt(String inputStringToDecrypt, String key) {
        if (inputStringToDecrypt == null) {
            return null;
        }

        final int keyLen = key.length();
        int keyIndex = 0;
        StringBuilder stringDecrypted = new StringBuilder();

        for (int i = 0; i < inputStringToDecrypt.length(); i++) {
            char inputCharToDecrypt = inputStringToDecrypt.charAt(i);
            char inputCharDecrypted;

            if (Character.isLowerCase(inputCharToDecrypt)) {
                char keyChar = Character.toLowerCase(key.charAt(keyIndex));
                inputCharDecrypted = (char) ((inputCharToDecrypt - keyChar + 26) % 26 + 'a');
                keyIndex = (keyIndex + 1) % keyLen;

            } else if (Character.isUpperCase(inputCharToDecrypt)) {
                char keyChar = Character.toUpperCase(key.charAt(keyIndex));
                inputCharDecrypted = (char) ((inputCharToDecrypt - keyChar + 26) % 26 + 'A');
                keyIndex = (keyIndex + 1) % keyLen;

            } else {
                inputCharDecrypted = inputCharToDecrypt;
            }

            stringDecrypted.append(inputCharDecrypted);
        }

        return stringDecrypted.toString();
    }
}
