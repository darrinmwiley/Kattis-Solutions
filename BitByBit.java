

import java.util.Arrays;
import java.util.Scanner;

public class BitByBit {

	public static void main(String[] args) throws Exception
	{
		new BitByBit().run();
	}

	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		while(true)
		{
			file.nextLine();
			if(N==0)
				return;

			int[] ints = new int[32];
			Arrays.fill(ints,2);
			for(int i = 0;i<N;i++)
			{
				String[] next = file.nextLine().split(" ");
				if(next[0].equals("CLEAR"))
				{
					int a = Integer.parseInt(next[1]);
					ints[a] = 0;
				}
				if(next[0].equals("SET"))
				{
					int a = Integer.parseInt(next[1]);
					ints[a] = 1;
				}
				if(next[0].equals("OR"))
				{
					int a = Integer.parseInt(next[1]);
					int b = Integer.parseInt(next[2]);
					ints[a] = or(ints[a],ints[b]);
				}
				if(next[0].equals("AND"))
				{
					int a = Integer.parseInt(next[1]);
					int b = Integer.parseInt(next[2]);
					ints[a] = and(ints[a],ints[b]);
				}
			}
			char[] chars = "01?".toCharArray();
			for(int i = ints.length-1;i>=0;i--)
			{
				System.out.print(chars[ints[i]]);
			}
			System.out.println();
			N = file.nextInt();
		}
	}

	public int or(int a, int b)
	{
		if(a==0)
		{
			switch(b)
			{
			case 0:return 0;
			case 1:return 1;
			case 2:return 2;
			}
		}
		if(a==1)
		{
			return 1;
		}
		if(a==2)
		{
			switch(b)
			{
			case 0:return 2;
			case 1:return 1;
			case 2:return 2;
			}
		}
		System.out.println(a+" or "+b);
		return -1;
	}

	public int and(int a, int b)
	{
		if(a==0)
		{
			return 0;
		}
		if(a==1)
		{
			switch(b)
			{
			case 0:return 0;
			case 1:return 1;
			case 2:return 2;
			}
		}
		if(a==2)
		{
			switch(b)
			{
			case 0:return 0;
			case 1:return 2;
			case 2:return 2;
			}
		}
		System.out.println(a+" and "+b);
		return -1;
	}

}
