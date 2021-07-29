import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BeeProblem {

	public static void main(String[] args) throws IOException
	{
		new BeeProblem().run();
	}

	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int H = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] chars = new char[R][];
		for(int i = 0;i<chars.length;i++)
			chars[i] = file.readLine().toCharArray();
		int[][] indices = new int[chars.length][];
		int x = 0;
		for(int r = 0;r<chars.length;r++)
		{
			indices[r] = new int[chars[r].length];
			Arrays.fill(indices[r],-1);
			for(int c = 0;c<chars[r].length;c++)
			{
				if(chars[r][c]=='.' || chars[r][c] == '#')
					indices[r][c] = x++;
			}
		}
		UnionFind uf = new UnionFind(R*C);
		int[][] d = new int[][] {{-1,-1,0,0,1,1},{-1,1,-2,2,-1,1}};
		for(int i = 0;i<R;i++)
			for(int j = 0;j<C;j++)
			{
				int r = i;
				int c = getCol(i,j);
				if(chars[r][c]=='.')
				{
					for(int k = 0;k<6;k++)
					{
						int rr = r+d[0][k];
						int cc = c+d[1][k];
						if(val(rr,cc,chars) && chars[rr][cc]=='.')
							uf.u(indices[r][c],indices[rr][cc]);
					}
				}
			}
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		for(int i = 0;i<indices.length;i++)
			for(int j = 0;j<indices[i].length;j++) {
				if(chars[i][j]=='.' && uf.ints[indices[i][j]]<0)
					sizes.add(-uf.ints[indices[i][j]]);
			}
		Collections.sort(sizes);
		Collections.reverse(sizes);
		int sum = 0;
		int ans = 0;
		while(sum<H)
		{
			sum+=sizes.get(ans++);
		}
		System.out.println(ans);
	}

	public boolean val(int r, int c, char[][] chars)
	{
		if(Math.min(r,c)>=0 && r<chars.length&& c<chars[r].length)
			return true;
		return false;
	}

	public int getCol(int r, int c)
	{
		return c*2+r%2;
	}

	private class UnionFind
	{
		public int[] ints;

		public UnionFind(int N)
		{
			ints = new int[N];
			Arrays.fill(ints, -1);
		}

		public int p(int x)
		{
			if(ints[x] < 0)
				return x;
			return ints[x] = p(ints[x]);
		}

		public void u(int a, int b)
		{
			int pa = p(a);
			int pb = p(b);
			if(pa == pb)
				return;
			ints[pa] += ints[pb];
			ints[pb] = pa;
		}
	}

}
