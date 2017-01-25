import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class QuickBrownFox {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		file.nextLine();
		for(int i = 0;i<N;i++)
		{
			String line = file.nextLine().toLowerCase();
			boolean[] vis = new boolean[26];
			int found = 0;
			for(char ch:line.toCharArray())
			{
				if(Character.isLetter(ch)&&!vis[ch-97])
				{
					vis[ch-97]=true;
					found++;
				}
			}
			if(found==26)
				System.out.println("pangram");
			else{
				System.out.print("missing ");
				for(int i1 = 0;i1<26;i1++)
					if(!vis[i1])
						System.out.print((char)(97+i1));
				System.out.println();
			}
		}
	}
}
