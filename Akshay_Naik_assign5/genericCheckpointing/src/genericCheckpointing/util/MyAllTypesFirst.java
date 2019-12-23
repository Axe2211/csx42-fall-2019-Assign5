package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject{
    private int myInt;
    private long myLong;
    private long myOtherLong;
    private String myString;
    private boolean myBool;
    private int myOtherInt;

    //constructor
    public MyAllTypesFirst(){
        setMyInt();
        setMyLong();
        setMyOtherLong();
        setMyString();
        setMyBool();
        setMyOtherInt();
    }

    //set get methods
    public void setMyInt(int myIntIn){
        myInt = myIntIn;
    }
    
    public void setMyInt(){
        myInt = 0;
    }

    public void setMyLong(long myLongIn){
        myLong = myLongIn;
    }

    public void setMyLong(){
        myLong = 0;
    }

    public void setMyOtherLong(long myLongIn){
        myOtherLong = myLongIn;
    }

    public void setMyOtherLong(){
        myOtherLong = 0;
    }

    public void setMyString(String myStringIn){
        myString = myStringIn;
    }

    public void setMyString(){
        myString = null;
    }

    public void setMyBool(boolean myBoolIn){
        myBool = myBoolIn;
    }

    public void setMyBool(){
        myBool = false;
    }

    public void setMyOtherInt(int myOtherIntIn){
        myOtherInt = myOtherIntIn;
    }

    public void setMyOtherInt(){
        myOtherInt = 0;
    }

    public int getMyInt(){
        return myInt;
    }
    
    public long getMyLong(){
        return myLong;
    }

    public long getMyOtherLong(){
        return myOtherLong;
    }

    public String getMyString(){
        return myString;
    }

    public boolean getMyBool(){
        return myBool;
    }

    public int getMyOtherInt(){
        return myOtherInt;
    }
}