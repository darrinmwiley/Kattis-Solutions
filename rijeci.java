import java.util.Scanner;


public class rijeci {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int n = file.nextInt();
        long b = 0;
        long a = 1;
        for(int i = 0;i<n;i++)
        {
            long c = b;
            b+=a;
            a = c;
        }
        System.out.println(a+" "+b);
    }
    
}