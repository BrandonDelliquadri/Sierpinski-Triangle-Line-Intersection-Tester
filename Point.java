class Point{
	private double x;
	private double y;

	public Point(double X, double Y){
		x = X;
		y = Y;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public void display(){
		System.out.println("(" + x + "," + y + ")");
	}
}