import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class LandlineTelephoneNetwork {
    public void run() throws Exception
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        if(N==1)
        {
            System.out.println(0);
            return;
        }
        int M = file.nextInt();
        int I = file.nextInt();
        boolean[] unsafe = new boolean[N];
        for(int i = 0;i<I;i++)
            unsafe[file.nextInt()-1] = true;
        PriorityQueue<Edge> bad = new PriorityQueue<Edge>();
        PriorityQueue<Edge> que = new PriorityQueue<Edge>();
        PriorityQueue<Edge> stupid = new PriorityQueue<Edge>();
        for(int i =0;i<M;i++)
        {
            int a = file.nextInt()-1;
            int b = file.nextInt()-1;
            int wt = file.nextInt();
            if(!unsafe[a]&&!unsafe[b])
                que.add(new Edge(a,b,wt));
            else if(unsafe[a]^unsafe[b])
                bad.add(new Edge(a,b,wt));
            else
                stupid.add(new Edge(a,b,wt));
        }
        boolean[] con = new boolean[N];
        if(N==2&&I==2&&!stupid.isEmpty())
        {
            System.out.println(stupid.poll().wt);
            return;
        }
        Edge ed = null;
        if(que.isEmpty()&&bad.isEmpty())
        {
            System.out.println("impossible");
            return;
        }
        if(que.isEmpty())
            ed = bad.poll();
        else
            ed = que.poll();
        con[ed.a] = true;
        con[ed.b] = true;
        long sum = ed.wt;
        int numConnected = 2;
        while(!que.isEmpty())
        {
            Queue<Edge> refill = new LinkedList<Edge>();
            while(!que.isEmpty())
            {
                Edge e = que.poll();
                if(con[e.a]^con[e.b])
                {
                    con[e.a]=true;
                    con[e.b]=true;
                    sum+=e.wt;
                    que.addAll(refill);
                    numConnected++;
                    break;
                }
                if(!con[e.a]&&!con[e.b])
                {
                    refill.add(e);
                }
            }
        }
        while(!bad.isEmpty())
        {
            Edge e = bad.poll();
            if(con[e.a]^con[e.b])
            if(unsafe[e.a]&&con[e.b]||unsafe[e.b]&&con[e.a])
            {
                con[e.a] = true;
                con[e.b] = true;
                sum +=e.wt;
                numConnected++;
            }
        }
        if(numConnected==N)
            System.out.println(sum);
        else
            System.out.println("impossible");
    }
    
    public static void main(String[] args) throws Exception
    {
        new LandlineTelephoneNetwork().run();  
    }
}class Edge implements Comparable<Edge>{
    
    int a,b;
    int wt;
    public Edge(int x, int y, int z)
    {
        a = x;
        b = y;
        wt = z;
    }
    
    public int compareTo(Edge e)
    {
        return wt-e.wt;
    }
}
