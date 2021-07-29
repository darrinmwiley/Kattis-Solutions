package kattis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class macarons {
	
	int MOD = 1000000000;
	
	public static void main(String[] args)
	{
		new macarons().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		long M = file.nextLong();
		long[][] trans = new long[1<<N][1<<N];
		for(int i = 0;i<(1<<N);i++)
		{
			boolean[] bools = new boolean[N];
			int x = i;
			for(int j = bools.length-1;x!=0;j--)
			{
				if(x%2==1)
					bools[j] = true;
				x/=2;
			}
			long[] row = new long[1<<N];
			bruteForce(bools,row,0,0);
			trans[i] = row;
		}
		long[][] ans = exp(trans, M);
		System.out.println(ans[0][0]);
	}
	
	public long[][] exp(long[][] a, long x)
	{
		long[][] result = new long[a.length][a.length];
		for(int i = 0;i<a.length;i++)
			result[i][i] = 1;
		char[] bin = Long.toBinaryString(x).toCharArray();
		for(char ch:bin)
		{
			result = multiply(result,result);
			if(ch=='1')
			{
				result = multiply(result,a);
			}
		}
		return result;
	}
	
	public long[][] multiply(long[][] a, long[][] b)//must be square
	{
		long[][] ret = new long[a.length][a.length];
		for(int i = 0;i<a.length;i++)
			for(int j = 0;j<a.length;j++)
				for(int k = 0;k<a.length;k++)
					ret[i][j]= (ret[i][j]+a[i][k]*b[k][j])%MOD;
		
		return ret;
	}
	
	public void bruteForce(boolean[] start, long[] row, int index, int sum)
	{
		if(index>=start.length)
			row[sum/2]++;
		else if(start[index])
			bruteForce(start,row,index+1,sum*2);
		else 
		{
			bruteForce(start,row,index+1,sum*2);
			if(index+2<=start.length&&!start[index+1])
				bruteForce(start,row,index+2,sum*4);
			bruteForce(start,row,index+1,(sum+1)*2);
		}
	}
	
}

