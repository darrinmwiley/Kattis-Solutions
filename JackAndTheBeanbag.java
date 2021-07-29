package page;

import java.util.Scanner;

public class JackAndTheBeanbag {

	public static void main(String[] args)
	{
		new JackAndTheBeanbag().run();
	}

	boolean[] uselessFarmers;
	boolean[] visited;
	int best = 0;

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int B = file.nextInt();
		int[] ints = new int[B];
		visited = new boolean[(1<<B)];
		for(int i = 0;i<B;i++)
		{
			ints[i] = file.nextInt();
		}
		int F = file.nextInt();
		uselessFarmers = new boolean[F];

		boolean[][] farmers = new boolean[F][B];
		for(int i = 0;i<F;i++)
		{
			int x = file.nextInt();
			for(int j = 0;j<x;j++)
			{
				farmers[i][file.nextInt()-1] = true;
			}
		}
		fillUselessFarmers(farmers,ints);
		bruteForce(0,B,ints,farmers);
		System.out.println(best);
	}

	public void fillUselessFarmers(boolean[][] farmers, int[] beans)
	{
	loop:
		for(int i = 0;i<farmers.length;i++)
		{
			for(int j = 0;j<beans.length;j++)
			{
				if(farmers[i][j]&&beans[j]!=0)
				{
					uselessFarmers[i] = false;
					continue loop;
				}
			}
			uselessFarmers[i] = true;
		}
	}

	public void bruteForce(int hash, int numBeans, int[] beans , boolean[][] farmers)//needs to be recursive rather than iterative to ensure minimality
	{
		if(visited[hash])
			return;
		visited[hash] = true;
		if(allUseless(farmers,hash))
		{
			best = Math.max(best, getCost(beans, hash));
		}else {
			for(int i = 0;i<numBeans;i++)
				if(!bit(i,hash))
					bruteForce(set(i,hash),numBeans,beans,farmers);
		}
	}

	boolean allUseless(boolean[][] farmers, int hash)
	{
		for(int ff = 0;ff<farmers.length;ff++)
			if(!isUseless(farmers,ff,hash))
				return false;
		return true;
	}

	public boolean isUseless(boolean[][] farmers, int ff,int hash)
	{
		if(uselessFarmers[ff])
			return true;
		boolean[] farmer = farmers[ff];
		for(int i = 0;i<farmer.length;i++)
			if(farmer[i]&&bit(i,hash))
				return true;
		return false;
	}

	public int getCost(int[] beans, int hash)
	{
		int cost = 0;
		for(int i = 0;i<beans.length;i++)
		{
			if(!bit(i,hash))
				cost+=beans[i];
		}
		return cost;
	}

	public boolean bit(int bit, int hash)
	{
		return (hash>>bit)%2==1;
	}

	public int set(int bit, int hash)
	{
		return hash | 1 << bit;
	}
}
