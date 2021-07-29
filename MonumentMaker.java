import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MonumentMaker{

	long[] ints;
	long[] sum;

	public static void main(String[] args) throws IOException
	{
		new MonumentMaker().run();
	}

	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder("");
		Queue<String> strs = new LinkedList<String>();
	loop:
		for(int i = 0;i<N;i++)
		{
			char[] chars = file.readLine().toCharArray();
			if(i%2 == 0)
			{
				for(int j = 0;j<chars.length;j++)
				{
					if(chars[j] == '.')
					{
						strs.offer(sb.toString());
						sb.delete(0, sb.length());
					}else if(chars[j] == ' ')
					{
						break loop;
					}else {
						sb.append(chars[j]);
					}
				}
			}else {
				for(int j = chars.length - 1;j>=0;j--)
				{
					if(chars[j] == '.')
					{
						strs.offer(sb.toString());
						sb.delete(0, sb.length());
					}else if(chars[j] == ' ')
					{
						break loop;
					}else {
						sb.append(chars[j]);
					}
				}
			}
		}
		if(sb.length() != 0)
			strs.offer(sb.toString());
		int ans = 1;
		int curr = 0;
		boolean begin = true;
		while(!strs.isEmpty())
		{
			String str = strs.poll();
			int needed = str.length() + (begin? 0:1);
			//System.out.println(strs+" "+needed+" "+curr+" "+w);
			begin = false;
			if(needed+curr>w)
			{
				ans++;
				curr = str.length();
			}else {
				curr += needed;
			}
		}
		System.out.println(ans);
	}
}
