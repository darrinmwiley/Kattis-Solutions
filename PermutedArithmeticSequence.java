import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class PermutedArithmeticSequence {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++){
            int N = file.nextInt();
            int[] seq = new int[N];
            for(int i = 0;i<N;i++)
                seq[i] = file.nextInt();
            if(test(seq))
                System.out.println("arithmetic");
            else{
                Arrays.sort(seq);
                if(test(seq))
                    System.out.println("permuted arithmetic");
                else
                    System.out.println("non-arithmetic");
            }
        }
    }
    
    public static boolean test(int[] in)
    {
        int diff = in[1]-in[0];
        for(int i = 1;i<in.length-1;i++)
            if(in[i+1]-in[i]!=diff)
                return false;
        return true;
    }
}