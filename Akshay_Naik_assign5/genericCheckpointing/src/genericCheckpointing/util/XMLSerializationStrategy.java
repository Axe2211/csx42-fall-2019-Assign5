package genericCheckpointing.util;

import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.lang.reflect.Method;

import genericCheckpointing.util.SerStrategy;

public class XMLSerializationStrategy implements SerStrategy{

    private List<String> serializedOutput;

    public XMLSerializationStrategy(){
        serializedOutput = new ArrayList<>();
    }

    public void addToOutput(String inputString){
        serializedOutput.add(inputString);
    }

    public void processInput(SerializableObject sObject){
        // if(sObject instanceof MyAllTypesSecond){
        //     processMySecond((MyAllTypesSecond)sObject);
        // }
        // else if(sObject instanceof MyAllTypesFirst){
        //     processMyFirst((MyAllTypesFirst)sObject);
        // }
        // else if(sObject instanceof MySpecialTypes){
        //     processMySpecial((MySpecialTypes)sObject);
        // }
        processMySpecial(sObject);
    }

    private void processMySpecial(SerializableObject sObject){
        List<Field> objectFields = null;
        Iterator<Field> fieldIterator;
        Class<?> sObjectClass;
        Method method;
        Object retObj;
        Field field;
        String methodName, className;
        String startTag, typeLabel = null, value, endTag;        

        try{
            addToOutput("<DPSerialization>");
            sObjectClass = sObject.getClass();
            addToOutput(" <complexType xsi:type=\"" + sObjectClass.getName() + "\">");
            objectFields = Arrays.asList(sObjectClass.getDeclaredFields());
            fieldIterator = objectFields.listIterator();
            while(fieldIterator.hasNext()){
                field = (Field) fieldIterator.next();
                methodName = new String("get" + toInitCap(field.getName()));
                method = sObject.getClass().getMethod(methodName);
                retObj = method.invoke(sObject);
                value = retObj.toString();
                if(retObj instanceof Integer){
                    typeLabel = new String("  xsi:type=\"xsd:int\">" + value);
                }
                if(retObj instanceof Short){
                    typeLabel = new String("  xsi:type=\"xsd:short\">" + value);
                }
                if(retObj instanceof Long){
                    typeLabel = new String("  xsi:type=\"xsd:long\">" + value);
                }
                if(retObj instanceof Float){
                    typeLabel = new String("  xsi:type=\"xsd:float\">" + value);
                }
                if(retObj instanceof Double){
                    typeLabel = new String("  xsi:type=\"xsd:double\">" + value);
                }
                if(retObj instanceof String){
                    typeLabel = new String("  xsi:type=\"xsd:string\">" + value);
                }
                if(retObj instanceof Character){
                    typeLabel = new String("  xsi:type=\"xsd:Character\">" + value);
                }
                if(retObj instanceof Boolean){
                    typeLabel = new String("  xsi:type=\"xsd:boolean\">" + value);
                }
                
                startTag = new String("  <" + field.getName() + typeLabel);
                endTag = new String("</" + field.getName() + ">");
                System.out.println(startTag + endTag);
                addToOutput(startTag + endTag);
            }
            addToOutput(" </complexType>");
            addToOutput("</DPSerialization>");
            
        }
        catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException ex){
            System.out.println("Encountered Error: " + ex + " in XMLSerialization.processMySpecial method");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    // private void processMySecond(MyAllTypesSecond sObject){
    //     List<Field> objectFields = null;
    //     Iterator<Field> fieldIterator;
    //     Method method;
    //     Object retObj;
    //     Field field;
    //     String methodName, className;
    //     String startTag, typeLabel = null, value, endTag;        

    //     try{
    //         addToOutput("<DPSerialization>");
    //         className = sObject.getClass().toString();
    //         addToOutput(" <complexType xsi:type=\"" + className + "\">");
    //         objectFields = Arrays.asList(sObject.getClass().getDeclaredFields());
    //         fieldIterator = objectFields.listIterator();
    //         while(fieldIterator.hasNext()){
    //             field = (Field) fieldIterator.next();
    //             methodName = new String("get" + toInitCap(field.getName()));
    //             method = sObject.getClass().getMethod(methodName);
    //             retObj = method.invoke(sObject);
    //             value = retObj.toString();
    //             if(retObj instanceof Integer){
    //                 typeLabel = new String("  xsi:type=\"xsd:int\">" + value);
    //             }
    //             if(retObj instanceof Short){
    //                 typeLabel = new String("  xsi:type=\"xsd:short\">" + value);
    //             }
    //             if(retObj instanceof Long){
    //                 typeLabel = new String("  xsi:type=\"xsd:long\">" + value);
    //             }
    //             if(retObj instanceof Float){
    //                 typeLabel = new String("  xsi:type=\"xsd:float\">" + value);
    //             }
    //             if(retObj instanceof Double){
    //                 typeLabel = new String("  xsi:type=\"xsd:double\">" + value);
    //             }
    //             if(retObj instanceof String){
    //                 typeLabel = new String("  xsi:type=\"xsd:string\">" + value);
    //             }
    //             if(retObj instanceof Character){
    //                 typeLabel = new String("  xsi:type=\"xsd:Character\">" + value);
    //             }
    //             if(retObj instanceof Boolean){
    //                 typeLabel = new String("  xsi:type=\"xsd:boolean\">" + value);
    //             }
                
    //             startTag = new String("  <" + field.getName() + typeLabel);
    //             endTag = new String("</" + field.getName() + ">");
    //             System.out.println(startTag + endTag);
    //             addToOutput(startTag + endTag);
    //         }
    //         addToOutput(" </complexType>");
    //         addToOutput("</DPSerialization>");
            
    //     }
    //     catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException ex){
    //         System.out.println("Encountered Error: " + ex + " in XMLSerialization.processMySecond method");
    //         ex.printStackTrace();
    //         System.exit(1);
    //     }
    // }

    // public void processMyFirst(MyAllTypesFirst sObject){
    //     List<Field> objectFields = null;
    //     Iterator<Field> fieldIterator;
    //     Method method;
    //     Object retObj;
    //     Field field;
    //     String methodName, className;
    //     String startTag, typeLabel = null, value, endTag;        

    //     try{
    //         addToOutput("<DPSerialization>");
    //         className = sObject.getClass().toString();
    //         addToOutput(" <complexType xsi:type=\"" + className + "\">");
    //         objectFields = Arrays.asList(sObject.getClass().getDeclaredFields());
    //         fieldIterator = objectFields.listIterator();
    //         while(fieldIterator.hasNext()){
    //             field = (Field) fieldIterator.next();
    //             methodName = new String("get" + toInitCap(field.getName()));
    //             method = sObject.getClass().getMethod(methodName);
    //             retObj = method.invoke(sObject);
    //             value = retObj.toString();
    //             if(retObj instanceof Integer){
    //                 typeLabel = new String("  xsi:type=\"xsd:int\">" + value);
    //             }
    //             if(retObj instanceof Short){
    //                 typeLabel = new String("  xsi:type=\"xsd:short\">" + value);
    //             }
    //             if(retObj instanceof Long){
    //                 typeLabel = new String("  xsi:type=\"xsd:long\">" + value);
    //             }
    //             if(retObj instanceof Float){
    //                 typeLabel = new String("  xsi:type=\"xsd:float\">" + value);
    //             }
    //             if(retObj instanceof Double){
    //                 typeLabel = new String("  xsi:type=\"xsd:double\">" + value);
    //             }
    //             if(retObj instanceof String){
    //                 typeLabel = new String("  xsi:type=\"xsd:string\">" + value);
    //             }
    //             if(retObj instanceof Character){
    //                 typeLabel = new String("  xsi:type=\"xsd:Character\">" + value);
    //             }
    //             if(retObj instanceof Boolean){
    //                 typeLabel = new String("  xsi:type=\"xsd:boolean\">" + value);
    //             }
                
    //             startTag = new String("  <" + field.getName() + typeLabel);
    //             endTag = new String("</" + field.getName() + ">");
    //             System.out.println(startTag + endTag);
    //             addToOutput(startTag + endTag);
    //         }
    //         addToOutput(" </complexType>");
    //         addToOutput("</DPSerialization>");
            
    //     }
    //     catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException ex){
    //         System.out.println("Encountered Error: " + ex + " in XMLSerialization.processMyFirst method");
    //         ex.printStackTrace();
    //         System.exit(1);
    //     }
    // }

    public Iterator<String> serialOutputItr(){
        return serializedOutput.listIterator();
    }

    private String toInitCap(String stringIn) {
        String result = "";
        try{
            if (stringIn != null && stringIn.length() > 0) {
                char[] charArray = stringIn.toCharArray();
                charArray[0] = Character.toUpperCase(charArray[0]);
                // set capital letter to first position
                result = new String(charArray);
                // return desired output
            } 
        }
        catch(Exception ex){
            System.out.println("Encountered Error: " + ex + " in XMLSerialization.toInitCap method");
            ex.printStackTrace();
            System.exit(1);
        }
        return result;
    }
}