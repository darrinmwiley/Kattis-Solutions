import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Outing {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int C = file.nextInt();
        int[] ints = new int[N];
        for(int i = 0;i<ints.length;i++)
        {
            ints[i] = file.nextInt()-1;
        }
        int[] len = new int[N];
        for(int i = 0;i<ints.length;i++)
            len[i] = getLen(ints,i);
        int[] fill = new int[ints.length];
        Arrays.fill(fill,-1);
        int x = 0;
        for(int i = 0;i<fill.length;i++)
        {
            if(fill[i]==-1)
            {
                int y = getFill(ints,fill,i);
                if(y==-1)
                    y = x++;
                fill(ints,fill,i,y);
            }
        }
        ArrayList[] lists = new ArrayList[x];
        for(int i =0;i<x;i++)
        {
            lists[i] = new ArrayList<Integer>();
            int min = 1000;
            int tot = 0;
            for(int j =0;j<fill.length;j++)
                if(fill[j]==i)
                {
                    min = Math.min(min,len[j]);
                    tot++;
                }
            for(int j = min;j<=tot;j++)
                lists[i].add(j);
        }
        boolean [] dp = new boolean[C+1];
        dp[0] = true;
        for(int i = 0;i<lists.length;i++)
        {
            Stack<Integer> st = new Stack<Integer>();
            ArrayList<Integer> list = (ArrayList<Integer>)(lists[i]);
            for(int j:list)
            {
                for(int k = j;k<dp.length;k++)
                {
                    if(dp[k-j])
                        st.add(k);
                }
            }
            for(int n:st)
                dp[n] = true;
        }
        int best = 0;
        for(int i = 0;i<dp.length;i++)
        {
            if(dp[i])
                best =i;
        }
        System.out.println(best);
    }
    
    public int getLen(int[] ints, int i)
    {
        boolean[] vis = new boolean[ints.length];
        int c = 0;
        while(true)
        {
            if(vis[i])
                return c;
            vis[i] = true;
            i = ints[i];
            c++;
        }
    }
    
    
    
    public void fill(int[] ints, int[] fill, int start, int id)
    {
        while(true)
        {
            if(fill[start]!=-1)
                return;
            fill[start] = id;
            start = ints[start];
        }
    }
    
    public int getFill(int[] ints,int[] fill, int i)
    {
        boolean[] vis = new boolean[ints.length];
        while(true)
        {
            if(fill[i]!=-1)
                return fill[i];
            if(vis[i])
                return -1;
            vis[i] = true;
            i = ints[i];
        }
    }
    
    public static void main(String[] args)
    {
        new Outing().run();
    }
}
