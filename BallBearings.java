import java.util.Arrays;
import java.util.Scanner;

public class BallBearings{

    public static void main(String[] args)
    {
        new BallBearings().run();
    }

    public void run()
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            double D = file.nextDouble();
            double d = file.nextDouble();
            double s = file.nextDouble();
            //check(D,d,s,20);
            for(int i = 4;true;i++)
            {
                if(!check(D,d,s,i)){
                    System.out.println(i-1);
                    break;
                }
            }
        }
    }

    public boolean check(double D, double d, double s, int N)
    {
        double R = D/2;
        double r = d/2;
        double theta = 360.0/N;
        double x = Math.sqrt(2*(R-r)*(R-r)-2*(R-r)*(R-r)*Math.cos(Math.toRadians(theta)));
        return (x-d>=s);
    }
}
