package page;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AnimalClassification {
    
    
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new AnimalClassification().run();
    }
    
    public void run() throws NumberFormatException, IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(file.readLine());
        char[] A = file.readLine().toCharArray();
        char[] B = file.readLine().toCharArray();
        node tree = tree(A);
        node tree2 = tree(B);
        HashMap<Integer,node> map = new HashMap<Integer,node>();
        tree.explore(map);
        tree2.getMinChild();
        for(int i = 1;i<18;i++) {
            tree.genParent(i);
        }
        Queue<node> que = new LinkedList<node>();
        que.add(tree2);
        int ans = 0;
        while(!que.isEmpty())
        {
            node x = que.poll();
            node n = search(tree,x,map);
            for(node nn:x.children) {
                que.add(nn);
            }
            if(x==null||n==null)
                continue;
            long hash1 = x.getHash();
            long hash2 = n.getHash();
            if(hash1==hash2)
            {
                ans++;
            }
        }
        System.out.println(ans);
    }
    
    public node search(node bigTree, node smallTree,HashMap<Integer,node> map)
    {
        node leaf = map.get(smallTree.getMinChild());
        int R = leaf.depth+1;
        int L = -1;
        int desiredSize = smallTree.getSize();
        while(R-L>1)
        {
            int M = (R+L)/2;
            node p = leaf.getParent(M);
            if(p.getSize()<desiredSize)
                L = M;
            else if(p.getSize()>desiredSize)
                R = M;
            else if(p.getSize()==desiredSize)
                return p;
        }
        return null;
    }
    
    public node tree(char[] A)
    {
        node product = null;
        Stack<node> stack = new Stack<node>();
        int numNodes = 0;
        int current = 0;
        for(int i = 0;i<A.length;i++)
        {
            if(A[i]=='(')
            {
                node n = new node(numNodes++);
                if(!stack.isEmpty())
                    stack.peek().addChild(n);
                stack.add(n);
                current = 0;
            }else if(A[i]==')') {
                if(current!=0) {
                node n = new node(numNodes++);
                n.setValue(current);
                if(!stack.isEmpty())
                    stack.peek().addChild(n);
                current = 0;
                }
                product = stack.pop();
            }else if(A[i]==','){
                if(current!=0)
                {
                    node n = new node(numNodes++);
                    n.setValue(current);
                    stack.peek().addChild(n);
                    current = 0;
                }
                
            }else {
                current*=10;
                current+=(char)(A[i]-48);
            }
        }
        return product;
    }
    
    class node{
        
        ArrayList<node> parents;
        int minChild = -1;
        int sz = -1;
        int mod = 1000000007;
        int id;
        int value;
        int depth = 0;
        // hash should be sum + multiple mod M
        long multipleHash;
        long sumHash;
        long hash;

        ArrayList<node> children;
        public node(int id)
        {
            multipleHash = -1;
            sumHash = -1;
            parents = new ArrayList<node>();
            children = new ArrayList<node>();
            this.id = id;
        }
        
        public node getParent(int x)
        {
            String bin = Integer.toBinaryString(x);
            int y = 0;
            node current = this;
            for(int i = bin.length()-1;i>-1;i--)
            {
                if(current==null)
                    return null;
                if(bin.charAt(i)=='1')
                    current = current.parents.get(y);
                y++;
            }
            return current;
        }
        
        public int getSize()
        {
            if(sz != -1)
                return sz;
            int ret = 0;
            for(node n:children)
                ret+=n.getSize();
            sz = ret+1;
            return sz;
        }
        
        public void genParent(int x)
        {
            if(parents.size()<x)
                parents.add(null);//sketch
            if(parents.get(x-1)==null)
                parents.add(null);
            else
                parents.add(parents.get(x-1).parents.get(x-1));
            for(node n:children)
                n.genParent(x);
        }
        
        public void setValue(int n)
        {
            value = n;
        }
        
        public int getMinChild()
        {
            if(value!=0)
                return value;
            if(minChild==-1)
            {
                int ret = Integer.MAX_VALUE;
                for(node n:children)
                    ret = Math.min(ret,n.getMinChild());
                minChild = ret;
            }
            return minChild;
        }
        
        public long getMultHash()
        {
            if(multipleHash!=-1)
                return multipleHash;
            if(value!=0)
                return value;
            long hash = 1;
            for(node child:children)
            {
                hash = hash*child.getMultHash()%mod;
            }
            multipleHash = hash;
            return hash;
        }
        
        public long getSumHash()
        {
            if(sumHash!=-1)
                return sumHash;
            if(value!=0)
                return value;
            long hash = 1;
            for(node child:children)
            {
                hash = hash+child.getSumHash()%mod;
            }
            sumHash = hash;
            return hash;
        }
        
        public long getHash() {
            if(hash==0)
                hash = (getSumHash()+getMultHash())%mod;
            return hash;
        }
        
        public void addChild(node n)
        {
            children.add(n);
            n.depth = depth+1;
            n.parents.add(this);
        }
        
        public String toString()
        {
            if(value!=0)
                return value+"";
            return "@"+id+"@"+" "+children;
        }
        
        public void explore(HashMap<Integer,node> map)
        {
            if(value!=0)
                map.put(value,this);
            for(node n:children)
                n.explore(map);
        }
        
    }
    
}