import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class BlackFriday {

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int[] die = new int[7];
        int[] ints = new int[N];
        int max = 0;
        for(int i = 0;i<N;i++)
        {
            int M = file.nextInt();
            die[M]++;
            ints[i] = M;
        }
        int best = -1;
        for(int i = 6;i>-1;i--)
        {
            if(die[i]==1)
            {
                best = i;
                break;
            }
        }
        if(best==-1)
            System.out.println("none");
        else
            for(int i = 0;i<ints.length;i++)
                if(ints[i]==best)
                    System.out.println(i+1);
    }
}