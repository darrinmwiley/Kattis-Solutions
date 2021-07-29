import java.util.*;
import java.io.*;

public class KeepItSorted{

	BufferedReader file;
	PrintWriter pout;
	StringTokenizer st;

/*
100
100 99 98 97 96 95 94 93 92 91 90 89 88 87 86 85 84 83 82 81 80 79 78 77 76 75 74 73 72 71 70 69 68 67 66 65 64 63 62 61 60 59 58 57 56 55 54 53 52 51 50 49 48 47 46 45 44 43 42 41 40 39 38 37 36 35 34 33 32 31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
 */

	public static void main(String[] args) throws Exception
	{
		new KeepItSorted().run();
	}

	public void run() throws Exception{
		file = new BufferedReader(new InputStreamReader(System.in));
		pout = new PrintWriter(System.out);
		int N = Integer.parseInt(file.readLine());
		st = new StringTokenizer(file.readLine());
		int[] ints = new int[N];
		//int[] ints = new int[] {1,3,7,2,8,10,9,4,5,6};
		/*for(int i = 0;i<ints.length;i++)
		{
			ArrayList<String> ans = answer2(ints, i);
			System.out.println(ans+" "+ans.size());
		}*/
		for(int i = 0;i<N;i++)
		{
			ints[i] = Integer.parseInt(st.nextToken());
		}
		boolean solved = false;
	loop:
		for(int i = 0;i<N;i++)
		{
			ArrayList<String> answer = answer2(ints, i);
			if(answer.size() < 192)
			{
				System.out.println(answer.size());
				for(String s: answer)
				{
					System.out.println(s);
				}
				solved = true;
				break loop;
			}
		}
		for(int i = 0;i<N;i++)
		{
			ints[i] = N+1-ints[i];
		}
		loop2:
		for(int i = 0;i<N;i++)
		{
			if(solved)
				break;
			ArrayList<String> answer = answer2(ints, i);
			if(answer.size() < 191)
			{
				System.out.println(answer.size()+1);
				for(String s: answer)
				{
					System.out.println(s);
				}
				System.out.println(1+" "+N);
				solved = true;
				break loop2;
			}
		}
		if(!solved)
		{
			ArrayList<String> answer1 = answer1(ints.clone());
			System.out.println(answer1.size());
			for(String s: answer1)
			{
				System.out.println(s);
			}
		}
	}

	public ArrayList<String> answer2(int[] ints, int middle)
	{
		ints = ints.clone();
		ArrayList<String> ans = new ArrayList<String>();
		for(int i = middle+1;i<ints.length;i++)
		{
			if(ints[i] < ints[middle])
			{
				swap(ints, middle, i-1);
				swap(ints, middle, i);
				ans.add(middle+1+" "+(i));
				ans.add(middle+1+" "+(i+1));
				continue;
			}else {
				for(int j = middle;j<i;j++)
				{
					if(ints[j+1] > ints[i] && ints[j] < ints[i])
					{
						swap(ints, j+1, i-1);
						swap(ints, j+1, i);
						ans.add(j+2+" "+(i));
						ans.add(j+2+" "+(i+1));
						break;
					}
				}
			}
		}
		//System.out.println(Arrays.toString(ints));
		for(int i = middle - 1;i>=0;i--)
		{
			if(ints[i] > ints[ints.length - 1])
			{
				swap(ints, i+1, ints.length - 1);
				swap(ints, i, ints.length - 1);
				ans.add(i+2+" "+(ints.length));
				ans.add(i+1+" "+(ints.length));
			}else {
				for(int j = i+1;j<ints.length - 1; j++)
				{
					if(ints[j+1] > ints[i] && ints[j] < ints[i])
					{
						swap(ints, i+1, j);
						swap(ints, i, j);
						ans.add(i+2+" "+(j+1));
						ans.add(i+1+" "+(j+1));
						break;
					}
				}
			}
		}
		//System.out.println(Arrays.toString(ints));
		return clean(ans);
	}

	public ArrayList<String> clean(ArrayList<String> list)
	{
		ArrayList<String> clean = new ArrayList<String>();
		for(String str: list)
		{
			String[] strs = str.split(" ");
			int a = Integer.parseInt(strs[0]);
			int b = Integer.parseInt(strs[1]);
			if(a!=b)
				clean.add(str);
		}
		return clean;
	}

	public ArrayList<String> answer1(int[] ints)
	{
			ArrayList<String> ret = new ArrayList<String>();
			int[] clone = ints.clone();
			while(!sorted(clone))
			{
				int[] dec = findLargestDecreasing(clone);
				swap(clone, dec[0], dec[1]);
				ret.add(dec[0]+1+" "+(dec[1]+1));
			}
			return ret;
	}

	public boolean sorted(int[] ints)
	{
		for(int i = 0;i<ints.length - 1;i++)
		{
			if(ints[i] > ints[i+1])
				return false;
		}
		return true;
	}

	public int[] findLargestDecreasing(int[] ints)
	{
		int best = 0;
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		for(int i = 0;i<ints.length-1;i++)
		{
			int ans = 1;
			int j = i+1;
			for(;j<=ints.length;j++)
			{
				if(j == ints.length || ints[j] > ints[j-1])//sketch
					break;
			}
			if(j-i > best)
			{
				best = j-i;
				candidates.clear();
			}
			if(j - i == best)
			{
				candidates.add(i);
			}
		}
		int rand = candidates.get((int)(Math.random()*candidates.size()));
		return new int[] {rand, rand+best-1};
	}

	public void randomize(int[] ints, int swaps, ArrayList<String> moves)
	{
		for(int i = 0;i<swaps;i++)
		{
			int swap = (int)(Math.random() * (ints.length - 1));
			swap(ints, swap, swap+1);
			moves.add(swap+" "+(swap+1));
		}
	}

	public void swap(int[] ints, int L, int R)
	{
		for(int i = 0;i<=(R-L)/2;i++)
		{
			int save = ints[L+i];
			ints[L+i] = ints[R-i];
			ints[R-i] = save;
		}
	}


}
