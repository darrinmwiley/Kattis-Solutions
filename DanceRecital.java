import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DanceRecital {
    
    public void run()
    {   
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int[][] dists = new int[N][N];
        String[] strs =  new String[N];
        for(int i = 0;i<N;i++)
            strs[i] = file.next();
        for(int i = 0;i<N;i++)
            for(int j = 0;j<N;j++)
            {
                dists[i][j] = dist(strs[i],strs[j]);
            }
        System.out.println(bruteForce(-1,new boolean[N],dists,0,0));
    }
    
    public int bruteForce(int current, boolean[] done, int[][] dists, int cost, int vis)
    {
        if(vis==done.length)
            return cost;
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<done.length;i++)
        {
            if(!done[i])
            {
                done[i] = true;
                int newCost = 0;
                if(current != -1)
                    newCost = cost+ dists[current][i];
                min = Math.min(min,bruteForce(i,done,dists, newCost,vis+1));
                done[i] = false;
            }
        }
        return min;
    }
    
    public int dist(String a, String b)
    {
        int ret = 0;
        for(char ch:a.toCharArray())
        {
            if(b.contains(ch+""))
                ret++;
        }
        return ret;
    }
    
    public static void main(String[] args)
    {
        new DanceRecital().run();
    }
    
}