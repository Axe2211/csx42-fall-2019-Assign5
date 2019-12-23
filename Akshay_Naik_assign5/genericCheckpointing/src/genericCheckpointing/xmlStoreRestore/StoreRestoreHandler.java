package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

// import genericCheckpointing.server.RestoreI;
// import genericCheckpointing.server.StoreI;
import genericCheckpointing.util.FileProcessor;
// import genericCheckpointing.util.MyAllTypesFirst;
// import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.XMLValueStore;
import genericCheckpointing.util.XMLSerializationStrategy;
import genericCheckpointing.util.SerStrategy;

public class StoreRestoreHandler implements InvocationHandler{

    FileProcessor nativeFileProcessor;

    public StoreRestoreHandler(){
        nativeFileProcessor = new FileProcessor();
    }

    public Object invoke(Object callObject, Method callMethod, Object[] callArgs) throws Throwable{
        Object result = null;
        try{
            String methodName = callMethod.getName();
            if(methodName.equals("readObj")){
                result = (Object) readObj((String) callArgs[0]);
            }
            if(methodName.equals("writeObj")){
                String mode = (String) callArgs[1];
                if(mode.equals("XML")){
                    serializeData((SerializableObject)callArgs[0], new XMLSerializationStrategy());
                }
            }      
        }
        catch(Exception ex){
            System.out.println(ex);
            ex.printStackTrace();
            System.exit(1);
        }

        return result;
    }

    private String toInitCap(String stringIn) {
        if (stringIn != null && stringIn.length() > 0) {
            char[] charArray = stringIn.toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            // set capital letter to first position
            return new String(charArray);
            // return desired output
        } else {
            return "";
        }
    }

    public SerializableObject readObj(String input){
        Object outputObj = null;
        XMLValueStore currentObjData = null;
        Iterator<NameTypeValue> fieldItr;
        NameTypeValue field;
        Class<?> targetClass = null;
        Class<?>[] signature = null;
        Method targetMethod = null;
        String methodName = null;
        Object[] methodParam;
        Object methodOutput;

        try{
            currentObjData = inputXMLParser();
            if(currentObjData != null){
                targetClass = Class.forName(currentObjData.getClassName());
                outputObj = targetClass.newInstance();
                signature = new Class<?>[1];
                methodParam = new Object[1];
                fieldItr = currentObjData.getFieldIterator();
                while(fieldItr.hasNext()){
                    field = (NameTypeValue) fieldItr.next();
                    //getting method name
                    methodName = new String("set" + toInitCap(field.getFieldName()));

                    //preparing the parameter for the set method
                    if(field.getFieldType().equals("int")){
                        methodParam[0] = Integer.parseInt(field.getFieldValue());
                        signature[0] = int.class;
                    }
                    else if(field.getFieldType().equals("short")){
                        methodParam[0] = Short.parseShort(field.getFieldValue());
                        signature[0] = short.class;
                    }
                    else if(field.getFieldType().equals("long")){
                        methodParam[0] = Long.parseLong(field.getFieldValue());
                        signature[0] = long.class;
                    }
                    else if(field.getFieldType().equals("double")){
                        methodParam[0] = Double.parseDouble(field.getFieldValue());
                        signature[0] = double.class;
                    }
                    else if(field.getFieldType().equals("float")){
                        methodParam[0] = Float.parseFloat(field.getFieldValue());
                        signature[0] = float.class;
                    }
                    else if(field.getFieldType().equals("string")){
                        methodParam[0] = field.getFieldValue();
                        signature[0] = String.class;
                    }
                    else if(field.getFieldType().equals("char")){
                        methodParam[0] = field.getFieldValue().charAt(0);
                        signature[0] = char.class;
                    }
                    else if(field.getFieldType().equals("boolean")){
                        methodParam[0] = Boolean.parseBoolean(field.getFieldValue());
                        signature[0] = boolean.class;
                    }

                    //fetching targetMethod on the basis of methodName and signature
                    targetMethod = targetClass.getMethod(methodName, signature);

                    methodOutput = targetMethod.invoke(outputObj, methodParam);
                }
            } 
        }
        catch(ClassNotFoundException 
                | InstantiationException 
                | IllegalAccessException 
                | NoSuchMethodException 
                | InvocationTargetException ex){
            System.out.println("Encountered Error: " + ex + " in StoreRestoreHandler.readObj method..");
            ex.printStackTrace();
            System.exit(1);
        }
        
        return (SerializableObject) outputObj;
    }

    private FileProcessor getFileProcessor(){
        return nativeFileProcessor;
    }

    public void openReadFile(String fileNameIn){
        try{
            getFileProcessor().setInputFileHandler(fileNameIn);
        }
        catch(Exception ex){
            System.out.println("Encountered Error: " + ex + " in StoreRestoreHandler.openReadFile method");
        }
    }

