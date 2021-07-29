import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class bond {
	
	double pad = 100000000.0;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new bond().run();	
	}
	
	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		double[][] orig = new double[N][N];
		double[][] costs = new double[N][N];
		double max = 0;
		for(int i = 0;i<N;i++)
			for(int j = 0;j<N;j++)
			{
				int x = file.nextInt();
				orig[i][j] = x/100.0;
				if(x==0)
					costs[i][j] = 0;
				else{
					costs[i][j] = Math.log(x*3)+pad;
					max = Math.max(costs[i][j],max);
				}
			}
		for(int i = 0;i<N;i++)
			for(int j = 0;j<N;j++)
				costs[i][j] = max-costs[i][j];
		TreeMap<Integer,Integer> map = hungarian(costs);
		double prob = 1.0;
		for(int x:map.keySet())
		{
			prob*=orig[x][map.get(x)];
		}
		System.out.println(prob*100);
	}
	
	public TreeMap<Integer,Integer> hungarian(double[][] costs)
	{
		//reduce rows
		for(int i = 0;i<costs.length;i++)
		{
			double min = 2*pad;
			for(int j = 0;j<costs.length;j++)
			{
				min = Math.min(min,costs[i][j]);
			}
			for(int j = 0;j<costs.length;j++)
			{
				costs[i][j]-=min;
			}
		}
		//reduce cols
		for(int j= 0;j<costs.length;j++)
		{
			double min = 2*pad;
			for(int i = 0;i<costs.length;i++)
			{
				min = Math.min(min,costs[i][j]);
			}
			for(int i = 0;i<costs.length;i++)
			{
				costs[i][j]-=min;
			}
		}
		TreeMap<Integer,Integer> map = matching(costs);
		while(map.size()!=costs.length)
		{
			boolean[][] marks = mark(map,costs);
			double min = pad*2;
			for(int i = 0;i<costs.length;i++)
				for(int j = 0;j<costs.length;j++)
					if(!marks[0][i]&&!marks[1][j])
						min = Math.min(costs[i][j],min);
			for(int i = 0;i<costs.length;i++)
				for(int j = 0;j<costs.length;j++)
					if(!marks[0][i]&&!marks[1][j])
						costs[i][j]-=min;
					else if(marks[0][i]&&marks[1][j])
						costs[i][j]+=min;
			map = matching(costs);
		}
		return map;
	}
	
	public boolean[][] mark(TreeMap<Integer,Integer> map,double[][] costs)
	{
		boolean[] tickRows = new boolean[costs.length];
		boolean[] tickCols = new boolean[costs.length];
		Arrays.fill(tickRows, true);
		for(int x:map.keySet())
			tickRows[x] = false;
		boolean changed = false;
		do{
			changed = false;
			for(int i = 0;i<costs.length;i++)
			{
				for(int j = 0;j<costs.length;j++)
				{
					if(!tickCols[j]&&costs[i][j]==0&&tickRows[i])
					{
						tickCols[j] = true;
						changed = true;
					}
				}
			}
			for(int x:map.keySet())
			{
				if(tickCols[map.get(x)])
				{
					tickRows[x] = true;
				}
			}
		}while(changed);
		boolean[][] ret = new boolean[2][costs.length];
		for(int i = 0;i<tickRows.length;i++)
			ret[0][i] = !tickRows[i];
		ret[1] = tickCols;
		return ret;
	}
	
	public TreeMap<Integer,Integer> interpret(int[][] res, int[][] orig,node[] nodes)
	{
		TreeMap<Integer,Integer> ret = new TreeMap<Integer,Integer>();
		int N = (nodes.length-2)/2;
		for(int i = 1;i<nodes.length/2;i++)
		{
			node n = nodes[i];
			for(int x:n.con)
				if(orig[n.id][x]>res[n.id][x])
					ret.put(n.id-1,x-nodes.length/2);
		}
		return ret;
	}
	
	public TreeMap<Integer,Integer> matching(double[][] costs)
	{
		//System.out.println("matching");
		//col = job, row = employee
		node[] nodes = new node[costs.length*2+2];
		for(int i = 0;i<nodes.length;i++)
			nodes[i] = new node(i);
		//start,employee,job,end
		for(int i = 0;i<costs.length;i++)
		{
			nodes[0].con(i+1);
			nodes[i+1+costs.length].con(nodes.length-1);
			for(int j = 0;j<costs.length;j++)
			{
				if(costs[i][j]==0)
				{
					nodes[i+1].con(j+1+costs.length);
					nodes[j+1+costs.length].con(i+1);
				}
			}
		}
		int[][] res = new int[nodes.length][nodes.length];
		for(node n:nodes)
			for(int x:n.con)
				if(x>n.id)
				res[n.id][x] = 1;
		int[][] clone = clone(res);
		int[] pred = new int[nodes.length];
		while(aug(nodes,res,pred))
			traceback(pred,res);
		TreeMap<Integer,Integer> map = interpret(res,clone,nodes);
		return map;
	}
	
	public int[][] clone(int[][] res)
	{
		int[][] ret = new int[res.length][];
		for(int i = 0;i<ret.length;i++)
			ret[i] = res[i].clone();
		return ret;
	}
	
	public boolean aug(node[] nodes, int[][] res, int[] pred)
	{
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(0);
		Arrays.fill(pred,-1);
		boolean[] vis = new boolean[nodes.length];
		while(!que.isEmpty())
		{
			int x = que.poll();
			if(vis[x])
				continue;
			vis[x] = true;
			node n = nodes[x];
			for(int y:n.con)
				if(res[x][y]!=0&&!vis[y])
				{
					pred[y] = x;
					que.add(y);
				}
		}
		return pred[pred.length-1]!=-1;
	}
	
	public void traceback(int[] pred, int[][] res)
	{
		int current = pred.length-1;
		while(pred[current]!=-1)
		{
			res[pred[current]][current]--;
			res[current][pred[current]]++;
			current = pred[current];
		}
	}
	
	private class node{
		
		ArrayList<Integer> con;
		int id;
		
		public node(int id)
		{
			this.id = id;
			con = new ArrayList<Integer>();
		}
		
		public void con(int i)
		{
			con.add(i);
		}
		
	}
	
}
