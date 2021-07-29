import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class RainbowRoadRace {
	char[] colors = "ROYGBIV".toCharArray();
	HashMap<Character,Integer> mask;

	public static void main(String[] args) {
		new RainbowRoadRace().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		mask = new HashMap();
		for(int i = 0;i<colors.length;i++)
			mask.put(colors[i],1<<i);
		int N = file.nextInt();
		int M = file.nextInt();
		node[] nodes = new node[N];
		for(int i = 0;i<N;i++)
			nodes[i] = new node(i);
		for(int i = 0;i<M;i++)
		{
			int a = file.nextInt() - 1;
			int b = file.nextInt() - 1;
			int c = file.nextInt();
			int d = mask.get(file.next().charAt(0));
			nodes[a].con.add(b);
			nodes[b].con.add(a);
			nodes[a].cost.add(c);
			nodes[b].cost.add(c);
			nodes[a].col.add(d);
			nodes[b].col.add(d);
		}
		state start = new state(0,0,0);
		Queue<state> que = new PriorityQueue<state>();
		que.add(start);
		long[][] d = new long[N][128];
		for(int i =0;i<N;i++)
			Arrays.fill(d[i], Long.MAX_VALUE/4);
		while(!que.isEmpty())
		{
			state s = que.poll();
			int place = s.place;
			int hash = s.hash;
			long cost = s.cost;
			if(d[place][hash] <= cost)
				continue;
			d[place][hash] = cost;
			node n = nodes[place];
			for(int x = 0;x<n.con.size();x++)
			{
				int id = n.con.get(x);
				int additionalCost = n.cost.get(x);
				int newHash = hash | n.col.get(x);
				if(d[id][newHash] > cost + additionalCost)
				{
					state next = new state(id,newHash,cost+additionalCost);
					que.add(next);
				}
			}
		}
		System.out.println(d[0][127]);

	}

	public String bin(int x)
	{
		String ans = Integer.toBinaryString(x);
		while(ans.length()!=7)
			ans = "0"+ans;
		return ans;
	}

	private class state implements Comparable<state>{

		int place;
		int hash;
		long cost;

		public state(int p, int h, long c)
		{
			place = p;
			hash = h;
			cost = c;
		}

		public int compareTo(state s)
		{
			return Long.compare(cost, s.cost);
		}

	}

	private class node{

		ArrayList<Integer> con;
		ArrayList<Integer> cost;
		ArrayList<Integer> col;
		int id;

		public node(int x)
		{
			id = x;
			con = new ArrayList();
			cost = new ArrayList();
			col = new ArrayList();
		}

	}
}
