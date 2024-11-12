import java.lang.Math;
import java.util.Arrays;
import java.awt.Color;
import java.io.File;

/*Check time:
long s = System.currentTimeMillis();
'your code'
System.out.println(System.currentTimeMillis() - s);
*/

public class Main{
	
	
	
	public static void qsort0(double[][] arr,int s,int r){
        if(s >= r) return;
		int o = s + (r - s)/2;
		swap(arr,o,r);
		int iL = s - 1;
		for(int i = s;i < r;i++){
			if(arr[i][0] < arr[r][0]){
				iL++;
				swap(arr,i,iL);
			}
		}
		swap(arr,iL+1,r);
		qsort0(arr,s,iL);
		qsort0(arr,iL + 2,r);
    }
	
	public static void qsort(double[][] arr){
		qsort0(arr,0,arr.length - 1);
	}
	
	public static void swap(double[][] array, int x, int y) {
        double[] temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
	
	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
	
    public static void main(String[] args) {
		String currentDirectory = System.getProperty("user.dir");
		File newDirectory = new File(currentDirectory, "out");newDirectory.mkdir();
		double xScale = 1;
		double yScale = 1;
		try{
			xScale = Double.parseDouble(args[0]);
			yScale = Double.parseDouble(args[1]);
		}catch(Exception e){
			System.out.println("No arguments");
			return;
		}
		if(Integer.parseInt(args[0]) < 0 || Integer.parseInt(args[1]) < 0){
			System.out.println("Diaphason cannot be lower than 0");
			return;
		}
		StdDraw.setTitle("Graphic");
		StdDraw.setCanvasSize(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
		double marginX = xScale*0.055;
		double marginY = yScale*0.05;
		StdDraw.setXscale(0,xScale + marginX);
		StdDraw.setYscale(0,yScale + marginY);
		double textMarginX = xScale*0.022;
		double textMarginY = yScale*0.019;
		boolean i = true;
		double lx = 0,ly = 0;
		StdDraw.line(marginX,marginY,xScale + marginX,marginY);
		StdDraw.line(marginX,marginY,marginX,yScale + marginY);
		StdDraw.text(marginX,textMarginY,"0");
		StdDraw.enableDoubleBuffering();
		if(xScale >= 15){
			int step = (int)(xScale / 15);
			for(int j = step;j<=xScale + marginX;j+=step){
				StdDraw.text(j+marginX,textMarginY,Integer.toString(j));
				StdDraw.text(j+marginX,marginY,"|");
			}
		}else{
			double step = round(xScale/15,2);
			for(double j = step;j<=xScale + marginX;j+=step){
				StdDraw.text(j+marginX,textMarginY,Double.toString(round(j,2)));
				StdDraw.text(j+marginX,marginY,"|");
			}
		}
		if(yScale >= 20){
			int step = (int)(yScale / 20);
			for(int j = step;j<yScale + marginY;j+=step){
				StdDraw.text(textMarginX,j+marginY,Integer.toString(j));
				StdDraw.text(marginX,j + marginY,"—");
			}
		}else{
			double step = round(yScale/20,2);
			for(double j = step;j<yScale + marginY;j+=step){
				StdDraw.text(textMarginX,j+marginY,Double.toString(round(j,2)));
				StdDraw.text(marginX,j + marginY,"—");
			}
		}
		StdDraw.disableDoubleBuffering();
		
		
		double[] gcount = new double[11];
		int c = 2;
		for(int j = 0;j<12;j++){
			String res = StdIn.readString();
			if(res.equals("*")){
				c = j;
				break;
			}else gcount[j] = Double.parseDouble(res);
		}
		
		double[][] resmass = new double[1][];
		resmass[0] = gcount;
		
		double[] stepMass = new double[c];
		boolean check = true;
		while(!StdIn.isEmpty()){
			if(check) check = false;
			resmass = Arrays.copyOf(resmass, resmass.length + 1);
			for(int j = 0;j<c;j++) stepMass[j] = StdIn.readDouble();
			resmass[resmass.length - 1] = stepMass;
			StdIn.readString();
			stepMass = new double[c];
		}
		if(check){
			System.out.println("Thats not a graph!");
			return;
		}
		qsort(resmass);
		
		Color[] COLORS = {StdDraw.GREEN,StdDraw.GREEN,StdDraw.BLUE,StdDraw.CYAN,StdDraw.MAGENTA,StdDraw.ORANGE,StdDraw.PINK,StdDraw.RED,StdDraw.YELLOW,StdDraw.BOOK_LIGHT_BLUE,StdDraw.	BOOK_RED};
		double max = 0,min = 0;
		long s = System.currentTimeMillis();
		for(int j = 1;j<c;j++){
			StdDraw.setPenColor(COLORS[j]);
			lx = resmass[0][0];ly = resmass[0][j];
			max = resmass[0][j];min = resmass[0][j];
			for(int k = 1;k<resmass.length;k++){
				if(resmass[k][j] > max) max = resmass[k][j];
				if(resmass[k][j] < min) min = resmass[k][j];
				if(resmass[k][0]+marginX > Double.parseDouble(args[0]) + marginX) break;
				StdDraw.point(resmass[k][0]+marginX,resmass[k][j]+marginY);
				StdDraw.line(lx+marginX,ly+marginY,resmass[k][0]+marginX,resmass[k][j]+marginY);
				lx = resmass[k][0];ly = resmass[k][j];
			}
			System.out.println("For " + j + " graph:");
			System.out.println("Max: " + max);
			System.out.println("Min: " + min + "\n");
			StdDraw.save(currentDirectory + "\\" + "out\\" + j + "graph.png");
		}
		StdDraw.save(currentDirectory + "\\" + "out\\result.png");
		System.out.println("\nTime spent: " + (System.currentTimeMillis() - s));
		
    }
}