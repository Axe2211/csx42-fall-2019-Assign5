package genericCheckpointing.driver;


import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.MySpecialTypes;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.server.StoreI;

import java.util.List;
import java.lang.reflect.Field;
import java.util.ArrayList;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.util.ProxyCreator;


/**
 * @author Akshay Naik
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 4 || args[0].equals("${arg0}") || args[1].equals("${arg1}") 
						|| args[2].equals("${arg2}") || args[3].equals("${arg3}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 4 arguments.");
			System.exit(0);
		}
		StoreRestoreHandler  handler = new StoreRestoreHandler();		
		ProxyCreator pc = new ProxyCreator();

		// create a proxy
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
									new Class[] {
										StoreI.class, RestoreI.class
									}, 
									handler
									);
			
		handler.openReadFile(args[1]);


		MyAllTypesFirst myFirst;
		MyAllTypesSecond  mySecond;
		MySpecialTypes  mySpecialT;
		Field[] fieldArray;

		SerializableObject myRecordRet;
		List<SerializableObject> deserList; 

		deserList = new ArrayList<>();

		while(true){
			myRecordRet = ((RestoreI) cpointRef).readObj("XML");
			if(myRecordRet == null){
				break;
			}
			deserList.add(myRecordRet);
		};

		handler.closeReadFile();

		handler.openWriteFile(args[2]);

		for (int i=0; i<deserList.size(); i++) {
			myRecordRet = deserList.get(i);
			((StoreI) cpointRef).writeObj(myRecordRet, "XML");			
		}

		handler.closeWriteFile();

	}
}
