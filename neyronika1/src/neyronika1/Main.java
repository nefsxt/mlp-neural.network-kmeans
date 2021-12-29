package neyronika1;
import java.util.Arrays;
import java.util.Random;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle error
import java.lang.Math;
import java.lang.reflect.Array;




public class Main {	
	static int hiddenlayers = 2;
	static int D = 2;
	static int H1 = 2;
	static int H2 = 2;
	static int P = 4;
	static int perceptronsPerLayer[] = {2,2,2,4};
	static perceptron perceptrons[][] = new perceptron[hiddenlayers+2][4];
	static float[][] perceptronOutputs = new float[hiddenlayers+2][4];
	
	
	public static void calcErrorsAndpopagateDeltas(float[] rightanswer) {
		///////////////////////////
		//calculate output deltas//
		//////////////////////////////////////////////////////////////////////

		for(int i = 0;i < 4;i++) {
			perceptrons[3][i].calcDelta(rightanswer[i]);
		}
		for(int i = hiddenlayers;i >= 0;i--) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				perceptrons[i][j].calcDelta( perceptronsPerLayer[i+1]);
			}
		}
		///////////////////////////////////////////
		//connect weights and deltas for backprop//
		//////////////////////////////////////////////////////////////////////

		//connect weights
		for(int i = 0;i < hiddenlayers+1;i++) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				float[] weights = new float[perceptronsPerLayer[i+1]];
				for(int o = 0;o < perceptronsPerLayer[i+1];o++) {
					weights[o] = perceptrons[i+1][o].getWeights()[j];
				}
				perceptrons[i][j].setOutputWeights(weights);
			}
		}
		
		//connect deltas
		for(int i = 0;i < hiddenlayers+1;i++) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				float[] deltas = new float[perceptronsPerLayer[i+1]];
				for(int o = 0;o < perceptronsPerLayer[i+1];o++) {
					deltas[o] = perceptrons[i+1][o].getDelta();
				}
				perceptrons[i][j].setNextPerceptronDeltas(deltas);
			}
		}
	}
	
	public static float[] passforward(float[] inputs) {
		///////////////////////////////
		//PUT INPUTS AND PASS FORWARD//
		//////////////////////////////////////////////////////////////////////
		
		for(int i = 0;i < D;i++) { //layerinput
			perceptrons[0][i].setInputs(inputs[i]);
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
			perceptronOutputs[3][i] = perceptrons[3][i].getOutput();
		}
		return perceptronOutputs[3];
	}
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		
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
		

		//////////////////////
		//CREATE PERCEPTRONS//
		//////////////////////////////////////////////////////////////////////
		
		for(int i = 0;i < D;i++) perceptrons[0][i] = new perceptron(1,"Tanh",0);
		for(int i = 1;i < hiddenlayers+1;i++) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				perceptrons[i][j] = new perceptron(perceptronsPerLayer[i-1],"Tanh",1);
				perceptrons[i][j].setNextLayerLength(perceptronsPerLayer[i+1]);
			}
		}
		for(int i = 0;i < P;i++)perceptrons[hiddenlayers+1][i] = new perceptron(H2,"output",1);
		
		////////////////////////////////
		//calculate right answer array//
		//////////////////////////////////////////////////////////////////////

				
		float[] rightanswer = new float[4];
		for(int i = 0;i < 4;i++) {
			if(i == rightAnswers[0] - 1) {
				rightanswer[i] = 1;
			}else {
				rightanswer[i] = 0;
			}
		}


		//////////////////////////////////////////////////////////////////////

		float curr = passforward(inputs[0])[1];
		calcErrorsAndpopagateDeltas(rightanswer);
		for(int i = 0;i < hiddenlayers+2;i++) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				perceptrons[i][j].updateWeights();
			}
		}
		for(int o = 0;o < 100;o++) {
			System.out.println(passforward(inputs[0])[1]);

			passforward(inputs[0]);
			calcErrorsAndpopagateDeltas(rightanswer);
			for(int i = 0;i < hiddenlayers+2;i++) {
				for(int j = 0;j < perceptronsPerLayer[i];j++) {
					perceptrons[i][j].updateWeights();
				}
			}
			//System.out.println(perceptrons[1][1].getWeights()[1]);
			
		}

		
		
		
		
	    long end = System.currentTimeMillis();
	    
	    System.out.println((end - start) + " ms");

	}
}


