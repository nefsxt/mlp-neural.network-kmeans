package neyronika1;

import java.util.Random;

public class S1_S2 {
	
	
	public static void createS1() {
		IO.createFile("dataset1withExamples.txt");
		int rng = 0;
	        
	    Random rand = new Random();

		float[][] dataset = new float[8000][2];
	    for(int i = 0;i < 8000;i++) {
	    	dataset[i][0] = rand.nextFloat()*2 - 1;
	        dataset[i][1] = rand.nextFloat()*2 - 1;
	    }
	   
		
	    for(int i = 0;i < 8000;i++) {
	    	if(Math.pow(dataset[i][0] - 0.5,2) + Math.pow(dataset[i][1] - 0.5,2) < 0.16 ||
	    			Math.pow(dataset[i][0] + 0.5,2) + Math.pow(dataset[i][1] + 0.5,2) < 0.16) {
				if(rand.nextFloat() < 0.1 && i < 4000) { //c1
					rng = rand.nextInt(3)+1;
					if(rng == 1) {
						IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"2");
					}else if (rng == 2) {
						IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"3");
					}else if (rng == 3) {
						IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"4");
					}
				}else {
					IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"1");
				}
	    	}else if(Math.pow(dataset[i][0] - 0.5,2) + Math.pow(dataset[i][1] + 0.5,2) < 0.16 ||
	    			Math.pow(dataset[i][0] + 0.5,2) + Math.pow(dataset[i][1] - 0.5,2) < 0.16) {
				if(rand.nextFloat() < 0.1 && i < 4000) { //c2
					rng = rand.nextInt(3)+1;
					if(rng == 1) {
						IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"1");
					}else if (rng == 2) {
						IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"3");
					}else if (rng == 3) {
						IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"4");
					}
				}else {
					IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"2");
				}
	    	}else if((dataset[i][0] > 0 && dataset[i][1] > 0) || (dataset[i][0] < 0 && dataset[i][1] < 0)) {
				if(rand.nextFloat() < 0.1 && i < 4000) { //c3
					rng = rand.nextInt(3)+1;
					if(rng == 1) {
						IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"1");
					}else if (rng == 2) {
						IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"2");
					}else if (rng == 3) {
						IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"4");
					}
				}else {
					IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"3");
				}
	    		
	    	}else {
				if(rand.nextFloat() < 0.1 && i < 4000) { //c4
					rng = rand.nextInt(3)+1;
					if(rng == 1) {
						IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"1");
					}else if (rng == 2) {
						IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"2");
					}else if (rng == 3) {
						IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"3");
					}
				}else {
					IO.WriteToFile("dataset1withExamples.txt",dataset[i][0],dataset[i][1],"4");
				}
	    	}
	    }	    
	}
	
	public static void createS2() {
		
		IO.createFile("dataset.txt");

	        
	    Random rand = new Random();
	    


		float[][] dataset = new float[1200][2];
    	//dataset[i][0] = rand.nextFloat() * (maxX - minX) + minX;
	    for(int i = 0;i < 150;i++) { //1
	    	dataset[i][0] = (float) (rand.nextFloat() * (0.75 - 1.25) + 1.25);
	    	dataset[i][1] = (float) (rand.nextFloat() * (0.75 - 1.25) + 1.25);
	    }
	    for(int i = 150;i < 300;i++) { //2
	    	dataset[i][0] = (float) (rand.nextFloat() * (0 - 0.5) + 0.5);
	    	dataset[i][1] = (float) (rand.nextFloat() * (0 - 0.5) + 0.5);
	    }
	    for(int i = 300;i < 450;i++) { //3
	    	dataset[i][0] = (float) (rand.nextFloat() * (0 - 0.5) + 0.5);
	    	dataset[i][1] = (float) (rand.nextFloat() * (1.5 - 2) + 2);
	    }
	    for(int i = 450;i < 600;i++) { //4
	    	dataset[i][0] = (float) (rand.nextFloat() * (1.5 - 2) + 2);
	    	dataset[i][1] = (float) (rand.nextFloat() * (0 - 0.5) + 0.5);
	    }
	    for(int i = 600;i < 750;i++) { //5
	    	dataset[i][0] = (float) (rand.nextFloat() * (1.5 - 2) + 2);
	    	dataset[i][1] = (float) (rand.nextFloat() * (1.5 - 2) + 2);
	    }
	   
	    for(int i = 750;i < 825;i++) { //6
	    	dataset[i][0] = (float) (rand.nextFloat() * (0.6 - 0.8) + 0.8);
	    	dataset[i][1] = (float) (rand.nextFloat() * (0 - 0.4) + 0.4);
	    }
	    for(int i = 825;i < 900;i++) { //7
	    	dataset[i][0] = (float) (rand.nextFloat() * (0.6 - 0.8) + 0.8);
	    	dataset[i][1] = (float) (rand.nextFloat() * (1.6 - 2) + 2);
	    }
	    for(int i = 900;i < 975;i++) { //8
	    	dataset[i][0] = (float) (rand.nextFloat() * (1.2 - 1.4) + 1.4);
	    	dataset[i][1] = (float) (rand.nextFloat() * (0 - 0.4) + 0.4);
	    }
	    for(int i = 975;i < 1050;i++) { //9
	    	dataset[i][0] = (float) (rand.nextFloat() * (1.2 - 1.4) + 1.4);
	    	dataset[i][1] = (float) (rand.nextFloat() * (1.6 - 2) + 2);
	    }
	    for(int i = 1050;i < 1200;i++) { //10
	    	dataset[i][0] = (float) (rand.nextFloat() * (0 - 2) + 2);
	    	dataset[i][1] = (float) (rand.nextFloat() * (0 - 2) + 2);
	    }
	   
	   
	   
		
	    for(int i = 0;i < 1200;i++) {
	    	IO.WriteToFile("dataset.txt",dataset[i][0],dataset[i][1]);
	    }
	}
}
