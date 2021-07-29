import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class PolygonArea {
	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		while(N!=0)
		{
			int[][] pts = new int[N][2];
			for(int i = 0;i<N;i++)
			{
				pts[i][0] = file.nextInt();
				pts[i][1] = file.nextInt();
			}
			int sum = 0;
			for(int i = 0;i<N;i++)
			{
				sum+=pts[i][0]*pts[(i+1)%N][1];
				sum-=pts[i][1]*pts[(i+1)%N][0];
			}
			if(sum>0)
			{
				System.out.print("C");
			}
			System.out.printf("CW %.1f%n",Math.abs(sum/2.0));
			N = file.nextInt();
		}
	}
}
