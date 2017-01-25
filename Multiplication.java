import java.util.Arrays;
import java.util.Scanner;


public class Multiplication{
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int cas = 1;
		while(true)
		{
			int a = file.nextInt();
			int b = file.nextInt();
			if(a==0&&b==0)
				return;
			int[] ad = new int[(a+"").length()];
			int[] bd = new int[(b+"").length()];
			int ac = a;
			int bc = b;
			for(int i = 0;i<ad.length;i++)
			{
				ad[ad.length-i-1] = ac%10;
				ac/=10;
			}
			for(int i = 0;i<bd.length;i++)
			{
				bd[bd.length-i-1] = bc%10;
				bc/=10;
			}
			int[][] prod = new int[bd.length][ad.length];
			for(int i = 0;i<prod.length;i++)
				for(int j = 0;j<prod[i].length;j++)
				{
					prod[i][j] = ad[j]*bd[i];
				}
			char[][] chars = new char[bd.length*4+5][ad.length*4+5];
			for(int i = 0;i<chars.length;i++)
				Arrays.fill(chars[i],' ');
			for(int i = 0;i<chars.length;i++)
			{
				chars[i][0] = '|';
				chars[i][chars[0].length-1] = '|';
			}
			for(int i = 0;i<chars[0].length;i++)
			{
				chars[0][i] = '-';
				chars[chars.length-1][i] = '-';
			}
			chars[0][0] = '+';
			chars[chars.length-1][0] = '+';
			chars[0][chars[0].length-1] = '+';
			chars[chars.length-1][chars[0].length-1] = '+';
			for(int j = 0;j<bd.length+1;j++)
			{
				for(int i = 2;i<chars[0].length-2;i++)
				{
				if((i-2)%4==0)
					chars[2+j*4][i] = '+';
				else
					chars[2+j*4][i] = '-';
				}
			}
			for(int j = 0;j<ad.length+1;j++)
			{
				for(int i = 2;i<chars.length-2;i++)
				{
					if((i-2)%4==0)
						chars[i][2+j*4] = '+';
					else
						chars[i][2+j*4] = '|';
				}
			}
			
			for(int i = 0;i<bd.length;i++)
				for(int j = 0;j<ad.length;j++)
					for(int k = 0;k<3;k++)
					{
						chars[3+i*4+k][5+j*4-k] = '/';
					}
			
			for(int i = 0;i<ad.length;i++)
				chars[1][4+i*4] = (char)(ad[i]+48);
			
			for(int i = 0;i<bd.length;i++)
				chars[4+i*4][chars[0].length-2] = (char)(bd[i]+48);
			
			int answe = a*b;
			String answer = answe+"";
			int numZeros = 0;
			while(answer.length()!=ad.length+bd.length)
			{
				numZeros++;
				answer = "0"+answer;
			}
			String x = (answer+"").substring(0,bd.length);
			String y = (answer+"").substring(bd.length);
			
			for(int i = numZeros;i<x.length();i++)
			{
				chars[5+i*4][1] = x.charAt(i);
				chars[7+i*4][1] = '/';
			}
				
			
			for(int i = 0;i<y.length();i++)
			{
				chars[chars.length-2][3+i*4] = y.charAt(i);	
				chars[chars.length-2][1+i*4] = '/';
			}
			
			if(numZeros == bd.length)
				chars[chars.length-2][1] = ' ';
			
			for(int i = 0;i<prod.length;i++)
				for(int j = 0;j<prod[0].length;j++)
				{
					int rr = 3+i*4;
					int cc = 3+j*4;
					chars[rr][cc] = (char)(prod[i][j]/10+48);
					chars[rr+2][cc+2] =(char) (prod[i][j]%10+48);
				}
			
			for(char[] cha:chars)
			{
				for(char ch:cha)
					System.out.print(ch);
				System.out.println();
			}
		}
	}
	
	public static void main(String[] args)
	{
		new Multiplication().run();
	}
	
}
