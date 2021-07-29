import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Addemup{
	
	char[] rep = "012xx59x86".toCharArray();
	
	public static void main(String[] args) throws IOException
	{
		new Addemup().run();
	}
	
	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashSet<Integer> set = new HashSet<Integer>();
		st = new StringTokenizer(file.readLine());
		for(int i = 0;i<N;i++)
		{
			int next = Integer.parseInt(st.nextToken());
			if(set.contains(M-next))
			{
				//System.out.println(set+" "+next);
				System.out.println("YES");
				return;
			}
			int flip = flip(next);
			if(flip != -1 && set.contains(M - flip))
			{
				//System.out.println(set+" "+next+" "+flip);
				System.out.println("YES");
				return;
			}
			//System.out.println(set+" "+next+" "+flip);
			set.add(next);
			if(flip != -1)
				set.add(flip);
		}
		System.out.println("NO");
	}
	
	public int flip(int x)
	{
		String str = x+"";
		String ans = "";
		for(int i = 0;i<str.length();i++) {
			int ch = str.charAt(i) - '0';
			ans += rep[ch];
		}
		if(ans.contains("x"))
			return -1;
		return Integer.parseInt(new StringBuffer(ans).reverse().toString());
	}
	
}
