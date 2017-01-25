import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;


public class FlexibleSpaces {

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(System.in);
        int W = file.nextInt();
        int N = file.nextInt();
        int[] ints = new int[N+2];
        ints[0] = 0;
        ints[N+1] = W;
        for(int i = 0;i<N;i++)
        {
            ints[i+1] = file.nextInt();
        }
        TreeSet<Integer> set = new TreeSet<Integer>();
        for(int i = 0;i<ints.length-1;i++)
        {
            for(int j = i+1;j<ints.length;j++)
            {
                set.add(ints[j]-ints[i]);
            }
        }
        for(int i:set)
            System.out.print(i+" ");
    }
}