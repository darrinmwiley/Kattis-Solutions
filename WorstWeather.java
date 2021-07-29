import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class WorstWeather {
	
	public static void main(String[] args) throws Exception
	{
		new WorstWeather().run();
	}
	
	public void run() throws Exception
	{
		Kattio io = new Kattio(System.in, System.out);
		PrintWriter pout = new PrintWriter(System.out);
		HashMap<Integer,Integer> yearMap;
		int[] amts;
		int[] rev;
		TreeSet<Integer> years;
		while(true)
		{
			int N = io.getInt();
			if(N == 0) {
				pout.flush();
				pout.close();
				return;
			}
			amts = new int[N];
			rev = new int[N];
			yearMap = new HashMap<Integer,Integer>();
			years = new TreeSet<Integer>();
			for(int i = 0;i<N;i++)
			{
				int y = io.getInt();
				yearMap.put(y, i);
				years.add(y);
				rev[i] = y;
				amts[i] = io.getInt();
			}
			
			segTree seg = new segTree(N);
			for(int i = 0;i<N;i++)
			{
				seg.update(i, 0, 0, N-1, amts[i]);
			}
			int M = io.getInt();
			for(int i = 0;i<M;i++)
			{
				int Y = io.getInt();
				int X = io.getInt();
				boolean xKnown = yearMap.containsKey(X);
				boolean yKnown = yearMap.containsKey(Y);
				if(!xKnown && ! yKnown)
				{
					pout.println("maybe");
				}else if(!xKnown && yKnown) {
					int yi = yearMap.get(Y);
					int previousYear = years.floor(X);
					int previousYearIndex = yearMap.get(previousYear);
					int greatestBetween = seg.greatestBetween(0, 0, N-1, yi+1, previousYearIndex);
					if(greatestBetween < amts[yi])
						pout.println("maybe");
					else
						pout.println("false");
				}else if(xKnown && !yKnown){//problem here
					int xi = yearMap.get(X);
					int nextYearIndex = yearMap.get(years.ceiling(Y));
					int maxBetween = seg.greatestBetween(0,0,N-1,nextYearIndex,xi-1);
					/*if(i == 223)
					{
						System.out.println(years.ceiling(Y));
						System.out.println(amts[xi-1]);
						System.out.println(xi+" "+nextYearIndex+" "+maxBetween+" "+amts[xi]);
					}*/
					if(maxBetween < amts[xi])
						pout.println("maybe");
					else
						pout.println("false");
				}else if(xKnown && yKnown)
				{
					int yi = yearMap.get(Y);
					int xi = yearMap.get(X);
					int maxBetween = seg.greatestBetween(0, 0, N-1, yi+1, xi-1);
					if(maxBetween >= amts[xi] || amts[yi] < amts[xi])
					{
						pout.println("false");
					}else if(xi - yi == X - Y)
					{
						pout.println("true");
					}else {
						pout.println("maybe");
					}
				}
			}
			pout.println();
		}
	}

	class Kattio extends PrintWriter {
	    public Kattio(InputStream i) {
	        super(new BufferedOutputStream(System.out));
	        r = new BufferedReader(new InputStreamReader(i));
	    }
	    public Kattio(InputStream i, OutputStream o) {
	        super(new BufferedOutputStream(o));
	        r = new BufferedReader(new InputStreamReader(i));
	    }

	    public boolean hasMoreTokens() {
	        return peekToken() != null;
	    }

	    public int getInt() {
	        return Integer.parseInt(nextToken());
	    }

	    public double getDouble() {
	        return Double.parseDouble(nextToken());
	    }

	    public long getLong() {
	        return Long.parseLong(nextToken());
	    }

	    public String getWord() {
	        return nextToken();
	    }



	    private BufferedReader r;
	    private String line;
	    private StringTokenizer st;
	    private String token;

	    private String peekToken() {
	        if (token == null)
	            try {
	                while (st == null || !st.hasMoreTokens()) {
	                    line = r.readLine();
	                    if (line == null) return null;
	                    st = new StringTokenizer(line);
	                }
	                token = st.nextToken();
	            } catch (IOException e) { }
	        return token;
	    }

	    private String nextToken() {
	        String ans = peekToken();
	        token = null;
	        return ans;
	    }
	}
	
	private class segTree{
		
		int[] maxChild;
		
		public segTree(int N)
		{
			maxChild = new int[500000];
		}
		
		public void update(int goalIndex, int realIndex, int L, int R, int V)
		{
			if(goalIndex > R || goalIndex < L)
				return;
			maxChild[realIndex] = Math.max(maxChild[realIndex], V);
			if(L != R) {
				int M = (L+R)/2;
				update(goalIndex, realIndex*2+1, L, M, V);
				update(goalIndex, realIndex*2+2, M+1, R, V);
			}
		}
		
		public int greatestBetween(int index,int currL, int currR, int L, int R)
		{
			if(currR < L)
				return -1;
			if(currL > R)
				return -1;
			if(currL >= L && currR <= R) {
				return maxChild[index];
			}
			int nextL = index*2+1;
			int nextR = index*2+2;
			int M = (currL+currR)/2;
			int left = greatestBetween(nextL, currL, M, L,R);
			int right = greatestBetween(nextR, M+1, currR, L, R);
			return Math.max(left, right);
		}
		
	}
	
}
