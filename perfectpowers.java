package page;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class perfectpowers {
	
	public static void main(String[] args) throws Exception
	{
		new perfectpowers().run();
	}
	
	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		long N = file.nextInt();
		int max = 1;
		while(N!=0)
		{
			max = 1;
			boolean negative = N<0;
			if(negative)
				N = -N;
			for(int i = 2;i<32;i++)
			{
				if(i%2==0&&negative)
					continue;
				double root = Math.pow(N,1.0/i);
				int root1 = (int)(root);
				boolean tryit = tryit(root1,i,N)||tryit(root1-1,i,N)||tryit(root1+1,i,N);
				if(tryit)
				{
					max = i;
				}
			}
			System.out.println(max);
			N = file.nextInt();
		}
	}	
	public boolean tryit(int totry, int power, long N)
	{
		long current = 1;
		for(int i = 0;i<power;i++)
		{
			current*=totry;
		}
		return current==N;
	}
}


