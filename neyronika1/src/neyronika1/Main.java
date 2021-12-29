package neyronika1;
import java.util.Arrays;
import java.util.Random;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle error
import java.lang.Math;
import java.lang.reflect.Array;




public class Main {	
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int hiddenlayers = 2;
		int D = 2;
		int H1 = 2;
		int H2 = 2;
		int P = 4;
		int perceptronsPerLayer[] = {2,2,2,4};
		perceptron perceptrons[][] = new perceptron[hiddenlayers+2][4];
		float[][] perceptronOutputs = new float[hiddenlayers+2][4];
		
	
		//S1_S2.createS1();
		//S1_S2.createS2();
		
		///////////////////////
		//LOAD DATA FROM FILE//
		//////////////////////////////////////////////////////////////////////////
		
		
		float[][] dataFromFile = IO.ReadFromFile("dataset1withExamples.txt", 8000,3);
		float[][] inputs = new float[10][2];
		float[] rightAnswers = new float[10];

		
		for(int i = 0;i < 10;i++) {
			inputs[i][0] =  dataFromFile[i][0];
			inputs[i][1] =  dataFromFile[i][1];

			rightAnswers[i] = dataFromFile[i][2]; 
		}
		
		//////////////////////////////////////////////////////////////////////////


		float LIoutputs[] = new float[D];
		float LH1outputs[] = new float[H1];
		float LH2outputs[] = new float[H2];
		float Loutputs[] = new float[P];

		//////////////////////
		//CREATE PERCEPTRONS//
		//////////////////////////////////////////////////////////////////////
		

		
		for(int i = 0;i < D;i++) perceptrons[0][i] = new perceptron(1,"Tanh",0);
		for(int i = 1;i < hiddenlayers+1;i++) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				perceptrons[i][j] = new perceptron(perceptronsPerLayer[i-1],"Tanh",1);
			}
		}
		for(int i = 0;i < P;i++)perceptrons[hiddenlayers+1][i] = new perceptron(H2,"output",1);
		
		//////////////////////////////////////////////////////////////////////
		
		///////////////////////////////
		//PUT INPUTS AND PASS FORWARD//
		//////////////////////////////////////////////////////////////////////
		
		for(int i = 0;i < D;i++) { //layerinput
			perceptrons[0][i].setInputs(inputs[0][i]);
			perceptrons[0][i].evaluate();
			perceptronOutputs[0][i] = perceptrons[0][i].getOutput();
		}
		for(int i = 1;i < hiddenlayers+2;i++) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				perceptrons[i][j].setInputs(perceptronOutputs[i-1]);
				perceptrons[i][j].evaluate();
				perceptronOutputs[i][j] = perceptrons[i][j].getOutput();
			}
		}
		for(int i = 0;i < 4;i++) {
			
			System.out.println(perceptrons[3][i].getOutput());
		}

		
	    long end = System.currentTimeMillis();
	    
	    System.out.println((end - start) + " ms");

	}
}


