import java.awt.Point;
import java.util.Scanner;

public class BottledFeelings {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int a = file.nextInt();
        int b = file.nextInt();
        int c = file.nextInt();
        for(int i = a/b;i>=0;i--)
        {
            if((a-(i*b))%c==0)
            {
                System.out.println(i+" "+(a-(i*b))/c);
                return;
            }
        }
        System.out.println("Impossible");
    }
    
    public static void main(String[] args)
    {
        new BottledFeelings().run();
    }
}