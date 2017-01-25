import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class KitchenMeasurements {
    
    public void run()
    {   
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int[] cups = new int[5];
        for(int i = 0;i<N;i++)
            cups[i] = file.nextInt();
        int goal = file.nextInt();
        int[][][][] dp = new int[cups[0]+1][cups[1]+1][cups[2]+1][cups[3]+1];
        boolean[][][][] reached = new boolean[cups[0]+1][cups[1]+1][cups[2]+1][cups[3]+1];
        state start = new state(cups[0],0,0,0,0,0);
        Queue<state> que = new LinkedList<state>();
        que.add(start);
        while(!que.isEmpty())
        {
            state s = que.poll();
            if(!reached[s.a][s.b][s.c][s.d]||dp[s.a][s.b][s.c][s.d]>s.time)
            {
                reached[s.a][s.b][s.c][s.d] = true;
                dp[s.a][s.b][s.c][s.d] = s.time;
                for(int i = 0;i<5;i++)
                    for(int j = 0;j<5;j++)
                    {
                        if(i!=j)
                        {
                            int[] state = new int[]{s.a,s.b,s.c,s.d,s.e};
                            int amountAvailableI = state[i];
                            int amountAvailableJ = cups[j]-state[j];
                            int trans = Math.min(amountAvailableI,amountAvailableJ);
                            state[i]-=trans;
                            state[j]+=trans;
                            state nextState = new state(state[0],state[1],state[2],state[3],state[4],s.time+trans);
                            if(!reached[nextState.a][nextState.b][nextState.c][nextState.d]||nextState.time<dp[nextState.a][nextState.b][nextState.c][nextState.d])
                            {
                                que.add(nextState);
                            }
                        }
                    }
            }
        }
        boolean reach = false;
        int min = 0;
        for(int i = 0;i<dp[0].length;i++)
            for(int j = 0;j<dp[0][0].length;j++)
                for(int k = 0;k<dp[0][0][0].length;k++)
                {
                    //System.out.println(goal+" "+i+" "+j+" "+k);
                    if(reached[goal][i][j][k])
                    {
                        if(!reach)
                        {
                            min = dp[goal][i][j][k];
                            reach = true;
                        }else
                            min = Math.min(min,dp[goal][i][j][k]);
                    }
                }
        if(!reach)
            System.out.println("impossible");
        else
            System.out.println(min);
    }
    
    private class state
    {
        int a,b,c,d,e,time;
        public state(int a, int b, int c, int d,int e, int time)
        {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.time = time;
        }
        public String toString()
        {
            return a+" "+b+" "+c+" "+d+" "+e+" "+time;
        }
    }
    
    public static void main(String[] args)
    {
        new KitchenMeasurements().run();
    }
    
}