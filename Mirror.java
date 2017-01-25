import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Mirror {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int R = file.nextInt();
			int C = file.nextInt();
			char[][] chars = new char[R][C];
			for(int i = 0;i<R;i++)
			{
				String next = file.next();
				for(int j = 0;j<C;j++)
					chars[chars.length-1-i][chars[0].length-1-j] = next.charAt(j);
			}
			System.out.println("Test "+(z+1));
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
		new Mirror().run();
	}
}

