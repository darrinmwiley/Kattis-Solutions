/*
Group members:
Biranchi Padhi
Darrin Wiley
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class automaticTrading{
	
	BufferedReader file;
	PrintWriter pout;
	StringTokenizer st;
	
	long P = 1000000007;
    long M = 2038074743;
    long lastTime = System.currentTimeMillis();
    long[] inv;
    long[] pow;
    long[] hash;
    int L;

	public static void main(String[] args) throws Exception
	{
		new automaticTrading().run();
	}
	
	//inclusive
    public long hash(int L, int R)
    {
        long ret = (((hash[R + 1] - hash[L]) * inv[L]) % M + M);
        if(ret >= M)
            ret -= M;
        return ret;
    }
	
	public void run() throws Exception{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		char[] line = file.readLine().toCharArray();
		L = line.length;
        inv = new long[L+1];
        pow = new long[L+1];
        hash = new long[L+1];
        inv[0] = 1;
        inv[1] = BigInteger.valueOf(P).modInverse(BigInteger.valueOf(M)).longValue();
        pow[0] = 1;
        pow[1] = P;
        hash[1] = line[0];
        
        for(int i = 2;i<inv.length;i++)
        {
            inv[i] = inv[i-1] * inv[1] % M;
            pow[i] = pow[i-1] * P % M;
            hash[i] = (hash[i-1] + line[i-1]*pow[i-1])%M;
        }
        
        int zz = Integer.parseInt(file.readLine());
        for(int z = 0;z<zz;z++)
        {
        	st = new StringTokenizer(file.readLine());
	        int a = Integer.parseInt(st.nextToken());
	        int b = Integer.parseInt(st.nextToken());
	        
	        /*if(line[a] != line[b]) {
	        	System.out.println(0);
	        	continue;
	        }*/
	        
	        int L = -1;
	        int R = line.length - Math.max(a,b);
	        int M = (L+R)/2;
	        int ans = 0;
	        while(R-L>1)
	        {
	        	M = (L+R)/2;
	        	long hash1 = hash(a,a+M);
	        	long hash2 = hash(b,b+M);
	        	//System.out.println(M+" "+hash1+" "+hash2);
	        	if(hash1 == hash2)
	        	{
	        		ans = M+1;
	        		L = M;
	        	}else {
	        		R = M;
	        	}
	        }
	        System.out.println(ans);
        }  
	}
	
}