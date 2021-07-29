/*
Group members:
Biranchi Padhi
Darrin Wiley
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Lexicography{
	
	BufferedReader file;
	PrintWriter pout;
	StringTokenizer st;
	
	long[] fact;

	public static void main(String[] args) throws Exception
	{
		new Lexicography().run();
	}
	
	public void run() throws Exception{
		fact = new long[17];
		fact[0] = fact[1] = 1;
		for(int i = 2;i<fact.length;i++)
		{
			fact[i] = i*fact[i-1];
		}
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringTokenizer st;
		while((line = file.readLine()) != null)
		{
			if(line.equals("# 0"))
				return;
			Scanner scan = new Scanner(line);
			String chars = scan.next();
			long K = scan.nextLong()-1;
			System.out.println(findKthArrangement(chars, K));
		}
	}
	
	public String findKthArrangement(String options, long K)
	{
		//System.out.println(options+"_"+K);
		TreeSet<Character> chars = new TreeSet<Character>();
		for(char ch: options.toCharArray())
			chars.add(ch);
		long totalArrangements = countArrangements(options.toCharArray(), new boolean[options.length()]);
		long passed = 0;
		for(char ch: chars)
		{
			String result = options.replaceFirst(ch+"", "");
			long arrangements = countArrangements(result.toCharArray(), new boolean[result.length()]);
			if(passed + arrangements > K)
			{
				return ch+findKthArrangement(result, K-passed);
			}
			passed += arrangements;
		}
		return "";
	}
	
	long countArrangements(char[] chars, boolean[] used)
	{
		int[] occ = new int[26];
		int available = 0;
		for(int i = 0;i<chars.length;i++)
		{
			if(!used[i])
			{
				occ[chars[i]-'A']++;
				available++;
			}
		}
		long ans = fact[available];
		for(int i:occ)
			ans /= fact[i];
		//System.out.println(Arrays.toString(chars)+" "+ans);
		return ans;
		
	}
	
}