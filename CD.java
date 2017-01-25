import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CD {

    public static void main(String[] args) throws NumberFormatException, IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        while(true)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if(N==0&&M==0)
                break;
            HashSet<Integer> set = new HashSet<Integer>();
            for(int i = 0;i<N;i++)
                set.add(Integer.parseInt(br.readLine()));
            int ans = 0;
            for(int i = 0;i<M;i++)
            {
                int x = Integer.parseInt(br.readLine());
                if(set.contains(x))
                    ans++;
            }
            out.println(ans);   
        }
        
        out.close();
    }
    
}