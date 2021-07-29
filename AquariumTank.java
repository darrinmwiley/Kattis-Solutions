package page;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class AquariumTank {

	public static void main(String[] args) throws Exception
	{
		new AquariumTank().run();
	}

	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int D = file.nextInt();
		double L = file.nextDouble();
		double target = L/D;
		double[] x = new double[N];
		double[] y = new double[N];
		for(int i = 0;i<N;i++)
		{
			x[i] = file.nextDouble();
			y[i] = file.nextDouble();
		}
		System.out.printf("%.2f",search(x,y,L,D));
	}

	public double search(double[] x, double[] y, double goal, int D)
	{
		double low = 0;
		double high = 1000;
		while(high-low>.000001)
		{
			//System.out.println(high+" "+low);
			double middle = (high+low)/2;
			double volume = volume(middle,x,y)*D/1000;
			//System.out.println("M: "+middle+" V: "+volume);
			if(volume>=goal)
				high = middle;
			else
				low = middle;
		}
		return high;
	}

	public double volume(double height, double[] x, double[] y)
	{
		LinkedList<Double> xx = new LinkedList<Double>();
		LinkedList<Double> yy = new LinkedList<Double>();
		for(int i = 0;i<x.length;i++)
		{
			boolean aunder = y[i]<height;
			boolean bunder = y[(i+1)%y.length]<height;
			if(aunder)
			{
				xx.add(x[i]);
				yy.add(y[i]);
			}
			if(aunder^bunder)
			{
				double y1 = y[i];
				double y2 = y[(i+1)%y.length];
				double x1 = x[i];
				double x2 = x[(i+1)%y.length];
				//height = mx + b;
				//m = dy/dx
				//  = (y1-y2)/(x1-x2);
				// y1 = m(x1) + b

				//b = y1 - m(x1);

				// x1 = (y1 - b)/m

				//
				// newx = (height-b)/m

				double m = (y2-y1)/(x2-x1);
				double b = y1-m*x1;
				double newx = (height-b)/m;
				if(x1==x2)
					newx = x1;
				xx.add(newx);
				yy.add(height);
			}
		}
		double sum = 0;
		for(int i = 0;i<xx.size();i++)
		{
			sum+=xx.get(i)*yy.get((i+1)%yy.size());
			sum-=xx.get((i+1)%xx.size())*yy.get(i);
		}
		return Math.abs(sum/2);
	}
}
