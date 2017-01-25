import java.util.ArrayList;
import java.util.Scanner;


public class Kornislav {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int a = file.nextInt();
        int b = file.nextInt();
        int c = file.nextInt();
        int d = file.nextInt();
        int ans1 = Math.min(a,b)*Math.min(c,d);
        int ans2 = Math.min(a,c)*Math.min(b,d);
        int ans3 = Math.min(a,d)*Math.min(b,c);
        System.out.println(Math.max(Math.max(ans1,ans2),ans3));
    }
}