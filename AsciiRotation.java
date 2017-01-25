import java.util.ArrayList;
import java.util.Scanner;

public class AsciiRotation {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int sz = file.nextInt();
		while(true)
		{
			file.nextLine();
			if(sz==0)
				return;
			char[][] chars = new char[sz][];
			int max = 0;
			for(int i = 0;i<chars.length;i++)
			{
				chars[i] = file.nextLine().toCharArray();
				max = Math.max(max,chars[i].length);
			}
				
			for(int c = 0;c<max;c++)
			{
				String toAdd = "";
				for(int r = chars.length-1;r>=0;r--)
				{
					
					if(c>=chars[r].length)
					{
						toAdd+=(" ");
						continue;
					}
					char ch = chars[r][c];
					if(ch=='|')
						toAdd+=('-');
					else if(ch=='-')
						toAdd+=('|');
					else
						toAdd+=(ch);
				}
				System.out.println(toAdd.replaceAll(" +$", ""));
			}
			sz = file.nextInt();
			if(sz!=0)
				System.out.println();
		}
	}
	
	public static void main(String[] args)
	{
		new AsciiRotation().run();
	}
}
