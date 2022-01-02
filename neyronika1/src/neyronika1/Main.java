package neyronika1;
import java.util.Arrays;
import java.util.Collections;
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
	
	public static void updateWeights() {
		for(int i = 0;i < hiddenlayers+2;i++) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				perceptrons[i][j].updateWeights();
			}
		}
	}
	
	public static float calculateError(float [] x,float[] rightanswer) {
		float Error = 0;

		for(int i = 0;i < 4;i++) {
			Error += Math.pow((x[i] - rightanswer[i]),2);
		}
		return Error;
	}
	
	public static void backprop(float[] rightanswer) {
		
		//////////////////////////////////
		//calculate deltas and propagate//
		//////////////////////////////////////////////////////////////////////
		
		for(int i = 0;i < 4;i++) {//output layer
			perceptrons[3][i].calcDelta(rightanswer[i]);
		}
		
		for(int i = hiddenlayers;i >= 0;i--) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				float[] deltas = new float[perceptronsPerLayer[i+1]];
				float[] weights = new float[perceptronsPerLayer[i+1]];
				for(int o = 0;o < perceptronsPerLayer[i+1];o++) {
					deltas[o] = perceptrons[i+1][o].getDelta();
					weights[o] = perceptrons[i+1][o].getWeights()[j];
				}
				perceptrons[i][j].setNextPerceptronDeltas(deltas);
				perceptrons[i][j].setOutputWeights(weights);
				perceptrons[i][j].calcDelta();
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
		float[][] inputs = new float[8000][2];
		float[] rightAnswers = new float[8000];

		
		for(int i = 0;i < 8000;i++) {
			inputs[i][0] =  dataFromFile[i][0];
			inputs[i][1] =  dataFromFile[i][1];

			rightAnswers[i] = dataFromFile[i][2]; 
		}
		

		//////////////////////
		//CREATE PERCEPTRONS//
		//////////////////////////////////////////////////////////////////////
		
		for(int i = 0;i < perceptronsPerLayer[0];i++) perceptrons[0][i] = new perceptron(1,"Tanh",0);
		for(int i = 0;i < perceptronsPerLayer[0];i++) perceptrons[0][i].setNextLayerLength(perceptronsPerLayer[1]);
		for(int i = 1;i < hiddenlayers+1;i++) {
			for(int j = 0;j < perceptronsPerLayer[i];j++) {
				perceptrons[i][j] = new perceptron(perceptronsPerLayer[i],"Tanh",1);
				perceptrons[i][j].setNextLayerLength(perceptronsPerLayer[i+1]);
			}
		}
		for(int i = 0;i < P;i++)perceptrons[hiddenlayers+1][i] = new perceptron(H2,"Sig",1);
		
		////////////////////////////////
		//calculate right answer array//
		//////////////////////////////////////////////////////////////////////

		float[][] rightanswer = new float[8000][4];
		for(int i = 0;i < 8000;i++) {
			for(int j = 0;j < 4;j++) {
				if(j == rightAnswers[i] - 1) {
					rightanswer[i][j] = 1;
				}else {
					rightanswer[i][j] = 0;
				}
			}
		}
		//////////////////////////////////////////////////////////////////////


		////////////
		//LEARN!!!//
		//////////////////////////////////////////////////////////////////////
		float MSE = 0;
		IO.createFile("Errors.txt");
		int q = 1;
	
		
		
		for(int h = 0;h < 3000;h++) { //epochs
			q++;
			MSE = 0;
			for(int p = 0;p < 400;p++) {

			//	for(int i = 0;i < hiddenlayers+2;i++) {
			//		for(int j = 0;j < perceptronsPerLayer[i];j++) {
			//			perceptrons[i][j].setDelta(0);
			//		}
			//	}
				for(int batch = 0;batch < 10;batch++) {
					MSE += calculateError(passforward(inputs[p*10 + batch]), rightanswer[p*10 + batch]);
					backprop(rightanswer[p*10 + batch]);
					updateWeights();
				}
				

				
			}
			float success = 0;
			for(int p = 4000;p < 8000;p++) {
				if((float)max(passforward(inputs[p])) == (float)rightAnswers[p]) {
					success++;
				}
			}
			
			
			
			//System.out.println("epoch=  " + h);
			System.out.println(success/4000 + "% "+ h);
			IO.WriteToFile("Errors.txt",success/4000, q);
		}
		
		for(int i = 0;i < 4;i++) {
			for(int j = 0;j < 4;j++) {
				System.out.println(passforward(inputs[i])[j]);
			}
			System.out.println();
		}
		
		//////////////////////////////////////////////////////////////////////


		
	    long end = System.currentTimeMillis();
	    
	    System.out.println((end - start) + " ms");

	}
	public static int max(float[] in) {
		float max = 0;
		int ret = 0;
		for(int i = 0;i<4;i++) {
			if(in[i] > max) {
				max = in[i];
				ret = i+1;
			}
		}
		return ret;
	}
}



