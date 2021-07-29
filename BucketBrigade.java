import java.util.Arrays;
import java.util.Scanner;

public class BucketBrigade {

    public static void main(String[] args)
    {
        new BucketBrigade().run();
    }

    public void run()
    {
        Scanner file = new Scanner(System.in);
        while(file.hasNext())
        {
            int N = file.nextInt();
            double v = file.nextDouble();
            double x = file.nextDouble();
            double f = file.nextDouble();
            double t = file.nextDouble();
            int loads = (int)(Math.ceil(v/x));
            double amt = v/loads;
            double pourTime = amt/f;
            double timeToTub = (N+1)*pourTime+t*(N);
            double time = (t*2+pourTime*2)*(loads-1);
            time += timeToTub;
            //System.out.println(timeToLast);
            System.out.println(time);
            //System.out.println(totalPourTime);
        }

    }
}
