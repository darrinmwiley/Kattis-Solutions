import java.util.*;
import java.io.*;

public class Single_Source_Shortest_Path_Non_Negative_Weights {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        while(true)
        {
            int n = file.nextInt();
            int m = file.nextInt();
            int q = file.nextInt();
            int s = file.nextInt();
            if(n==0&&m==0&&q==0&&s==0)
                break;
            Vertex[] vertices = new Vertex[n];
            for(int i = 0;i<n;i++)
                vertices[i] = new Vertex(i);
            for(int i = 0;i<m;i++)
                vertices[file.nextInt()].add(vertices[file.nextInt()],file.nextInt());
            PriorityQueue<Vertex> que = new PriorityQueue<Vertex>();
            vertices[s].fastestPath = 0;
            que.add(vertices[s]);
            while(!que.isEmpty())
            {
                Vertex v = que.poll();
                for(int i = 0;i<v.list.size();i++)
                {
                    Vertex x = v.list.get(i);
                    if(x.update(v.fastestPath+v.cost.get(i)))
                    {
                        que.remove(x);
                        que.add(x);
                    }
                }
            }
            for(int i = 0;i<q;i++)
            {
                int z = file.nextInt();
                if(vertices[z].fastestPath==Integer.MAX_VALUE)
                    System.out.println("Impossible");
                else
                    System.out.println(vertices[z].fastestPath);
            }
            System.out.println();           
        }
    }
    
    public static void main(String[] args)
    {
        new Single_Source_Shortest_Path_Non_Negative_Weights().run();
    }
}
class Vertex implements Comparable<Vertex>{
    ArrayList<Vertex> list;
    ArrayList<Integer> cost;
    long fastestPath = Integer.MAX_VALUE;
    int id;
    public Vertex(int id)
    {
        this.id = id;
        list = new ArrayList<Vertex>();
        cost = new ArrayList<Integer>();
    }
    public void add(Vertex v, int i)
    {
        list.add(v);
        cost.add(i);
    }
    public int compareTo(Vertex v)
    {
        if(fastestPath<v.fastestPath)
            return -1;
        if(fastestPath==v.fastestPath)
            return 0;
        return 1;
    }
    public boolean equals(Vertex v)
    {
        return id == v.id;
    }
    public boolean update(long length)
    {
        if(length<fastestPath)
        {
            fastestPath = length;
            return true;
        }return false;
    }
}