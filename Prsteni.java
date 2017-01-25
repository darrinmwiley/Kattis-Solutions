import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;


public class Prsteni {

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int[] ints = new int[N];
        for(int i = 0;i<N;i++)
        {
            ints[i] = file.nextInt();
        }
        BigInteger a = new BigInteger(ints[0]+"");
        for(int i = 1;i<ints.length;i++)
        {
            BigInteger b = new BigInteger(ints[i]+"");
            System.out.println(a.intValue()/a.gcd(b).intValue()+"/"+b.intValue()/b.gcd(a).intValue());
        }
    }
}