import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class ModularArithmetic
{
	
	public static void main (String[] args) throws java.lang.Exception
	{
		new ModularArithmetic().solve();
	}
	
	public void solve() throws Exception
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true)
		{
			st = new StringTokenizer(file.readLine());
			BigInteger mod = new BigInteger(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			if(t == 0)
				return;
			for(int i = 0;i<t;i++)
			{
				st = new StringTokenizer(file.readLine());
				BigInteger a = new BigInteger(st.nextToken());
				char op = st.nextToken().charAt(0);
				BigInteger b = new BigInteger(st.nextToken());
				if(op == '+')
				{
					System.out.println(a.add(b).mod(mod));
				}
				if(op == '-')
				{
					System.out.println(a.mod(mod).subtract(b.mod(mod)).mod(mod).add(mod).mod(mod));
				}
				if(op == '*')
				{
					System.out.println(a.multiply(b).mod(mod));
				}
				if(op == '/')
				{
					try {
						BigInteger inv = b.modInverse(mod);
						System.out.println(a.multiply(inv).mod(mod));
					}
					catch(Exception ex)
					{
						System.out.println(-1);
					}
				}
			}
		}
		
	}
}