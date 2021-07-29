import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class JugHard {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new JugHard().run();
	}

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader((System.in)));
		int N = Integer.parseInt(file.readLine());
		StringTokenizer st;
		for(int i = 0;i<N;i++)
		{
			st = new StringTokenizer(file.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int x = new BigInteger(a+"").gcd(new BigInteger(b+"")).intValue();
			if(d%x==0)
				System.out.println("Yes");
			else
				System.out.println("No");
		}
	}
}
