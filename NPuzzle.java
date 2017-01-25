import java.io.IOException;
import java.util.Scanner;

public class NPuzzle {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		char[][] fixed = new char[][]{"ABCD".toCharArray(),"EFGH".toCharArray(),"IJKL".toCharArray(),"MNO.".toCharArray()};
		char[][] in = new char[4][4];
		for(int i = 0;i<4;i++)
		{
			in[i] = file.next().toCharArray();
		}
		int dist = 0;
		for(int i = 0;i<4;i++)
			for(int j = 0;j<4;j++)
			{
				if(in[i][j]=='.')
					continue;
				int[] ints = find(in[i][j],fixed);
				dist+=Math.abs(ints[0]-i)+Math.abs(ints[1]-j);
			}
		System.out.println(dist);
	}
	public static int[] find(char ch, char[][] chars)
	{
		for(int i = 0;i<4;i++)
		{
			for(int j = 0;j<4;j++)
			{
				if(chars[i][j]==ch)
					return new int[]{i,j};
			}
		}
		return null;
	}
}
