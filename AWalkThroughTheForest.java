import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class AWalkThroughTheForest {
    
    public void go() throws IOException {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            StringTokenizer sta = new StringTokenizer(file.readLine());
            int N = Integer.parseInt(sta.nextToken());
            if(N==0)
                return;
            int M = Integer.parseInt(sta.nextToken());
            node[] nodes = new node[N];
            for(int i = 0;i<nodes.length;i++)
                nodes[i] = new node(i);
            for(int i = 0;i<M;i++)
            {
                sta = new StringTokenizer(file.readLine());
                int a = Integer.parseInt(sta.nextToken())-1;
                int b = Integer.parseInt(sta.nextToken())-1;
                int d = Integer.parseInt(sta.nextToken());
                nodes[a].add(b,d);
                nodes[b].add(a,d);
            }
            PriorityQueue<State> que = new PriorityQueue<State>();
            State start = new State(1,0);
            que.add(start);
            while(!que.isEmpty())
            {
                State st = que.poll();
                int id = st.nodeID;
                node n = nodes[id];
                int fp = st.fp;
                if(n.fp>fp)
                {
                    n.fp = fp;
                    for(int i = 0;i<n.con.size();i++)
                    {
                        if(nodes[n.con.get(i)].fp>n.fp+nodes[id].len.get(i))
                        {
                            que.add(new State(n.con.get(i),n.fp+nodes[id].len.get(i)));
                        }
                    }
                }
            }
            node[] sorted = nodes.clone();
            Arrays.sort(sorted);
            sorted[0].paths = 1;
            for(int i = 1;i<sorted.length;i++)
            {
                for(int j = 0;j<sorted[i].con.size();j++)
                {
                    if(sorted[i].fp>nodes[sorted[i].con.get(j)].fp)
                        sorted[i].paths+=nodes[sorted[i].con.get(j)].paths;
                }
            }
            System.out.println(nodes[0].paths);
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        new AWalkThroughTheForest().go();
    }
    
    private class State implements Comparable<State>
    {
        int nodeID; 
        int fp;
        public State(int n, int fp){
            nodeID = n;
            this.fp =fp;
        }

        @Override
        public int compareTo(State o) {
            return fp-o.fp;
        }
    }
    
    private class node implements Comparable<node>{
        ArrayList<Integer> con;
        ArrayList<Integer> len;
        int id,fp,paths;
        public node(int v)
        {
            id = v;
            fp = Integer.MAX_VALUE/2;
            con = new ArrayList();
            len = new ArrayList();
        }
        public void add(int i, int j)
        {
            con.add(i);
            len.add(j);
        }
        @Override
        public int compareTo(node o) {
            return fp-o.fp;
        }
    }
    
}