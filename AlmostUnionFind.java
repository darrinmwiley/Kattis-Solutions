import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AlmostUnionFind {
    
    int[] arr;
    int[] point;
    long[] sum;
    int size;
    public void run() throws IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String line;
        while((line = file.readLine())!=null)
        {
            StringTokenizer st = new StringTokenizer(line);
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            arr = new int[N+Q];
            point = new int[N];
            sum = new long[N+Q];
            size = N;
            Arrays.fill(arr,-1);
            for(int i = 0;i<N;i++)
                point[i] = i;
            for(int i = 0;i<N;i++)
            {
                sum[i] = i+1;
            }
            for(int i = 0;i<Q;i++)
            {
                st = new StringTokenizer(file.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(a==1)
                {
                    int c = Integer.parseInt(st.nextToken());
                    union(b-1,c-1);
                }else if(a==2){
                    int c = Integer.parseInt(st.nextToken());
                    move(b-1,c-1);
                }else{
                    out.println(-arr[find(point[b-1])]+" "+sum[find(point[b-1])]);
                }
            }
        }
        
        out.flush();
        out.close();
    }
    
    public void union(int a, int b)
    {
        int aroot = find(point[a]);
        int broot = find(point[b]);
        if(aroot == broot)
            return;
        sum[aroot]+=sum[broot];
        arr[aroot]+=arr[broot];
        arr[broot] = aroot;
    }
    
    public int find(int a)
    {
        if(arr[a]<0)
        {
            return a;
        }
        return arr[a] = find(arr[a]);
    }
    
    public void move(int a, int b)
    {
        int roota = find(point[a]);
        int rootb = find(point[b]);
        if(roota == rootb)
            return;
        arr[roota]++;
        arr[rootb]--;
        sum[roota]-=a+1;
        sum[rootb]+=a+1;
        point[a] = size++;
        arr[point[a]] = rootb;
    }
    
    public static void main(String[] args) throws IOException
    {
        new AlmostUnionFind().run();
    }
}