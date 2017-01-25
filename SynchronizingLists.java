import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;


public class SynchronizingLists {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        while(N!=0)
        {
            int[] a = new int[N];
            int[] b = new int[N];
            int[] orig = new int[N];
            for(int i = 0;i<N;i++)
            {
                a[i] = orig[i] = file.nextInt();
            }
            for(int i = 0;i<N;i++)
            {
                b[i] = file.nextInt();
            }
            Arrays.sort(a);
            Arrays.sort(b);
            TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
            for(int i = 0;i<orig.length;i++)
            {
                map.put(a[i],b[i]);
            }
            for(int i =0;i<orig.length;i++)
            {
                System.out.println(map.get(orig[i]));
            }
            System.out.println();
            N = file.nextInt();
        }
    }
}