package genericCheckpointing.util;

import genericCheckpointing.util.SerializableObject;

public class MySpecialTypes extends SerializableObject{

    private int myInt1;
    private int myInt2;
    private String myString1;
    private String myString2;
    private double myDoubleT1;
    private double myDoubleT2;

    //constructor
    public MySpecialTypes(){
        setMyInt1();
        setMyInt2();
        setMyString1();
        setMyString2();
        setMyDoubleT1();
        setMyDoubleT2();
    }

    public void setMyInt1(){
        myInt1 = 0;
    }

    public void setMyInt1(int myInt1In){
        myInt1 = myInt1In;
    }

    public void setMyInt2(){
        myInt2 = 0;
    }

    public void setMyInt2(int myInt2In){
        myInt2 = myInt2In;
    }

    public void setMyString1(){
        myString1 = new String("(none)");
    }

    public void setMyString1(String myString1In){
        myString1 = myString1In;
    }

    public void setMyString2(){
        myString2 = new String("(none2)");
    }

    public void setMyString2(String myString2In){
        myString2 = myString2In;
    }

    public void setMyDoubleT1(){
        myDoubleT1 = 0;
    }

    public void setMyDoubleT1(double myDouble1In){
        myDoubleT1 = myDouble1In;
    }

    public void setMyDoubleT2(){
        myDoubleT2 = 0;
    }
    
    public void setMyDoubleT2(double myDouble2In){
        myDoubleT2 = myDouble2In;
    }

    public int getMyInt1(){
        return myInt1;
    }

    public int getMyInt2(){
        return myInt2;
    }

    public String getMyString1(){
        return myString1;
    }

    public String getMyString2(){
        return myString2;
    }

    public double getMyDoubleT1(){
        return myDoubleT1;
    }

    public double getMyDoubleT2(){
        return myDoubleT2;
    }

}