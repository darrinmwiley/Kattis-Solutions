

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PebbleSolitaire2 {

	int min = 23;

	public static void main(String[] args) throws Exception
	{
		new PebbleSolitaire2().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		file.nextLine();
		for(int i = 0;i<N;i++)
		{
			boolean[] reached = new boolean[1<<23];
			min = 23;
			String s = file.nextLine();
			boolean[] bools = new boolean[23];
			for(int j = 0;j<s.length();j++)
				bools[j] = s.charAt(j)=='o';
			Queue<Integer> que = new LinkedList<Integer>();
			que.offer(hash(bools));
			while(!que.isEmpty())
			{
				int x = que.poll();
				if(reached[x])
					continue;
				reached[x] = true;
				min = Math.min(min,Integer.bitCount(x));
				for(int j = 0;j<21;j++)
				{
					if(set(j+1,x)&&(set(j,x)^set(j+2,x)))
					{
						que.offer(x^(7<<j));
					}
				}
			}
			System.out.println(min);
		}
	}

	public boolean set(int bit, int number)//0 based
	{
		return (number & (1<<bit))!=0;
	}

	public int hash(boolean[] bools)
	{
		int ret = 0;
		for(boolean x:bools)
		{
			ret*=2;
			if(x)
				ret++;
		}
		return ret;
	}

	public boolean[] unhash(int x)
	{
		boolean[] bools = new boolean[23];
		int index = 22;
		while(x!=0)
		{
			bools[index] = (x%2==1);
			x/=2;
			index--;
		}
		return bools;
	}

}
