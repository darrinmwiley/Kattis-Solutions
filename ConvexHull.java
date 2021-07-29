import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ConvexHull {
	
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new ConvexHull().run();  
    }
    
    public void run() throws NumberFormatException, IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(file.readLine());
        while(N!=0)
        {
        	pt bottom = null;
        	ArrayList<pt> pts = new ArrayList<pt>();
        	for(int i = 0;i<N;i++)
        	{
        		StringTokenizer st = new StringTokenizer(file.readLine());
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		pt p = new pt(x,y);
        		if(bottom!=null&&p.x==bottom.x&&p.y==bottom.y)
        			continue;
        		if(bottom==null)
        			bottom = p;
        		else if(y<bottom.y||(y==bottom.y&&x<bottom.x))
        		{
        			pts.add(bottom);
        			bottom = p;
        		}else
        			pts.add(p);
        	}
        	final pt bot = bottom;
        	Comparator<pt> comp = new Comparator<pt>(){
				@Override
				public int compare(pt p1, pt p2) {
					double t1 = Math.atan2(p1.y-bot.y, p1.x-bot.x);
					double t2 = Math.atan2(p2.y-bot.y,p2.x-bot.x);
					return Double.compare(t1,t2);
				}
        	};
        	Collections.sort(pts,comp);
        	ArrayList<pt> list = new ArrayList<pt>();
        	if(!pts.isEmpty())
        		list.add(pts.get(0));
        	for(int i = 1;i<pts.size();i++)
        	{
        		if(comp.compare(pts.get(i),list.get(list.size()-1))==0)
        		{
        			int d1 = d2(bottom,pts.get(i));
        			int d2 = d2(bottom,list.get(list.size()-1));
        			if(d2>d1)
        				continue;
        			list.set(list.size()-1,pts.get(i));
        		}else
        			list.add(pts.get(i));
        	}
        	ArrayList<pt> st = new ArrayList<pt>();
            st.add(bottom);
            if(!list.isEmpty())
            	st.add(list.get(0));
            if(list.size()>1)
            	st.add(list.get(1));
            for(int i = 2;i<list.size();i++)
            {
            	pt a = st.get(st.size()-2);
            	pt b = st.get(st.size()-1);
            	pt c = list.get(i);
            	while(!ccw(a,b,c))
            	{
            		st.remove(st.size()-1);
            		a = st.get(st.size()-2);
                	b = st.get(st.size()-1);
            	}
            	st.add(c);
            }
            System.out.println(st.size());
            for(pt p:st)
            	System.out.println(p);
        	N = Integer.parseInt(file.readLine());
        }
    }
    
    public static boolean ccw(pt a, pt b, pt c)
    {
        return (c.y-a.y)*(b.x-a.x)>(b.y-a.y)*(c.x-a.x);
    }
    
    public int d2(pt a, pt b)
    {
    	return (a.x-b.x)*(a.x-b.x)+(a.y-b.y)*(a.y-b.y);
    }
    
    private class pt{
    	
    	int x,y;
    	
    	public pt(int a, int b)
    	{
    		x = a;
    		y = b;
    	}
    	
    	public String toString()
    	{
    		return x+" "+y;
    	}
    	
    }
}