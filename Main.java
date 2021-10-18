/*
*Created by: Brandon Delliquadri
*/
import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;

class Main {
	/*
	*Rotates the points in the arraylist
	*@param: arraylist of points and an angle
	*@result: all values processed by rotation along axis formula
	*/
	public static void rotatePt(ArrayList<Point> points, double angle){
		for(int i = 0; i<points.size(); i++){
			double x = points.get(i).getX();
			double y = points.get(i).getY();
			double X = rotatePtX(x, y, angle);
			double Y = rotatePtY(x,y,angle);
			if(Y<0 && Y>-.01){
				Y=0;
			}
			Point p = new Point(X,Y);
			points.set(i,p);
		}
		
	}
	/*
	*Rotates the x-value of a point by a degree
	*@param: x,y, and angle doubles
	*@result: x value processed by rotation along axis formula
	*/
	public static double rotatePtX(double x, double y, double angle){
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return (x*(cos))-(y*(sin));
	}
	/*
	*Rotates the y-value of a point by a degree
	*@param: x,y, and angle doubles
	*@result: y value processed by rotation along axis formula
	*/
	public static double rotatePtY(double x, double y, double angle){
		double cos = Math.cos(angle);
		double sin = Math.sin(angle);
		return (x*(sin))+(y*(cos));
	}
	/*
	*Flips points in the arraylist along the x axis.
	*@param: the arraylist of points
	*@result: all y-values are negated.
	*/
	public static void flipPt(ArrayList<Point> points){
		
		for(int i = 0; i<points.size(); i++){
			double x = points.get(i).getX();
			double y = points.get(i).getY()*(-1);
			Point p = new Point(x,y);
			points.set(i,p);
		}
		

	}

	/*
	*Displays the points in the arraylist.
	*@param: the arraylist of points
	*/
	public static void printPts(ArrayList<Point> points){
		for(int i = 0; i<points.size(); i++){
			points.get(i).display();
		}
	}

	/*
	*Creates the first shape.
	*@param: the arraylist of points
	*@result: adds 4 points to the arraylist
	*/
	public static void n1(ArrayList<Point> points){
		Point A = new Point(0,0);
		Point B = new Point((double)1/2,(Math.sqrt(3)/2.0));
		Point C = new Point((double)3/2,(Math.sqrt(3)/2.0));
		Point D = new Point(2,0);
		points.add(A);
		points.add(B);
		points.add(C);
		points.add(D);
	}

	public static void resetArray(ArrayList<Point> nMinOne,ArrayList<Point> nCurrent){
		nCurrent.clear();
		for(int i = 0; i<nMinOne.size();i++){
			double x = nMinOne.get(i).getX();
			double y = nMinOne.get(i).getY();
			Point temp = new Point(x,y);
			nCurrent.add(temp);
		}
	}
  
	/*
	*This is the omega method. Recursively creates the Sierpinski triangle
	*@param: the arraylist of points, the n integer for how many times to repeat
	*@result: adds 4 points to the arraylist
	*/
	public static void nextShape(ArrayList<Point> nMinOne, double n){
		ArrayList<Point> nCurrent = new ArrayList<Point>();
		double d;
		for(int i = 1; i<n;i++){
			resetArray(nMinOne,nCurrent);
			//STEP 1:	
			d = Math.toRadians(60.0);
			flipPt(nMinOne);
			rotatePt(nMinOne, d);

			//STEP 2:
			double transX = nMinOne.get(nMinOne.size()-1).getX();
			double transY = nMinOne.get(nMinOne.size()-1).getY();
			double x,y;
			int size = nMinOne.size();
			for(int j = 0; j<size;j++){
				x = nCurrent.get(j).getX();
				y = nCurrent.get(j).getY();
				x += transX;
				y += transY;
				Point temp = new Point(x,y);
				if(j==0){
					nMinOne.set(size-1,temp);
				}
				else{
					nMinOne.add(nMinOne.size(),temp);
				}
				
			}
			// System.out.println("STEP 2 RESULTS:");
			// for (int j = 0;j<nMinOne.size();j++){
			// 	nMinOne.get(j).display();
			// }
			// System.out.println();
			//STEP 3:
			d = Math.toRadians(-60.0);
			flipPt(nCurrent);
			rotatePt(nCurrent, d);

			transX = nMinOne.get(nMinOne.size()-1).getX();
			transY = nMinOne.get(nMinOne.size()-1).getY();
			size = nCurrent.size();
			for(int k = 0; k<size;k++){
				x = nCurrent.get(k).getX();
				y = nCurrent.get(k).getY();
				x += transX;
				y += transY;
				if(y<0 && y>-.1){
					y=0;
				}
				Point temp = new Point(x,y);
				if(k==0){
					nMinOne.set(nMinOne.size()-1,temp);
				}
				else{
					nMinOne.add(nMinOne.size(),temp);
				}
			}
			// System.out.println("STEP 3 RESULTS:");
			// for (int j = 0;j<nMinOne.size();j++){
			// 	nMinOne.get(j).display();
			// }
			// System.out.println();
		}

	}

	/*
	*Reads the input file. puts all of the data in an arraylist
	*/
	public static void readFile(ArrayList<Double> input){
		Scanner scan;
				File file = new File("input.txt");
				try{
					scan = new Scanner(file);

					while(scan.hasNextDouble()){
						input.add(scan.nextDouble());
					}
				}
				catch(FileNotFoundException e){
					System.out.println("Expected input.txt");
					e.printStackTrace();
				}
	}

