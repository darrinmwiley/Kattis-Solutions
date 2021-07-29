import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Herkabe {
	
	final long MOD = 1000000007;
	long[] fact = new long[31];
	
	public static final void main(final String[]arg) throws Exception {
		new Herkabe().run();
	}
	
	public void run() throws IOException
	{
		fact(30);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int numNames = Integer.parseInt(in.readLine());
		String[] names = new String[numNames];
		for (int i = 0; i < numNames; i++)
		{
			names[i] = in.readLine();
		}
		Arrays.sort(names);
		int[] same = new int[numNames+1];
		for (int i = 1; i < numNames; i++)
		{
			int lenMin = Math.min(names[i-1].length(), names[i].length());
			same[i] = lenMin;
			for (int e = 0; e < lenMin; e++)
			{
				if (names[i-1].charAt(e) != names[i].charAt(e))
				{
					same[i] = e;
					break;
				}
			}
		}
//		System.out.println(Arrays.toString(same));
		
		Node root = new Node();
		for (int i = 0; i < numNames; i++)
		{
			root.add(names[i], 0, Math.min(names[i].length(), Math.max(same[i], same[i+1])+1));
		}
		long ans = find(root);
		System.out.println(ans);
		
		in.close();
	}
	
	public long find(Node x)
	{
		if (x.count <= 1)
		{
			return 1;
		}
		int count = x.numChildren;
		if (x.isEnd)
		{
			count++;
		}
		long result = fact[count] % MOD;
		for (int i = 0; i < 26; i++)
		{
			Node n = x.children[i];
			if (n != null)
			{
				result *= find(n);
				result %= MOD;
			}
		}
		return result;
	}
	
	public long fact(int n)
	{
		if (n <= 1)
		{
			fact[n] = 1;
			return 1;
		}
		fact[n] = n * fact(n-1) % MOD;
		return fact[n];
	}
	
	private class Node
	{
		boolean isEnd;
		Node[] children = new Node[26];
		int count;
		int numChildren;
		
		public void add(String s, int i, int n)
		{
			count++;
			if (i == s.length() || i == n)
			{
				isEnd = true;
				return;
			}
			int next = s.charAt(i)-'A';
			if (children[next] == null)
			{
				children[next] = new Node();
				numChildren++;
			}
			children[next].add(s, i+1, n);
		}
	}
}
