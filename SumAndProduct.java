import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumAndProduct {

    public static void main(String[] args) throws IOException {
        new SumAndProduct().run();
    }


    public void run() throws IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(file.readLine());
        long[] ints = new long[N];
        StringTokenizer st = new StringTokenizer(file.readLine());
        for(int i = 0;i<N;i++)
        {
            ints[i] = Long.parseLong(st.nextToken());
        }
        int[] oneFront = new int[ints.length + 1];
        oneFront[N] = 0;
        for(int i = ints.length - 1;i>=0;i--)
        {
            oneFront[i] = ints[i] == 1? oneFront[i+1]+1:0;
        }
        int ans = 0;
    loop:
        for(int i = 0;i<ints.length;i++)
        {
            long sum = ints[i];
            long mult = ints[i];
            for(int j = i+1;j<ints.length;j++)
            {
                if(mult - sum > 200001)
                    continue loop;
                if(ints[j] == 1)
                {
                    int skip = oneFront[j];
                    if(sum < mult && sum + skip >= mult)
                    {
                        ans++;
                    }
                    j += skip - 1;
                    sum += skip;
                }else{
                    sum += ints[j];
                    mult *= ints[j];
                    if(sum == mult)
                    {
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
