import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class APegGameForTwo {
	public static void main(final String[] args)throws Exception {
		new APegGameForTwo().run();
	}

	HashMap<String,Integer> ans = new HashMap<>();

	public void run() {
		Scanner file = new Scanner(System.in);

		int[][] board = new int[5][];
		for(int i = 0; i < 5; i++) {
			board[i] = Arrays.stream(file.nextLine().split("\\s+")).mapToInt(x -> Integer.parseInt(x)).toArray();
		}

		System.out.println(tryJumps(board));
	}


	public int tryJumps(int[][] board)
	{
		String hash = hash(board);
		if(ans.containsKey(hash))
			return ans.get(hash);
		int[][] d = new int[][] {{1,-1,0,1,0,-1},{1,-1,1,0,-1,0}};
		int best = -Integer.MAX_VALUE/4;
		boolean noJump = true;
		for(int i = 0;i<board.length;i++)
		{
			for(int j = 0;j<board[i].length;j++)
			{
				for(int k = 0;k<6;k++)
				{
					int rr = i + d[0][k]*2;
					int cc = j + d[1][k]*2;
					int mr = i + d[0][k];
					int mc = j+ d[1][k];
					if(val(board, rr,cc) && board[mr][mc] != 0 && board[rr][cc] == 0 && board[i][j] != 0)
					{
						int save = board[mr][mc];
						board[mr][mc] = 0;
						board[rr][cc] = board[i][j];
						board[i][j] = 0;
						best = Math.max(board[rr][cc] * save - tryJumps(board),best);
						noJump = false;
						board[mr][mc] = save;
						board[i][j] = board[rr][cc];
						board[rr][cc] = 0;
					}
				}
			}
		}
		if(noJump)
			ans.put(hash,0);
		if(noJump)
			return 0;
		ans.put(hash, best);
		return best;
	}

	public String hash(int[][] board)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<board.length;i++)
		{
			for(int x: board[i])
			{
				sb.append(x+" ");
			}
		}
		return sb.toString().trim();
	}



	public boolean val(int[][] board, int r, int c)
	{
		return Math.min(r,c) >=0 && r<board.length && c <board[r].length;
	}

}
