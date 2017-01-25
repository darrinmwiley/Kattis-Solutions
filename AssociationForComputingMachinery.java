import java.util.Arrays;
import java.util.Scanner;


public class AssociationForComputingMachinery {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int first = file.nextInt();
        int[] times = new int[N];
        for(int i = 0;i<times.length;i++)
            times[i] = file.nextInt();
        int time = 300-times[first];
        int penalty = time>=0?300-time:0;
        int d = time>=0?1:0;
        times[first] = 0;
        Arrays.sort(times);
        boolean[] done = new boolean[N];
        done[0] = true;
        for(int i = 1;i<done.length;i++)
        {
            if(time<=0)
                break;
            if(times[i]<=time)
            {
                d++;
                time-=times[i];
                penalty+=300-time;
            }
        }
        System.out.println(d+" "+penalty);
    }
    
    public static boolean val(int r, int c)
    {
        return Math.max(r,c)<7&&Math.min(r,c)>=0;
    }
}