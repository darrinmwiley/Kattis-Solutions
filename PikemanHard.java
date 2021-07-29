import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PikemanHard{

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
		new PikemanHard().run();
	}

	long modpow(long a, long b) {
		return (b != 0)? modpow(a*a%MOD, b/2) * ((b&1) == 1?a:1) % MOD : 1;
	}

	public void run() throws Exception
	{
		file = new BufferedReader(new InputStreamReader(System.in));
		pout = new PrintWriter(System.out);
		st = new StringTokenizer(file.readLine());
		long N = Long.parseLong(st.nextToken());
		long T = Long.parseLong(st.nextToken());
		st = new StringTokenizer(file.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		long T0 = Long.parseLong(st.nextToken());
		Queue<Long> times = new LinkedList<Long>();
		boolean[] vis = new boolean[1000001];
		long[] occ = new long[1000001];
		vis[(int)T0] = true;
		long tPrev = T0;
		times.add(tPrev);
		long loopTime = -1;
		int problemsAdded = 1;
		while(problemsAdded != N)
		{
			long tCurr = (A*tPrev + B) % C + 1;
			if(vis[(int)(tCurr)])
			{
				loopTime = tCurr;
				break;
			}
			vis[(int)(tCurr)] = true;
			times.add(tCurr);
			problemsAdded++;
			tPrev = tCurr;
		}
		if(loopTime == -1)
		{
			ArrayList<Long> times2 = new ArrayList<Long>(times);
			Collections.sort(times2);
			long elapsed = 0;
			long penalty = 0;
			long solved = 0;
			for(int i = 0;i<times2.size();i++)
			{
				if(elapsed + times2.get(i) <= T)
				{
					penalty += elapsed + times2.get(i);
					elapsed += times2.get(i);
					solved++;
				}
			}
			System.out.println(solved+" "+penalty);
		}else {
			long leftToDistribute = N;
			//System.out.println(leftToDistribute+" "+times);
			while(times.peek() != loopTime)
			{
				occ[(int)(long)(times.poll())]++;
				leftToDistribute--;
			}
			int cycleSize = times.size();
			long numFullCycles = leftToDistribute/cycleSize;
			long numLeftOver = leftToDistribute - (numFullCycles * cycleSize);
			int index = 0;
			while(!times.isEmpty())
			{
				long time = times.poll();
				occ[(int)time]+=numFullCycles;
				if(index < numLeftOver)
				{
					occ[(int)time]++;
					index++;
				}
			}
			//for(int i = 0;i<C;i++)
			//{
			//	System.out.print(occ[i+1]+" ");
			//}
			//System.out.println();
			long pro = 0;
			long pen = 0;
			long elapse = 0;
			long div2 = modpow(2, MOD - 2);

			for (long x = 1;x<occ.length;x++) {
				long amt = occ[(int)x];

				if (elapse + 1L*amt*x >= T)
					amt = (T-elapse)/x;

				if (amt != 0) {
					pen = (pen + 1L * amt * (amt + 1) % MOD * div2 % MOD * x % MOD +     1L * amt * elapse % MOD) % MOD;

					pro += amt;
					elapse += 1L*amt*x;
					elapse %= MOD;
				}
			}

			System.out.println(pro+" "+pen);
		}
	}
}
