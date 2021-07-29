package page;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class oil {

    long sweepTime;
    long sortTime;
    long inputTime;
    long totalTime;
    long tanTime;
    
    double[][] atan;
	
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new oil().run();
    }
    
    public void run() throws NumberFormatException, IOException
    {
    	long start = System.currentTimeMillis();
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(file.readLine());
        line[] lines = new line[N];
        StringTokenizer st;
        for(int i = 0;i<N;i++)
        {
        	st = new StringTokenizer(file.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	lines[i] = new line(a,b,c);
        }
        pt[] pts = new pt[N*2];
        for(int i = 0;i<lines.length;i++)
        {
        	pts[i*2] = lines[i].p1;
        	pts[i*2].id = i*2;
        	pts[i*2+1] = lines[i].p2;
        	pts[i*2+1].id = i*2+1;
        }
        atan = new double[pts.length][pts.length];
        for(int i = 0;i<pts.length;i++)
        	for(int j = 0;j<pts.length;j++)
        	{
        		if(i!=j)
        		{
        			atan[i][j] = (Math.atan2((pts[j].y-pts[i].y), (pts[j].x-pts[i].x))+Math.PI)%Math.PI;
        		}
        	}
        inputTime = System.currentTimeMillis()-start;
        int best = 0;
        for(int i = 0;i<lines.length;i++)
        {
        	best = Math.max(sweep(i,false,lines),best);
        }
        System.out.println(best);
        totalTime = System.currentTimeMillis()-start;
        //System.out.println(totalTime+" "+inputTime+" "+sweepTime+" "+sortTime+" "+tanTime);
        
    }   
    
    public int sweep(int index, boolean x1, line[] lines)
    {
    		long start = System.currentTimeMillis();
            boolean[] covered = new boolean[lines.length];
            ArrayList<pt> pts = new ArrayList<pt>();
            for(int i = 0;i<lines.length;i++)
            {
                if(i!=index)
                {
                    pt p1 = lines[i].p1;
                    pt p2 = lines[i].p2;
                    if(p1.y!=lines[index].y)
                    {
                        pts.add(p1);
                        pts.add(p2);
                        if(lines[index].y<p1.y)
                        {
                        	p1.open = false;
                        	p2.open = true;
                        }else {
                        	p1.open = true;
                        	p2.open = false;
                        }
                    }
                }
            }
            int x = lines[index].x1;
            int y = lines[index].y;
            Comparator<pt> comp = new Comparator<pt>() {

                @Override
                public int compare(pt o1, pt o2) {
                	long tanstart = System.currentTimeMillis();
                    double atan1 = atan[o1.id][lines[index].p1.id];
                    double atan2 = atan[o2.id][lines[index].p1.id];
                    tanTime+=System.currentTimeMillis()-tanstart;
                    if(atan1 == atan2)
                    {
                    	if(o1.open&&!o2.open)
                    		return -1;
                    	if(!o1.open&&o2.open)
                    		return 1;
                    }
                    return Double.compare(atan1, atan2);
                }
                
            };
            long presort = System.currentTimeMillis();
            Collections.sort(pts,comp);
            sortTime += System.currentTimeMillis()-presort;
            int current = 0;
            int best = 0;
            for(pt p:pts)
            {
                if(p.open)
                {
                	current+=p.value;
                	best = Math.max(current,best);
                }else {
                	current-=p.value;
                }
            }
            sweepTime+=System.currentTimeMillis()-start;
            return best+lines[index].value;
            
    }
    
    private class pt{
         int x,y;
         int value;
         boolean open;
         int id;
         public pt(int x, int y, int value)
         {
             this.x = x;
             this.y = y;
             this.value = value;
         }
         public String toString()
         {
             return "("+x+","+y+" "+open+")";
         }
    }
    
    private class line{
        
            int x1,x2,y;
            
            pt p1, p2;
            
            int value;
            
            public line(int xa, int xb, int y)
            {
                this.y = y;
                x1 = Math.min(xa,xb);
                x2 = Math.max(xa,xb);
                value = x2-x1;
                p1 = new pt(x1,y,x2-x1);
                p2 = new pt(x2,y,x2-x1);
            }
            
            public String toString()
            {
                return "("+x1+" "+x2+" "+y+")";
            }
        
    }
}