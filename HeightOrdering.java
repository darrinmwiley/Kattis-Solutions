import java.util.ArrayList;
import java.util.Scanner;


public class HeightOrdering {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        for(int i = 0;i<N;i++)
        {
            file.nextInt();
            int[] in = new int[20];
            int swaps = 0;
            for(int j = 0;j<20;j++)
                in[j] = file.nextInt();
            for(int j = 1;j<in.length;j++)
            {
                int k = j;
                while(k>0&&in[k]<in[k-1])
                {
                    int save = in[k];
                    in[k] = in[k-1];
                    in[--k] = save;
                    swaps++;
                }
            }
            System.out.println(i+1+" "+swaps);
        }
    }
}