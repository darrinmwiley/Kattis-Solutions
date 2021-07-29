import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;

public class rectilinear {
	
	public static void main(String[] args)
	{
		new rectilinear().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		Comparator<pt> xval = new Comparator<pt>(){
			@Override
			public int compare(pt o1, pt o2) {
				if(o1.x==o2.x)
					return o2.y-o1.y;
				return o1.x-o2.x;
			}
		};
		Comparator<pt> yval = new Comparator<pt>(){
			public int compare(pt o1, pt o2) {
				if(o1.y==o2.y)
					return o2.x-o1.x;
				return o1.y-o2.y;
			}
		};
		int zz = file.nextInt();
	loop:
		for(int z = 0;z<zz;z++)
		{
			int N = file.nextInt();
			pt[] pts = new pt[N];
			for(int i = 0;i<N;i++)
				pts[i] = new pt(file.nextInt(),file.nextInt(),i);
			if(N%2==1)
			{
				System.out.println(-1);
				continue;
			}
			pt[] orig = pts.clone();
			Arrays.sort(pts,xval);
			int ans = 0;
			for(int i = 0;i<pts.length;i+=2)
			{
				pt a = pts[i];
				pt b = pts[i+1];
				if(a.x!=b.x){
					System.out.println(-1);
					continue loop;
				}
				a.top = true;
				b.bottom = true;
				a.con(b.id);
				b.con(a.id);
				ans+=a.y-b.y;
			}
			Arrays.sort(pts,yval);
			for(int i = 0;i<pts.length;i+=2)
			{
				pt a = pts[i];
				pt b = pts[i+1];
				if(a.y!=b.y){
					System.out.println(-1);
					continue loop;
				}
				a.right = true;
				b.left = true;
				a.con(b.id);
				b.con(a.id);
				ans+=a.x-b.x;
			}
			if(!connected(orig)||!nonIntersecting(pts,orig))
				System.out.println(-1);
			else
				System.out.println(ans);
		}	
	}
	
	public boolean nonIntersecting(pt[] pts,pt[] orig)
	{
		TreeSet<Integer> active = new TreeSet<Integer>();
		for(int i = 0;i<pts.length;i++)
		{
			pt p = pts[i];
			if(p.bottom)
				active.add(p.x);
			else
				active.remove(p.x);
			if(p.left)
			{
				Integer higher = active.higher(p.x);
				if(higher!=null&&higher<p.getHorz(orig).x)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean connected(pt[] pts)
	{
		boolean[] vis = new boolean[pts.length];
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(0);
		while(!que.isEmpty())
		{
			int i = que.poll();
			if(!vis[i])
			{
				vis[i] = true;
				for(int x:pts[i].con)
				{
					if(!vis[x])
						que.add(x);
				}
					
			}
		}
		for(boolean x:vis)
			if(!x)
				return false;
		return true;
	}
	
	private class pt{
		int x,y,id,z;
		boolean left, right, top, bottom;
		int[] con;
		public pt(int a, int b, int c)
		{
			x = a;
			y = b;
			id = c;
			z = 0;
			con = new int[2];
		}
		
		public void con(int a)
		{
			con[z++] = a;
		}
		public String toString()
		{
			return ("("+x+","+y+","+Arrays.toString(con)+")");
		}
		
		public pt getHorz(pt[] pts)
		{
			if(pts[con[0]].y==y)
				return pts[con[0]];
			return pts[con[1]];
		}
		
		public pt getVert(pt[] pts)
		{
			if(pts[con[0]].x==x)
				return pts[con[0]];
			return pts[con[1]];
		}
	}
}
