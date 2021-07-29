import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Union_Find {
	
	StringTokenizer st;
	BufferedReader file;
	static final boolean RUN_TIMING = false;
	int[] p;
	
	public static void main(String[] args) throws Exception
	{
        new Union_Find().run();		//<--- where program written starts running
	}	
	
	public void run() throws Exception
	{
		long time = 0;
       	time -= System.nanoTime();
		
		//this is fast IO - faster than Scanner/System.out.println
		//If needed to input file it would be: new BufferedReader(new FileReader(new File(filename)));
		//file = new BufferedReader(new FileReader(new File("text.txt")));
       	file = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pout = new PrintWriter(System.out);
		
		//solution code goes here
		int n = nextInt();
		int q = nextInt();
		p = new int[n];
		Arrays.fill(p, -1);
		int a, b;
		for(int i = 0; i < q; i++) {
			if(next().equals("?")) {
				//check
				a = nextInt();
				b = nextInt();
				if(find(a) == find(b)) {
					pout.println("yes");
				}else {
					pout.println("no");
				}
			}else {
				//connect
				union(nextInt(), nextInt());
				
			}
		}
		
		time += System.nanoTime();
        if (RUN_TIMING) {
            System.out.printf("%.3f ms%n", time / 1000000.0);
        }
		pout.flush();
		pout.close();
	}
		
	public int find(int a) {
		if(p[a] < 0)//if a is the set representative, then you can return it
			return a;
		int ans = find(p[a]);//if a is not the set representative, then ask it's parent
		p[a] = ans;//path compression
		return ans;
	}
	
	public boolean union(int a, int b) {
		int pa = find(a);//find a's set representative
		int pb = find(b);//find b's set representative
		if(pa == pb)//if a and b are already in the same set terminate
			return false;
		if(pb < pa) //if b's set is larger, swap a and b to ensure small to large merging
		{			//this is often not necessary in practice but it gives better asymptotic runtime
			int swap = pb;
			pb = pa;
			pa = swap;
		}
		p[pa] += p[pb];//increase a's set size by b's set size
		p[pb] = pa;//make b's set representative a's set representative
		return true;
	}
	
	//don't worry about this, just a helper method
	public void newst()
	{
		try {
			st = new StringTokenizer(file.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//consumes the entire next line of input
	public String readLine() throws IOException
	{
		return file.readLine();
	}
	
	//get's the next word of input
	public String next()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return st.nextToken();
	}
	
	//tries to parse the next piece of input as an int
	public int nextInt()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return Integer.parseInt(st.nextToken());
	}
	
	//tries to parse the next piece of input as a long
	public long nextLong()
	{
		if(st == null || !st.hasMoreTokens())
			newst();
		return Long.parseLong(st.nextToken());
	}

  	public void shuffle(int[] arr) {
        	int n = arr.length;
        	for (int i = 0; i < n; i++) {
           		int j = (int)(Math.random() * (n-i));
            		int temp = arr[i];
            		arr[i] = arr[j+i];
            		arr[j+i] = temp;
        	}
  	}
	public void shuffle(long[] arr) {
        	int n = arr.length;
        	for (int i = 0; i < n; i++) {
           		int j = (int)(Math.random() * (n-i));
            		long temp = arr[i];
            		arr[i] = arr[j+i];
            		arr[j+i] = temp;
        	}
	}
	public void shuffle(Object[] arr) {
        	int n = arr.length;
        	for (int i = 0; i < n; i++) {
           		int j = (int)(Math.random() * (n-i));
            		Object temp = arr[i];
            		arr[i] = arr[j+i];
            		arr[j+i] = temp;
        }
    }
	
}

