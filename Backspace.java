import java.util.Scanner;

public class Backspace {
	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int pos = 0;
		char[] line = file.nextLine().toCharArray();
		for(int i = 0;i<line.length;i++)
		{
			if(line[i]=='<')
				sb.setCharAt(--pos,' ');
			else if(pos<sb.length())
				sb.setCharAt(pos++, line[i]);
			else{
				sb.append(line[i]);
				pos++;
			}
				
		}
		System.out.println(sb);
	}
}
