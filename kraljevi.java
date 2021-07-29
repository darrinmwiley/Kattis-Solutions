package codejam;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class kraljevi {

	int[] pred;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new kraljevi().run();
	}
	
	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		int R = file.nextInt();
		int C = file.nextInt();
		file.nextLine();
		char[][] chars = new char[R][C];
		
		//solve for S first;
		for(int i = 0;i<R;i++)
		{
			chars[i] = file.nextLine().toCharArray();
		}
		char[][] rot = rotate(chars);
		long dist = getTriangleSum(chars,'M');
		dist+= getTriangleSum(rot,'M');
		dist-=getOverlap(chars,'M');
		System.out.print(dist+" ");
		dist = getTriangleSum(chars,'S');
		dist+= getTriangleSum(rot,'S');
		dist-=getOverlap(chars,'S');
		System.out.println(dist);
	}
	
	public char[][] rotate(char[][] chars)
	{
		char[][] ret = new char[chars[0].length][chars.length];
		for(int i = 0;i<ret.length;i++)
			for(int j = 0;j<ret[i].length;j++)
				ret[i][j] = chars[j][i];
		return ret;
	}
	
	public long getOverlap(char[][] chars, char ch)
	{
		int R = chars.length;
		int C = chars[0].length;
		long[][] diag1 = new long[R][C];
		for(int i = 0;i<C;i++)
			if(chars[0][i]==ch)
				diag1[0][i] = 1;
		for(int i = 0;i<R;i++)
			if(chars[i][0]==ch)
				diag1[i][0] = 1;
		for(int i = 1;i<R;i++)
			for(int j = 1;j<C;j++)
			{
				diag1[i][j] = diag1[i-1][j-1];
				if(chars[i][j]==ch)
					diag1[i][j]++;
			}
		long[][] distdiag1 = new long[R][C];
		for(int i = 1;i<R;i++)
			for(int j = 1;j<C;j++)
				distdiag1[i][j] = distdiag1[i-1][j-1]+diag1[i-1][j-1];
		
		long[][] diag2 = new long[R][C];
		for(int i = 0;i<C;i++)
			if(chars[R-1][i]==ch)
				diag2[R-1][i] = 1;
		for(int i = 0;i<R;i++)
			if(chars[i][0]==ch)
				diag2[i][0] = 1;
		for(int i = R-2;i>=0;i--)
			for(int j = 1;j<C;j++) 
			{
				diag2[i][j] = diag2[i+1][j-1];
				if(chars[i][j]==ch)
					diag2[i][j]++;
			}
		long[][] distdiag2 = new long[R][C];
		for(int i = R-2;i>=0;i--)
		{
			for(int j = 1;j<C;j++)
			{
				distdiag2[i][j] = distdiag2[i+1][j-1]+diag2[i+1][j-1];
			}
		}
		long sum = 0;
		for(int i = 0;i<R;i++)
		{
			for(int j = 0;j<C;j++)
			{
				if(chars[i][j]==ch)
				{
					sum+=distdiag1[i][j];
					sum+=distdiag2[i][j];
				}
			}
		}
		return sum;
	}

	
	public long getTriangleSum(char[][] chars,char ch)
	{
		int R = chars.length;
		int C = chars[0].length;
		long[][] diag1 = new long[R][C];
		for(int i = 0;i<C;i++)
			if(chars[0][i]==ch)
				diag1[0][i] = 1;
		for(int i = 0;i<R;i++)
			if(chars[i][0]==ch)
				diag1[i][0] = 1;
		for(int i = 1;i<R;i++)
			for(int j = 1;j<C;j++)
			{
				diag1[i][j] = diag1[i-1][j-1];
				if(chars[i][j]==ch)
					diag1[i][j]++;
			}
		long[][] distdiag1 = new long[R][C];
		for(int i = 1;i<R;i++)
			for(int j = 1;j<C;j++)
				distdiag1[i][j] = distdiag1[i-1][j-1]+diag1[i-1][j-1];
		
		long[][] diag2 = new long[R][C];
		for(int i = 0;i<C;i++)
			if(chars[R-1][i]==ch)
				diag2[R-1][i] = 1;
		for(int i = 0;i<R;i++)
			if(chars[i][0]==ch)
				diag2[i][0] = 1;
		for(int i = R-2;i>=0;i--)
			for(int j = 1;j<C;j++) 
			{
				diag2[i][j] = diag2[i+1][j-1];
				if(chars[i][j]==ch)
					diag2[i][j]++;
			}
		long[][] distdiag2 = new long[R][C];
		for(int i = R-2;i>=0;i--)
		{
			for(int j = 1;j<C;j++)
			{
				distdiag2[i][j] = distdiag2[i+1][j-1]+diag2[i+1][j-1];
			}
		}
		
		long[][] triangle = new long[R][C];
		for(int i = 0;i<R;i++)
			if(chars[i][0]==ch)
				triangle[i][0] = 1;
		for(int i = 0;i<R;i++)
			for(int j = 1;j<C;j++)
			{
				triangle[i][j] = triangle[i][j-1];
				if(chars[i][j]==ch)
					triangle[i][j]++;
				if(i!=0)
					triangle[i][j]+=diag1[i-1][j-1];
				if(i!=R-1)
					triangle[i][j]+=diag2[i+1][j-1];
			}
		long[][] disttriangle = new long[R][C];
		for(int i = 0;i<R;i++)
			for(int j = 1;j<C;j++)
			{
				disttriangle[i][j] = disttriangle[i][j-1]+triangle[i][j-1];
				disttriangle[i][j]+=distdiag1[i][j];
				disttriangle[i][j]+=distdiag2[i][j];
			}
		long sum = 0;
		for(int i = 0;i<R;i++)
		{
			for(int j = 0;j<C;j++)
			{
				if(chars[i][j]==ch)
					sum+=disttriangle[i][j];
			}
		}
		return sum;
	}
	
}