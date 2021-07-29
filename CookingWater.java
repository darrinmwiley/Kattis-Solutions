

import java.util.Arrays;
import java.util.Scanner;

public class CookingWater {

	long[][] dp;
	boolean[][] spots;

	public static void main(String[] args) throws Exception
	{
		new CookingWater().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int highestLeft = Integer.MIN_VALUE;
		int lowestRight = Integer.MAX_VALUE;
		for(int i = 0;i<N;i++)
		{
			int left = file.nextInt();
			int right = file.nextInt();
			highestLeft = Math.max(highestLeft,left);
			lowestRight = Math.min(lowestRight,right);
		}
		if(highestLeft>lowestRight)
			System.out.println("edward is right");
		else
			System.out.println("gunilla has a point");
	}

	double dist(double x1, double y1, double x2, double y2)
	{
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
}
