package page;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class abandonedanimal {
	
	public static void main(String[] args) throws Exception
	{
		new abandonedanimal().run();
	}
	
	public void run() throws Exception
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		int M = Integer.parseInt(file.readLine());
		ArrayList<item> items = new ArrayList<item>();
		final TreeMap<String,Integer> map = new TreeMap<String,Integer>();
		for(int i = 0;i<M;i++)
		{
			StringTokenizer st = new StringTokenizer(file.readLine());
			items.add(new item(Integer.parseInt(st.nextToken()),st.nextToken()));
		}
		int K = Integer.parseInt(file.readLine());
		for(int i = 0;i<K;i++)
		{
			map.put(file.readLine(),map.size()+1);
		}
		for(int i = 0;i<items.size();i++)
			if(map.get(items.get(i).name)==null)
				items.remove(i--);
		Comparator<item> comp = new Comparator<item>() {
			public int compare(item a, item b)
			{
				int x = Integer.compare(a.store, b.store);
				if(x!=0)
					return x;
				return Integer.compare(map.get(a.name),map.get(b.name));
			}
		};
		int IMPOSSIBLE = 0;
		int UNIQUE = 1;
		int AMBIGUOUS = 2;
		Collections.sort(items, comp);
		int[] dp = new int[map.size()+1];
		dp[0] = UNIQUE;
		for(item x:items)
		{
			int index = map.get(x.name);
			if(dp[index]==AMBIGUOUS)
				continue;
			else if(dp[index]==IMPOSSIBLE)
				dp[index] = dp[index-1];
			else if(dp[index-1]!=IMPOSSIBLE)
				dp[index] = AMBIGUOUS;
		}
		switch(dp[K])
		{
		case 0:System.out.println("impossible");break;
		case 1:System.out.println("unique");break;
		case 2:System.out.println("ambiguous");break;
		}
	}
	
	public class item{
		
		String name;
		int store;
		
		public item(int s, String n)
		{
			name = n;
			store = s;
		}
		
	}
	
	


}


