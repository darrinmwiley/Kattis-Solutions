import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TenKindsOfPeople {
	int r,c;
	public static void main(String[] args) throws IOException
	{
		new TenKindsOfPeople().run();
	}

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		char[][] chars = new char[r][c];
		for(int i = 0;i<chars.length;i++)
			chars[i] = file.readLine().toCharArray();
		int[][] d = new int[][]{{1,0,-1,0},{0,1,0,-1}};
		UnionFind uf = new UnionFind(r*c);
		for(int i = 0;i<r;i++)
			for(int j = 0;j<c;j++)
				for(int k = 0;k<4;k++)
					if(val(i+d[0][k],j+d[1][k])&&chars[i][j]==chars[i+d[0][k]][j+d[1][k]])
						uf.union(i*c+j,(i+d[0][k])*c+j+d[1][k]);
		int N = Integer.parseInt(file.readLine());
		for(int i = 0;i<N;i++)
		{
			st = new StringTokenizer(file.readLine());
			int r1 = Integer.parseInt(st.nextToken())-1;
			int c1 = Integer.parseInt(st.nextToken())-1;
			int r2 = Integer.parseInt(st.nextToken())-1;
			int c2 = Integer.parseInt(st.nextToken())-1;
			boolean con = uf.find(r1*c+c1)==uf.find(r2*c+c2);
			if(!con)
				System.out.println("neither");
			else
				System.out.println(chars[r1][c1]=='0'?"binary":"decimal");
		}
	}

	public boolean val(int r, int c)
	{
		return Math.min(r,c)>=0&&r<this.r&&c<this.c;
	}

	private class UnionFind
	{
		int[] ints;

		public UnionFind(int N)
		{
			ints = new int[N];
			Arrays.fill(ints,-1);
		}

		public int find(int x)
		{
			if(ints[x]>=0)
				return ints[x] = find(ints[x]);
			return x;
		}

		public void union(int x, int y)
		{
			int px = find(x);
			int py = find(y);
			if(px==py)
				return;
			ints[px]+=ints[py];
			ints[py] = px;
		}
	}
}
