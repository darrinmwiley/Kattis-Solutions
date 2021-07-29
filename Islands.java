import java.util.Scanner;

public class Islands {
	static int di[] = {0, 0, 1, -1};
	static int dj[] = {1, -1, 0, 0};
	static int n, m;
	static boolean[][] vis;
	static char[][] board;
	static boolean ingrid(int i, int j) {
		return i>=0&&i<n&&j>=0&&j<m&&board[i][j]!='W'&&!vis[i][j];
	}
	static int dfs(int i, int j) {
		int sz = board[i][j]=='L'?1:0;
		vis[i][j]=true;
		for (int k = 0; k < 4; k++) {
			int ni = i+di[k], nj = j+dj[k];
			if (ingrid(ni,nj)) sz+=dfs(ni,nj);
		}
		return sz;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		n = input.nextInt(); m = input.nextInt();
		board = new char[n][m];
		vis = new boolean[n][m];
		for (int i = 0; i < n; i++) { 
			String s = input.next();
			board[i] = s.toCharArray();
		}
		int ans = 0;
		for (int i = 0; i < n; i++) for (int j = 0; j < m; j++)
			if (ingrid(i,j) && dfs(i,j)>0) ans++;
		System.out.println(ans);
	}
}