	/*
	*sorts the Sierpinski triangle
	*/
	// public static void sort(ArrayList<Point> sierpinski){
	// 	int size = sierpinski.size();
	// 	for (int i = size-1;i>0;i--){
	// 		for(int j=0;j<i;j++){
	// 			if(sierpinski.get(j).getY() > sierpinski.get(j+1).getY()){
	// 				Collections.swap(sierpinski,j,j+1);
	// 			}
	// 		}
	// 	}
	// }

	public static void sortLines(ArrayList<Line> sierpinski){
		int size = sierpinski.size();
		for (int i = size-1;i>0;i--){
			for(int j=0;j<i;j++){
				if(sierpinski.get(j).getY() > sierpinski.get(j+1).getY()){
					Collections.swap(sierpinski,j,j+1);
				}
			}
		}
	}

	/*
	*Turns a point array into a line array
	*/
	public static void intoLine(ArrayList<Point> points, ArrayList<Line> lines){
		for(int i =0;i<points.size()-1;i++){
			Line temp = new Line(points.get(i),points.get(i+1));
			lines.add(temp);
		}
	}

	public static void printLines(ArrayList<Line> lines){
		for(int i = 0; i<lines.size(); i++){
			lines.get(i).display();
		}
	}

	public static void test(ArrayList<Double> inputArray, ArrayList<Line> sierpinskiLines){
		Point temp;
		Point temp2;
		Line tempLine;
		int[] output = new int[(inputArray.size()-1)/4];
		Arrays.fill(output,0);
		int out;
		for(int i = 0;i<sierpinskiLines.size();i++){
			for(int j = 1;j<inputArray.size();j+=4){
				temp = new Point(inputArray.get(j),inputArray.get(j+1));
				temp2 = new Point(inputArray.get(j+2),inputArray.get(j+3));
				tempLine = new Line(temp,temp2);
				out = lineIntersection(sierpinskiLines.get(i),tempLine);
				if(out == 1){
					output[(j-1)/4] = out;
				}
			}
		}
		try{
			writeOutput(output);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}

	public static void writeOutput(int[] output)throws IOException{
		File file = new File("output.txt");
		FileWriter write = new FileWriter(file);
		PrintWriter print = new PrintWriter(write);
		//System.out.println(lineIntersection(l1,l2));
		//print.print(lineIntersection(l1,l2));
		for(int i = 0;i<output.length;i++){
			print.println(output[i]);
		}
		write.close();
	}

	public static boolean crossProduct(double x0, double y0, double x1, double y1, double x2, double y2){
		double crossProduct = (x1-x0)*(y2-y0)-(x2-x0)*(y1-y0);
		if(crossProduct>0){
			return true;
		}
		else{
			return false;
		}
	}

	public static int lineIntersection(Line sierpinskiLine,Line testLine){
		double x0,y0,x1,y1,x2,y2,x3,y3;
		//x0 through y1 are points on the sierpinski triangle
		x0 = sierpinskiLine.getX();
		y0 = sierpinskiLine.getY();
		x1 = sierpinskiLine.getX2();
		y1 = sierpinskiLine.getY2();

		//x2 through y3 are points given by the input file AKA test points
		if(testLine.getY()<testLine.getY2()){
			x2 = testLine.getX();
			y2 = testLine.getY();
			x3 = testLine.getX2();
			y3 = testLine.getY2();
		}
		else{
			x2 = testLine.getX2();
			y2 = testLine.getY2();
			x3 = testLine.getX();
			y3 = testLine.getY();
		}

		boolean b1,b2,b3,b4;
		//I thought I did these cross products backwards but apparently they work. CHECK THIS IF SOMETHING GOES WRONG LATER
		b1 = crossProduct(x2,y2,x0,y0,x1,y1);
		b2 = crossProduct(x3,y3,x0,y0,x1,y1);

		b3 = crossProduct(x0,y0,x2,y2,x3,y3);
		b4 = crossProduct(x1,y1,x2,y2,x3,y3);

		//System.out.println(b1+" "+b2+" "+b3+" "+b4);
		if(b1==false &&b2==true && b3==true && b4==false){
			//System.out.println(b1+" "+b2+" "+b3+" "+b4);
			return 1;
		}
		else{
			return 0;
		}
		
	}


	////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////MAIN/////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		ArrayList<Double> inputArray = new ArrayList<Double>();
		ArrayList<Line> sierpinskiLines = new ArrayList<Line>();
		readFile(inputArray);
		double n = inputArray.get(0);//How many iterations to create sierpinski triangle
		ArrayList<Point> sierpinski = new ArrayList<Point>();
		if(n==1){
			n1(sierpinski);
			//printPts(sierpinski);
		}
		else{
			n1(sierpinski);
			nextShape(sierpinski,n);
			//printPts(sierpinski);
		}
		intoLine(sierpinski,sierpinskiLines);
		// System.out.println("UNSORTED");
		// printLines(sierpinskiLines);
		// sortLines(sierpinskiLines);
		// System.out.println("SORTED");
		// printLines(sierpinskiLines);
		test(inputArray,sierpinskiLines);
		// System.out.println("====================");
		// System.out.println("Sorted by y:");
		// sort(sierpinski);
		//printPts(sierpinski);
  }
}