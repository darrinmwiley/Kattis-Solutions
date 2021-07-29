import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class subseqhard {
	
	public static void main(String[] args) throws IOException
	{
		new subseqhard().run();
	}
	
	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int zz = Integer.parseInt(file.readLine());
		for(int z = 0;z<zz;z++)
		{
			String line;
			while((line = file.readLine()).isEmpty()){}
			int N = Integer.parseInt(line);
			int [] sum = new int[N+1];
			StringTokenizer st = new StringTokenizer(file.readLine());
			//long[] ints = new long[N];
			sum[0] = 0;
			for(int i = 1;i<N+1;i++)
			{
				sum[i] = sum[i-1]+Integer.parseInt(st.nextToken());
				//ints[i-1] = sum[i]-sum[i-1];
			}
			HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
			int ans = 0;
			for(int x:sum){
				if(map.get(x-47)!=null)
					ans+=map.get(x-47);
				if(!map.containsKey(x))
					map.put(x,0);
				map.put(x,map.get(x)+1);
			}
			System.out.println(ans);
		}
	}

}
