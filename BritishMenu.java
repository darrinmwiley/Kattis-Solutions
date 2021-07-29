import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class BritishMenu {

	int components = -1;
	int z = 0;
	node[] nodes;
	int[] scc;
	scc[] sccs;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new BritishMenu().run();
	}
	
	public static int solve(String s)
	{
		boolean[][] con = new boolean[10][10];
		Scanner file = new Scanner(System.in);
		file.nextInt();
		int M = file.nextInt();
		for(int i = 0;i<M;i++)
		{
			int a = file.nextInt()-1;
			int b = file.nextInt()-1;
			con[a][b] = true;
		}
		int ans = 0;
		for(int i = 0;i<10;i++)
			ans = Math.max(ans,bruteForce(i,con,new boolean[10]));
		return ans;
	}
	
	public static int bruteForce(int start, boolean[][] con, boolean[] vis)
	{
		vis[start] = true;
		int best = 1;
		for(int i = 0;i<10;i++)
			if(con[start][i]&&!vis[i])
			{
				vis[i] = true;
				best = Math.max(best,1+bruteForce(i,con,vis));
				vis[i] = false;
			}
		return best;
	}
	
	public static String genCase()
	{
		String ans = "";
		int con = (int)(Math.random()*50)+1;
		ans+="10 "+con+"\n";
		boolean[][] bools = new boolean[10][10];
		for(int i = 0;i<con;i++)
		{
			int a = (int)(Math.random()*10)+1;
			int b = (int)(Math.random()*10)+1;
			if(a!=b&&!bools[a-1][b-1]){
				bools[a-1][b-1] = true;
				ans+=a+" "+b+"\n";
			}
			else
				i--;
		}
		return ans;
	}
	
	public void run() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		nodes = new node[N];
		for(int i = 0;i<N;i++)
			nodes[i] = new node(i);
		for(int i = 0;i<M;i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			nodes[a].con(b);
			nodes[b].incoming(a);
		}
		scc = scc();
		int[] sizes = new int[components+1];
		for(int x:scc)
			sizes[x]++;
		sccs = new scc[components+1];
		for(int i = 0;i<sccs.length;i++)
		{
			sccs[i] = new scc(i,sizes[i]);
		}
		for(int i = 0;i<nodes.length;i++)
			sccs[scc[i]].add(i);
		for(scc x:sccs)
		{
			x.fillCon();
			x.fillLongestPaths();
		}
		Queue<Integer> order = topological();
		int ans = 1;
		while(!order.isEmpty())
		{
			int current = order.poll();
			scc x = sccs[current];
			for(int i = 0;i<x.size;i++)
			{
				ans = Math.max(ans,x.longestPathStartingWith(i));
			}
		}
		System.out.println(ans);
	}
	
	public Queue<Integer> topological()
	{
		Queue<Integer> zero = new LinkedList<Integer>();
		Queue<Integer> que = new LinkedList<Integer>();
		for(scc s:sccs)
			if(s.outdeg==0)
				zero.add(s.id);
		while(que.size()!=sccs.length){
			int x = zero.poll();
			que.add(x);
			for(int y:sccs[x].incoming){
				sccs[y].outdeg--;
				if(sccs[y].outdeg==0)
					zero.add(y);
			}
		}
		return que;
	}
	
	public int[] scc()
	{
		boolean[] vis = new boolean[nodes.length];
		Stack<Integer> st = new Stack<Integer>();
		for(int i = 0;i<nodes.length;i++)
			if(!vis[i])
				dfs(i,vis,st);
		int[] scc = new int[nodes.length];
		Arrays.fill(scc,-1);
		boolean[] vis2 = new boolean[nodes.length];
		while(!st.isEmpty())
		{
			int i = st.pop();
			if(scc[i]==-1)
			{
				components++;
				revdfs(scc,vis2,i,components);
			}
		}
		return scc;
	}
	
	public void dfs(int v, boolean[] visited, Stack st)
	{
		node current = nodes[v];
		visited[v] = true;
		for(int y:current.con)
		{
			if(!visited[y])
				dfs(y,visited,st);
		}
		st.push(v);
	}
	
	public void revdfs(int[] scc, boolean[] vis, int x, int comp)
	{
		vis[x] = true;
		scc[x] = comp;
		node n = nodes[x];
		for(int y:n.incoming)
			if(!vis[y])
				revdfs(scc,vis,y,comp);
	}
	
	private class node implements Comparable<node>{
		
		int id;
		int num;
		int denom;
		ArrayList<Integer> incoming;
		ArrayList<Integer> con;
		
		public node(int x)
		{
			id = x;
			incoming = new ArrayList<>();
			con = new ArrayList<>();
		}
		
		public void con(int x)
		{
			con.add(x);
		}
		
		public void incoming(int x)
		{
			incoming.add(x);
		}
		
		public int compareTo(node n)
		{
			return Integer.compare(denom, n.denom);
		}

	}
	
	private class scc{
		
		int id;
		int indeg;
		int outdeg;
		int size;
		HashMap<Integer,Integer> nodeToIndex;
		ArrayList<Integer> con;
		HashSet<Integer> con2;
		ArrayList<Integer> incoming;
		int currentIndex;
		int[] ids;
		int[][] lp;
		int[] dp;
		
		public scc(int d, int sz)
		{
			id = d;
			size = sz;
			nodeToIndex = new HashMap<>();
			ids = new int[sz];
			dp = new int[sz];
			lp = new int[sz][sz];
			for(int i = 0;i<sz;i++)
				Arrays.fill(lp[i], -1);
			con = new ArrayList<>();
			incoming = new ArrayList<>();
			con2 = new HashSet<>();
		}
		
		public boolean contains(int x)
		{
			return nodeToIndex.containsKey(x);
		}
		
		public int getNode(int x)
		{
			return ids[x];
		}
		
		public int getIndex(int x)
		{
			return nodeToIndex.get(x);
		}
		
		public void add(int x)
		{
			ids[nodeToIndex.size()] = x;
			nodeToIndex.put(x,nodeToIndex.size());
		}
		
		public void fillLongestPath(int start, int current, int length, boolean[] visited)
		{
			int startIndex = nodeToIndex.get(start);
			int currentIndex = nodeToIndex.get(current);
			visited[current] = true;
			lp[startIndex][currentIndex] = Math.max(lp[startIndex][currentIndex],length);
			node n = nodes[current];
			for(int y:n.con)
				if(contains(y)&&!visited[y])
					fillLongestPath(start,y,length+1,visited);
			visited[current] = false;
		}
		
		public void fillLongestPaths()
		{
			for(int i:ids)
				fillLongestPath(i,i,0,new boolean[nodes.length]);
		}
		
		public void con(int x)
		{
			outdeg++;
			con.add(x);
		}
		
		public void incoming(int x)
		{
			indeg++;
			incoming.add(x);
		}
		
		public void fillCon()
		{
			for(int x: ids)
			{
				for(int y:nodes[x].con)
				{
					if(scc[y]!=id&&!con2.contains(scc[y]))
					{
						
						con2.add(scc[y]);
						con(scc[y]);
						sccs[scc[y]].incoming(id);
					}
				}
			}
		}
		
		public void print()
		{
			System.out.println(id+":");
			System.out.println("Longest path:");
			for(int[] in:lp)
				System.out.println(Arrays.toString(in));
			System.out.println();
			System.out.println("outgoing: "+con);
			System.out.println("incoming: "+incoming);
			System.out.println();
		}
		
		public String toString()
		{
			return id+" "+outdeg;
		}
		
		public int longestPathStartingWith(int index)//called in reverse topographical order
		{
			int longest = 0;
			for(int i = 0;i<size;i++)
			{
				node exit = nodes[ids[i]];
				int rest = 0;
				for(int j = 0;j<exit.con.size();j++){
					node next = nodes[exit.con.get(j)];
					scc nextscc = sccs[scc[next.id]];
					int id2 = nextscc.nodeToIndex.get(next.id);
					if(nextscc==this)
						continue;
					rest = Math.max(rest,nextscc.dp[id2]);
				}
				longest = Math.max(longest,rest+lp[index][i]+1);
			}
			dp[index] = longest;
			return longest;
		}
		
	}
	
}
