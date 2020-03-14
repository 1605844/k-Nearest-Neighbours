//file creator packages
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;

public class K_Nearest_Neighbours {

	private Point[] P;
	private int neighbours;
	
	//------------------------------------------------------------------------------------------------
	//constructor - the object is simply an array of points
	
	public K_Nearest_Neighbours(int numEntries, double size, int neighbours) {
		P = new Point[numEntries];
		
		for (int i = 0; i < numEntries; i++) {
			P[i] = new Point(size);
		}
		
		this.neighbours = neighbours;
	}
		
	public K_Nearest_Neighbours(int numEntries, double size) {
		P = new Point[numEntries];
		
		for (int i = 0; i < numEntries; i++) {
			P[i] = new Point(size);
		}
		
		this.neighbours = 1;
	}	
		
		
	//------------------------------------------------------------------------------------------------
	//getters
	
	public Point getPoint(int i) {
		if(i < 0 || i >= this.P.length) {
			System.out.println("not a valid point");
			return new Point();
		} else {
			return this.P[i];
		}
	}
	
	
	//------------------------------------------------------------------------------------------------
	//setters
	
	public void setNeighbours(int i) {
		this.neighbours = i;
	}
	
	
	//------------------------------------------------------------------------------------------------
	//writes the array of original points into a text file
	
	public String orignialPoints() {
		String s = "";
		
		for (int i = 0; i < this.P.length; i++) {
			Point Q = getPoint(i);
			s += Q.getX() + "	" + Q.getY() + "	" + Q.getColour() + "	" + "\n";
		}
		
		return s;
	}
	
	//------------------------------------------------------------------------------------------------
	//writes the array of all points into a text file
	
	public String allPoints(Point q) {
		String s = "";
		
		for (int i = 0; i < this.P.length; i++) {
			Point Q = getPoint(i);
			s += Q.getX() + "	" + Q.getY() + "	" + Q.getColour() + "	" + "\n";
		}
		
		
		s += "\n" + q.getX() + "	" + q.getY() + "	" + q.getColour() + "	" + "\n";
		
		return s;
	}
	
	//------------------------------------------------------------------------------------------------
	//writes a string  into a text file in the directory
	
	private static void writeFile(String data, String fileName) {
        try {
            Files.write(Paths.get("C:/Users/danie/OneDrive/Documents/Uni/4th Year Project/Java Programs/Presentation/K-nearest Neighbours/" + fileName + ".txt"), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	//------------------------------------------------------------------------------------------------
	//given the x and y values of the point, estimate the label using modal k-NN
	
	public Point labelPoint(double qx, double qy) {
		Point Q = new Point(qx,qy,0);
		int k = this.neighbours;
		
		Point[] nearestNeighbours = new Point[k];
		double epsilon = 0.05; //the step length when finding close points
		double circleSize = 0; //the circle size for each iteration
		int neighboursFound = 0; //keeps track of how many neighbours have been found
		
		//finds the k-nearest neighbours
		do {
			circleSize += epsilon;
			for (int i = 0; i < P.length; i++) {
				double dist = Q.distance(getPoint(i));
				if (dist < circleSize){
					nearestNeighbours[neighboursFound] = getPoint(i);
					neighboursFound++;
					if (neighboursFound == k) //in case there is more than one point in the final circle increment
						break;
				}
			}			
		} while (neighboursFound < k);
		
		//estimates the label
		int[] nv = new int[6];
		for (int i = 0; i < nearestNeighbours.length; i++) {
			if (nearestNeighbours[i].getColour() < 0 || nearestNeighbours[i].getColour() > 4) {
				nv[6]++;
			} else {
				nv[nearestNeighbours[i].getColour()]++;
			}
		}
		double mode = 0;
		for (int i = 1; i < 5; i++) {
			if (nv[i] > mode) {
				Q.setColour(i);
				mode = nv[i];
			}
		}
		
		return Q;
	}
	
	//------------------------------------------------------------------------------------------------
	//main method
	
	public static void main(String[] args) {
		
		K_Nearest_Neighbours K = new K_Nearest_Neighbours(1000,50);
		Point[] testPoints = new Point[100000];
		
		for(int i = 0; i < 50; i++) {
			testPoints[i] = new Point(50);
		}
		
		String s = "";
		
		for (int i = 1; i <= 50; i++) {
			K.setNeighbours(i);
			int er = 0;
			//System.out.println(error);
			for(int j = 0; j < 50; j++) {	
				int trueColour = testPoints[j].getColour();
				Point pred = K.labelPoint(testPoints[j].getX(),testPoints[j].getY());
				int guessColour = pred.getColour();
				if(trueColour != guessColour) {
					//System.out.println("New error at" + pred.toString());	
					er++;
				}
			}
			System.out.println(er);
			s += er + "\n";
		}
		
		writeFile(s,"boom");
		
	}


}





















