package kattis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ZoningHouses {
	
	public static void main(String[] args) throws IOException
	{
		//new ZoningHouses().runCase(100);
		new ZoningHouses().run();
	}
	
	public int bruteForce(int[] x,int[] y,int a, int b)
	{
		a = a-1;
		b = b-1;
		int min = Integer.MAX_VALUE;
		int[] indices = new int[b-a+1];
		for(int i = 0;i<indices.length;i++)
			indices[i] = a+i;
		for(int i = a;i<=b;i++)
		{
			min = Math.min(min,getSquare(indices,i-a,x,y));
		}
		return min;
	}
	
	public int runCase(int[] x, int[] y, int a,int b)
	{
		a--;
		b--;
		int n = x.length;
		int size = (int)Math.floor(Math.log(n)/Math.log(2))+1;
		
		int[][][] rmqx = new int[size][][];
		int[][][] rmqy = new int[size][][];
		
		for(int i = 0;i<rmqx.length;i++)
			rmqx[i] = new int[n+1-(1<<i)][4];
		for(int i = 0;i<n;i++)
			rmqx[0][i] = new int[] {i,-1,i,-1};
		for(int i = 1;i<rmqx.length;i++)
			for(int j = 0;j<rmqx[i].length;j++)
				rmqx[i][j] = combine(rmqx[i-1][j],rmqx[i-1][j+(1<<(i-1))],x);
		
		for(int i = 0;i<rmqy.length;i++)
			rmqy[i] = new int[n+1-(1<<i)][4];
		for(int i = 0;i<n;i++)
			rmqy[0][i] = new int[] {i,-1,i,-1};
		for(int i = 1;i<rmqy.length;i++)
			for(int j = 0;j<rmqy[i].length;j++)
				rmqy[i][j] = combine(rmqy[i-1][j],rmqy[i-1][j+(1<<(i-1))],y);
		
		int[] xq = query(a,b,rmqx,x,true);
		int[] yq = query(a,b,rmqy,y,false);
		int[] combined = new int[xq.length+yq.length];
		int it = 0;
		for(int j = 0;j<xq.length;j++)
			combined[it++] = xq[j]; 
		for(int j = 0;j<yq.length;j++)
			combined[it++] = yq[j];
		int min = Integer.MAX_VALUE;
		for(int j = 0;j<combined.length;j++)
		{
			min = Math.min(min,getSquare(combined,j,x,y));
		}
		return (min);
	}
	
	public void runCase(int len)
	{
		while(true)
		{
			int[] x = genArray(len);
			int[] y = genArray(len);
			int aa = (int) (Math.random()*len);
			int bb = (int)(Math.random()*len);
			int a = Math.min(aa,bb)+1;
			int b = Math.max(aa,bb)+1;
			int ans1 = runCase(x,y,a,b);
			int ans2 = bruteForce(x,y,a,b);
			if(ans1!=ans2)
			{
				System.out.println(Arrays.toString(x)+" "+Arrays.toString(y)+" "+a+" "+b);
				System.out.println(ans1+" "+ans2);
				return;
			}
		}
		
	}
	
	public int[] genArray(int len)
	{
		int[] ints = new int[len];
		for(int i = 0;i<len;i++)
			ints[i] = (int)(Math.random()*200)-100;
		return ints;
	}
	
	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int[] x = new int[n];
		int[] y = new int[n];
		int size = (int)Math.floor(Math.log(n)/Math.log(2))+1;
		
		for(int i = 0;i<n;i++)
		{
			st = new StringTokenizer(file.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][][] rmqx = new int[size][][];
		int[][][] rmqy = new int[size][][];
		
		for(int i = 0;i<rmqx.length;i++)
			rmqx[i] = new int[n+1-(1<<i)][4];
		for(int i = 0;i<n;i++)
			rmqx[0][i] = new int[] {i,-1,i,-1};
		for(int i = 1;i<rmqx.length;i++)
			for(int j = 0;j<rmqx[i].length;j++)
				rmqx[i][j] = combine(rmqx[i-1][j],rmqx[i-1][j+(1<<(i-1))],x);
		
		for(int i = 0;i<rmqy.length;i++)
			rmqy[i] = new int[n+1-(1<<i)][4];
		for(int i = 0;i<n;i++)
			rmqy[0][i] = new int[] {i,-1,i,-1};
		for(int i = 1;i<rmqy.length;i++)
			for(int j = 0;j<rmqy[i].length;j++)
				rmqy[i][j] = combine(rmqy[i-1][j],rmqy[i-1][j+(1<<(i-1))],y);
		
		PrintWriter pw = new PrintWriter(System.out);
		
		for(int i = 0;i<q;i++)
		{
			st = new StringTokenizer(file.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			int[] xq = query(a,b,rmqx,x,true);
			int[] yq = query(a,b,rmqy,y,false);
			int[] combined = new int[xq.length+yq.length];
			int it = 0;
			for(int j = 0;j<xq.length;j++)
				combined[it++] = xq[j]; 
			for(int j = 0;j<yq.length;j++)
				combined[it++] = yq[j];
			int min = Integer.MAX_VALUE;
			//System.out.println(Arrays.toString(xq));
			//System.out.println(Arrays.toString(yq));
			for(int j = 0;j<combined.length;j++)
			{
				min = Math.min(min,getSquare(combined,j,x,y));
			}
			pw.println(min);
		}
		pw.close();
		//97959 98069
	}
	
	public int getSquare(int[] indices, int exclude, int[] x, int[] y)
	{
		if(indices.length<=2)
			return 0;
		int minx = Integer.MAX_VALUE;
		int miny = Integer.MAX_VALUE;
		int maxx = Integer.MIN_VALUE;
		int maxy = Integer.MIN_VALUE;
		for(int i = 0;i<indices.length;i++)
		{
			if(indices[i]==indices[exclude]||indices[i]==-1)
				continue;
			minx = Math.min(minx, x[indices[i]]);
			miny = Math.min(miny, y[indices[i]]);
			maxx = Math.max(maxx, x[indices[i]]);
			maxy = Math.max(maxy, y[indices[i]]);
		}
		return Math.max(maxx-minx,maxy-miny);
	}
	
	public int[] query(int low,int high,int[][][] rmq, int[] ints,boolean x)// make correction for 1 or 2 points
	{
		int level = (int)Math.floor(Math.log(high-low+1)/Math.log(2));
		if(Integer.bitCount(high-low+1)==1)
			return rmq[level][low];
		//print(rmq,x,level,low);
		int newlow = low+(1<<level);
		return combine(rmq[level][low],query(newlow,high,rmq,ints,x),ints);
	}
	
	public void print(int[][][] rmq, boolean x, int level, int low)
	{
		System.out.println("rmq "+(x?"x ":"y ")+low+" to "+(low-1+(1<<level))+" is "+Arrays.toString(rmq[level][low]));
	}
	
	public int[] combine(int[] a,int[] b, int[] ints)
	{
		int sum = 0;
		for(int i = 0;i<4;i++)
		{
			if(a[i] != -1)
				sum++;
			if(b[i] != -1)
				sum++;
		}
		Integer[] indices = new Integer[sum];
		int x = 0;
		for(int i = 0;i<4;i++)
		{
			if(a[i]!=-1)
				indices[x++] = a[i];
			if(b[i]!=-1)
				indices[x++] = b[i];
		}
		Comparator<Integer> comp = new Comparator<Integer>() {

			@Override
			public int compare(Integer a, Integer b) {
				return ints[a]-ints[b];
			}
			
		};
		Arrays.sort(indices,comp);
		int[] ans = new int[4];
		ans[0] = indices[0];
		ans[2] = indices[indices.length-1];
		x = 1;
		while(indices[x]==ans[0])
			x++;
		ans[1] = indices[x];
		x = indices.length-2;
		while(indices[x]==ans[2])
			x--;
		ans[3] = indices[x];
		return ans;
	}
	
}
