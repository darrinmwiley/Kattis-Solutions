import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class MaximumLoot {

    public static void main(String[] args)
    {
        new MaximumLoot().run();
    }

    public void run()
    {
        /*for(int i = 1;i<60;i++)
        {
            System.out.println(i+" "+Math.min(2000000000,(long)(Math.pow(2.0, (75-i)/2.0))));
        }*/
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            int N = file.nextInt();
            int K = file.nextInt();
            int[] W = new int[N];
            int[] V = new int[N];
            int sum = 0;
            for(int i = 0;i<N;i++)
                V[i] = file.nextInt();
            for(int i = 0;i<N;i++)
                sum += W[i] = file.nextInt();
            double knapsack = (double)(N)*K;
            double middle = N/2*Math.pow(2,N/2);
            double value = Math.pow(2.0,(75-N)/2);
            double dp = value*N;
            if(N<=31)
                meetInMiddle(N,K,W,V);
            else
                dp(N,K,W,V);
        }
    }

    public void knapsack(int N, int K, int[] W, int[] V)
    {
        int[][] dp = new int[N+1][K+1];
        for(int i = 1;i<dp.length;i++)
            for(int j = 1;j<dp[i].length;j++)
                if(j>=W[i-1])
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-W[i-1]]+V[i-1]);
                else
                    dp[i][j] = dp[i-1][j];
        System.out.println(dp[N][K]);
    }

    public void dp(int N, int K, int[] W, int[] V)
    {
        int total = 0;
        for(int i:V)
            total+=i;
        int[] dp = new int[total+1];
        Arrays.fill(dp,K+1);
        dp[0] = 0;
        for(int i = 0;i<N;i++)
        {
            for(int j = dp.length-1;j>0;j--)
            {
                if(j>=V[i])
                    dp[j] = Math.min(dp[j],dp[j-V[i]]+W[i]);
            }
        }
        int max = 0;
        for(int i = 0;i<dp.length;i++)
            if(dp[i]<=K)
                max = Math.max(max,i);
        System.out.println(max);
    }

    public void meetInMiddle(int N, int K, int[] W, int[] V)
    {
        int halfLen = N/2;
        int[] leftW = new int[halfLen];
        int[] leftV = new int[halfLen];
        int[] rightW = new int[N-halfLen];
        int[] rightV = new int[N-halfLen];
        for(int i = 0;i<halfLen;i++)
        {
            leftW[i] = W[i];
            leftV[i] = V[i];
        }
        for(int i = 0;i<rightW.length;i++)
        {
            rightW[rightW.length-1-i] = W[W.length-1-i];
            rightV[rightV.length-1-i] = V[V.length-1-i];
        }
        ArrayList<tuple> left = new ArrayList<tuple>();
        ArrayList<tuple> right = new ArrayList<tuple>();
        permute(leftW,leftV,0,0,0,left);
        permute(rightW,rightV,0,0,0,right);
        Comparator<tuple> comp = new Comparator<tuple>(){
            @Override
            public int compare(tuple o1, tuple o2) {
                if(o1.W==o2.W)
                    return o1.V-o2.V;
                return o1.W-o2.W;
            }
        };
        Collections.sort(right,comp);
        Collections.sort(left,comp);
        dominate(left);
        dominate(right);
        int best = 0;
        for(tuple t:left)
        {
            if(t.W>K)
                continue;
            tuple match = findBest(K-t.W,right);
            best = Math.max(best,t.V+match.V);
        }
        System.out.println(best);
    }

    public void dominate(ArrayList<tuple> list)
    {
        int best = list.get(0).V;
        for(int i = 1;i<list.size();i++)
        {
            if(list.get(i).V<=best)
                list.remove(i--);
            else
                best = list.get(i).V;
        }
    }

    public tuple findBest(int W, ArrayList<tuple> list)
    {
        int L = -1;
        int R = list.size();
        tuple best = null;
        while(R-L>1)
        {

            int M = (R+L)/2;
            tuple t = list.get(M);
            if(t.W<=W)
            {
                best = t;
                L = M;
            }else
                R = M;
        }
        return best;
    }

    public void permute(int[] W, int[] V, int w, int v, int index, ArrayList<tuple> list)
    {
        if(index==W.length)
            list.add(new tuple(w,v));
        else{
            permute(W,V,w+W[index],v+V[index],index+1,list);
            permute(W,V,w,v,index+1,list);
        }
    }

    private class tuple{

        int W,V;

        public tuple(int a, int b)
        {
            W = a;
            V = b;
        }

        public String toString()
        {
            return "(W="+W+", V="+V+")";
        }

    }

}
