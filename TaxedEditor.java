import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TaxedEditor {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new TaxedEditor().run();		
	}

	book[] books;

	public void run() throws IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int allowed = Integer.parseInt(st.nextToken());
		books = new book[N];
		for(int i = 0;i<N;i++)
		{
			st = new StringTokenizer(file.readLine());
			long p = Long.parseLong(st.nextToken());
			long d = Long.parseLong(st.nextToken());
			books[i] = new book(p,d);
		}
		long low = 0;
		long high = 1000000000000000l;
		long mid = (high + low)/2;
		long best = high;
		while(high - low > 1)
		{
			mid = (high + low)/2;
			int cost = cost(mid);
			if(cost <= allowed)
			{
				best = Math.min(best, mid);
				high = mid;
			}else {
				low = mid;
			}
		}
		System.out.println(best);
	}

	private class book implements Comparable<book>{

		public long pages;
		public long dl;
		public long ppd;

		public book(long p, long d)
		{
			pages = p;
			dl = d;
		}

		public long deadline()
		{
			return ppd*dl;
		}

		public double value()
		{
			return time() - deadline();
		}

		public long time()
		{
			return pages;
		}

		public int compareTo(book b)
		{
			return Long.compare(b.deadline(), deadline());
		}

		public String toString()
		{
			return time() +" "+ deadline();
		}

	}

	/*private class frac{

		public BigInteger num;
		public BigInteger denom;

		public frac(BigInteger n, BigInteger d)
		{
			num = n;
			denom = d;
		}

		public frac add(frac f)
		{
			BigInteger n = num.multiply(f.denom).add(f.num.multiply(denom));
			BigInteger d = denom.multiply(f.denom);
			return new frac(n,d);
		}

		public void reduce()
		{
			BigInteger gcd = num.gcd(denom);
			num = num.divide(gcd);
			denom = denom.divide(gcd);
		}

		public frac negate(frac f)
		{

		}

	}*/

	public int cost(long p)
	{
		for(int i = 0;i<books.length;i++)
		{
			books[i].ppd = p;
		}
		PriorityQueue<Long> que = new PriorityQueue<Long>();
		int completed = 0;
		Arrays.sort(books);
		for(int i = 0;i<books.length;i++)
		{
			long t = books[i].deadline() - (i == books.length - 1? 0:books[i+1].deadline());
			que.add(books[i].time());
			while(t != 0 && !que.isEmpty())
			{
				long next = que.poll();
				if(next > t)
				{
					que.add(next - t);
					t = 0;
				}else {
					t -= next;
					completed++;
				}
			}
		}
		return books.length - completed;
	}

}
