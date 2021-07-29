

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Azulejos {
 
    
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new Azulejos().run();
    }
    
    //middlebegin: start + num left in tail
    //if(middlebegin in range)'
    		//you need to add a new tail
    
    //to find if tail exists: top chunk ends after end
    	//to see if you need to add that tail: tail begin chunk starts after middleBegin;
    
    public void run() throws NumberFormatException, IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(file.readLine());
        int[] p1 = new int[N];
        int[] h1 = new int[N];
        int[] p2 = new int[N];
        int[] h2 = new int[N];
        StringTokenizer st;
        for(int[] in: new int[][]{p1, h1, p2, h2}) {
            st = new StringTokenizer(file.readLine());
            for(int i = 0;i<N;i++)
            {
                in[i] = Integer.parseInt(st.nextToken());
            }
        }
        tile[] bot = new tile[N];//back
        tile[] top = new tile[N];//front
        //bot must exceed top
        
        for(int i = 0;i<N;i++)
        {
            bot[i] = new tile(p1[i], h1[i], i);
            top[i] = new tile(p2[i], h2[i], i);
        }
        Arrays.sort(top);
        Arrays.sort(bot);
        
        //COUNT CHUNKS
        
        int topChunks = 1;
        int botChunks = 1;
        for(int i = 1;i<N;i++)
        {
            if(top[i].price != top[i-1].price)
                topChunks++;
            if(bot[i].price != bot[i-1].price)
                botChunks++;
        }
        
        //GET CHUNK BEGIN/END/ALLOCATIONS
        int[] topChunk = new int[N];
        int[] botChunk = new int[N];
        int[] topChunkBegin = new int[topChunks];
        int[] topChunkEnd = new int[topChunks];
        int[] botChunkBegin = new int[botChunks];
        int[] botChunkEnd = new int[botChunks];
        int tc = 0;
        int bc = 0;
        for(int i = 1;i<N;i++)
        {
            topChunk[i-1] = tc;
            botChunk[i-1] = bc;
            if(top[i].price != top[i-1].price)
            {
                topChunkEnd[tc++] = i-1;
                topChunkBegin[tc] = i;
            }
            
            if(bot[i].price != bot[i-1].price)
            {
                botChunkEnd[bc++] = i-1;
                botChunkBegin[bc] = i;
            }
        }
        botChunkEnd[botChunks - 1] = topChunkEnd[topChunks - 1] = N - 1;
        topChunk[N-1] = topChunks - 1;
        botChunk[N-1] = botChunks - 1;
        
        for(int j = 0;j<N;j++)
        {
            top[j].chunk = topChunk[j];
            bot[j].chunk = botChunk[j];
        }
        
        for(int i = 0;i<N;i++)
        {
            top[i].min = topChunkBegin[topChunk[i]];
            bot[i].min = botChunkBegin[botChunk[i]];
            top[i].max = topChunkEnd[topChunk[i]];
            bot[i].max = botChunkEnd[botChunk[i]];
        }
        
        //minimum index for top tile with id i
        int[] min = new int[N];
        for(int i = 0;i<min.length;i++)
        {
            min[top[i].id] = topChunkBegin[topChunk[i]];
        }
        
        Comparator<tile> onlyHeight = new Comparator<tile>() {
            
            public int compare(tile a, tile b)
            {
                int comp = Integer.compare(a.height, b.height);
                return comp == 0? Integer.compare(a.id, b.id): comp;
            }
            
        };
        
        TreeSet<tile> head = new TreeSet<tile>(onlyHeight);
        TreeSet<tile> tail = new TreeSet<tile>(onlyHeight);
        PriorityQueue<tile> que = new PriorityQueue<tile>(); 
        
        ArrayList<pair> pairs = new ArrayList<>();
        
        int tailBegin = -1;
        for(int i = 0;i<botChunks;i++)
        {
            int start = botChunkBegin[i];
            int end = botChunkEnd[i];
            for(int j = start;j<=end;j++)
            {
                que.add(bot[j]);
            }
            
          //middlebegin: start + num left in tail
            //if(middlebegin in range)'
            		//you need to add a new tail
            
            //to find if tail exists: top chunk ends after end
            	//to see if you need to add that tail: tail begin chunk starts after middleBegin;
            int headStart = end+1;
            int headEnd = end;
            
			int tailLeft = tail.size();
			boolean headTransfer = false;
			if(tailLeft + start <= end)
			{
				headTransfer = true;
				head.addAll(tail);
				headStart = start+tail.size();
				tail.clear();
			}
			int tailEnd = -1;
			   
			if(headTransfer)
			{
				tailEnd = topChunkEnd[topChunk[end]];
				if(tailEnd > end)
				{
					headEnd = topChunkBegin[topChunk[end]] - 1;
				}	   
			}
			
			for(int j = headStart;j<=headEnd;j++)
			{
				head.add(top[j]);
			}
			
			for(int j = headEnd+1;j<=tailEnd;j++)
			{
				tail.add(top[j]);
			}
			            
            
                              
            int tailptr = tailBegin;
            while(!que.isEmpty())
            {
                tile current = que.poll();
                int currSave = current.id;
                current.id = -1;
                tile headPeek = head.lower(current);
                tile tailPeek = tail.lower(current);
                current.id = currSave;
                if(headPeek == null && tailPeek == null)
                {
                    System.out.println("impossible");
                    return;
                }
                if(headPeek != null)
                {
                    head.remove(headPeek);
                    pairs.add(new pair(current, headPeek));
                    current.paired = headPeek.paired = true;
                }else
                {
                    if(tailptr > end)
                    {
                        System.out.println("impossible");
                        return;
                    }else {
                        tail.remove(tailPeek);
                        pairs.add(new pair(current, tailPeek));
                        current.paired = tailPeek.paired = true;
                        tailptr++;
                    }
                }
            }
        }
        
        Comparator<pair> start = new Comparator<pair>() {
            public int compare(pair a, pair b)
            {
                return Integer.compare(a.min,b.min);
            }
        };
        
        Collections.sort(pairs);
        LinkedList<pair> list = new LinkedList<pair>();
        list.addAll(pairs);
        
        PriorityQueue<pair> pq = new PriorityQueue<pair>();
        
        int[] topAssignment = new int[N];
        int[] botAssignment = new int[N];
        
        for(int i = 0;i<N;i++)
        {
            while(!list.isEmpty() && list.peekFirst().min >= i)
            {
                pq.add(list.removeFirst());
            }
            pair assign = pq.poll();
            topAssignment[i] = assign.top+1;
            botAssignment[i] = assign.bottom+1;
        }
        
        StringBuilder b = new StringBuilder();
        StringBuilder t = new StringBuilder();
        for(int i = 0;i<N;i++)
        {
            b.append(botAssignment[i]+" ");
            t.append(topAssignment[i]+" ");
        }
        PrintWriter pout = new PrintWriter(System.out);
        pout.println(b.toString());
        pout.println(t.toString());
        pout.flush();
        pout.close();
    }
    
