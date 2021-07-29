package page;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class pokegene {	
	
	HashMap<String,node> map;
	
	public static void main(String[] args) throws Exception
	{ 
		new pokegene().run();
	}
	
	public void run() throws Exception
	{
		map = new HashMap<String,node>();
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		trie trie = new trie();
		String[] strs = new String[N];
		for(int i = 0;i<N;i++) {
			String s = file.readLine().trim();
			strs[i] = s;
			trie.add(s);
		}
		trie.genParents();
		for(int i = 0;i<M;i++)
		{
			st = new StringTokenizer(file.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(file.readLine());
			String[] pokes = new String[P];
			for(int j = 0;j<P;j++)
				pokes[j] = strs[Integer.parseInt(st.nextToken())-1];
			Arrays.sort(pokes);
			int ans = 0;
			for(int j = 0;j<pokes.length-(L-1);j++)
			{
				node a = map.get(pokes[j]);
				node b = map.get(pokes[j+L-1]);
				node lca = trie.LCA(a, b);
				node end = null;
				if(j!=0)
					end = trie.LCA(lca, map.get(pokes[j-1]));
				if(j+L!=pokes.length)
				{
					node lca2 = trie.LCA(lca,map.get(pokes[j+L]));
					if(end==null||end.dep<lca2.dep)
						end = lca2;
				}
				if(end==null)
				{
					ans+=lca.dep;
				}else {
					ans+=lca.dep-end.dep;
				}
			}
			System.out.println(ans);
		}
	}
	
	private class trie{
		
		node head;
		
		public trie()
		{
			head = new node(' ',false,null,0);
		}
		
		public void add(String s)
		{
			head.add(s, 0);
		}
		
		public void genParents()
		{
			for(int i = 1;i<18;i++)
				head.genParent(i);
		}
		
		public node LCA(node na, node nb)
		{
			int da = na.dep;
			int db = nb.dep;
			//let a be the deeper one
			if(da<db)
			{
				node save = na;
				na = nb;
				nb = save;
				int sav = da;
				da = db;
				db = sav;
			}
			int diff = da-db;
			int L = -1;
			int R = da+1;
			node best = null;
			while(R-L>1)
			{
				int M = (R+L)/2;
				node p1 = na.getParent(M);
				node p2 = nb.getParent(M-diff);
				if(p1==p2)
				{
					best = p1;
					R = M;
				}
				else {
					L = M;
				}
			}
			return best;
		}
	}
	
	private class node{
		node[] parents;//keeps every 2^n parent, max is 200,000 so we only need 18
		boolean terminal;
		node[] children;
		char value;
		int dep;
		
		public node(char value,boolean isTerminal, node parent,int depth)
		{
			this.value = value;
			this.terminal = isTerminal;
			parents = new node[18];
			parents[0] = (parent);
			children = new node[26];
			dep = depth;
		}
		
		public void add(String s, int index)
		{
			if(index==s.length())
			{
				map.put(s,this);
				terminal = true;
				return;
			}
			char ch = s.charAt(index);
			if(children[ch-'a']==null)
				children[ch-'a'] = new node(ch,false,this,dep+1);
			children[ch-'a'].add(s, index+1);
		}
		
		public void genParent(int x)
		{
			int len = x-1;
			if(parents[x-1] == null)
				parents[x] = null;
			else
				parents[x] = parents[x-1].parents[x-1];
			for(node n:children)
				if(n!=null)
					n.genParent(x);
		}
		
		public node getParent(int N)
		{
			String bin = Integer.toBinaryString(N);
			node current = this;
			int x = 0;
			for(int i = bin.length()-1;i>=0;i--)
			{
				if(current==null)
					return null;
				if(bin.charAt(i)=='1')
				{
					current = current.parents[x];
				}
				x++;
			}
			return current;
		}
	}

}


