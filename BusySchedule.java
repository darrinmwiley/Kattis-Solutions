import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class BusySchedule {
	char fakespace = '_';
	HashMap<Character,TreeSet<Integer>> map;

	public static void main(String[] args)
	{
		new BusySchedule().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		while(true)
		{
			file.nextLine();
			if(N==0)
				return;
			time[] t = new time[N];
			for(int i = 0;i<N;i++)
				t[i] = new time(file.nextLine());
			Arrays.sort(t);
			for(time s:t)
				System.out.println(s);
			N = file.nextInt();
			if(N==0)
				return;
			System.out.println();
		}
	}

	private class time implements Comparable<time>{

		boolean am;
		int hr;
		int min;
		String q;

		public time(String str)
		{
			q = str;
			String[] s = str.split("[: ]");
			hr = Integer.parseInt(s[0]);
			min = Integer.parseInt(s[1]);
			am = s[2].equals("a.m.");
		}

		public int compareTo(time o) {
			ArrayList<String> order = new ArrayList<String>();
			String[] strs = "12a.m. 1a.m. 2a.m. 3a.m. 4a.m. 5a.m. 6a.m. 7a.m. 8a.m. 9a.m. 10a.m. 11a.m. 12p.m. 1p.m. 2p.m. 3p.m. 4p.m. 5p.m. 6p.m. 7p.m. 8p.m. 9p.m. 10p.m. 11p.m.".split(" ");
			for(String s:strs)
				order.add(s);
			String a = hr+(am?"a.m.":"p.m.");
			String b = o.hr+(o.am?"a.m.":"p.m.");
			//System.out.println(a+" "+b);
			if(order.indexOf(a)!=order.indexOf(b))
				return order.indexOf(a)-order.indexOf(b);
			else
				return Integer.compare(min, o.min);
		}

		public String toString()
		{
			return q;
		}

	}

}
