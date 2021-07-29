import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class AssemblyLine{

	BufferedReader file;
	PrintWriter pout;
	StringTokenizer st;

	long MOD = 1000000007;

	char[] current;
	int[][] result;
	int[][] cost;
	char[] types;

	HashMap<Integer, HashSet<Point>> goalMap;

	HashMap<String, Integer> map = new HashMap<String, Integer>();

	int K;

	public static void main(String[] args) throws Exception
	{
		new AssemblyLine().run();
	}

	public void run() throws Exception
	{
		file = new BufferedReader(new InputStreamReader(System.in));
		pout = new PrintWriter(System.out);
		K = Integer.parseInt(file.readLine());
		st = new StringTokenizer(file.readLine());
		types = new char[K];
		goalMap = new HashMap<>();
		for(int i = 0;i<K;i++)
		{
			types[i] = st.nextToken().charAt(0);
			goalMap.put(types[i]-'a',new HashSet<Point>());
		}
		result = new int[K][K];
		cost = new int[K][K];
		boolean[] present = new boolean[26];
		for(int i = 0;i<types.length;i++)
		{
			st = new StringTokenizer(file.readLine());
			for(int j = 0;j<types.length;j++)
			{
				String[] strs = st.nextToken().split("-");
				cost[i][j] = Integer.parseInt(strs[0]);
				result[i][j] = strs[1].charAt(0)-'a';
				goalMap.get(result[i][j]).add(new Point(i,j));
				present[result[i][j]] = true;
			}
		}
		int N = Integer.parseInt(file.readLine());
		for(int i = 0;i<N;i++)
		{
			current = file.readLine().trim().toCharArray();
			map.clear();
			int[][][] dp = doDP();
			int ans = -1;
			char bestLetter = ' ';
			for(int kk = 0;kk<types.length;kk++)
			{
				int k = types[kk] - 'a';
				if(!present[k])
					continue;
				int candidate = dp[0][current.length-1][k];
				if(ans == -1 || (candidate < ans && candidate != -1))
				{
					ans = candidate;
					bestLetter = (char)('a'+k);
				}
			}
			System.out.println(ans+"-"+bestLetter);
		}

	}

/*
2
a b
3-b 5-b
6-a 2-b
1
bba
0
 */

	public int[][][] doDP()
	{
		int[][][] dp = new int[current.length][current.length][26];
		for(int i = 0;i<dp.length;i++)
		{
			for(int j = 0;j<dp.length;j++)
			{
				for(int k = 0;k<dp[i][j].length;k++)
				{
					if(i == j && current[i] - 'a' == k)
						dp[i][j][k] = 0;
					else
						dp[i][j][k] = Integer.MAX_VALUE/4;
				}
			}
		}
		for(int len = 2;len<=current.length;len++)
		{
			for(int begin = 0;begin+len-1 < current.length;begin++)
			{
				for(int g = 0;g<types.length;g++)
				{
					int goal = types[g] - 'a';
					for(Point p: goalMap.get(goal))
					{
						int i = p.x;
						int j = p.y;
						for(int middle = begin;middle<begin+len-1;middle++)
						{
							int cost1 = dp[begin][middle][types[i]-'a'];
							int cost2 = dp[middle+1][begin+len-1][types[j]-'a'];
							int cost3 = cost1 + cost2 + cost[i][j];
							dp[begin][begin+len-1][goal] = Math.min(cost3, dp[begin][begin+len-1][goal]);
						}
					}
				}

			}
		}
		return dp;
	}

	public int minCost(int L, int R, int goal)
	{
		String state = L+" "+R+" "+goal;
		if(map.containsKey(state))
		{
			return map.get(state);
		}

		if(L == R) {
			if(current[R]-'a' != goal) {
				return -1;
			}
			else
				return 0;
		}
		int ans = -1;
		for(Point p: goalMap.get(goal))
		{
			int i = p.x;
			int j = p.y;
			if(result[i][j] == goal)
			{
				for(int middle = L;middle<R;middle++)
				{
					int cost1 = minCost(L, middle, types[i]-'a');
					int cost2 = minCost(middle+1, R, types[j]-'a');
					if(cost1 != -1 && cost2 != -1)
					{
						int cost3 = cost1 + cost2 + cost[i][j];
						if(ans == -1 || cost3 < ans)
						{
							ans = cost3;
						}
					}
				}
			}
		}
		map.put(state, ans);
		return ans;
	}

}
