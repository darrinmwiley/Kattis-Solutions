import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ElementaryMath {
    static ArrayList<node> list;
    static boolean[][] adj;
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int[][] ints = new int[N][2];
        for(int i = 0;i<N;i++)
        {
            ints[i][0] = file.nextInt();
            ints[i][1] = file.nextInt();
        }
        list = new ArrayList<node>();
        list.add(new node("source"));
        for(int i = 0;i<N;i++)
            list.add(new node(ints[i][0]+" "+ints[i][1]));
        for(int i = 0;i<N;i++)
        {
            int plus = ints[i][0]+ints[i][1];
            int minus = ints[i][0]-ints[i][1];
            long times = (long)(ints[i][0])*ints[i][1];
            if(!node.nodeExists(plus+""))
                list.add(new node(plus+""));
            node.map.get(i+1).add(node.stringMap.get(plus+""));
            node.stringMap.get(plus+"").weakAdd(node.map.get(i+1));
            if(!node.nodeExists(minus+""))
                list.add(new node(minus+""));
            node.map.get(i+1).add(node.stringMap.get(minus+""));
            node.stringMap.get(minus+"").weakAdd(node.map.get(i+1));
            if(!node.nodeExists(times+""))
                list.add(new node(times+""));
            node.map.get(i+1).add(node.stringMap.get(times+""));
            node.stringMap.get(times+"").weakAdd(node.map.get(i+1));
        }
        list.add(new node("sink"));
        for(int i = N+1;i<list.size()-1;i++)
        {
            list.get(i).add(list.get(list.size()-1));
        }
        for(int i = 0;i<N;i++)
        {
            list.get(0).add(list.get(i+1));
        }
        adj = new boolean[list.size()][list.size()];
        for(int i = 0;i<list.size();i++)
            for(int j = 0;j<list.get(i).con.size();j++)
                adj[i][list.get(i).con.get(j).id] = true;
        for(node asdf:list)
            asdf.dump();
        int x = 0;
        while(aug())
        {
            x++;
        }
        if(x<N)
        {
            System.out.println("impossible");
        }else{
        loop:
            for(int i = 1;i<=N;i++)
            {
                for(int j = N+1;j<=list.size()-1;j++)
                    if(adj[j][i])
                    {
                        String[] a = list.get(i).tag.split(" ");
                        String b = list.get(j).tag;
                        System.out.println(findAnswer(Long.parseLong(a[0]),Long.parseLong(a[1]),Long.parseLong(b)));
                    }
            }
        }
    }
    public static String findAnswer(long x, long y, long z)
    {
        if(x+y==z)
            return x+" + "+y+" = "+z;
        if(x-+y==z)
            return x+" - "+y+" = "+z;
        if(x*y==z)
            return x+" * "+y+" = "+z;
        return "error";
    }
    
    public static boolean aug(){
        int[] prev = new int[list.size()];
        Arrays.fill(prev,-1);
        boolean[] vis = new boolean[list.size()];
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(0);
        vis[0] = true;
        while(!que.isEmpty())
        {
            if(vis[list.size()-1]){
                int current = list.size()-1;
                while(current!=0)
                {
                    adj[current][prev[current]] = true;
                    adj[prev[current]][current] = false;
                    current = prev[current];
                }
                return true;
            }
            else{
                Integer x = que.poll();
                node n = list.get(x);
                for(node y:n.con)
                {
                    if(adj[x][y.id])
                    {
                        if(!vis[y.id])
                        {
                            prev[y.id] = x;
                            vis[y.id] = true;
                            que.add(y.id);
                        }
                    }
                }
            }
        }
        if(vis[list.size()-1]){
            int current = list.size()-1;
            while(current!=0)
            {
                adj[current][prev[current]] = true;
                adj[prev[current]][current] = false;
                current = prev[current];
            }
            return true;
        }
        return false;
    }
    
}
class node{
    
    static HashMap<Integer, node> map;
    static HashMap<String,node> stringMap;
    ArrayList<node> con;
    ArrayList<node> weak;
    public int id;
    public String tag;
    public node()
    {
        weak = new ArrayList<node>();
        con = new ArrayList<node>();
        if(map==null)
            map = new HashMap<Integer,node>();
        if(stringMap==null)
            stringMap = new HashMap<>();
        this.id = map.size();
        map.put(id,this);
    }
    
    public node(String tag)
    {
        this();
        this.tag = tag;
        stringMap.put(tag,this);
    }
    
    public static boolean nodeExists(int i)
    {
        return map.containsKey(i);
    }
    
    public static boolean nodeExists(String i)
    {
        return stringMap.containsKey(i);
    }
    
    public void weakAdd(node i)
    {
        weak.add(i);
    }
    
    public void dump()
    {
        con.addAll(weak);
    }
    
    public void add(node i)
    {
        con.add(i);
    }
    
    public String toString()
    {
        return tag;
    }
}