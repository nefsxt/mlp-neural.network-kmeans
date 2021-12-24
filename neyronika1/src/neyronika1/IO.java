package neyronika1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IO {
	public static void createFile(String FileName) {
	    try {
	        File myObj = new File(FileName);
	        if (myObj.createNewFile()) {
	          System.out.println("File created: " + myObj.getName());
	        } else {
	          System.out.println("File already exists.");
	        }
	    } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	}
	
	public static void WriteToFile(String FileName,float x1, float x2 ) {
	    try {
	        FileWriter myWriter = new FileWriter(FileName,true);
	        myWriter.write(x1 + "," + x2 + "\n");
	        myWriter.close();
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	}
}
