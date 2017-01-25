import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Skener {
	
	int S;
	int L;
	
	public void go() throws IOException {
		Scanner file = new Scanner(System.in);
		int r = file.nextInt();
		int c = file.nextInt();
		int nr = file.nextInt();
		int nc = file.nextInt();
		file.nextLine();
		for(int i = 0;i<r;i++)
		{
			String next = file.nextLine();
			for(int j = 0;j<nr;j++)
			{
				for(char ch:next.toCharArray())
					for(int k = 0;k<nc;k++)
						System.out.print(ch);
				System.out.println();
			}
		}
	}
	
	
	public static void main(String[] args) {
		try {
			new Skener().go();
		} catch (IOException e) {
			
		}
	}
}
