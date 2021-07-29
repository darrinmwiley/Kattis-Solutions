/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/memorymatch
TAGS: casework
EXPLANATION:
the problem falls into 3 possible cases.

1) you've seen at least 1 of every card type, in this case you can flip them all
2) you've seen both of all but one card type, in this case you can flip them all
3) in any other case, you can only flip cards that you've seen both locations for
END ANNOTATION
*/
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
//
public class RubiksRevenge3 {
	
	
	
	HashMap<Long, Integer> map = new HashMap<Long,Integer>();
	HashMap<Long,Integer> back = new HashMap<Long,Integer>();
	
	int bestSolution = 13;
	
	int[][] moves = new int[][] {
		{5,2,3,4,9,6,7,8,13,10,11,12,1,14,15,16},
		{13,2,3,4,1,6,7,8,5,10,11,12,9,14,15,16},
		{1,6,3,4,5,10,7,8,9,14,11,12,13,2,15,16},
		{1,14,3,4,5,2,7,8,9,6,11,12,13,10,15,16},
		{1,2,7,4,5,6,11,8,9,10,15,12,13,14,3,16},
		{1,2,15,4,5,6,3,8,9,10,7,12,13,14,11,16},
		{1,2,3,8,5,6,7,12,9,10,11,16,13,14,15,4},
		{1,2,3,16,5,6,7,4,9,10,11,8,13,14,15,12},
		{2,3,4,1,5,6,7,8,9,10,11,12,13,14,15,16},
		{4,1,2,3,5,6,7,8,9,10,11,12,13,14,15,16},
		{1,2,3,4,8,5,6,7,9,10,11,12,13,14,15,16},
		{1,2,3,4,6,7,8,5,9,10,11,12,13,14,15,16},
		{1,2,3,4,5,6,7,8,10,11,12,9,13,14,15,16},
		{1,2,3,4,5,6,7,8,12,9,10,11,13,14,15,16},
		{1,2,3,4,5,6,7,8,9,10,11,12,16,13,14,15},
		{1,2,3,4,5,6,7,8,9,10,11,12,14,15,16,13}
	};
	
	int[][][] movemap = new int[16][2][4];//which move, which locations, what should they be;
	
	public static void main(String[] args) throws Exception
	{
		new RubiksRevenge3().run();
	}
	
	public void run()
	{
		for(int i = 0;i<16;i++)
		{
			for(int j = 0;j<16;j++)
			{
				moves[i][j]--;
			}
		}
		for(int i = 0;i<16;i++)
		{
			int[] a = new int[4];
			int[] b = new int[4];
			int x = 0;
			for(int j = 0;j<16;j++)
			{
				if(moves[i][j] != j)
				{
					a[x] = j;
					b[x] = moves[i][j];
					x++;
				}
			}
			movemap[i] = new int[][] {a,b};
		}
		Scanner file = new Scanner(System.in);
		char[][] chars = new char[4][4];
		for(int i = 0;i<4;i++)
		{
			chars[i] = file.next().toCharArray();
		}
		int[][] ints = new int[4][4];
		int[][] solved = new int[][] {{0,0,0,0},{1,1,1,1},{2,2,2,2},{3,3,3,3}};
		for(int i = 0;i<4;i++)
		{
			for(int j = 0;j<4;j++)
			{
				if(chars[i][j] == 'R')
					ints[i][j] = 0;
				if(chars[i][j] == 'G')
					ints[i][j] = 1;
				if(chars[i][j] == 'B')
					ints[i][j] = 2;
				if(chars[i][j] == 'Y')
					ints[i][j] = 3;
			}
		}	
		
		bruteForceQueue(ints);
		bruteForceBack(solved);
		System.out.println(bestSolution);
	}
	
	public void bruteForceQueue(int[][] start)
	{
		long hash = hash(start);
		Queue<state> que = new LinkedList<state>();
		map.put(hash, 0);
		que.add(new state(hash, 0));
		while(!que.isEmpty())
		{
			state st = que.poll();
			hash = st.hash;
			for(int i = 0;i<16;i++)
			{
				long next = move(hash, i);
				if(!map.containsKey(next))
				{
					map.put(next, st.cost+1);
					if(st.cost != 5)
						que.add(new state(next, st.cost+1));
				}
			}
		}
	}
	
	public void bruteForceBack(int[][] start)
	{
		long hash = hash(start);
		Queue<state> que = new LinkedList<state>();
		back.put(hash, 0);
		que.add(new state(hash, 0));
		if(map.containsKey(hash))
		{
			this.bestSolution = Math.min(bestSolution, map.get(hash));
		}
		while(!que.isEmpty())
		{
			state st = que.poll();
			hash = st.hash;	
			for(int i = 0;i<16;i++)
			{
				long next = move(hash, i);
				if(!back.containsKey(next))
				{
					back.put(next, st.cost+1);
					if(st.cost != 6)
						que.add(new state(next, st.cost+1));
					if(map.containsKey(next))
					{
						this.bestSolution = Math.min(bestSolution, map.get(next) + st.cost + 1);
					}
				}
			}
		}
	}
	
	public void print(int[][] ints)
	{
		for(int[] in:ints)
		{
			System.out.println(Arrays.toString(in));
		}
		System.out.println();
	}
	
	public String str(Long l)
	{
		String bin = Long.toBinaryString(l);
		while(bin.length() != 32)
		{
			bin = "0"+bin;
		}
		return bin;
	}
	
	public long move(long hash, int move)
	{
		long ans = hash;
		for(int i = 0;i<4;i++)
		{
			int index1 = movemap[move][0][i];
			int index2 = movemap[move][1][i];
			
			int shouldbe1 = (int) ((hash >> (16-index2)*2 - 1 ) & 1);
			int shouldbe2 = (int) ((hash >> (16-index2)*2 - 2) & 1);
			
			int n1 = (16-index1)*2 - 1;
			int n2 = n1-1;
			
			ans ^= (-shouldbe1 ^ ans) & 1L << n1;
			ans ^= (-shouldbe2 ^ ans) & 1L << n2;
		}
		return ans;
	}
	
	private class state{
		
		long hash;
		int cost;
		
		public state(long h, int c)
		{
			this.hash = h;
			cost = c;
		}
	}
	
	public long hash(int[][] ints)
	{
		long ans = 0;
		for(int i = 0;i<ints.length;i++)
		{
			for(int j = 0;j<ints.length;j++)
			{
				ans <<=2;
				ans += ints[i][j];
			}
		}
		return ans;
	}
	
	public int[][] unhash(long hash)
	{
		int[][] ints = new int[4][4];
		for(int i = ints.length - 1;i>=0;i--)
		{
			for(int j = ints.length - 1;j>=0;j--)
			{
				ints[i][j] = (int) (hash & 3);
				hash >>=2;
			}
		}
		return ints;
	}
	
}