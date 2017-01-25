import java.math.BigInteger;
import java.util.Scanner;

public class DigitSum {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            BigInteger a = file.nextBigInteger();
            BigInteger b = file.nextBigInteger();
            if(a.equals(BigInteger.ZERO))
                System.out.println(digsum(b));
            else
                System.out.println(digsum(b).subtract(digsum(a.subtract(BigInteger.ONE))));
        }
    }
    
    public BigInteger digsum(BigInteger n)
    {
        int len = (n+"").length();
        BigInteger sum = BigInteger.ZERO;
        for(int i = 0;i<len;i++)
        {
            sum = sum.add(new BigInteger("45").multiply(new BigInteger("10").pow(i)).multiply(new BigInteger(n+"").divide(new BigInteger("10").pow(i+1))));
            //sum+=45*pow(10,i)*(n/pow(10,i+1));
            int d = new BigInteger(n+"").divide(new BigInteger("10").pow(i)).mod(new BigInteger("10")).intValue();
            //int d = n/pow(10,i)%10;
            if(i!=0)
                sum = sum.add(new BigInteger("10").pow(i).multiply(new BigInteger(d+"")).multiply(new BigInteger(d-1+"")).divide(new BigInteger("2")));
                //sum+=pow(10,i)*d*(d-1)/2;
            else
                sum = sum.add(new BigInteger(d+"").multiply(new BigInteger(d+1+"")).divide(new BigInteger(2+"")));
                //sum+=d*(d+1)/2;
            if(i!=0)
                sum = sum.add(new BigInteger(d+"").multiply(new BigInteger(n+"").mod(new BigInteger("10").pow(i)).add(BigInteger.ONE)));
                //sum+=d*(n%pow(10,i)+1);
        }
        return sum;
    }
    
    static int pow(int a, int b)
    {
        return (int)(Math.pow(a, b));
    }
    
    public static int digsum2(int a)
    {
        int sum = 0;
        for(int i = 1;i<=a;i++)
        {
            int N = i;
            while(N!=0)
            {
                sum+=N%10;
                N/=10;
            }
        }
        return sum;
    }
    
    public static void main(String[] args)
    {
        new DigitSum().run();
    }
}