import java.math.BigInteger;
import java.util.Scanner;


public class LockedTreasure {
    public void run() throws Exception
    {
        Scanner file = new Scanner(System.in);
        BigInteger[][] choose = new BigInteger[32][32];
        for(int i = 0;i<32;i++)
            choose[i][0] = BigInteger.ONE;
        for(int i = 1;i<32;i++)
            for(int j = 1;j<=i;j++)
                choose[i][j] = new BigInteger(i+"").multiply(choose[i-1][j-1]).divide(new BigInteger(j+""));
        int n = file.nextInt();
        for(int i = 0;i<n;i++)
        {
            System.out.println(choose[file.nextInt()][file.nextInt()-1]);
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        new LockedTreasure().run();
    }
}