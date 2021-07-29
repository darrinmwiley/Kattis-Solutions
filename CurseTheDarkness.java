import java.util.Arrays;
import java.util.Scanner;

public class CurseTheDarkness {

    public static void main(String[] args)
    {
        new CurseTheDarkness().run();
    }

    public void run()
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            double x = file.nextDouble();
            double y = file.nextDouble();
            int N = file.nextInt();
            boolean flag = false;
            for(int i = 0;i<N;i++)
            {
                double xx = file.nextDouble();
                double yy = file.nextDouble();
                if((xx-x)*(xx-x)+(yy-y)*(yy-y)<=64)
                    flag = true;
            }
            System.out.println(flag?"light a candle":"curse the darkness");
        }
    }
}
