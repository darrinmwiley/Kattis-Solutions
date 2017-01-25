import java.util.Scanner;
import java.util.TreeMap;


public class EstimatingTheAreaOfACircle {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        double r = file.nextDouble();
        int m = file.nextInt();
        int c = file.nextInt();
        while(r!=0)
        {
            double ratio = c/(m+0.0);
            double tr = r*r*Math.PI;
            double est = r*r*4*ratio;
            System.out.println(tr+" "+est); 
            r = file.nextDouble();
            m = file.nextInt();
            c = file.nextInt();
        }
        
    }
}