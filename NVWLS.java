import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class NVWLS{
    
/*
11
BETWEEN
SUBTLE
SHADING
AND
THE
ABSENCE
OF
LIGHT
LIES
NUANCE
IQLUSION
BTWNSBTLSHDNGNDTHBSNCFLGHTLSTHNNCFQLSN
 */
    
    long P = 1000000007;
    long M = 2038074743;
    long lastTime = System.currentTimeMillis();
    long[] inv;
    long[] pow;
    long[] hash;
    int L;
    
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new NVWLS().run();
    }
    
    public String removeVowels(String s)
    {
        StringBuilder sb = new StringBuilder("");
        for(char ch:s.toCharArray())
            if(ch != 'A' && ch != 'E' && ch != 'I' && ch != 'O' && ch != 'U')
                sb.append(ch);
        return sb.toString();
    }
    
    public int countVowels(String s)
    {
        int ct = 0;
        for(char ch:s.toCharArray())
            if(ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U')
                ct++;
        return ct;
    }
    
    //inclusive
    public long hash(int L, int R)
    {
        long ret = (((hash[R + 1] - hash[L]) * inv[L]) % M + M);
        if(ret >= M)
        	ret -= M;
        return ret;
    }
    
    public long hash(String s)
    {
        long ans = 0;
        for(int i = 0;i<s.length();i++)
        {
            ans += s.charAt(i) * pow[i];
            ans %= M;
        }
        return ans;
    }
    
    public void time(String s)
    {
    	//System.out.println(s+": "+(System.currentTimeMillis() - lastTime));
    	//lastTime = System.currentTimeMillis();
    }
    
    public void run() throws NumberFormatException, IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
    	time("start");
        //BufferedReader file = new BufferedReader(new FileReader("secret01.in")); 
        int N = Integer.parseInt(file.readLine());
        HashMap<String,String> map = new HashMap<>();
        HashMap<Long,String> words = new HashMap<Long,String>();
        HashMap<String, Integer> vow = new HashMap<String,Integer>();
        HashMap<Long,Integer> comp = new HashMap<Long,Integer>();
        HashSet<Integer> len = new HashSet<Integer>();
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0;i<N;i++)
            list.add(file.readLine());
        char[] line = file.readLine().toCharArray();
        time("readin");
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
        
        time("arrays");
        
        for(int i = 0;i<N;i++)
        {
            String s = list.get(i);
            int v = countVowels(s);
            String remove = removeVowels(s);
            len.add(remove.length());
            if(!map.containsKey(remove) || v > countVowels(map.get(remove)))
            {
                map.put(remove,s);
                vow.put(remove, v);
                long h = hash(remove);
                comp.put(h,v);
                words.put(h,remove);
            }
        }
        
        time("maps");
        
        int[] dp = new int[L+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i = 0;i<dp.length;i++)
        {
            if(dp[i] != -1)
            {
                for(int x:len)
                {
                    if(x+i<dp.length)
                    {
                        long h = hash(i,i + x - 1);
                        Integer cand = comp.get(h);
                        if(cand != null) {
                            dp[i + x] = Math.max(dp[i + x], dp[i] + cand);
                        }
                    }
                }
            }
        }
        
        time("dp");
        
        Stack<String> st = new Stack<String>();
        int index = L;
    loop:
        while(index != 0)
        {
            for(int x:len)
            {
                if(x <= index)
                {
                    long h = hash(index - x,index - 1);
                    if(words.containsKey(h)){
                        String candidate = words.get(h);
                        int v = vow.get(candidate);
                        if(dp[index] - dp[index - x] == v)
                        {
                            st.add(map.get(candidate));
                            index -= x;
                            continue loop;
                        }
                    }
                }
            }
        }
        
        time("stack");
        
        StringBuilder out = new StringBuilder("");
        while(!st.isEmpty()){
            out.append(st.pop());
            out.append(" ");
        }
        System.out.println(out.toString().trim());
    }
    
}