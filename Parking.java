import java.util.ArrayList;
import java.util.Scanner;


public class Parking {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int a = file.nextInt();
        int b = file.nextInt();
        int c = file.nextInt();
        int[] costs = new int[]{0,a,b*2,c*3};
        int[] times = new int[101];
        for(int i = 0;i<3;i++)
        {
            int x = file.nextInt();
            int y = file.nextInt();
            for(int j = x;j<y;j++)
                times[j]++;
        }
        int q = 0;
        for(int i = 0;i<times.length;i++)
        {
            q+=costs[times[i]];
        }
        System.out.println(q);
    }
}
