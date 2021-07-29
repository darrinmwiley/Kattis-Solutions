import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class BeautifulPrimes
{
	
	public static void main (String[] args) throws java.lang.Exception
	{
		new BeautifulPrimes().solve();
	}
	
	ArrayList<Integer> primes = new ArrayList<Integer>();
	HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
	
	public void solve() throws Exception
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		boolean[] s = new boolean[1000000];
		for(int i = 2;i<s.length;i++)
		{
			if(!s[i])
			{
				primes.add(i);
				map.put(i,primes.size() -1 );
				for(int j = i*2;j<s.length;j+=i)
				{
					s[j] = true;
				}
			}
		}
		for(int i = 0;i<N;i++)
		{
			int next = Integer.parseInt(file.readLine());
			int[] ans = pray(next);
			for(int x: ans)
			{
				System.out.print(x+" ");
			}
			System.out.println();
		}
	}
	
	public int[] pray(int N)
	{
		int[] ans = new int[N];
		for(int i = 0;i<ans.length;i++)
		{
			ans[i] = primes.get((int)(Math.random() * primes.size()));
		}
		double sum = 0;
		for(int i:ans)
		{
			sum += Math.log10(i);
		}
		while(true)
		{
			if(sum >= N-1 && sum < N)
			{
				return ans;
			}
			else if(sum <= N-1)
			{
				//increase
				int randIndex = (int)(Math.random() * N);
				double prev = Math.log10(ans[randIndex]);
				if(ans[randIndex] != primes.get(primes.size() - 1)) {
					ans[randIndex] = primes.get(map.get(ans[randIndex]) + 1);
					sum += Math.log10(ans[randIndex]) - prev;
				}
			}else {
				//decrease
				int randIndex = (int)(Math.random() * N);
				double prev = Math.log10(ans[randIndex]);
				if(ans[randIndex] != 2) {
					ans[randIndex] = 2;
					sum += Math.log10(2) - prev;
				}
			}
		}
	}
}