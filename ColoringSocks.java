import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class ColoringSocks {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int C = file.nextInt();
        int K = file.nextInt();
        int[] socks = new int[N];
        for(int i = 0;i<N;i++)
            socks[i] = file.nextInt();
        Arrays.sort(socks);
        int currentSocks = 1;
        int smallestSock = socks[0];
        int washers = 1;
        for(int i = 1;i<socks.length;i++)
        {
            if(socks[i]-smallestSock<=K&&currentSocks!=C)
                currentSocks++;
            else
            {
                currentSocks = 1;
                smallestSock = socks[i];
                washers++;
            }
        }
        System.out.println(washers);
    }
}