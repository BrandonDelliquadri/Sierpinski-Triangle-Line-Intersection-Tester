class Line{
	private double x1,x2,y1,y2;

	public Line(Point one, Point two){
		x1=one.getX();
		y1=one.getY();
		x2=two.getX();
		y2=two.getY();
	}
	public double getX(){
		if(x1 <x2){
			return x1;
		}
		else{
			return x2;
		}
	}

	public double getX2(){
		if(x1 <x2){
			return x2;
		}
		else{
			return x1;
		}
	}

	public double getY(){
		if(x1 <x2){
			return y1;
		}
		else{
			return y2;
		}
	}

	public double getY2(){
		if(x1 <x2){
			return y2;
		}
		else{
			return y1;
		}
	}
	public void display(){
		System.out.println("(" + x1 + "," + y1 + ") and " + "(" + x2 + "," + y2 + ")");
	}
	public String toString(){
		return "(" + x1 + "," + y1 + ") and " + "(" + x2 + "," + y2 + ")";
	}
}