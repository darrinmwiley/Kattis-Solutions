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

public class ArrangingHat {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		/*char[] min = "39317".toCharArray();
		char[] current = "11210".toCharArray();
		int edits = 2;
		System.out.println(new ArrangingHat().lowest(min, current, edits));*/
		new ArrangingHat().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int sz = 81;
		int N = file.nextInt();
		int M = file.nextInt();
		file.nextLine();
		char[][] chars = new char[N][];
		for(int i = 0;i<N;i++)
			chars[i] = file.nextLine().toCharArray();
		char[][][] dp = new char[N][sz][M];
		for(int i = 0;i<dp.length;i++)
			for(int j = 0;j<dp[i].length;j++)
				dp[i][j] = null;
		for(int i = 0;i<=80;i++){
			dp[0][i] = lowest(chars[0],i);
		}
		for(int i = 1;i<N;i++){//end
			for(int j = 0;j<=sz-1;j++){//total
				for(int k = 0;k<=j;k++)//left
				{
					char[] potential = dp[i-1][j-k];
					if(potential!=null)
					{
						char[] next = lowest(potential,chars[i],k);
						if(next!=null)
						{
							if(dp[i][j]==null)
								dp[i][j] = next;
							else
								dp[i][j] = smallest(dp[i][j],next);
						}
					}
				}
			}
		}
		int min = -1;
		for(int i = sz-1;i>=0;i--)
		{
			if(dp[N-1][i]!=null)
				min = i;
		}
		//System.out.println(min+" changes");
		Stack<String> st = new Stack<String>();
		trace(st,N-1,min,chars,dp);
		while(!st.isEmpty())
			System.out.println(st.pop());
		/*while(true)
		{
			int a = file.nextInt();
			int b = file.nextInt();
			trace(st,a,b,chars,dp);
			while(!st.isEmpty())
				System.out.println(st.pop());
			System.out.println();
		}*/
		//11,14
	}
	
	public void trace(Stack<String> st, int i, int j, char[][] chars, char[][][] dp)
	{
		if(i==-1)
			return;
		st.push(new String(dp[i][j]));
		int diff = countDifferences(dp[i][j],chars[i]);
		trace(st,i-1,j-diff,chars,dp);
	}
	
	public int countDifferences(char[] a, char[] b)
	{
		int c = 0;
		for(int i = 0;i<a.length;i++)
			if(a[i]!=b[i])
				c++;
		return c;
	}
	
	public char[] smallest(char[] a, char[] b)
	{
		for(int i = 0;i<a.length;i++)
		{
			if(a[i]<b[i])
				return a;
			if(b[i]<a[i])
				return b;
		}
		return a;
	}
	
	char[] lowest(char[] current, int edits){
		char[] ans = current.clone();
		for(int i = 0;i<ans.length;i++)
		{
			if(edits==0)
				return ans;
			if(ans[i]!='0'){
				ans[i] = '0';
				edits--;
			}
		}
		return ans;
	}
	
	boolean greaterOrEqualThan(char[] a, char[] b)
	{
		for(int i = 0;i<a.length;i++)
		{
			if(a[i]>b[i])
				return true;
			if(b[i]>a[i])
				return false;
		}
		return true;
	}
	
	char[] lowest(char[] min,char[] current, int edits)
	{
		HashSet<Integer> changed = new HashSet<Integer>();
		char[] ans = current.clone();
		if(edits==0){
			if(greaterOrEqualThan(ans,min))
			{
				return ans;
			}else
				return null;
		}
		for(int i = 0;i<current.length;i++)
		{
			if(ans[i]!=min[i])
			{
				if(edits>1)
				{
					changed.add(i);
					ans[i] = min[i];
					edits--;
				}else{
					char prev = ans[i];
					ans[i] = min[i];
					if(greaterOrEqualThan(ans,min))
						return ans;
					else if(ans[i]!='9')
					{
						ans[i]++;
						if(ans[i]==prev)
						{
							for(int j = i+1;j<ans.length;j++)
								if(ans[j]!='0')
								{
									ans[j] = '0';
									return ans;
								}
						}
						return ans;
					}
					ans[i] = prev;
					i--;
					if(i==-1)
						return null;
					while(ans[i]=='9')
					{
						i--;
						if(i==-1)
							return null;
					}
					ans[i]++;
					if(changed.contains(i))
					{
						for(int j = i+1;j<ans.length;j++)
							if(ans[j]!='0')
							{
								ans[j] = '0';
								return ans;
							}
					}
					return ans;
				}
			}
			
		}
		return ans;
	}
}