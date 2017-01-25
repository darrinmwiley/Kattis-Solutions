import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;


public class JustAMinute {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        double total = 0;
        double total2 = 0;
        for(int i = 0;i<N;i++)
        {
            total+=file.nextInt()*60;
            total2+=file.nextInt();
        }
        double ans = total2/total;
        if(ans<=1)
            System.out.println("measurement error");
        else
        System.out.println(total2/total);
    }
}