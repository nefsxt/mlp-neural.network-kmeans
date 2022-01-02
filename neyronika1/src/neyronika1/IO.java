package neyronika1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; // Import the Scanner class to read text files
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
	
	public static void WriteToFile(String FileName,float x1, float x2) {
	    try {
	        FileWriter myWriter = new FileWriter(FileName,true);
	        myWriter.write(x1 + "," + x2 + "\n");
	        myWriter.close();
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	}
	public static void WriteToFile(String FileName,float x1, float x2, String x3) {
	    try {
	        FileWriter myWriter = new FileWriter(FileName,true);
	        myWriter.write(x1 + "," + x2 + "," + x3 + "\n");
	        myWriter.close();
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	}
	public static void WriteToFile(String FileName,float x1, float x2, String x3, String x4) {
	    try {
	        FileWriter myWriter = new FileWriter(FileName,true);
	        myWriter.write(x1 + "," + x2 + "," + x3 + "," + x4 + "\n");
	        myWriter.close();
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	    }
	}

	
	public static float[][] ReadFromFile(String FileName,int FileLines,int infoNum) {
		float[][] output = new float[FileLines][infoNum];
	    try {
	        File myObj = new File(FileName);
	        Scanner myReader = new Scanner(myObj);
	        String[] dataS = new String[infoNum];
	        for(int i = 0;i < FileLines;i++) {
	        	dataS = myReader.nextLine().split(",");
	        	for(int j = 0;j < infoNum;j++) {
	        		output[i][j] = Float.parseFloat(dataS[j]);
	        	}
	        }
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }

	    return output;
	}
}
