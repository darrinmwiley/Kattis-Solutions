import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Dots {
    
	int[] pred;
	
    public void run()
    {
    	
        Scanner file = new Scanner(System.in);
        int N = file.nextInt()-1;file.nextLine();
        char[][] chars = new char[N*2+1][N*2+1];
        for(int i = 0;i<chars.length;i++)
        	chars[i] = file.nextLine().toCharArray();
        int[][] budgets = new int[N][N];
        for(int i = 0;i<budgets.length;i++)
        	Arrays.fill(budgets[i],3);
        for(int i = 1;i<chars.length;i+=2)
        	for(int j = 1;j<chars.length;j+=2)
        	{
        		if(chars[i-1][j]!='.')
        			budgets[i/2][j/2]--;
        		if(chars[i+1][j]!='.')
        			budgets[i/2][j/2]--;
        		if(chars[i][j-1]!='.')
        			budgets[i/2][j/2]--;
        		if(chars[i][j+1]!='.')
        			budgets[i/2][j/2]--;
        	}
        int ans = 0;
        for(int i = 0;i<N;i++)
        {
        	if(chars[0][i*2+1]=='.'&&budgets[0][i]!=0)
        	{
        		chars[0][i*2+1] = '-';
        		budgets[0][i]--;
        		ans++;
        	}
        	if(chars[chars.length-1][i*2+1]=='.'&&budgets[budgets.length-1][i]!=0)
        	{
        		chars[chars.length-1][i*2+1] = '-';
        		budgets[budgets.length-1][i]--;
        		ans++;
        	}
        	if(chars[i*2+1][0]=='.'&&budgets[i][0]!=0)
        	{
        		chars[i*2+1][0] = '|';
        		budgets[i][0]--;
        		ans++;
        	}
        	if(chars[i*2+1][chars.length-1]=='.'&&budgets[i][budgets.length-1]!=0)
        	{
        		chars[i*2+1][chars.length-1] = '|';
        		budgets[i][budgets.length-1]--;
        		ans++;
        	}
        }
        node[] nodes = new node[N*N+2];
        pred = new int[nodes.length];
        int[][] res = new int[nodes.length][nodes.length];
        for(int i = 0;i<nodes.length;i++)
        	nodes[i] = new node(i);
        for(int i = 0;i<N;i++)
        {
        	for(int j = 0;j<N;j++)
        	{
        		if((i+j)%2==0){
        			nodes[0].add(i*N+j+1,nodes);
        			res[0][i*N+j+1] = budgets[i][j];
        		}else{
        			nodes[i*N+j+1].add(nodes.length-1,nodes);
        			res[i*N+j+1][nodes.length-1] = budgets[i][j];
        		}
        		if((i+j)%2==0&&budgets[i][j]!=0)
        		{
	        		if(chars[i*2+1][j*2]=='.')
	        			if(j!=0&&budgets[i][j-1]!=0)
	        			{
	        				nodes[N*i+j+1].add(N*i+j,nodes);
	        				res[N*i+j+1][N*i+j] = 1;
	        			}
	        		if(chars[i*2+1][j*2+2]=='.')
	        			if(j!=budgets.length-1&&budgets[i][j+1]!=0)
	        			{
	        				nodes[N*i+j+1].add(N*i+j+2,nodes);
	        				res[N*i+j+1][N*i+j+2] = 1;
	        			}
	        		if(chars[i*2][j*2+1]=='.')
	        			if(i!=0&&budgets[i-1][j]!=0)
	        			{
	        				nodes[N*i+j+1].add(N*(i-1)+j+1,nodes);
	        				res[N*i+j+1][N*(i-1)+j+1] = 1;
	        			}
	        		if(chars[i*2+2][j*2+1]=='.')
	        			if(i!=budgets.length-1&&budgets[i+1][j]!=0)
	        			{
	        				nodes[N*i+j+1].add(N*(i+1)+j+1,nodes);
	        				res[N*i+j+1][N*(i+1)+j+1] = 1;
	        			}	
        		}
        	}
        }
        while(aug(nodes,res))
        {
        	int current = nodes.length-1;
        	while(pred[current]!=-1)
        	{
        		res[current][pred[current]]++;
        		res[pred[current]][current]--;
        		current = pred[current];
        		
        	}
        	ans++;
        }
        System.out.println(ans+1);
    }
    
    public boolean aug(node[] nodes, int[][] res)
    {
    	Arrays.fill(pred,-1);
    	Queue<Integer> que = new LinkedList();
    	boolean[] vis = new boolean[nodes.length];
    	que.add(0);
    	vis[0] = true;
    	while(!que.isEmpty())
    	{
    		int x = que.poll();
    		for(int y:nodes[x].con)
    			if(!vis[y]&&res[x][y]!=0)
    			{
    				vis[y] = true;
    				pred[y] = x;
    				if(y==nodes.length-1)
    					return true;
    				que.add(y);
    			}
    	}
    	return vis[vis.length-1];
    }
    
    public static void main(String[] args)
    {
        new Dots().run();
    }
    
    private class node{
    	
    	int id;
    	ArrayList<Integer> con;
    	
    	public node(int id)
    	{
    		con = new ArrayList();
    		this.id = id;
    	}
    	
    	public void add(int i,node[] nodes)
    	{
    		con.add(i);
    		nodes[i].add(id,true);
    	}
    	
    	public void add(int i,boolean quit)
    	{
    		con.add(i);
    	}
    	
    }
    
}