package page;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class Aqueducts {
    
	double pad = Double.MAX_VALUE;
    
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new Aqueducts().run();
    }
    
    public void run() throws NumberFormatException, IOException
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int S = file.nextInt();
        int T = file.nextInt();
        int Q = file.nextInt();
        int[][] hills = new int[N][3];//x,y,h
        for(int i = 0;i<N;i++)
        {
        	hills[i][0] = file.nextInt();
        	hills[i][1] = file.nextInt();
        	hills[i][2] = file.nextInt();
        }
        double[][] dist = new double[N][N];
        for(int i = 0;i<hills.length;i++)
        	for(int j = 0;j<hills.length;j++)
        	{
        		dist[i][j] = Double.MAX_VALUE;
        		double hypot = distance(hills[i][0],hills[i][1],hills[i][2],hills[j][0],hills[j][1],hills[j][2]);
        		if(hypot<=Q&&hills[i][2]!=hills[j][2])
        		{
        			if(hills[i][2]>hills[j][2])
        			{
        				dist[i][j] = hypot;
        			}else {
        				dist[j][i] = hypot;
        			}
        		}
        	}
        for(int k = 0;k<dist.length;k++)
        {
        	for(int i = 0;i<dist.length;i++)
        	{
        		for(int j = 0;j<dist.length;j++)
        		{
        			if(i!=j) {
        				if(dist[i][j]>dist[i][k]+dist[k][j])
        				{
        					dist[i][j] = dist[i][k]+dist[k][j];
        				}
        			}
        		}
        	}
        }
        
        int[] aqueducts = new int[S];
        int[] towns = new int[T];
        for(int i = 0;i<S;i++)
        	aqueducts[i] = file.nextInt();
        for(int i = 0;i<T;i++)
        	towns[i] = file.nextInt();
        
        if(T>S)
        {
        	System.out.println("IMPOSSIBLE");
        	return;
        }
        
        double[][] costs = new double[S][S];
        for(int i =0 ;i<S;i++)
        	for(int j = 0;j<T;j++)
        		costs[i][j] = dist[aqueducts[i]-1][towns[j]-1];//rows are ducts, cols are jobs
        TreeMap<Integer,Integer> map = hungarian(clone(costs));
        double sum = 0;
        for(int x:map.keySet())
        	sum+=costs[x][map.get(x)];
        if(sum>1000000000000.0)
        	System.out.println("IMPOSSIBLE");
        else
        	System.out.println(sum);
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
    
    public double distance(int x, int y, int h, int x2, int y2, int h2)
    {
    	double d1 = Math.hypot(Math.abs(x2-x),Math.abs(y2-y));
    	return Math.hypot(d1,Math.abs(h2-h));
    }
    
}