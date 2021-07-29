package codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class RadioTransmission {

	int[] pred;

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new RadioTransmission().run();
	}

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(file.readLine());
		String line = file.readLine();
		char[] chars = line.toCharArray();
		int chop = pslength(chars);
		int len = chars.length-chop;
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.addAll(factorize(len));
		Collections.sort(list);
		//Collections.reverse(list);
		for(int x:list)
		{
			if(test(chars,x))
			{
				System.out.println(x);
				return;
			}
		}
	}

	public boolean test(char[] chars, int L)
	{
		for(int i = 0;i<L;i++)
		{
			if(!individualTest(chars,i,L))
				return false;
		}
		return true;
	}

	public boolean individualTest(char[] chars, int pos, int L)
	{
		char ch = chars[pos];
		for(int i = pos;i<chars.length;i+=L)
		{
			if(chars[i]!=ch)
				return false;
		}
		return true;
	}

	public TreeSet<Integer> factorize(int N)
	{
		int root = (int) (Math.sqrt(N));
		TreeSet<Integer> ret = new TreeSet<Integer>();
		for(int i = 1;i<=root;i++)
		{
			if(N%i==0) {
				ret.add(i);
				ret.add(N/i);
			}
		}
		return ret;
	}

	public int pslength(char[] chars)
	{
		int[] dp = new int[chars.length];
		int j = 0;
		int i = 1;
		while(i<dp.length)
		{
			if(chars[i]==chars[j])
			{
				j++;
				dp[i] = j;
				i++;
			}else
			{
				if(j!=0)
					j = dp[j-1];
				else {
					dp[i] = j;
					i++;
				}
			}
		}
		while (j > chars.length / 2)
    			j = dp[j - 1];
    		return j;
	}

	public int GCD(int a, int b)
	{
		return new BigInteger(a+"").gcd(new BigInteger(b+"")).intValue();
	}

}
