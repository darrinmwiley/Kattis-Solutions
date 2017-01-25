import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mafija {
	
	boolean[] vis;
	int[] indeg;
	int[] acc;
	
	int ans = 0;
	
	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		vis = new boolean[N];
		indeg = new int[N];
		acc = new int[N];
		for(int i = 0;i<N;i++)
		{
			acc[i] = Integer.parseInt(file.readLine())-1;
			indeg[acc[i]]++;
		}
		for(int i = 0;i<N;i++)
			if(indeg[i]==0)
				color(i,true);
		for(int i = 0;i<N;i++)
			color(i,false);
		System.out.println(ans);
	}
	
	public void color(int start, boolean mafia)
	{
		if(vis[start])
			return;
		vis[start] = true;
		if(mafia)
			ans++;
		int accused = acc[start];
		if(--indeg[accused]==0||mafia)
			color(accused,!mafia);
	}

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new Mafija().run();
	}
}
