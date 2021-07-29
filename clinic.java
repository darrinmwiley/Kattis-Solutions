import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class clinic {
    
    StringTokenizer st;
    BufferedReader file;
    
   	ArrayList<patient> list = new ArrayList<patient>();
    HashSet<String> dead = new HashSet<String>();
    PrintWriter pout; 
    
    long T = 0;
    
    PriorityQueue<patient> que;
    
    public static void main(String[] args) throws Exception
    {
        new clinic().run();
    }   
    
    public void run() throws Exception
    {
    	Comparator<patient> comp = new Comparator<patient>() {

			@Override
			public int compare(patient arg0, patient arg1) {
				return arg0.compare(arg1, T);
			}
    		
    	};
    	que = new PriorityQueue<patient>(comp);
        file = new BufferedReader(new InputStreamReader(System.in));
        pout = new PrintWriter(System.out);
        int Q = nextInt();
        long K = nextInt();
        try {
        for(int i = 0;i<Q;i++)
        {
        	int next = nextInt();
        	if(next == 1)
        	{
        		try {
        		T = nextLong();
        		addPatient(new patient(T,  next(), nextLong(), K));
        		}catch(Exception ex) {}
        	}
        	if(next == 2)
        	{
        		try {
        		T = nextLong();
        		treatPatient(T);
        		}catch(Exception ex){}
        	}
        	if(next == 3)
        	{
        		T = nextLong();
        		try {
        		kill(next());
        		}catch(Exception ex) {}
        	}
        }
        pout.flush();
        }catch(Exception ex) {while(true) {}}
    }
    
    public void addPatient(patient p)
    {
    	que.add(p);
    }
    
    public void treatPatient(long T)
    {
    	while(true)
    	{
    		if(que.isEmpty())
    		{
    			pout.println("doctor takes a break");
    			return;
    		}
    		patient first = que.poll();
    		if(!dead.contains(first.name))
    		{
    			pout.println(first.name);
    			return;
    		}
    	}
    }
    
    public void kill(String name)
    {
    	dead.add(name);
    }
    
/*
4 3 3 3 1
1 2 1
2 3 1
3 4 5
2 3 4
 */
    
    private class patient{
    	
    	long S;
    	long ArrivalTime;
    	String name;
    	long K;
    	
    	public patient(long A,String name, long S, long K)
    	{
    		this.S = S;
    		this.ArrivalTime = A;
    		this.name = name;
    		this.K = K;
    	}
    	
    	public long getPriority(long T)
    	{
    		return S + K*(T - ArrivalTime);
    	}
    	
    	public int compare(patient p, long T)
    	{
    		int comp = Long.compare(p.getPriority(T), getPriority(T));
    		if(comp == 0) {
    			return name.compareTo(p.name);
    		}
    		return comp;
    	}
    	
    	public String toString()
    	{
    		return S+" "+name+" "+ArrivalTime;
    	}
    	
    }
    
    public void newst()
    {
        try {
            st = new StringTokenizer(file.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String readLine() throws IOException
    {
        return file.readLine();
    }
    
    public String next()
    {
        if(st == null || !st.hasMoreTokens())
            newst();
        return st.nextToken();
    }
    
    public int nextInt()
    {
        if(st == null || !st.hasMoreTokens())
            newst();
        return Integer.parseInt(st.nextToken());
    }
    
    public long nextLong()
    {
        if(st == null || !st.hasMoreTokens())
            newst();
        return Long.parseLong(st.nextToken());
    }
    
}