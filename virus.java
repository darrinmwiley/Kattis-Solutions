import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class virus {

	public static void main(String[] args) throws IOException
	{
		new virus().run();
	}

	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		char[] a = file.readLine().toCharArray();
		char[] b = file.readLine().toCharArray();
		System.out.println(solve(a,b));
	}

	public void genCases()
	{
		int L1 = (int)(Math.random()*6+5);
		int L2 = (int)(Math.random()*6+5);
		char[] chars = "AGTC".toCharArray();
		char[] a = new char[L1];
		char[] b = new char[L2];
		for(int i = 0;i<L1;i++)
			a[i] = chars[(int)(Math.random()*4)];
		for(int i = 0;i<L2;i++)
			b[i] = chars[(int)(Math.random()*4)];
		int solve = solve(a,b);
	}

	public void bruteForce(char[] a, char[] b)
	{
		/*for(int i = 0;i<a.length;i++)//start a
			for(int j = i;j<a.length;j++)//end a
				for(int k = 0;k<b.length;k++)//start b
					for(int l = k;k<b.length;k++)//end b
	*/}

	public int solve(char[] a, char[] b)
	{
		int begin = 0;
		while(begin<a.length&&begin<b.length&&a[begin]==b[begin])
			begin++;
		int end = 0;
		while(end<a.length&&end<b.length&&a[a.length-1-end]==b[b.length-1-end])
			end++;
		int total = begin+end;
		if(total>a.length)
			return(Math.max(0,b.length-a.length));
		else
			return(Math.max(0,b.length-total));
	}
}
