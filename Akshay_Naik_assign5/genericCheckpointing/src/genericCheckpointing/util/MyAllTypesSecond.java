package genericCheckpointing.util;

import genericCheckpointing.util.MyAllTypesFirst;

public class MyAllTypesSecond extends MyAllTypesFirst{
    private double myDoubleT;
    private double myOtherDoubleT;
    private float myFloatT;
    private short myShortT;
    private char myCharT;

    //constructor
    public MyAllTypesSecond(){
        setMyInt();
        setMyLong();
        setMyString();
        setMyBool();
        setMyOtherInt();
        setMyDoubleT();
        setMyOtherDoubleT();
        setMyFloatT();
        setMyShortT();
        setMyCharT();
    }

    //set get methods
    public void setMyDoubleT(double myDoubleTIn){
        myDoubleT = myDoubleTIn;
    }

    public void setMyDoubleT(){
        myDoubleT = 0;
    }
    
    public void setMyOtherDoubleT(double myDoubleTIn){
        myOtherDoubleT = myDoubleTIn;
    }

    public void setMyOtherDoubleT(){
        myOtherDoubleT = 0;
    }

    public void setMyFloatT(float myFloatTIn){
        myFloatT = myFloatTIn;
    }

    public void setMyFloatT(){
        myFloatT = 0;
    }

    public void setMyShortT(short myShortTIn){
        myShortT = myShortTIn;
    }

    public void setMyShortT(){
        myShortT = 0;
    }

    public void setMyCharT(char myCharTIn){
        myCharT = myCharTIn;
    }

    public void setMyCharT(){
        myCharT = '\0';
    }

    public double getMyDoubleT(){
        return myDoubleT;
    }
    
    public double getMyOtherDoubleT(){
        return myOtherDoubleT;
    }

    public float getMyFloatT(){
        return myFloatT;
    }

    public short getMyShortT(){
        return myShortT;
    }

    public char getMyCharT(){
        return myCharT;
    }
}