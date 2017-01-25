import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class GetShorty {

    public void run() throws NumberFormatException, IOException
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(System.out);
            while(true)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
                if(n==0&&m==0)
                    break;
                node[] nodes = new node[n];
                for(int i = 0;i<nodes.length;i++)
                    nodes[i] = new node(i);
                
                for(int i = 0;i<m;i++)
                {
                    st = new StringTokenizer(br.readLine());
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    double c = Double.parseDouble(st.nextToken());
                    nodes[a].add(b,c);
                    nodes[b].add(a, c);
                }
                nodes[0].largest = 1;
                PriorityQueue<node> que = new PriorityQueue<node>();
                que.add(nodes[0]);
                while(!que.isEmpty())
                {
                    node current = que.poll();
                    for(int i = 0;i<current.con.size();i++)
                    {
                        if(current.largest*current.cost.get(i)>nodes[current.con.get(i)].largest)
                        {
                            nodes[current.con.get(i)].largest = current.largest*current.cost.get(i);
                            que.add(nodes[current.con.get(i)]);
                        }
                    }
                }
                System.out.printf("%.4f%n",nodes[nodes.length-1].largest);
            }
            out.close();
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new GetShorty().run();
    }
    
    private class node implements Comparable<node>{
        
        double largest;
        int id;
        ArrayList<Integer> con;
        ArrayList<Double> cost;
        
        public node(int id)
        {
            this.id = id;
            con = new ArrayList<>();
            cost = new ArrayList();
        }
        
        public void add(int a, double b)
        {
            con.add(a);
            cost.add(b);
        }
        
        public int compareTo(node o)
        {
            if(largest>o.largest)
                return -1;
            if(o.largest>largest)
                return 1;
            return 0;
        }
        
    }
    
}