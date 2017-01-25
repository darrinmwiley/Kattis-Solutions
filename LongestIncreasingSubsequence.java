import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class LongestIncreasingSubsequence {
    
    public void run() throws IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = file.readLine())!=null)
        {
            int N = Integer.parseInt(line.trim());
            StringTokenizer st = new StringTokenizer(file.readLine());
            long[] ints = new long[N];
            for(int i = 0;i<N;i++)
                ints[i] = Integer.parseInt(st.nextToken());
            int[] pred = new int[N];
            Arrays.fill(pred,-1);
            int[] list = new int[N];
            int l = 0;
         loop:
            for(int i = 0;i<ints.length;i++)
            {
                if(l==0)
                    list[l++] = i;
                else if(ints[list[0]]>=ints[i])
                {
                    list[0] = i;
                }
                else if(ints[list[l-1]]<ints[i])
                {
                    list[l++] = i;
                    pred[i] = list[l-2];
                }
                else{
                    int L = 0;
                    int R = l;
                    int M = (R+L)/2;
                    while(R-L>1)
                    {
                        if(ints[list[M]]==ints[i])
                            continue loop;
                        if(ints[list[M]]>ints[i])
                        {
                            R = M;
                        }else
                            L = M;
                        M = (L+R)/2;
                    }
                    list[R]=i;
                    pred[i] = list[R-1];    
                }
            }
            System.out.println(l);
            int current = list[l-1];
            Stack<Integer> sta = new Stack<Integer>();
            while(current!=-1)
            {
                sta.add(current);
                current = pred[current];
            }
            while(!sta.isEmpty())
            {
                System.out.print(sta.pop());
                if(!sta.isEmpty())
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        new LongestIncreasingSubsequence().run();
    }
}