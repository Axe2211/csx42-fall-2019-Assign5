package genericCheckpointing.util;

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.lang.Exception;

public class FileProcessor {

    private File activeXMLFile;
    private Scanner inputFileHandler;
    private PrintWriter outputFileHandler;
    
    //constructor
    public FileProcessor(){}

    public void setActiveXMLFile(String inXMLFileNameIn){
        this.activeXMLFile = new File(inXMLFileNameIn);
    }

    public void setInputFileHandler(String inXMLFileNameIn){
        File inputFile;
        try{
            if(inXMLFileNameIn.equals("")){
                throw new IOException("Empty File Name passed..");
            }
            
            setActiveXMLFile(inXMLFileNameIn);
            inputFile = getActiveXMLFile();

            if(inputFile.length() == 0){
                throw new IOException("File is empty or file does not exist..");
            }
            this.inputFileHandler = new Scanner(inputFile);
        }
        catch(IOException ex){
            System.out.println("File Not Found Exception in setInputFileHandler method");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public void setOutputFileHandler(String outXMLFileNameIn){
        File outputFile;
        try{
            if(outXMLFileNameIn.equals("")){
                throw new IOException("Empty File Name passed..");
            }
            
            setActiveXMLFile(outXMLFileNameIn);
            outputFile = getActiveXMLFile();

            this.outputFileHandler = new PrintWriter(outputFile);
        }
        catch(IOException ex){
            System.out.println("File Not Found Exception in setInputFileHandler method");
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public File getActiveXMLFile(){
        return activeXMLFile;
    }

    public String readLine(){
        String currentLine = null;

        try{
            if(inputFileHandler.hasNextLine()){
                currentLine = inputFileHandler.nextLine();
            }
        }
        catch(Exception ex){
            System.out.println("Error: Unable to read next line due to: " + ex + " error");
            ex.printStackTrace();
            System.exit(0);
        }
        if(currentLine == null){
            currentLine = new String("EOF");
        }
        return currentLine;
    }

    public void writeFile(String output){
        outputFileHandler.println(output);
    }

    public void closeOutFile(){
        outputFileHandler.close();
    }

    public void closeInFile(){
        inputFileHandler.close();
    }	
	
}
