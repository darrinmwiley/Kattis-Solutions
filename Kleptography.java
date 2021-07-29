import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Kleptography {
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new Kleptography().run();
	}
	
	public void run() throws NumberFormatException, IOException//bfs by shortest connection number
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int M = file.nextInt();
		char[] a = file.next().toCharArray();
		char[] b = file.next().toCharArray();
		char[] ans = new char[M];
		char[] key = new char[M];
		for(int i = 0;i<a.length;i++)
		{
			ans[M - N + i] = a[i];
			key[M - N + i] = (char)((b[M - N + i] - a[i] + 26)%26 + 'a');
		}
		for(int i = M - N -1;i>=0;i--)
		{
			ans[i] = key[i+N];
			key[i] = (char)((b[i] - ans[i] + 26)%26 + 'a');
		}
		System.out.println(new String(ans));
	}	
}
