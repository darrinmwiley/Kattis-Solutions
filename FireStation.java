import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class FireStation {

    public void run() throws NumberFormatException, IOException
    {
            //BufferedReader br = new BufferedReader(new FileReader(new File("bphoto.in")));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(System.out);
            int zz = Integer.parseInt(br.readLine());
            br.readLine();
            for(int z = 0;z<zz;z++)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                
                int f = Integer.parseInt(st.nextToken());
                int I = Integer.parseInt(st.nextToken());
                if(I==1){
                    System.out.println(1);
                }
                    
                node[] nodes = new node[I];
                int[] stations = new int[f];
                for(int i = 0;i<nodes.length;i++)
                    nodes[i] = new node(i);
                for(int i = 0;i<f;i++)
                {
                    stations[i] = Integer.parseInt(br.readLine())-1;
                }
                while(true)
                {
                    String line = br.readLine();
                    if(line==null||line.isEmpty())
                    {
                        break;
                    }
                    st = new StringTokenizer(line);
                    int a = Integer.parseInt(st.nextToken())-1;
                    int b = Integer.parseInt(st.nextToken())-1;
                    int c = Integer.parseInt(st.nextToken());
                    nodes[a].add(b, c);
                    nodes[b].add(a, c);
                }
                if(I!=1)
                {
                    PriorityQueue<node> que = new PriorityQueue<node>();
                    boolean[] fs = new boolean[I];
                    for(int i:stations){
                        nodes[i].fp=0;
                        que.add(nodes[i]);
                        fs[i] = true;
                    }
                    
                    boolean[] vis = new boolean[I];
                    while(!que.isEmpty())
                    {
                        node current = que.poll();
                        if(vis[current.id])
                            continue;
                        vis[current.id] = true;
                        for(int i = 0;i<current.con.size();i++)
                        {
                            if(current.fp+current.cost.get(i)<nodes[current.con.get(i)].fp)
                            {
                                nodes[current.con.get(i)].fp = current.fp+current.cost.get(i);
                                que.add(nodes[current.con.get(i)]);
                            }
                        }
                    }
                    
                    Comparator<node> comp = new Comparator<node>(){
                        
                        public int compare(node a, node b)
                        {
                            if(a.fp2<b.fp2)
                                return -1;
                            if(a.fp2>b.fp2)
                                return 1;
                            return 0;
                        }
                        
                    };
                    int lowest = 0;
                    int best = -1;
                    for(node i:nodes)
                        lowest = Math.max(lowest,i.fp);
                    best = 0;
                    for(int q = 0;q<I;q++)
                    {
                        if(fs[q])
                            continue;
                        for(node n:nodes)
                            n.reset();
                        que = new PriorityQueue<node>(comp);
                        nodes[q].fp2 = 0;
                        que.add(nodes[q]);
                        vis = new boolean[I];
                        while(!que.isEmpty())
                        {
                            node current = que.poll();
                            if(vis[current.id])
                                continue;
                            vis[current.id] = true;
                            for(int i = 0;i<current.con.size();i++)
                            {
                                if(current.fp2+current.cost.get(i)<nodes[current.con.get(i)].fp2)
                                {
                                    nodes[current.con.get(i)].fp2 = current.fp2+current.cost.get(i);
                                    que.add(nodes[current.con.get(i)]);
                                }
                            }
                        }
                        int current = 0;
                        for(node i:nodes)
                        {
                            current = Math.max(i.fp2, current);
                        }
                        if(current<lowest)
                        {
                            lowest = current;
                            best = q;
                        }
                    }
                    System.out.println(best+1);
                    System.out.println();
                }
            }
            out.close();
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new FireStation().run();
    }
    
    private class node implements Comparable<node>{
        
        int fp = Integer.MAX_VALUE/4;
        int fp2 = Integer.MAX_VALUE/4;
        int id;
        ArrayList<Integer> con;
        ArrayList<Integer> cost;
        
        public node(int id)
        {
            this.id = id;
            con = new ArrayList<>();
            cost = new ArrayList<>();
        }
        
        public void add(int a, int b)
        {
            con.add(a);
            cost.add(b);
        }
        
        public void reset()
        {
            fp2 = fp;
        }
        
        public int compareTo(node o)
        {
            if(fp>o.fp)
                return 1;
            if(o.fp>fp)
                return -1;
            return 0;
        }
        
    }
    
}