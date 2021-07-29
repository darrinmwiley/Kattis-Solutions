import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ChessCompetition {
	
	node[] nodes;
	int[][] res;
	
	public static void main(String[] args)
	{
		new ChessCompetition().run();
	}
/*
1
5
x.11d
.x1d1
00x.0
0d.x.
d01.x
*/
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int N = file.nextInt();
			file.nextLine();
			char[][] chars = new char[N][N];
			for(int i = 0;i<N;i++)
				chars[i] = file.nextLine().toCharArray();
			boolean[] canWin = new boolean[N];
			for(int i =0; i<N;i++)
				canWin[i] = canWin(chars,i);
			for(int i = 0;i<N;i++)
				if(canWin[i])
					System.out.print(i+1+" ");
		}
	}
	
	public boolean canWin(char[][] chars, int row)
	{
		chars = clone(chars);
		for(int i = 0;i<chars.length;i++)
		{	
			if(chars[row][i]=='.')
			{
				chars[row][i] = '1';
				chars[i][row] = '0';
			}
		}
		int[] scores = score(chars);
		int maxScore = scores[row];
		nodes = new node[chars.length+2];
		res = new int[nodes.length][nodes.length];
		for(int i = 0;i<nodes.length;i++)
			nodes[i] = new node(i);
		int[] input = input(chars);
		for(int i = 0;i<chars.length;i++)
		{
			res[0][i+1] = input[i];
			connect(0,i+1);
			if(maxScore<scores[i])
				return false;
			res[i+1][nodes.length-1] = maxScore-scores[i];
			connect(i+1,nodes.length-1);
			for(int j = i+1;j<chars.length;j++)
			{
				//System.out.println(i+" "+j+" "+nodes.length);
				if(chars[i][j]=='.')
				{
					res[i+1][j+1] = 2;
					connect(i+1,j+1);
				}
			}
		}
		//possible if flow is equal to sum of inputs
		int flow = 0;
		while(true)
		{
			int aug = aug();
			//System.out.println(aug);
			if(aug==-1)
				break;
			else 
				flow+=aug;
				
		}
		int sum = 0;
		for(int x:input)
			sum+=x;
		return flow==sum;
	}
	
	public int aug()
	{
		boolean[] vis = new boolean[nodes.length];
		int[] pred = new int[nodes.length];
		Arrays.fill(pred,-1);
		Queue<Integer> que = new LinkedList<Integer>();
		vis[0] = true;
		que.add(0);
		while(!que.isEmpty())
		{
			int x = que.poll();
			for(int y:nodes[x].con)
			{
				if(res[x][y]>0&&!vis[y])
				{
					vis[y] = true;
					pred[y] = x;
					que.add(y);
				}
			}
		}
		if(vis[nodes.length-1])
			return traceBack(pred);
		else
			return -1;
	}
	
	public int traceBack(int[] pred)
	{
		int current = pred.length-1;
		int min = 5;
		while(pred[current]!=-1)
		{
			int p = pred[current];
			min = Math.min(min,res[p][current]);
			current = pred[current];
		}
		current = pred.length-1;
		while(pred[current]!=-1)
		{
			int p = pred[current];
			res[p][current]-=min;
			res[current][p]+=min;
			current = pred[current];
		}
		return min;
	}
	
	public void connect(int a, int b)
	{
		nodes[a].con.add(b);
		nodes[b].con.add(a);
	}
	
	public int[] input(char[][] chars)
	{
		int[] input = new int[chars.length];
		for(int i = 0;i<chars.length;i++)
			for(int j = i+1;j<chars.length;j++)
			{
				if(chars[i][j]=='.') {
					input[i]+=2;
				}
			}
		return input;
	}
	
	public int[] score(char[][] chars)
	{
		int[] scores = new int[chars.length];
		for(int i = 0;i<scores.length;i++)
		{
			for(int j = 0;j<scores.length;j++)
			{
				if(chars[i][j]=='1')
					scores[i]+=2;
				if(chars[i][j]=='d')
					scores[i]++;
			}
		}
		return scores;
	}
	
	public char[][] clone(char[][] chars)
	{
		char[][] clone = new char[chars.length][chars.length];
		for(int i = 0;i<chars.length;i++)
			clone[i] = chars[i].clone();
		return clone;
	}
	
	private class node{
		
		ArrayList<Integer> con;
		int id;
		
		public node(int id) {
			this.id = id;
			con = new ArrayList<Integer>();
		}
		
	}
	
}

