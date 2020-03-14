public class Point {
	
	private double a,b;
	private int c;
	
	//------------------------------------------------------------------------------------------------
	//constructorss

	//x and y coorindates are doubles
	//Colours are represented by integers: Black = 0, Red = 1, Blue = 2, Green = 3, Yellow = 4
	public Point(double a, double b,int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	//created a black point at the origin
	public Point() {
		this.a = 0;
		this.b = 0;
		this.c = 0;
	}
	
	//creates a random point in a square the size of the input
	public Point(double size) {
		double x = (Math.random() - 0.5) * size;
		double y = (Math.random() - 0.5) * size;
		
		this.a = x;
		this.b = y;
		
		if (y == 0) {
			this.c = 4;
		} else {
			double r = Math.abs(x / y);
			if (r < 1) {
				this.c = 3;
			} else {
				double rl = r % 2;
				if (rl < 1) {
					this.c = 2;
				} else {
					this.c = 1;
				}
			}
		}
		
	}
	
	//------------------------------------------------------------------------------------------------
	//getters
	
	public double getX() {
		return this.a;
	}
	
	public double getY() {
		return this.b;
	}
	
	public int getColour() {
		return this.c;
	}
	
	//------------------------------------------------------------------------------------------------
	
	public void setColour(int col) {
		this.c = col;
	}
	
	//------------------------------------------------------------------------------------------------
	//mutators - all new points are made black
	
	/* Changes the colour of the point
		If the leading digit of the ratio between the x and y coorindates is odd, the colour is red
		If the leading digit of the ratio between the x and y coorindates is even, the colour is blue
		If the leading digit of the ratio between the x and y coorindates is zero, the colour is green
		If the y - coorindate is 0, the colour is yellow
	*/
	public void changeColour() {
		double x = getX();
		double y = getY();
		
		if (y == 0) {
			this.c = 4;
		} else {
			double r = Math.abs(x / y);
			if (r < 1) {
				this.c = 3;
			} else {
				double rl = r % 2;
				if (rl < 1) {
					this.c = 2;
				} else {
					this.c = 1;
				}
			}
		}
	}
	
	
	//adds two points together with ordinate-wise addition
	public Point add(Point q) {
		double x = getX() + q.getX();
		double y = getY() + q.getY();
		
		Point Q = new Point(x,y,0);
		Q.changeColour();
		return Q;
	}
	
	//scales a point with ordinate-wise scaling
	public Point scale(double N) {
		double x = getX() * N;
		double y = getY() * N;
		
		Point Q = new Point(x,y,0);
		Q.changeColour();
		return Q;
	}
	
	//------------------------------------------------------------------------------------------------
	
	//finds the Euclidean distance between two points
	public double distance(Point p) {
		
		double x = getX() - p.getX();
		double y = getY() - p.getY();
		
		double dist = Math.sqrt(x*x + y*y);
		return dist;
		
	}
	
	//------------------------------------------------------------------------------------------------
	
	//returns the colour of the point
	public String colour() {
		int A = getColour();
		String S = "";
		
		switch (A) {
			case 0: S += "black";
				break;
			case 1: S += "red";
				break;
			case 2: S += "blue";
				break;
			case 3: S += "green";
				break;
			case 4: S += "yellow";
				break;
			default: S += "undefined colour";
				break;
		}
		
		return S;
	}
	
	//------------------------------------------------------------------------------------------------
	
	//converts a point to string
	public String toString() {
		String S = "(" + this.a + "," + this.b + "," + colour() + ")";
		return S;
	}
	
	public static void main(String[] args) {
		

		
	}
		
}
