import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BuzzWords {
	int[] dp;
	
	public static final void main(final String[]arg) throws Exception {
		new BuzzWords().run();
	}
	
	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		while((line = file.readLine())!=null)
		{
			char[] chars = line.replace(" ","").toCharArray();
			dp = new int[chars.length+1];
			node root = new node(0,dp);
			for(int i = 0;i<chars.length;i++)
			{
				root.add(chars, i);
			}
			for(int i = 1;i<=chars.length;i++)
			{
				int max = dp[i];
				if(max==1)
				{
					System.out.println();
					break;
				}else
					System.out.println(max);
			}
		}
	}
	
	private class node{
		
		int ct;
		node[] nodes;
		ArrayList<Integer> children;
		int dep;
		int[] max;
		
		public int max(int len)
		{
			if(len==0)
				return ct;
			int max = 0;
			for(int x:children)
				max = Math.max(nodes[x].max(len-1),max);
			return max;
		}
		
		public node(int d, int[] dop)
		{
			dep = d;
			this.max = dop;
			nodes = new node[26];
			ct = 0;
			children = new ArrayList<Integer>();
		}
		
		public void add(char[] chars, int i)
		{
			ct++;
			max[dep] = Math.max(ct,max[dep]);
			if(i==chars.length)
				return;
			int ch = chars[i]-65;	
			if(nodes[ch]==null) {
				nodes[ch] = new node(dep+1,max);
				children.add(ch);
			}
			nodes[ch].add(chars, i+1);
		}
		
		public int occur(char[] chars, int i)
		{
			if(i==chars.length-1)
				return ct;
			return nodes[chars[i]-65].occur(chars,i+1);
		}
		
	}
}
