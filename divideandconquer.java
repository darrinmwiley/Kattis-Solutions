/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/abstractart
TAGS: java, geometry
EXPLANATION:
This problem is just finding the area of shapes with potential holes. We can lean on java's geometry library to solve this easily.
END ANNOTATION
*/
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class divideandconquer{
    
    public static void main(String[] args)
    {
       new divideandconquer().run();
    }
    
    private final static BigInteger ZERO = new BigInteger("0");
    private final static BigInteger ONE  = new BigInteger("1");
    private final static BigInteger TWO  = new BigInteger("2");
    private final SecureRandom random = new SecureRandom();
    
    public void run()
    {
    	 Scanner file = new Scanner(System.in);
         BigInteger b = file.nextBigInteger();
         BigInteger d = file.nextBigInteger();
         BigInteger dm1 = d.subtract(BigInteger.ONE);
         TreeSet<BigInteger> set = new TreeSet<BigInteger>();
         factor(dm1, set);
         ArrayList<BigInteger> list = new ArrayList<BigInteger>();
         list.addAll(set);
         ArrayList<BigInteger> factors = permuteFactorsWrapper(dm1, list);
         //System.out.println(factors.size());
         for(BigInteger fac: factors)
         {
        	 if(b.modPow(fac, d).equals(dm1))
        	 {
        		 System.out.println("yes");
        		 return;
        	 }
         }
         System.out.println("no");
         return;
    }
    
    public ArrayList<BigInteger> permuteFactorsWrapper(BigInteger N, ArrayList<BigInteger> primes)
    {
        int[] max = new int[primes.size()];
        for(int i = 0;i<primes.size();i++)
        {
        	BigInteger y = primes.get(i);
        	BigInteger x = N;
            while(x.mod(y).equals(BigInteger.ZERO))
            {
                
                max[i]++;
                x = x.divide(y);
            }
        }
        ArrayList<BigInteger> factors = new ArrayList<BigInteger>();
        permuteFactors(0,0,new int[max.length],max,primes,factors);
        return factors;
    }
    
    public void permuteFactors(int index, int total, int[] current, int[] max,ArrayList<BigInteger> primes,ArrayList<BigInteger> factors)
    {
        if(index==current.length) {
            factors.add(getFactor(current,primes));
            return;
        }
        for(int i = 0;i<=max[index];i++)
        {
            current[index] = i;
            permuteFactors(index+1,total+i,current,max,primes,factors);
            current[index] = 0;
        }
    }
    
    public BigInteger getFactor(int[] current, ArrayList<BigInteger> primes)
    {
        BigInteger ans = BigInteger.ONE;
        for(int i = 0;i<current.length;i++)
        {
            for(int j = 0;j<current[i];j++)
                ans = ans.multiply(primes.get(i));
        }
        return ans;
    }
    
    public void factor(BigInteger N, TreeSet<BigInteger> list) {
        if (N.compareTo(ONE) == 0) return;
        if (N.isProbablePrime(100)) { list.add(N); return; }
        BigInteger divisor = rho(N);
        factor(divisor, list);
        factor(N.divide(divisor), list);
    }
    
    public BigInteger rho(BigInteger N) {
        BigInteger divisor;
        BigInteger c  = new BigInteger(N.bitLength(), random);
        BigInteger x  = new BigInteger(N.bitLength(), random);
        BigInteger xx = x;

        // check divisibility by 2
        if (N.mod(TWO).compareTo(ZERO) == 0) return TWO;

        do {
            x  =  x.multiply(x).mod(N).add(c).mod(N);
            xx = xx.multiply(xx).mod(N).add(c).mod(N);
            xx = xx.multiply(xx).mod(N).add(c).mod(N);
            divisor = x.subtract(xx).gcd(N);
        } while((divisor.compareTo(ONE)) == 0);

        return divisor;
    }
    
    public BigInteger g(BigInteger x, BigInteger n)
    {
    	return x.multiply(x).add(BigInteger.ONE).mod(n);
    }
    
}
