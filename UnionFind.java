import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UnionFind {
	
	int[] arr;
	
	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		arr = new int[N];
		Arrays.fill(arr,-1);
		for(int i = 0;i<Q;i++)
		{
			st = new StringTokenizer(file.readLine());
			String q = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(q.equals("?"))
			{
				if(find(a)==find(b))
					out.write("yes\n");
				else
					out.write("no\n");
			}else{
				union(a,b);
			}
		}
		out.flush();
		out.close();
	}
	
	public void union(int a, int b)
	{
		int aroot = find(a);
		int broot = find(b);
		if(aroot == broot)
			return;
		arr[aroot]+=arr[broot];
		arr[broot] = aroot;
	}
	
	public int find(int a)
	{
		if(arr[a]<0)
		{
			return a;
		}
		return arr[a] = find(arr[a]);
	}
	
	
	public static void main(String[] args) throws IOException
	{
		new UnionFind().run();
	}
}

