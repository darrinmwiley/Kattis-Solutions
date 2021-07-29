package kattis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bookcircle {

	int[] pred;
	
	public static void main(String[] args)
	{
		new bookcircle().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int B = file.nextInt();
		int G = file.nextInt();
		HashMap<String, Integer> index = new HashMap<String,Integer>();
		HashMap<String,ArrayList<Integer>> edges = new HashMap<String,ArrayList<Integer>>();
		node[] nodes = new node[B+G+2];
		for(int i = 0;i<B+G;i++)
		{
			String name = file.next();
			int N = file.nextInt();
			index.put(name, index.size());
			for(int j = 0;j<N;j++)
			{
				String book = file.next();
				if(!edges.containsKey(book))
					edges.put(book, new ArrayList<Integer>());
				edges.get(book).add(index.get(name));
			}
		}
		for(int i = 0;i<nodes.length;i++)
			nodes[i] = new node(i);
		
		for(int i = 0;i<B;i++)
		{
			nodes[0].con(i+1,1);
			nodes[i+1].con(0,0);
		}
		for(int i = 0;i<G;i++)
		{
			nodes[B+1+i].con(nodes.length-1,1);
			nodes[nodes.length-1].con(B+1+i,0);
		}
		for(String s:edges.keySet())
		{
			ArrayList<Integer> list = edges.get(s);
			int a = list.get(0);
			int b = list.get(1);
			nodes[a+1].con(b+1,1);
			nodes[b+1].con(a+1,0);
		}
		
		int x = 0;
		
		while(aug(nodes))
		{
			x++;
			traceBack(nodes);
		}
		System.out.println(x);
	}
	
	public boolean aug(node[] nodes)
	{
		pred = new int[nodes.length];
		boolean[] vis = new boolean[pred.length];
		vis[0] = true;
		Arrays.fill(pred,-1);
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(0);
		while(!que.isEmpty()&&pred[pred.length-1]==-1)
		{
			int x = que.poll();
			node current = nodes[x];
			for(int i = 0;i<current.con.size();i++)
			{
				if(!vis[current.con.get(i)]&&current.res.get(i)!=0)
				{
					pred[current.con.get(i)] = x;
					que.add(current.con.get(i));
					vis[current.con.get(i)] = true;
				}
			}
		}
		return pred[pred.length-1] != -1;
	}
	
	public void traceBack(node[] nodes)
	{
		int current = pred.length-1;
		while(current!=0)
		{
			node n = nodes[current];
			node p = nodes[pred[current]];
			int npi = n.index.get(p.id);
			int pni = p.index.get(n.id);
			n.res.set(npi, n.res.get(npi)+1);
			p.res.set(pni, p.res.get(pni)-1);
			current = pred[current];
		}
	}
	
	private class node {
		
		ArrayList<Integer> con;
		ArrayList<Integer> res;
		HashMap<Integer,Integer> index;
		int id;
		
		public node(int id)
		{
			this.id = id;
			con = new ArrayList<Integer>();
			res = new ArrayList<Integer>();
			index = new HashMap<>();
		}
		
		public void con(int id, int r)
		{
			con.add(id);
			res.add(r);
			index.put(id,con.size()-1);
		}
		
		
		
	}
	
}
