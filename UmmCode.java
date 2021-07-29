import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UmmCode {

	public static void main(String[] args) throws IOException
	{
		new UmmCode().run();		
	}

	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		String line = file.readLine().replaceAll("[^a-zA-Z0-9 ]","");
		StringBuilder um = new StringBuilder("");
		StringTokenizer st = new StringTokenizer(line);
		while(st.hasMoreTokens())
		{
			String token = st.nextToken();
			if(token.matches("[um]*"))
			{
				um.append(token);
			}
		}
		StringBuilder ans = new StringBuilder("");
		String coded = um.toString();
		for(int i = 0;i<coded.length();i+=7)
		{
			String s = coded.substring(i,i+7);
			s = s.replace('u', '1');
			s = s.replace('m', '0');
			ans.append(""+(char)(Integer.parseInt(s,2)));
		}
		System.out.println(ans);
	}

}
