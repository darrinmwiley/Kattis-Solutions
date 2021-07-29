import java.math.BigInteger;
import java.util.Scanner;

public class FlipFive {

	public void run()
	{
		Scanner in = new Scanner(System.in);
		int numCases = in.nextInt();
		for (int zz = 0; zz < numCases; zz++)
		{
			boolean[][] board = new boolean[3][3];
			for (int i = 0;  i < 3; i++)
			{
				char[] line = in.next().toCharArray();
				for (int e = 0; e < 3; e++)
				{
					board[i][e] = line[e] == '*';
				}
			}
			int min = flip(0, board, 0);

			System.out.println(min);
		}
	}

	public int flip(int x, boolean[][] board, int n)
	{
		if (allWhite(board))
		{
			return n;
		}
		if (x == 9)
		{
			return 1000;
		}
		int r = x / 3;
		int c = x % 3;
		int min = flip(x+1, board, n);
		board[r][c] = !board[r][c];
		if (inBounds(r-1, c, board))
		{
			board[r-1][c] = !board[r-1][c];
		}
		if (inBounds(r+1, c, board))
		{
			board[r+1][c] = !board[r+1][c];
		}
		if (inBounds(r, c-1, board))
		{
			board[r][c-1] = !board[r][c-1];
		}
		if (inBounds(r, c+1, board))
		{
			board[r][c+1] = !board[r][c+1];
		}
		min = Math.min(flip(x+1, board, n+1), min);
		board[r][c] = !board[r][c];
		if (inBounds(r-1, c, board))
		{
			board[r-1][c] = !board[r-1][c];
		}
		if (inBounds(r+1, c, board))
		{
			board[r+1][c] = !board[r+1][c];
		}
		if (inBounds(r, c-1, board))
		{
			board[r][c-1] = !board[r][c-1];
		}
		if (inBounds(r, c+1, board))
		{
			board[r][c+1] = !board[r][c+1];
		}
		return min;
	}

	public boolean inBounds(int r, int c, boolean[][] mat)
	{
		return r >= 0 && r < mat.length && c >= 0 && c < mat[0].length;
	}

	public boolean allWhite(boolean[][] board)
	{
		for (int i = 0; i < board.length; i++)
		{
			for (int e = 0; e < board.length; e++)
			{
				if (board[i][e])
				{
					return false;
				}
			}
		}
		return true;
	}

	//2 3 5
	//1 2 3 4
	//399 772 163 959 242

	public static void main(String[] args)
	{
		new FlipFive().run();
	}

}
