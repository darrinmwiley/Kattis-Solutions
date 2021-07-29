import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CairoCorridor {
    
    int r,c;
    node[][][] nodes;
    int time;
    triplet leftT = new triplet(-10,-10,-10);
    triplet rightT = new triplet(-11,-10,-10);
    triplet topT = new triplet(-12,-10,-10);
    triplet botT = new triplet(-13,-10,-10);
    node left;
    node right;
    node top;
    node bot;
    
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new CairoCorridor().run();  
    }
    
    public void run() throws NumberFormatException, IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        int zz = Integer.parseInt(file.readLine());
    loop:
        for(int z = 0;z<zz;z++)
        {
            left = new node(leftT,true);
            right = new node(rightT,true);
            top = new node(topT,true);
            bot = new node(botT,true);
            StringTokenizer st = new StringTokenizer(file.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            nodes = new node[r][c][2];
            for(int i = 0;i<r;i++)
            {
                char[] line = file.readLine().toCharArray();
                for(int j = 0;j<c;j++)
                {
                    nodes[i][j][0] = new node(new triplet(i,j,0),line[j*2]=='0');
                    nodes[i][j][1] = new node(new triplet(i,j,1),line[j*2+1]=='0');
                }
            }
            for(int i = 0;i<r;i++)
            {
                for(int j = 0;j<c;j++)
                {
                    boolean horz = (i+j)%2==0;
                    if(horz)
                    {
                        nodes[i][j][0].con(new triplet(i,j-1,0));
                        nodes[i][j][0].con(new triplet(i,j-1,1));
                        nodes[i][j][0].con(new triplet(i-1,j,1));
                        nodes[i][j][0].con(new triplet(i+1,j,0));
                        nodes[i][j][0].con(new triplet(i,j,1));
                        
                        nodes[i][j][1].con(new triplet(i-1,j,1));
                        nodes[i][j][1].con(new triplet(i,j+1,0));
                        nodes[i][j][1].con(new triplet(i,j+1,1));
                        nodes[i][j][1].con(new triplet(i+1,j,0));
                        nodes[i][j][1].con(new triplet(i,j,0));
                    }else{
                        nodes[i][j][0].con(new triplet(i-1,j,0));
                        nodes[i][j][0].con(new triplet(i-1,j,1));
                        nodes[i][j][0].con(new triplet(i,j-1,1));
                        nodes[i][j][0].con(new triplet(i,j+1,0));
                        nodes[i][j][0].con(new triplet(i,j,1));
                        
                        nodes[i][j][1].con(new triplet(i,j-1,1));
                        nodes[i][j][1].con(new triplet(i,j+1,0));
                        nodes[i][j][1].con(new triplet(i+1,j,0));
                        nodes[i][j][1].con(new triplet(i+1,j,1));
                        nodes[i][j][1].con(new triplet(i,j,0));
                    }
                    //System.out.println(nodes[i][j][0]);
                    //System.out.println(nodes[i][j][1]);
                }
            }
            int[][][] groups = dfs();
            /*for(int x = 0;x<8;x++){
                System.out.print("group "+x+": ");
                for(int i = 0;i<groups.length;i++)
                    for(int j = 0;j<groups[i].length;j++)
                        for(int k = 0;k<2;k++)
                            if(groups[i][j][k]==x)
                                System.out.print(nodes[i][j][k].id+" ");
                System.out.println();
            }*/
            int corridor = findCorridor(groups);
            if(corridor==-1)
            {
                System.out.println("NO MINIMAL CORRIDOR");
                continue loop;
            }
            int size = corridorSize(groups,corridor);
            if(size==-1)
            	System.out.println("NO MINIMAL CORRIDOR");
            else
            	System.out.println(size);
        }
    }
    
    public int corridorSize(int[][][] groups, int corridor){
        ArrayList<node> list = new ArrayList<node>();
        if(list.size()==1)
        	return 1;
        for(int i = 0;i<r;i++)
        	for(int j = 0;j<c;j++)
        		for(int k = 0;k<2;k++)
        			if(groups[i][j][k]==corridor)
        				list.add(nodes[i][j][k]);
        int one = 0;
    	int three = 0;
    	for(node n:list)
    		if(n.degre()==1)
    			one++;
    		else if(n.degre()>=3)
    			three++;
    	if(one>4||three>6)
    		return -1;
    	//System.out.println(one+" "+three);
        for(int i = 0;i<list.size();i++)
        {
        	if(!shouldCheck(list.get(i)))
        		continue;
        	//System.out.println("checking");
        	list.get(i).clear = false;
        	triplet t = list.get(0).id;
        	if(i==0)
        	{
        		t = list.get(1).id;
        	}
        	int ii = t.a;
        	int j = t.b;
        	int k = t.c;
        	int[][][] newGroups = new int[r][c][2];
        	for(int[][] a:newGroups)
        		for(int[] b:a)
        			Arrays.fill(b,-1);
        	singleDFS(newGroups,0,ii,j,k);
        	int newCorridor = findCorridor(newGroups);
        	if(newCorridor!=-1)
        		return -1;
        	list.get(i).clear = true;
        }
        return list.size();
    }
    
    public boolean shouldCheck(node n)
    {
    	if(n.degre()==1||n.degre()>=3)
    		return true;
    	for(triplet t:n.con)
    		if(val(t)&&get(t).clear&&get(t).degre()>=3)
    			return true;
    	return false;
    }
    
    public boolean left(int i, int j, int k)
    {
        boolean horz = (i+j)%2==0;
        return j==0&&(!horz||k==0);
    }
    
    public boolean right(int i, int j, int k)
    {
        boolean horz = (i+j)%2==0;
        return j==c-1&&(!horz||k==1);
    }
    
    public boolean top(int i, int j, int k)
    {
        boolean horz = (i+j)%2==0;
        return i==0&&(horz||k==0);
    }
    
    public boolean bot(int i, int j, int k)
    {
        boolean horz = (i+j)%2==0;
        return i==r-1&&(horz||k==1);
    }
    
    public int findCorridor(int[][][] groups)
    {
        TreeSet<Integer> left = new TreeSet<Integer>();
        TreeSet<Integer> right = new TreeSet<Integer>();
        TreeSet<Integer> top = new TreeSet<Integer>();
        TreeSet<Integer> bot = new TreeSet<Integer>();
        for(int i = 0;i<r;i++)
            for(int j = 0;j<c;j++)
                for(int k = 0;k<2;k++)
                {
                    if(groups[i][j][k]==-1)
                        continue;
                    if(left(i,j,k))
                        left.add(groups[i][j][k]);
                    if(right(i,j,k))
                        right.add(groups[i][j][k]);
                    if(top(i,j,k))
                        top.add(groups[i][j][k]);
                    if(bot(i,j,k))
                        bot.add(groups[i][j][k]);
                }
        /*System.out.println(left);
        System.out.println(right);
        System.out.println(top);
        System.out.println(bot);*/
        left.retainAll(right);
        left.retainAll(top);
        left.retainAll(bot);
        if(left.isEmpty())
            return -1;
        return left.first();
    }
    
    public boolean val(triplet t)
    {
        if(t==leftT||t==rightT||t==topT||t==botT)
            return true;
        return Math.min(Math.min(t.a,t.b),t.c)>=0&&t.a<r&&t.b<c&&t.c<2;
    }
    
    public void singleDFS(int[][][] group, int groups, int i, int j, int k)
    {
    	triplet start = new triplet(i,j,k);
        Queue<triplet> que = new LinkedList<triplet>();
        que.add(start);
        while(!que.isEmpty())
        {
            triplet t = que.poll();
            if(group[t.a][t.b][t.c]!=-1)
                continue;
            group[t.a][t.b][t.c] = groups;
            node n = get(t);
            for(triplet x:n.con)
            {
                if(val(x)&&group[x.a][x.b][x.c]==-1&&get(x).clear)
                {
                    que.add(x);
                }
            }
        }
    }
    
    public int[][][] dfs()
    {
        int[][][] group = new int[r][c][2];
        int groups = -1;
        for(int i = 0;i<r;i++)
            for(int j = 0;j<c;j++)
                Arrays.fill(group[i][j],-1);
        for(int i = 0;i<r;i++)
        {
            for(int j = 0;j<c;j++)
            {
                for(int k = 0;k<2;k++)
                {
                    if(nodes[i][j][k].clear&&group[i][j][k]==-1)
                    {
                        groups++;
                        singleDFS(group,groups,i,j,k);
                    }
                }
            }
        }
        return group;
    }
    
    public node get(triplet t)
    {
        if(t.equals(leftT))
            return left;
        if(t.equals(rightT))
            return right;
        if(t.equals(topT))
            return top;
        if(t.equals(botT))
            return bot;
        return nodes[t.a][t.b][t.c];
    }
    
    private class triplet
    {
        int a,b,c;
        public triplet(int a, int b, int c)
        {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public String toString()
        {
            String ans = "";
            if(c==0)
                ans+="a";
            else
                ans+="b";
            return ans+a+b;
        }
        
        public int hashCode()
        {
            return a<<20+b<<10+c;
        }
        
        public boolean equals(triplet t)
        {
            return a==t.a&&b==t.b&&c==t.c;
        }
    }
    
    private class node{
        
        triplet id;
        boolean clear;
        ArrayList<triplet> con;
        int deg;
        
        public int degre()
        {
        	return deg;
        }
        
        public node(triplet id, boolean clear)
        {
            this.clear = clear;
            this.id = id;
            con = new ArrayList<triplet>();
        }
        
        public void con(triplet N)
        {
        	if(N!=leftT&&N!=rightT&&N!=topT&&N!=botT&&val(N)&&get(N).clear)
        		deg++;
            con.add(N);
        }
        
        public String toString()
        {
            return id+" "+con;
        }
    }
}