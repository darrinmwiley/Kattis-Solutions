import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;


public class VirtualFriends {

    public void run() throws Exception
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pout = new PrintWriter(System.out);
        int zz = Integer.parseInt(file.readLine());
        UnionFind ds = new UnionFind(200000);
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        StringTokenizer st;
        for(int z = 0;z<zz;z++)
        {
            int N = Integer.parseInt(file.readLine());
            ds.clear();
            for(int i = 0;i<N;i++)
            {
            		st = new StringTokenizer(file.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if(!map.containsKey(a))
                    map.put(a,map.size());
                if(!map.containsKey(b))
                    map.put(b,map.size());
                ds.union(map.get(a),map.get(b));
                pout.println(-ds.p[ds.find(map.get(a))]);
            }
        }
        pout.flush();
        pout.close();

    }

    public static void main(String[] args) throws Exception
    {
        new VirtualFriends().run();
    }
}
class UnionFind{

    public int[] p;
    HashSet<Integer> toClear;

    public UnionFind(int N)
    {
        p = new int[N];
        Arrays.fill(p,-1);
        toClear = new HashSet<Integer>();
    }

    public void clear()
    {
            for(int x: toClear)
            {
                p[x] = -1;
            }
            toClear.clear();
    }

    public void union(int a, int b)
    {
        int pa = find(a);
        int pb = find(b);
        if(pa != pb)
        {
                p[pa]+=p[pb];
            p[pb]=pa;
        }
    }

    public int find(int a)
    {
            toClear.add(a);
        if(p[a]<0)
            return a;
        return p[a] = find(p[a]);
    }

}
