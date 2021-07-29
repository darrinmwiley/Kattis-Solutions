package page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HNumbers {

    public static void main(String[] args)
    {
        new HNumbers().run();
    }

    public boolean isSemiPrime(ArrayList<Integer> primes, int x)
    {
    	for(int p:primes)
    	{
    		if(x%p==0&&primes.contains(x/p))
    			return true;
    	}
    	return false;
    }

    public void run()
    {
    	ArrayList<Integer> h = new ArrayList<Integer>();
    	for(int i = 5;i<=1000001;i+=4)
    		h.add(i);
    	boolean[] sieve = new boolean[1000002];
    	int[] factors = new int[1000002];
    	Arrays.fill(sieve,true);
    	sieve[0] = sieve[1] = false;
    	boolean[] semi = new boolean[1000002];
    	for(int i = 5;i<sieve.length;i+=4)
    	{
    		if(sieve[i])
    		{
    			for(int x:h)
    			{
    				if(x*i<sieve.length) {
    					sieve[i*x] = false;
    				}
    				else
    					break;
    			}
    		}
    	}
    	for(int i = 0;i<sieve.length;i++)
    		if(i%4!=1)
    			sieve[i] = false;
    	for(int i = 5;i<sieve.length;i+=4)
    	{
    		if(sieve[i])
    		{
    			for(int x:h)
    			{
    				if(x*i<sieve.length) {
    					if(sieve[x])
    						semi[x*i] = true;
    				}
    				else
    					break;
    			}
    		}
    	}
    	ArrayList<Integer> primes = new ArrayList<Integer>();
    	for(int i = 0;i<sieve.length;i++)
    		if(sieve[i])
    			primes.add(i);

    	int[] sum = new int[semi.length];
    	for(int i = 1;i<sum.length;i++)
    	{
    		sum[i] = sum[i-1];
    		if(semi[i])
    			sum[i]++;
    	}
        Scanner file = new Scanner(System.in);
        while(true)
        {
        	int x = file.nextInt();
        	if(x==0)
        		return;
        	System.out.println(x+" "+sum[x]);
        }
    }
}
