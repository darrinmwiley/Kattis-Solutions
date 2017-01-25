import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;


public class PebbleSolitaire {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        file.nextLine();
        for(int z = 0;z<zz;z++)
        {
            String next = file.nextLine();
            boolean[] bools = new boolean[12];
            int pebbles = 0;
            for(int i = 0;i<12;i++){
                bools[i] = next.charAt(i)=='o';
                if(bools[i])
                    pebbles++;
            }
            System.out.println(bruteForce(bools,pebbles));
        }
    }
    
    public static int bruteForce(boolean[] bools, int x)
    {
        int min = x;
        for(int i = 1;i<bools.length-1;i++)
        {
            if(bools[i]&&(bools[i-1]^bools[i+1]))
            {
                bools[i] = false;
                bools[i-1] = !bools[i-1];
                bools[i+1] = !bools[i+1];
                min = Math.min(min,bruteForce(bools,x-1));
                bools[i] = true;
                bools[i-1] = !bools[i-1];
                bools[i+1] = !bools[i+1];
            }
        }
        return min;
    }
}