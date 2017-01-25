import java.util.Arrays;
import java.util.Scanner;


public class Billiard {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int a = file.nextInt();
        int b = file.nextInt();
        int c = file.nextInt();
        int d = file.nextInt();
        int e = file.nextInt();
        while(a!=0)
        {
            int horzDistTraveled = a*d;
            int vertDistTraveled = b*e;
            double totalDist = Math.hypot(horzDistTraveled,vertDistTraveled);
            double velocity = totalDist/c;
            double angle = 90-Math.toDegrees(Math.atan((horzDistTraveled+0.0)/vertDistTraveled));
            a = file.nextInt();
            b = file.nextInt();
            c = file.nextInt();
            d = file.nextInt();
            e = file.nextInt();
            System.out.printf("%.2f %.2f %n",angle,velocity);
        }
    }
}