import java.util.Scanner;
import java.util.TreeMap;


public class OddManOut {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            int N = file.nextInt();
            int ret = 0;
            for(int i = 0;i<N;i++)
                ret^=file.nextInt();
            System.out.printf("Case #%d: %d%n",z+1,ret);
        }
    }
}
