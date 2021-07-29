import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AbsurdistanRoads2 {
    
    node[] nodes;
    int N;
    
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new AbsurdistanRoads2().genCase(50000);
        new AbsurdistanRoads2().run();  
    }
    
    public void run() throws NumberFormatException, IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(file.readLine());
        nodes = new node[N];
        for(int i = 0;i<nodes.length;i++)
        {
            nodes[i] = new node(i);
        }
        StringTokenizer st;
        for(int i = 0;i<N;i++)
        {
            st = new StringTokenizer(file.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            nodes[a].con(b);
            nodes[b].con(a);
        }
        Queue<Integer> zero = new LinkedList<Integer>();
        for(node n:nodes)
            if(n.deg==1)
                zero.add(n.id);
        int edges = 0;
        while(!zero.isEmpty())
        {
            int x = zero.poll();
            int con = nodes[x].first();
            System.out.println(x+1+" "+(con+1));
            nodes[con].rem(x);
            nodes[x].rem(con);
            if(nodes[con].deg==1)
                zero.add(con);
            edges++;
        }
        while(edges!=N)
        {
	        int start = -1;
	        for(int i = 0;i<nodes.length;i++)
	            if(nodes[i].deg>1)
	            {
	                start = i;
	                break;
	            }
	        while(true)
	        {
	            node current = nodes[start];
	            if(current.deg==0)
	                break;
	            int next = current.first();
	            nodes[next].rem(current.id);
	            current.rem(next);
	            System.out.println(1+current.id+" "+(next+1));
	            start = next;
	            edges++;
	        }
        }
        
    }
    
    public void genCase(int N) throws IOException
    {
        PrintWriter out = new PrintWriter(new FileWriter(new File("input.txt")));
        out.println(N);
        UnionFind uf = new UnionFind(N);
        for(int i = 0;i<N-1;i++)
        {
            while(true){
                int a = (int)(Math.random()*N);
                int b = (int)(Math.random()*N);
                if(uf.find(a)!=uf.find(b))
                {
                    uf.union(a, b);
                    out.println(a+1+" "+(b+1));
                    break;
                }
            }
        }
        int a = (int)(Math.random()*N);
        int b = (int)(Math.random()*N);
        out.println(a+1+" "+(b+1));
        out.close();
    }
    
    private class UnionFind{
        
        int[] ints;
        
        public UnionFind(int N)
        {
            ints = new int[N];
            Arrays.fill(ints, -1);
        }
        
        public int find(int N)
        {
            if(ints[N]<0)
                return N;
            return ints[N] = find(ints[N]);
        }
        
        public void union(int a, int b)
        {
            int pa = find(a);
            int pb = find(b);
            if(pa==pb)
                return;
            ints[pa]+=ints[pb];
            ints[pb] = pa;
        }
        
    }
    
    private class node{
        int id;
        int deg;
        HashMap<Integer,Integer> con;
        public node(int id)
        {
            this.id = id;
            con = new HashMap<Integer,Integer>();
        }
        public void con(int x)
        {
            deg++;
            if(!con.containsKey(x))
                con.put(x,1);
            else
                con.put(x,con.get(x)+1);
        }
        public void rem(int x)
        {
            con.put(x, con.get(x)-1);
            if(con.get(x)==0)
                con.remove(x);
            deg--;
        }
        public int first()
        {
            return con.keySet().iterator().next();
        }
        public String toString()
        {
            return id+" "+con;
        }
    }
    
}