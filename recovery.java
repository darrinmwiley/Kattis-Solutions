import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class recovery {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new recovery().run();
	}
	
	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		char[] rows = file.next().toCharArray();
		char[] cols = file.next().toCharArray();
		int[][] ints = new int[rows.length][cols.length];
		for(int[] in:ints)
		{
			Arrays.fill(in, 1);
		}
		boolean[] rowflip = new boolean[rows.length];
		boolean[] colflip = new boolean[cols.length];
		int rf = 0;
		int cf = 0;
		for(int i = 0;i<rowflip.length;i++)
		{
			rowflip[i] = (cols.length % 2) != (rows[i]%2);
			if(rowflip[i])
				rf++;
		}
		for(int i = 0;i<colflip.length;i++)
		{
			colflip[i] = rows.length % 2 != cols[i] % 2;
			if(colflip[i])
				cf++;
		}
		if(rf% 2 != cf %2 )
		{
			System.out.println(-1);
			return;
		}
		if(rf >= cf)
		{
			int left = rf;
		outer:
			for(int i = 0;i<rows.length;i++)
			{
				if(rowflip[i])
				{
					for(int j = 0;j<cols.length;j++)
					{
						if(ints[i][j]==1)
						{
							if(colflip[j] || left > cf){
								left--;
								ints[i][j] = 0;
								rowflip[i] = false;
								rf--;
								if(colflip[j]){
									colflip[j] = false;
									cf--;
								}
								continue outer;
							}
						}
					}
				}
			}
		}else{
			int left = cf;
		outer:
			for(int i = 0;i<cols.length;i++)
			{
				if(colflip[i])
				{
					for(int j = 0;j<rows.length;j++)
					{
						if(ints[j][i] == 1)
						{
							if(rowflip[j] || left > rf)
							{
								left--;
								ints[j][i] = 0;
								colflip[i] = false;
								if(rowflip[j])
								{
									rowflip[j] = false;
									rf--;
								}
								continue outer;
							}
						}
					}
				}
			}
		}
		print(ints);
	}	
	
	public void print(int[][] ints)
	{
		for(int i = 0;i<ints.length;i++)
		{
			for(int j = 0;j<ints[i].length;j++)
			{
				System.out.print(ints[i][j]);
			}
			System.out.println();
		}
	}
	
}