/*
Group members:
Biranchi Padhi
Darrin Wiley
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ApallingArchitecture{
	
	BufferedReader file;
	PrintWriter pout;
	StringTokenizer st;

	public static void main(String[] args) throws Exception
	{
		new ApallingArchitecture().run();
	}
	
	public void run() throws Exception{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] chars = new char[R][C];
		for(int i = 0;i<R;i++)
		{
			chars[i] = file.readLine().toCharArray();
		}
		double centerX = 0;
		int weight = 0;
		for(int i = 0;i<R;i++)
		{
			for(int j = 0;j<C;j++)
			{
				if(chars[i][j] != '.')
				{
					centerX += j;
					weight++;
				}
			}
		}
		double leftmost = 1000;
		double rightmost = -1;
		for(int i = 0;i<C;i++)
		{
			if(chars[R-1][i] != '.')
			{
				leftmost = Math.min(leftmost, i-.5);
				rightmost = Math.max(rightmost, i+.5);
			}
		}
		centerX /= weight;
		if(centerX < leftmost)
		{
			System.out.println("left");
		}else if(centerX > rightmost)
		{
			System.out.println("right");
		}else {
			System.out.println("balanced");
		}
	}
	
}