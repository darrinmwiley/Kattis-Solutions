import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CityPark {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new CityPark().run();	
	}

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		rect[] rects = new rect[N];
		PriorityQueue<event> xSweep = new PriorityQueue<event>();
		PriorityQueue<event> ySweep = new PriorityQueue<event>();
		for(int i = 0;i<N;i++)
		{
			StringTokenizer st = new StringTokenizer(file.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			xSweep.add(new event(x,i,true));
			xSweep.add(new event(x+w,i,false));
			ySweep.add(new event(y,i,true));
			ySweep.add(new event(y+h,i,false));
			rects[i] = new rect(x,y,w,h);
		}
		HashMap<Integer,TreeSet<Integer>> xPos = new HashMap<Integer,TreeSet<Integer>>();
		while(!xSweep.isEmpty())
		{
			event e = xSweep.poll();
			rect r = rects[e.id];
			if(e.start)
			{
				TreeSet<Integer> botset = xPos.get(r.y);
				TreeSet<Integer> topset = xPos.get(r.y+r.ht);
				if(botset==null)
				{
					botset = new TreeSet<Integer>();
					xPos.put(r.y,botset);
				}
				if(topset==null)
				{
					topset = new TreeSet<Integer>();
					xPos.put(r.y+r.ht,topset);
				}
				if(!botset.isEmpty())
				{
					for(int i:botset)
					{
						rects[i].con(e.id);
						r.con(i);
					}
				}
				if(!topset.isEmpty())
				{
					for(int i:topset)
					{
						rects[i].con(e.id);
						r.con(i);
					}
				}
				botset.add(e.id);
				topset.add(e.id);
			}else{
				TreeSet<Integer> botset = xPos.get(r.y);
				TreeSet<Integer> topset = xPos.get(r.y+r.ht);
				botset.remove(e.id);
				topset.remove(e.id);
			}
		}
		HashMap<Integer,TreeSet<Integer>> yPos = new HashMap<Integer,TreeSet<Integer>>();
		while(!ySweep.isEmpty())
		{
			event e = ySweep.poll();
			rect r = rects[e.id];
			if(e.start)
			{
				TreeSet<Integer> leftset = yPos.get(r.x);
				TreeSet<Integer> rightset = yPos.get(r.x+r.wid);
				if(leftset==null)
				{
					leftset = new TreeSet<Integer>();
					yPos.put(r.x,leftset);
				}
				if(rightset==null)
				{
					rightset = new TreeSet<Integer>();
					yPos.put(r.x+r.wid,rightset);
				}
				if(!leftset.isEmpty())
				{
					for(int i:leftset)
					{
						rects[i].con(e.id);
						r.con(i);
					}
				}
				if(!rightset.isEmpty())
				{
					for(int i:rightset)
					{
						rects[i].con(e.id);
						r.con(i);
					}
				}
				leftset.add(e.id);
				rightset.add(e.id);
			}else{
				TreeSet<Integer> leftset = yPos.get(r.x);
				TreeSet<Integer> rightset = yPos.get(r.x+r.wid);
				leftset.remove(e.id);
				rightset.remove(e.id);
			}
		}
		//for(rect r:rects)
		//System.out.println(r);
		int[] groups = new int[rects.length];
		int g = group(rects,groups);
		long[] ans = new long[g];
		for(int i = 0;i<rects.length;i++)
			ans[groups[i]]+=rects[i].area();
		long best = 0;
		for(int i = 0;i<ans.length;i++)
			best = Math.max(best,ans[i]);
		System.out.println(best);

	}

	public int group(rect[] rects, int[] ints)
	{
		boolean[] vis = new boolean[rects.length];
		int group = -1;
		for(int i = 0;i<rects.length;i++)
		{
			if(!vis[i])
			{
				group++;
				Queue<Integer> que = new LinkedList<Integer>();
				que.add(i);
				while(!que.isEmpty())
				{
					int x = que.poll();
					if(vis[x])
						continue;
					vis[x] = true;
					ints[x] = group;
					rect r = rects[x];
					for(int y:r.con)
						if(!vis[y])
							que.add(y);
				}
			}
		}
		return group+1;
	}

	private class event implements Comparable<event>
	{
		int prec,id;
		boolean start;
		public event(int prec, int id, boolean start)
		{
			this.start = start;
			this.id = id;
			this.prec = prec;
		}
		@Override
		public int compareTo(event o) {
			if(prec==o.prec)
			{
				if(!start&&o.start)
					return 1;
				if(!o.start&&start)
					return -1;
			}
			return prec-o.prec;
		}

	}

	private class rect
	{
		int x,y,wid,ht;
		ArrayList<Integer> con;
		public rect(int a, int b, int c, int d)
		{
			x = a;
			y = b;
			wid = c;
			ht = d;
			con = new ArrayList<Integer>();
		}

		public int area()
		{
			return wid*ht;
		}

		public String toString()
		{
			return x+" "+y+" "+wid+" "+ht+" "+con;
		}

		public void con(int a)
		{
			con.add(a);
		}

		public boolean intersects(rect r)
		{
			if(x+wid==r.x)
			{
				if(y>=r.y&&y<r.y+r.ht)
					return true;
				int y2 = y+ht;
				if(y2>r.y&&y2<=r.y+r.ht)
					return true;
				if(y<=r.y&&y2>=r.y+r.ht)
					return true;
			}
			if(y+ht==r.y)
			{
				if(x>=r.x&&x<r.x+r.wid)
					return true;
				int x2 = x+wid;
				if(x2>r.x&&x2<=r.x+r.wid)
					return true;
				if(x<=r.x&&x2>=r.x+r.wid)
					return true;
			}
			return false;
		}
	}


}