    public void closeReadFile(){
        try{
            getFileProcessor().closeInFile();
        }
        catch(Exception ex){
            System.out.println("Encountered Error: " + ex + " in StoreRestoreHandler.closeReadFile method");
        }
    }

    public void openWriteFile(String fileNameIn){
        try{
            getFileProcessor().setOutputFileHandler(fileNameIn);
        }
        catch(Exception ex){
            System.out.println("Encountered Error: " + ex + " in StoreRestoreHandler.openFile method");
        }
    }

    public void closeWriteFile(){
        try{
            getFileProcessor().closeOutFile();
        }
        catch(Exception ex){
            System.out.println("Encountered Error: " + ex + " in StoreRestoreHandler.closeReadFile method");
        }
    }

    private XMLValueStore inputXMLParser(){
        XMLValueStore obj = null;

        try{
            if(getFileProcessor().readLine().equals("<DPSerialization>")){
                obj = readObjectSpecs();
            }
        }
        catch(Exception ex){
            System.out.println("Encountered Error: " + ex + " in StoreRestoreHandler.inputXMLParser method");
            ex.printStackTrace();
        }

        return obj; 
    }

    private XMLValueStore readObjectSpecs(){

        XMLValueStore newObj = new XMLValueStore();
        String currentLine, fieldName, type, value;
        
        try{
            newObj.setClassName(readClassName(getFileProcessor().readLine()));
            currentLine = getFileProcessor().readLine();

            while(!currentLine.contains("</complexType>")){
                if(currentLine.matches("\\s+<[a-zA-Z0-9]+\\sxsi:type=\"xsd:[a-zA-Z]+\">[a-zA-Z0-9.\\s-]+</[a-zA-Z0-9]+>")){
                    fieldName = readFieldName(currentLine);
                    type = readFieldType(currentLine);
                    value = readFieldValue(currentLine);
                    newObj.addField(fieldName, type, value);
                    currentLine = getFileProcessor().readLine();
                }
                else{
                    throw new Exception("Incorrect Format on complex type tag");
                }
            }
            //reading the end tag of DPSerialization
            getFileProcessor().readLine();
        }
        catch(Exception ex){
            System.out.println("Encountered Error: " + ex + " in StoreRestoreHandler.readObjectSpecs method");
            ex.printStackTrace();
            System.exit(1);
        }

        return newObj;
    }

    private String readClassName(String typeTagIn){
        String className = null;

        try{
            if(typeTagIn.matches("\\s+<complexType xsi:type=\"[a-zA-Z0-9.]+\">")){
                int startIndex = typeTagIn.indexOf("\"") + 1;
                int endIndex = typeTagIn.indexOf("\"", startIndex);
                className = typeTagIn.substring(startIndex, endIndex);
            }
            else{
                throw new Exception("Incorrect Format error");
            }
        }
        catch(Exception ex){
            System.out.println("Encountered Error: " + ex + " in StoreRestoreHandler.readClassName method");
        }
        return className;
    }

    public String readFieldName(String tagIn){
        
        int startIndex, endIndex;
        String fieldName = null;

        try{
            startIndex = tagIn.indexOf("<") + 1;
            endIndex = tagIn.indexOf(" xsi:type");
            fieldName = tagIn.substring(startIndex, endIndex);
        }
        catch(Exception ex){
            System.out.println("Encountered Error: " + ex + " in StoreRestoreHandler.readFieldName method");
        }

        return fieldName;
    }

    private String readFieldType(String tagIn){
        
        int startIndex, endIndex;
        String fieldType = null;

        try{
            startIndex = tagIn.indexOf("\"") + 1;
            endIndex = tagIn.indexOf("\"", startIndex);
            fieldType = tagIn.substring(startIndex, endIndex).replace("xsd:", "");
        }
        catch(IndexOutOfBoundsException ex){
            System.out.println("Encountered Error: " + ex + " in StoreRestoreHandler.readFieldType method");
        }

        return fieldType;
    }

    private String readFieldValue(String tagIn){
        
        int startIndex, endIndex;
        String fieldValue = null;

        try{
            startIndex = tagIn.indexOf(">") + 1;
            endIndex = tagIn.indexOf("<", startIndex);
            fieldValue = tagIn.substring(startIndex, endIndex);
        }
        catch(IndexOutOfBoundsException ex){
            System.out.println("Encountered Error: " + ex + " in StoreRestoreHandler.readFieldValue method");
        }

        return fieldValue;
    }

    public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
        Iterator<String> serOutItr;
        String line;

        try{
            sStrategy.processInput(sObject);
            serOutItr = sStrategy.serialOutputItr();
            while(serOutItr.hasNext()){
                line = (String) serOutItr.next();
                getFileProcessor().writeFile(line);
            }
        }
        catch(Exception ex){
            System.out.println("Encountered Error: " + ex + " in StoreRestoreHandler.serializeData method");
            ex.printStackTrace();
            System.exit(1);
        }
    }

}