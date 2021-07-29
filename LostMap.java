import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LostMap {
    
    public static void main(String[] args) throws FileNotFoundException
    {
        new LostMap().run();
    }

    public void run() throws FileNotFoundException
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int[][] ints = new int[N][N];
        for(int i = 0;i<N;i++)
        {
            for(int j = 0;j<N;j++)
            {
                ints[i][j] = file.nextInt();
            }
        }
        PriorityQueue<edge> que = new PriorityQueue<edge>();
        for(int i = 0;i<N;i++)
            for(int j = i+1;j<ints.length;j++)
            {
                que.add(new edge(i+1,j+1,ints[i][j]));
            }
        UnionFind uf = new UnionFind(N);
        while(!que.isEmpty())
        {
            edge e = que.poll();
            int a = e.a-1;
            int b = e.b-1;
            int pa = uf.parent(a);
            int pb = uf.parent(b);
            if(pa==pb)
                continue;
            uf.union(a, b);
            System.out.println(e.a+" "+e.b);
        }
    }
    
    private class edge implements Comparable<edge>{
        
        int a,b,c;
        
        public edge(int a, int b, int c)
        {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public int compareTo(edge e)
        {
            return Integer.compare(c,e.c);
        }
        
    }
    
    private class UnionFind{
        
        int[] ints;
        
        public UnionFind(int N)
        {
            ints = new int[N];
            Arrays.fill(ints, -1);
        }
        
        public int parent(int x)
        {
            if(ints[x]<=-1)
                return x;
            return ints[x] = parent(ints[x]);
        }
        
        public void union(int a, int b)
        {
            int pa = parent(a);
            int pb = parent(b);
            if(pa==pb)
                return;
            ints[pa]+=ints[pb];
            ints[pb] = pa;
        }
        
    }
    
}