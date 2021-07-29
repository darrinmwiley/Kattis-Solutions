import java.math.BigInteger;
import java.util.Scanner;

public class HamiltonianHypercube {

    public void go() {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        String a = file.next();
        String b = file.next();
        BigInteger x = getPosition(a);
        BigInteger y = getPosition(b);
        System.out.println(y.subtract(x).subtract(BigInteger.ONE));
    }



    public BigInteger getPosition(String x)
    {
        if(x.isEmpty())
            return BigInteger.ZERO;
        if(x.charAt(0)=='0')
        {
            return getPosition(x.substring(1));
        }else{
            BigInteger base = new BigInteger("2").pow(x.length());
            return base.subtract(BigInteger.ONE).subtract(getPosition(x.substring(1)));
        }
    }

    public static void main(String[] args) {
        new HamiltonianHypercube().go();
    }
}
