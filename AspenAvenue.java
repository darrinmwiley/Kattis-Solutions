package page;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class AspenAvenue {
    
    int N,L,W;
    double distBetween;
    int[] ints;
    
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new AspenAvenue().run();
    }
    
    public void run() throws NumberFormatException, IOException
    {
        Scanner file = new Scanner(System.in);
        N = file.nextInt();
        L = file.nextInt();
        W = file.nextInt();
        distBetween = L/(N/2-1.0);
        ints = new int[N];
        for(int i = 0;i<N;i++)
        {
        	ints[i] = file.nextInt();
        }
        Arrays.sort(ints);
        double[][] dp = new double[ints.length+1][ints.length/2+1];
        double ret = dp(dp,dp.length-1,dp[0].length-1);
        System.out.println(ret);
    }
    
    public double dp(double[][] dp, int used, int bottomFilled)
    {
    	if(used==0)
    		return 0;
    	if(dp[used][bottomFilled]!=0)
    		return dp[used][bottomFilled];
    	int topFilled = used-bottomFilled;
    	double nextTopX = distBetween*(topFilled-1);
    	double nextBottomX = distBetween*(bottomFilled-1);
    	double a = Double.MAX_VALUE;
    	if(topFilled>0)
    		a = Math.abs(ints[used-1]-nextTopX)+dp(dp,used-1,bottomFilled);
    	double b = Double.MAX_VALUE;
    	if(bottomFilled>0){
    		b = dist(ints[used-1],0,nextBottomX,W)+dp(dp,used-1,bottomFilled-1);
    	}
    	dp[used][bottomFilled] = Math.min(a, b);
    	return dp[used][bottomFilled];
    }
    
    public double dist(double x, double y, double x2, double y2)
    {
    	return Math.hypot(Math.abs(x-x2), Math.abs(y-y2));
    }
}