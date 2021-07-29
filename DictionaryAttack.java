import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class DictionaryAttack {
	
	public static void main(String[] args) throws Exception
	{
		new DictionaryAttack().run();
	}
	
	HashSet<String> bad = new HashSet<String>();
	BufferedReader file;
	StringTokenizer st;
	
	public void run() throws Exception
	{
		file = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(file.readLine());
		for(int i = 0;i<T;i++)
		{
			char[] chars = file.readLine().toCharArray();
			alter(chars, 3);
		}
		String line = null;
		while((line = file.readLine()) != null)
		{
			String line2 = line.replaceAll("[0-9]", "0");
			if(!bad.contains(line2))
				System.out.println(line);
		}
	}
	
	public void alter(char[] chars, int editsLeft)
	{
		bad.add(new String(chars));
		if(editsLeft == 0)
			return;
		else {
			for(int i = 0;i<chars.length - 1;i++)
			{
				char save = chars[i];
				chars[i] = chars[i+1];
				chars[i+1] = save;
				alter(chars, editsLeft - 1);
				chars[i+1] = chars[i];
				chars[i] = save;
			}
			for(int i = 0;i<chars.length;i++)
			{
				char save = chars[i];
				chars[i] = '0';
				alter(chars, editsLeft - 1);
				chars[i] = save;
			}
		}
			
	}
	
	

	
}
