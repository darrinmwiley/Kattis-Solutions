import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.awt.Point;

public class ComponentGame {
	static int n, m;
	static int di[] = {0,-1,1};
	static int dj[] = {-1,0,0};
	static int[][] map;
	static int[] dsu;
	static int[] sz;
	static boolean[] ac;
	
	static boolean in(int i, int j) {
		return i>=0&&i<n&&j>=0&&j<m;
	}
	
	static void init() {
		for (int i = 0; i < n*m; i++) dsu[i]=i;
		Arrays.fill(sz, 1);
		Arrays.fill(ac, false);
	}
	static int root(int i) {return dsu[i]=dsu[i]==i?dsu[i]:root(dsu[i]);}
	static int merge(int i, int j) {
		i=root(i); j=root(j);
		if (i==j) return 0;
		if (sz[i]<sz[j]) {i^=j;j^=i;i^=j;}
		dsu[j]=i; sz[i]+=sz[j];
		return -1;
	}
	
	static int activate(int i, int j) {
		int res = 1;
		ac[i*m+j] = true;
		for (int k = 0; k < 3; k++) {
			int ni = i+di[k], nj = j+dj[k];
			if (in(ni,nj) && map[ni][nj]==map[i][j]&&ac[ni*m+nj]) {
				int d = merge(i*m+j, ni*m+nj);
				res += d;
			}
		}
		return res;
	}
	
	static void solve(int[] w, int[] b, int[] secret) {
		boolean[] found = new boolean[n*m];
		int x = 0; int y = 0;
		for (int j = 0; j < m; j++) {
			w[j]=x; b[j]=y;
			if (j>0) for (int i = 0; i < n; i++) {
				int ii = root(i*m+j-1);
				if (!found[ii] && map[i][j-1]==1) {
					found[ii]=true;
					secret[j]++;
				}
			}
			for (int i = 0; i < n; i++) {
				if (j>0 && map[i][j-1]==1) found[root(i*m+j-1)]=false;
				if (map[i][j]==0)
					x+=activate(i,j);
				else
					y+=activate(i,j);
			}
		}
	}
	
	static boolean large(Point a, Point b) {
		return a.x+a.y>b.x+b.y || (a.x+a.y==b.x+b.y && a.x>=b.x);
	}
	
	static Point max(Point a, Point b) {
		return large(a,b)?a:b;
	}
	
	static void reverse(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n-1-i; i++) {
			arr[i]^=arr[n-1-i]; arr[n-1-i]^=arr[i]; arr[i]^=arr[n-1-i];
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(input.readLine());
		n = Integer.parseInt(token.nextToken());
		m = Integer.parseInt(token.nextToken());
		map=new int[n][m]; dsu = new int[n*m]; sz = new int[n*m];
		ac = new boolean[n*m];
		for (int i = 0; i < n; i++) {
			String s = input.readLine();
			for (int j = 0; j < m; j++)
				map[i][j]=(s.charAt(j)=='1'?1:0);
		}
		Point ans = new Point(0,0);
		init();
		int[] 	w1 = new int[m], w2 = new int[m], 
				b1 = new int[m], b2 = new int[m],
				s1 = new int[m], s2 = new int[m];
		solve(w1,b1,s1);
		for (int i = 0; i < n; i++) reverse(map[i]);
		init();
		solve(w2,b2,s2); 
		reverse(w2); reverse(b2); reverse(s2); 
		for (int i = 0; i < m; i++) {
			int a = w1[i]+w2[i], b = b1[i]+b2[i]+1-(s1[i]+s2[i]);
			ans = max(ans,new Point(a,b));
		}
		System.out.println(ans.x + " " + ans.y);
	}
}
