import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IRepeatMyself {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new IRepeatMyself().run();
	}

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(file.readLine());
	loop:
		for(int z = 0;z<t;z++)
		{
			String s = file.readLine();
			for(int i = 0;i<s.length();i++)
			{
				if(test(s, i+1))
				{
					System.out.println(i+1);
					continue loop;
				}
			}
		}
	}

	public boolean test(String s, int x)
	{
		for(int i = x;i<s.length();i++)
		{
			if(s.charAt(i) != s.charAt(i-x))
				return false;
		}
		return true;
	}

}
