import java.math.BigDecimal;
import java.util.Scanner;


public class A1Paper {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int n = file.nextInt();
        int[] sizes = new int[n-1];
        double[] longside = new double[31];
        double[] shortside = new double[31];
        longside[1] = Math.pow(2,-1.25)*2;
        shortside[1] = Math.pow(2,-.75);
        for(int i = 2;i<=30;i++)
        {
            longside[i] = shortside[i-1];
            shortside[i] = longside[i-1]/2;
        }
        for(int i = 0;i<n-1;i++)
        {
            sizes[i] = file.nextInt();
        }
        int need = 1;
        double tape = 0;
        for(int i = 0;i<sizes.length;i++)
        {
            need*=2;
            if(sizes[i]>=need)
            {
                sizes[i] = need;
                for(int j = i;j>=0;j--)
                {
                    tape+=longside[j+2]*sizes[j]/2;
                    if(j!=0)
                        sizes[j-1] += sizes[j]/2;
                }
                System.out.println(tape);
                return;
            }
            need-=sizes[i];
        }
        System.out.println("impossible");
    }
    
    public static void main(String[] args)
    {
        new A1Paper().run();
    }
    
}