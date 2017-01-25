import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ClosingTheLoop {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            ArrayList<Integer> red = new ArrayList<Integer>();
            ArrayList<Integer> blue = new ArrayList<Integer>();
            int N = file.nextInt();
            for(int i =0 ;i<N;i++)
            {
                String next = file.next();
                int M = Integer.parseInt(next.replaceAll("[RB]",""));
                if(next.endsWith("B"))
                    blue.add(M);
                else
                    red.add(M);
            }
            Collections.sort(red);
            Collections.sort(blue);
            Collections.reverse(red);
            Collections.reverse(blue);
            int min = Math.min(red.size(),blue.size());
            int max = 0;
            for(int i = 0;i<min;i++)
            {
                max+=red.remove(0);
                max+=blue.remove(0);
            }
            System.out.printf("Case #%d: %d%n",z+1,max-min*2);
        }
    }
    
    public static boolean val(int r, int c)
    {
        return Math.max(r,c)<7&&Math.min(r,c)>=0;
    }
}