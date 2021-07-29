import java.io.IOException;
import java.util.Scanner;

public class YinYang {
	
	String studentID = "2021202859";
	int inf = Integer.MAX_VALUE/4;
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new YinYang().run();	
	}
	
	public void run() throws IOException
	{
		Scanner file = new Scanner(System.in);
		char[] chars = file.next().toCharArray();
		int count = 0;
		for(int i = 0;i<chars.length;i++)
		{
			if(chars[i] =='W')
				count++;
		}
		if(chars.length % 2 == 0 && count == chars.length/2)
			System.out.println(1);
		else
			System.out.println(0);
	}
	
}
