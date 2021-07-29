import java.util.*;
import java.io.*;

public class simonsays{
	
	BufferedReader file;
	PrintWriter pout;
	StringTokenizer st;
	
	public static void main(String[] args) throws Exception
	{
		new simonsays().run();
	}
	
	public void run() throws Exception{
		file = new BufferedReader(new InputStreamReader(System.in));
		pout = new PrintWriter(System.out);
		int N = Integer.parseInt(file.readLine());
		for(int i = 0;i<N;i++)
		{
			String line = file.readLine();
			if(line.startsWith("Simon says"))
			{
				System.out.println(line.substring(11));
			}
		}
	}
	
}