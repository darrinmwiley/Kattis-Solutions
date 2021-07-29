package kattis;

import java.util.Arrays;
import java.util.Scanner;

public class FlashingFlourescents {
	
	public static void main(String[] args)
	{
		new FlashingFlourescents().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		String line = file.next();
		int N = line.length();
		int[] d = new int[1<<N];
		Arrays.fill(d,Integer.MAX_VALUE/4);
		d[d.length-1] = 0;
		for(int len = 1;len<=N;len++)
		{
			for(int start = 0;start<N;start++)
			{
				int flip = (1<<len)-1;
				int left = start-len+1;
				int xor = flip<<left;
				if(left<0)
					xor = flip>>(-left);
				for(int i = 0;i<d.length;i++)
				{
					if(d[i]<len)
						d[i^xor] = Math.min(d[i^xor], len);
				}
			}
		}
		System.out.println(d[Integer.parseInt(line,2)]);
	}
}

