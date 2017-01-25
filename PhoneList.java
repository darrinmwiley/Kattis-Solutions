import java.util.ArrayList;
import java.util.Scanner;


public class PhoneList {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
    loop:
        for(int i = 0;i<N;i++)
        {
            int M = file.nextInt();
            node root = new node();
            boolean stop = false;
            for(int j = 0;j<M;j++)
            {
                String s = file.next();
                if(root.contains(s))
                    stop = true;
                root.add(s);
            }
            System.out.println(stop?"NO":"YES");
        }
        
    }
}
class node{
    node[] nodes;
    boolean ending;
    public node()
    {
        nodes = new node[10];
    }
    
    public void add(String s)
    {
        if(s.isEmpty())
        {
            ending  = true;
            return;
        }
        int d = s.charAt(0)-48;
        if(nodes[d]==null)
            nodes[d] = new node();
        nodes[d].add(s.substring(1));   
    }
    
    public boolean contains(String s)
    {
        if(s.isEmpty()||ending)
            return true;
        int d = s.charAt(0)-48;
        if(nodes[d]==null)
            return false;
        return nodes[d].contains(s.substring(1));
    }
    
}