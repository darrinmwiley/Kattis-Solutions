import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class MusicCollection{
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z= 0;z<zz;z++)
        {
            System.out.println("Case #"+(z+1)+":");
            int N = file.nextInt();
            node[] roots = new node[N];
            for(int i = 0;i<roots.length;i++)
                roots[i] = new node();
            file.nextLine();
            for(int i = 0;i<N;i++)
            {
                String s = file.nextLine().toUpperCase();
                while(!s.isEmpty())
                {
                    roots[i].add(s);
                    s = s.substring(1);
                }
            }
        loop:
            for(int i = 0;i<N;i++)
            {
                Queue<String> que = new LinkedList<String>();
                que.add("");
                while(!que.isEmpty())
                {
                    String str = que.poll();
                    boolean flag = true;
                    for(node n:roots)
                        if(n!=roots[i]&&n.contains(str))
                            flag = false;
                    if(flag){
                        System.out.println("\""+str+"\"");
                        continue loop;
                    }
                    for(node n:roots[i].get(str).children())
                    {
                        que.add(str+n.val);
                    }
                }
                System.out.println(":(");
            }
        }
    }
    
    public static void main(String[] args)
    {
        new MusicCollection().run();
    }
    
}
class node{
    
    public static String order = " -ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char val;
    private node[] nodes;
    public node(){
        val = '.';
        nodes = new node[28];
    }
    
    public node get(String s)
    {
        if(s.isEmpty())
            return this;
        char first = s.charAt(0);
        int spot = order.indexOf(first);
        if(nodes[spot]==null)
            return null;
        return nodes[spot].get(s.substring(1));
    }
    
    public ArrayList<node> children()
    {
        ArrayList<node> ret = new ArrayList<node>();
        for(node n:nodes)
        {
            if(n!=null)
                ret.add(n);
        }
        return ret;
    }
    
    public node(char v)
    {
        this();
        val = v;
    }
    
    public void add(String s)
    {
        if(s.isEmpty())
            return;
        char first = s.charAt(0);
        int spot = order.indexOf(first);
        if(nodes[spot]==null)
            nodes[spot] = new node(first);
        nodes[spot].add(s.substring(1));
    }
    
    public boolean contains(String s){
        if(s.isEmpty())
            return true;
        char first = s.charAt(0);
        int spot = order.indexOf(first);
        if(nodes[spot]==null)
            return false;
        return nodes[spot].contains(s.substring(1));
    }
}