package neyronika1;

import java.util.Random;

public class perceptron {
	int inputsize = 0;
	float[] input;
	float output;
	float[] weights;
	float bias = 1;
	String function;
	
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
		if (this.function.equals("Tanh")) {
			this.output = Tanh(SUM);
		}else if (this.function.equals("Relu")) {
			this.output = relu(SUM);
		}else {
			this.output = SUM;
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
		
	
	public void getWeights() {
		for (int i = 0;i < this.inputsize;i++) {
			System.out.println(this.weights[i]);
		}
	}
}
