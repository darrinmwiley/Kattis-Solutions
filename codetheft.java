import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class codetheft{
    
    BufferedReader file;
    PrintWriter pout;
    StringTokenizer st;
    
    long P = 1000000093;
    long M = 2038074743;
    long lastTime = System.currentTimeMillis();
    long[] inv;
    long[] pow;
    long[][] hash;
    long[] code;
    long[] codehash;
    
    
    String[] names;
    TreeSet<Long> hashes;

    public static void main(String[] args) throws Exception
    {
        new codetheft().run();
    }
    
    //inclusive
    public long hash(int X, int L, int R)
    {
    	//System.out.println(hash[X].length+" "+inv.length);
        long ret = (((hash[X][R + 1] - hash[X][L]) * inv[L]) % M + M);
        if(ret >= M)
            ret -= M;
        return ret;
    }
    
  //inclusive
    public long codehash(int L, int R)
    {
        long ret = (((codehash[R + 1] - codehash[L]) * inv[L]) % M + M);
        if(ret >= M)
            ret -= M;
        return ret;
    }
    
    public long hash(char[] s)
    {
    	long hash = 0;
    	int p_pow = 1;
    	for(char ch: s) {
    		hash = (hash + (ch - 31) * pow[p_pow]) % M;
    		p_pow++;
    	}
    	return hash;
    }
    
    public void run() throws Exception{
    	BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(file.readLine());
        if(N == 0)
        {
        	System.out.println(0);
        	return;
        }
        long[][] lines = new long[N][];
        names = new String[N];
        inv = new long[10000+1];
        pow = new long[10000+1];
        inv[0] = 1;
        inv[1] = BigInteger.valueOf(P).modInverse(BigInteger.valueOf(M)).longValue();
        pow[0] = 1;
        pow[1] = P;
        for(int i = 2;i<inv.length;i++)
        {
            inv[i] = inv[i-1] * inv[1] % M;
            pow[i] = pow[i-1] * P % M;
        }
        for(int i = 0;i<N;i++)
        {
        	names[i] = file.readLine();
        	ArrayList<Long> list = new ArrayList<Long>();
        	while(true) {
	        	String line = file.readLine();
	        	if(fix(line).isEmpty())
	        		continue;
	        	if(line.equals("***END***"))
	        	{
	        		lines[i] = new long[list.size()];
	        		for(int j = 0;j<lines[i].length;j++)
	        		{
	        			lines[i][j] = list.get(j);
	        		}
	        		break;
	        	}else {
	        		list.add(hash(fix(line).toCharArray()));
	        	}
        	}
        }
        hash = new long[N][];
        for(int i = 0;i<N;i++)
        {
        	int L = lines[i].length;
        	hash[i] = new long[L+1];
            hash[i][1] = lines[i][0];
	        for(int j = 2;j<hash[i].length;j++)
	        {
	            hash[i][j] = (hash[i][j-1] + lines[i][j-1]*pow[j-1])%M;
	        }
        }
        ArrayList<Long> cl = new ArrayList<Long>();
        while(true) {
        	String line = file.readLine();
        	if(fix(line).isEmpty())
        		continue;
        	if(line.equals("***END***"))
        	{
        		if(line.isEmpty())
        		{
        			System.out.println(0);
        			return;
        		}
        		code = new long[cl.size()];
        		for(int j = 0;j<code.length;j++)
        		{
        			code[j] = cl.get(j);
        		}
        		break;
        	}
        	cl.add(hash(fix(line).toCharArray()));
    	}  
        int L = code.length;
    	codehash = new long[L+1];
        codehash[1] = code[0];
        for(int j = 2;j<codehash.length;j++)
        {
            codehash[j] = (codehash[j-1] + code[j-1]*pow[j-1])%M;
        }
        hashes = new TreeSet<Long>();
        int l = 0;
        int r = 10001;
        int m = (l+r)/2;
        ArrayList<String> best = null;
        int len = 0;
        while(r-l>1)
        {
        	m = (l+r)/2;
        	fill(m);
        	ArrayList<String> match = findMatch(m);
        	if(!match.isEmpty())
        	{
        		best = match;
        		len = m;
        		l = m;
        	}else {
        		r = m;
        	}
        }
        if(best == null)
        {
        	System.out.println(0);
        }else {
        	System.out.print(len+" ");
        	for(String s: best)
        		System.out.print(s+" ");
        	System.out.println();
        }
    }
    
    public void fill(int len)
    {
    	hashes.clear();
    	for(int i = 0;i<code.length-len+1;i++)
    	{
    		hashes.add(codehash(i,i+len-1));
    	}
    }
    
    public String fix(String line)
    {
    	return line.replaceAll("\\s+", " ").trim();
    }
    
    public ArrayList<String> findMatch(int len)
    {
    	ArrayList<String> answers = new ArrayList<String>();
    	for(int i = 0;i<hash.length;i++)
    	{
    		if(findMatch(i,len)) {
    			answers.add(names[i]);
    			continue;
    		}
    	}
    	return answers;
    }
    
    public boolean findMatch(int X, int len)
    {
    	for(int i = 0;i<hash[X].length - len;i++)
    	{
    		//System.out.println(hash[X].length+" "+len+" "+i+" "+(i+len-1));
    		long hash = hash(X,i,i+len-1);
    		if(hashes.contains(hash))
    			return true;
    	}
    	return false;
    }
    
}
