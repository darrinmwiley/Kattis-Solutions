import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class carvet {
	
	int[][] d = new int[][]{{1,0,-1,0},{0,1,0,-1}};
	int N,M;
	public static void main(String[] args)
	{
		new carvet().run();
	}

	public void run()//bfs by shortest connection number
	{
		Scanner file = new Scanner(System.in);
		N = file.nextInt();
		M = file.nextInt();
		HashMap<Point,Point> map = new HashMap<Point, Point>();
		HashMap<Integer,Point> map2 = new HashMap<Integer,Point>();
		HashMap<Point,Integer> num = new HashMap<Point,Integer>();
		Point start = null;
		for(int i = 0;i<N;i++)
		{
			for(int j = 0;j<M;j++)
			{
				int x = file.nextInt();
				num.put(new Point(i,j), x);
				if(x == -2)
					continue;
				if(x == -1)
					start = new Point(i,j);
				if(map2.containsKey(x))
				{
					Point a = map2.get(x);
					Point b = new Point(i,j);
					map.put(a, b);
					map.put(b, a);
				}else{
					map2.put(x, new Point(i,j));
				}
			}
		}
		HashSet<Point> vis = new HashSet<Point>();
		vis.add(start);
		HashMap<Point,Point> pred = new HashMap<Point,Point>();
		Queue<Point> que = new LinkedList<Point>();
		que.add(start);
		while(!que.isEmpty())
		{
			Point curr = que.poll();
			ArrayList<next> next = new ArrayList<next>();
			for(int i = 0;i<4;i++)
			{
				Point p = new Point(curr.x+d[0][i], curr.y+d[1][i]);
				if(map.containsKey(p))
				{
					Point p2 = map.get(p);
					if(!vis.contains(p2) && canSlide(curr,p,p2))
					{
						next.add(new next(num.get(p2),p2));
						vis.add(p2);
						pred.put(p2, curr);
					}
				}
			}
			Collections.sort(next);
			for(next n:next)
			{
				que.add(n.p);
			}
		}
		Point current = new Point(file.nextInt() - 1, file.nextInt() - 1);
		if(!vis.contains(current))
		{
			System.out.println("impossible");
		}else{
			StringBuilder sb = new StringBuilder("");
			while(pred.containsKey(current))
			{
				sb.insert(0, " ");
				sb.insert(0, num.get(current));
				current = pred.get(current);
			}
			System.out.println(sb.toString());
		}
	}
	
	public boolean canSlide(Point p1, Point p2, Point p3)
	{
		return p1.x == p2.x && p2.x == p3.x || p1.y == p2.y && p2.y == p3.y;
	}
	
	private class next implements Comparable<next>{
		int id;
		Point p;
		
		public next(int id,Point p)
		{
			this.id = id;
			this.p = p;
		}
		
		public int compareTo(next a)
		{
			return Integer.compare(id, a.id);
		}
		
		public String toString()
		{
			return p.x+" "+p.y;
		}
	}
}
