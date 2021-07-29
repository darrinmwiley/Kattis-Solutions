import java.math.BigInteger;
import java.util.Scanner;

public class DeadFraction {

    public static void main(String[] args)
    {
        new DeadFraction().run();
    }

    public void run()
    {
        Scanner file = new Scanner(System.in);
        while(file.hasNext())
        {
            String next = file.nextLine();
            if(next.equals("0"))
                return;
            next = next.replaceAll("^0\\.","").replace("...","");
            int len = next.length();
            frac best = null;
            for(int i = 1;i<=len;i++)
            {
                frac potential = getFraction(next,i);
                if(best==null||potential.denom.compareTo(best.denom)<0)
                    best = potential;
            }
            System.out.println(best);
        }
    }

    public frac getFraction(String str, int chop)
    {
        String tail = str.substring(str.length()-chop,str.length());
        String head = str.substring(0,str.length()-chop);
        String denom = make(tail.length(),9)+make(head.length(),0);
        frac repeating = new frac(new BigInteger(tail),new BigInteger(denom));
        String denom2 = 1+make(head.length(),0);
        if(head.length()!=0)
        {
            frac add = new frac(new BigInteger(head),new BigInteger(denom2));
            repeating = repeating.add(add);
        }
        repeating.reduce();
        return repeating;
    }

    public String make(int n,int k)
    {
        String ans = "";
        for(int i = 0;i<n;i++)
            ans+=k;
        return ans;
    }

    private class frac{

    BigInteger num,denom;

    public frac(BigInteger num, BigInteger denom)
    {
        this.num = num;
        this.denom = denom;
    }

    public void reduce()
    {
        BigInteger gcd = num.gcd(denom);
        num = num.divide(gcd);
        denom = denom.divide(gcd);
    }

    public frac add(frac f)
    {
        BigInteger newDenom = denom.multiply(f.denom);
        BigInteger left = num.multiply(f.denom);
        BigInteger right = f.num.multiply(denom);
        BigInteger newNum = left.add(right);
        return new frac(newNum,newDenom);
    }

    public String toString()
    {
        return num+"/"+denom;
    }

    }
}
