import java.util.Arrays;
import java.util.Random;

public class Main {

	public static final int M = 9;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		int[] centroids = new int[M];
		int[] prevcentroids = new int[M];
		int[] bestcentroids = new int[M];
		float[][] dataset = new float[1200][2];
		float[] clusters = new float[1200];
		float divergance = 0;
		float bestdivergance = Float.POSITIVE_INFINITY;
		dataset = IO.ReadFromFile("dataset.txt", 1200, 2);

		
		for(int i = 0;i < 1200;i++) {
			clusters[i] = 0;
		}
		
		Random rand = new Random();
		for(int w = 0;w < 20;w ++) {
			
			/////////////////////////////////////////
			//initialise centroids to random points//
			/////////////////////////////////////////
			for(int i = 0;i < M;i++) {
				centroids[i] = rand.nextInt(1200);
				prevcentroids[i] = 0;
			}
			while(!Arrays.equals(prevcentroids,centroids)) {//Run until no changes//
				
				divergance = 0;
				for(int i = 0;i < M;i++) {
					prevcentroids[i] = centroids[i];
				}
				///////////////////////////////
				//assign points to a centroid//
				///////////////////////////////
				for(int i = 0;i < 1200;i++) { 
					float mindistance = Float.POSITIVE_INFINITY;
					int minDistCentroid = -1;
					for(int p = 0;p < M;p++) {
						if(distance(dataset[i],dataset[centroids[p]]) < mindistance) {
							mindistance = distance(dataset[i],dataset[centroids[p]]);
							minDistCentroid = p;
						}
					}
					clusters[i] = minDistCentroid;
				}
				
				///////////////////////////////
				//new locations for centroids//
				///////////////////////////////
				float[] newloc = new float[2];
				for(int i = 0;i < M;i++) {
					///////////////////////////////////////
					//calculate the center of the cluster//
					/////////////////////////////////////////////////////////////////
					newloc[0] = 0;
					newloc[1] = 0;
					int cnt = 0;
					float mindistance = Float.POSITIVE_INFINITY;
					for(int j = 0;j < 1200;j++) {	//sum the distances
						if(clusters[j] == i) {
							cnt ++;
							newloc[0] += dataset[j][0];
							newloc[1] += dataset[j][1];
						}
						
					}
					mindistance = Float.POSITIVE_INFINITY;
					newloc[0] /= cnt;//and divide with the clusters size to find the mean location
					newloc[1] /= cnt;
					/////////////////////////////////////////////////////////////////
					
					
					/////////////////////////////////////////////////
					//find a point nearest to the calculated center//
					/////////////////////////////////////////////////////////////////
					int newLocIndex = centroids[i];
					for(int j = 0;j < 1200;j++) {
						if(clusters[j] == i && distance(dataset[j],newloc) < mindistance) {
							mindistance = distance(dataset[j],newloc);
							newLocIndex = j;
						}
					}
					
					centroids[i] = newLocIndex;
				}
				////////////////////////////////////////////////
				//calculate the divergance for those centroids//
				//////////////////////////////////////////////////////////////////////
				for(int i = 0;i < M;i++) {
					for(int j = 0;j < 1200;j++) {
						if(clusters[j] == i) {
							divergance += distance(dataset[j],dataset[centroids[i]]);
						}
					}
				}
			}
			if(divergance < bestdivergance) {
				bestdivergance = divergance;
				for(int i = 0;i < M;i++) {
					bestcentroids[i] = centroids[i];
				}
			}
		}
		
		centroids = bestcentroids;
		///////////////////////////////////
		//write to file the new centroids//
		//////////////////////////////////////////////////////////////////////
		IO.createFile("centroids.txt");
		IO.ClearFile("centroids.txt");
		for(int i = 0;i < M;i++) {
			IO.WriteToFile("centroids.txt", dataset[centroids[i]][0], dataset[centroids[i]][1]);
		}
	    long end = System.currentTimeMillis();
	    
	    System.out.println((end - start) + " ms");
	}//end of main
	
	public static float distance(float[] x1,float[] x2) {
		/////calculate euclidian distance
		return (float)Math.sqrt((x2[1] - x1[1]) * (x2[1] - x1[1]) + (x2[0] - x1[0]) * (x2[0] - x1[0]));
	}

}
