import java.util.ArrayList;
import java.util.Scanner;


public class FunHouse {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int cas = 1;
		while(true)
		{
			int c = file.nextInt();
			int r = file.nextInt();
			file.nextLine();
			if(r+c==0)
				return;
			char[][] chars = new char[r][c];
			for(int i = 0;i<r;i++)
			{
				chars[i] = file.nextLine().toCharArray();
			}
			int sr = -1;
			int sc = -1;
			for(int i = 0;i<chars.length;i++)
				for(int j = 0;j<chars.length;j++)
					if(chars[i][j] == '*')
					{
						sr = i;
						sc = j;
					}
			int dr = 0;
			int dc = 0;
			if(sr == 0)
				dr = 1;
			if(sc == 0)
				dc = 1;
			if(sr==r-1)
				dr = -1;
			if(sc==c-1)
				dc = -1;
			findEnd(chars,sr,sc,dr,dc);
			System.out.println("HOUSE "+cas++);
			for(char[] ch:chars)
			{
				for(char cc:ch)
					System.out.print(cc);
				System.out.println();
			}
		}
	}
	
	public void findEnd(char[][] mat, int r, int c, int dr, int dc)
	{
		char ch = mat[r][c];
		if(ch=='x')
		{
			mat[r][c] = '&';
			return;
		}
		else if(ch=='/')
		{
			if(dr==1)
			{
				findEnd(mat,r,c-1,0,-1);
			}
			if(dr==-1)
			{
				findEnd(mat,r,c+1,0,1);
			}
			if(dc==1)
			{
				findEnd(mat,r-1,c,-1,0);
			}
			if(dc==-1)
			{
				findEnd(mat,r+1,c,1,0);
			}
		}
		else if(ch=='\\')
		{
			if(dr==1)
			{
				findEnd(mat,r,c+1,0,1);
			}
			if(dr==-1)
			{
				findEnd(mat,r,c-1,0,-1);
			}
			if(dc==1)
			{
				findEnd(mat,r+1,c,1,0);
			}
			if(dc==-1)
			{
				findEnd(mat,r-1,c,-1,0);
			}
		}
		else{
			findEnd(mat,r+dr,c+dc,dr,dc);
		}
	}
	
	public static void main(String[] args)
	{
		new FunHouse().run();
	}
	
}