/*
4
3 2 1 2
2 3 4 3
2 1 2 1
2 2 1 3

2
1 2
2 3
2 8
2 1

5
1 3 3 2 2
11 11 9 5 14
4 4 5 4 4
4 9 6 5 9

5
5 6 6 7 4
9 11 11 7 13
5 4 2 3 4
3 6 9 5 10

5
5 2 4 5 3
8 7 15 13 4
2 2 2 2 2
10 3 2 8 2

3
4 5 5
7 7 7
4 3 3
5 3 2
*/
    
    private class pair implements Comparable<pair>{
        
        int min, max;
        int top, bottom;
        tile TT, BB;
        
        public pair(tile a, tile b)
        {
            TT = a;
            BB = b;
            top = b.id;
            bottom = a.id;
            min = Math.max(a.min, b.min);
            max = Math.min(a.max, b.max);
            if(min > max)
            {
                System.out.println("impossible");
                System.exit(0);
            }
        }
        
        public int compareTo(pair p)
        {
            return Integer.compare(max, p.max);
        }
        
        public String toString()
        {
            return TT+" "+BB+" ["+min+","+max+"]";
        }
        
    }
    
    private class tile implements Comparable<tile>{
        
        int price;
        int height;
        int id;
        boolean paired;
        int chunk;
        int min;
        int max;
        
        public tile(int price, int height, int id)
        {
            this.price = price;
            this.height = height;
            this.id = id;
        }
        
        public int compareTo(tile t)
        {
            int comp = Integer.compare(price, t.price);
            if(comp == 0) {
                int comp2 = Integer.compare(height, t.height);
                if(comp2 == 0)
                {
                    return Integer.compare(id, t.id);
                }
                return comp2;
            }
            return comp;
        }
        
        public String toString()
        {
            return "("+price+" "+height+")";
        }
        
        
        
    }
}