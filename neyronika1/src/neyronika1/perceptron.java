package neyronika1;

import java.lang.reflect.Array;
import java.util.Random;

public class perceptron {
	int inputsize = 0;
	float[] input;
	float output;
	float[] weights;
	float u;
	float bias = 1;
	public float delta;
	String function;
	float[] outputweights;
	float[] NextPerceptronDeltas;
	int NextLayerLength;
	float learningRate = 0.002f;
	
	public perceptron(int inputsize,String function, float bias) {
		this.function = function;
		this.bias = bias;
	    Random rand = new Random();

		
		this.inputsize = inputsize;
		this.input = new float[inputsize];
		this.weights = new float[inputsize];
		
		for (int i = 0;i < inputsize;i++) {
			this.weights[i] = rand.nextFloat()*2 -1;
		}
	}
	
	public void evaluate() {
		float SUM = bias;
		for(int i = 0;i < this.inputsize;i++) {
			SUM += this.input[i]*this.weights[i];
		}
		this.u = SUM;
		if (this.function.equals("Tanh")) {
			this.output = Tanh(SUM);
		}else if (this.function.equals("Relu")) {
			this.output = relu(SUM);
		}else {
			this.output = SUM;
		}
		//System.out.println(this.output);
	}
	
	public void calcDelta() {
		if(function.equals("Tanh")) {
			float SUM = 0;
			for(int i = 0;i < NextLayerLength;i++) {
				SUM += outputweights[i] * NextPerceptronDeltas[i];
			}
			this.delta = (float)(1 - Math.pow(Math.tanh(this.u),2)) * SUM;
		}else {}
		//System.out.println(this.delta);
	}
	
	public void calcDelta(float rightAnswer) {
		this.delta = rightAnswer - this.output;
		//System.out.println(this.delta);
	}
	
	public void updateWeights() {
		for(int i = 0;i < inputsize;i++) {
			weights[i] = weights[i] + learningRate*delta*output;
		}
	}
	
	////////////////////////
	//ACTIVATION FUNCTIONS//
	//////////////////////////////////////////////////////////////////////
	
	public float relu(float value) {
		return Math.max(0,value);
	}
	public float Tanh(float value) {
		return (float)Math.tanh(value);
	}
	
	///////////////////
	//SETTERS GETTERS//
	//////////////////////////////////////////////////////////////////////
	
	public void setNextLayerLength(int length) {
		this.NextLayerLength = length;
	}
	
	public void setNextPerceptronDeltas(float[] deltas) {
		this.NextPerceptronDeltas = deltas;
	}
	
	public float[] getNextPerceptronDeltas() {
		for (int i = 0;i < this.inputsize;i++) {
			//System.out.println(this.NextPerceptronDeltas[i]);
		}
		return this.NextPerceptronDeltas;
	}
	
	public float getDelta() {
		//System.out.println(delta);
		return this.delta;
	}
	
	
	public void setOutputWeights(float[] weights) {
		this.outputweights = weights;
	}
	public float[] getOutputWeights() {
		return this.outputweights;
	}
	
	public void setInputs(float[] inputs) {
		for (int i = 0;i < this.inputsize;i++) {
			this.input[i] = inputs[i];
		}
	}
	public void setInputs(float input) {
		for (int i = 0;i < this.inputsize;i++) {
			this.input[i] = input;
		}
	}
	
	public float getOutput() {
		return output;
	}
		
	
	public float[] getWeights() {
		for (int i = 0;i < this.inputsize;i++) {
			//System.out.println(this.weights[i]);
		}
		return this.weights;
	}
}
