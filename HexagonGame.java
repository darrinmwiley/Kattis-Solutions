package page;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class HexagonGame {
    
	node[] nodes;
	
	int pad = 100000;
	
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new HexagonGame().run();
    }
    
    public void run() throws NumberFormatException, IOException
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        file.nextLine();
        for(int z = 0;z<zz;z++)
        {
        		String[] line1 = file.nextLine().split(" ");//locations
        		String[] line2 = file.nextLine().split(" ");//costs
        		int S = line1.length;
        		int[] locations = new int[S];
        		int[] weights = new int[S];
        		for(int i = 0;i<S;i++)
        		{
        			locations[i] = Integer.parseInt(line1[i]);
        			weights[i] = Integer.parseInt(line2[i]);
        		}
        		int[][] ints = new int[S][];
        		int start = (S+1)/2;
        		int total = 0;
        		for(int i = 0;i<ints.length;i++)
        		{
        			ints[i] = new int[start];
        			total+=ints[i].length;
        			if(i<S/2)
        				start++;
        			else
        				start--;
        		}
        		int x = 1;
        		for(int i = 0;i<ints.length;i++)
        			for(int j = 0;j<ints[i].length;j++)
        				ints[i][j] = x++;
        		nodes = new node[total+1];
        		for(int i = 0;i<nodes.length;i++)
        			nodes[i] = new node(i);
        		for(int i = 0;i<ints.length;i++)
        		{
        			for(int j = 0;j<ints[i].length;j++)
        			{
        				if(i<S/2)
        				{
        					//top half, only connect downward
        					connect(ints[i][j],ints[i+1][j]);
        					connect(ints[i][j],ints[i+1][j+1]);
        				}
        				if(i>S/2)
        				{
        					connect(ints[i][j],ints[i-1][j]);
        					connect(ints[i][j],ints[i-1][j+1]);
        				}
        				if(j!=0)
        				{
        					connect(ints[i][j],ints[i][j-1]);
        				}
        			}
        		}
        		int[] diag1 = new int[S];
        		int[] diag2 = new int[S];
        		int[] horz = new int[S];
        		int d1 = 0;
        		int d2 = S/2;
        		for(int i = 0;i<ints.length;i++)
        		{
        			diag1[i] = ints[i][d1];
        			diag2[i] = ints[i][d2];
        			if(i<S/2)
        				d1++;
        			else
        				d2--;
        			horz[i] = ints[S/2][i];
        		}
        		int[][] d = new int[S][];
        		for(int i = 0;i<S;i++)
        			d[i] = d(locations[i]);
        		int a = min(diag1, d, weights);
        		int b = min(diag2, d, weights);
        		int c = min(horz, d, weights);
        		System.out.printf("Case #%d: %d%n",z+1,Math.min(Math.min(a, b),c));
        }
    }
    
    public int min(int[] ending, int[][] d, int[] weights)
    {
    		int[][] hungarian = new int[ending.length][ending.length];
    		for(int i = 0;i<d.length;i++)
    		{
    			for(int j = 0;j<ending.length;j++)
    			{
    				hungarian[i][j] = d[i][ending[j]]*weights[i];
    			}
    		}
    		TreeMap<Integer,Integer> map = hungarian(clone(hungarian));
    		int sum = 0;
    		for(int x:map.keySet())
    			sum+=hungarian[x][map.get(x)];
    		return sum;
    }
    
    public TreeMap<Integer,Integer> hungarian(int[][] costs)
    {
        //reduce rows
        for(int i = 0;i<costs.length;i++)
        {
            int min = 2*pad;
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
            double min = Double.MAX_VALUE;
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
    
    public boolean[][] mark(TreeMap<Integer,Integer> map,int[][] costs)
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
    
    public TreeMap<Integer,Integer> matching(int[][] costs)
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
    
    public double[][] clone(double[][] res)
    {
        double[][] ret = new double[res.length][];
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
    
    public int[] d(int start)
    {
    		int[] cost = new int[nodes.length];
    		Arrays.fill(cost, -1);
    		Queue<Integer> que = new LinkedList<Integer>();
    		que.add(start);
    		cost[start] = 0;
    		while(!que.isEmpty())
    		{
    			int x = que.poll();
    			for(int y:nodes[x].con)
    			{
    				if(cost[y]==-1) {
    					cost[y] = cost[x]+1;
    					que.add(y);
    				}
    			}
    		}
    		return cost;
    }
    
    public void connect(int a, int b)
    {
    		nodes[a].con(b);
    		nodes[b].con(a);
    }
    
    private class node{
    	
	    	int id;
	    	ArrayList<Integer> con;
	    	
	    	public node(int id)
	    	{
	    		this.id = id;
	    		this.con = new ArrayList<Integer>();
	    	}
	    	
	    	public void con(int x)
	    	{
	    		con.add(x);
	    	}
	    	
	    	public String toString()
	    	{
	    		return id+"";
	    	}
    }
}