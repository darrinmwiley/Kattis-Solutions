package page;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class artur {
	
	public static void main(String[] args) throws Exception
	{
		new artur().run();
	}
	
	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		line[] lines = new line[N];
		for(int i = 0;i<lines.length;i++)
		{
			lines[i] = new line(file.nextInt(),file.nextInt(),file.nextInt(),file.nextInt(),i+1);
		}
		boolean[][] under = new boolean[N][N];
		int[] indeg = new int[N];
		for(int i = 1;i<lines.length;i++)
		{
			for(int j = 0;j<i;j++)
			{
				int comp = lines[i].compare(lines[j]);
				if(comp==1)
				{
					indeg[i]++;
					under[j][i] = true;
				}
				if(comp==-1)
				{
					indeg[j]++;
					under[i][j] = true;
				}
			}
		}
		Queue<Integer> sort = new LinkedList<Integer>();
		Queue<Integer> zero = new LinkedList<Integer>();
		for(int i = 0;i<N;i++)
		{
			if(indeg[i]==0)
				zero.add(i);
		}
		while(!zero.isEmpty())
		{
			int current = zero.poll();
			sort.add(current);
			for(int i = 0;i<under[current].length;i++)
				if(under[current][i])
				{
					indeg[i]--;
					if(indeg[i]==0)
						zero.add(i);
				}
		}
		for(int i:sort)
			System.out.print(i+1+" ");
	}
	
	private class line{
		
		int x1,y1,x2,y2,id;
		double m,b;
		boolean isVertical;
		
		public line(int x1, int y1, int x2, int y2, int id)
		{
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.id = id;
			isVertical = (x1==x2);
			m = ((double)(y2-y1))/(x2-x1);
			b = y1-m*(x1);
			format();
		}
		
		public void format()
		{
			if(x1>x2)
			{
				int x3 = x1;
				int y3 = y1;
				x1 = x2;
				y1 = y2;
				x2 = x3;
				y2 = y3;
			}
		}
		
		//0:no intersection, -1: below, 1:above
		public int compare(line l)
		{
			double a = l.getIntersection(x1);
			double b = l.getIntersection(x2);
			if(a!=-1)
			{
				if(a<y1)
					return 1;
				return -1;
			}
			if(b!=-1)
			{
				if(b<y2)
					return 1;
				return -1;
			}
			double c = getIntersection(l.x1);
			double d = getIntersection(l.x2);
			if(c!=-1)
			{
				if(c<l.y1)
					return -1;
				return 1;
			}
			if(d!=-1)
			{
				if(d<l.y2)
					return -1;
				return 1;
			}
			return 0;
		}
		
		//gets y intersection with X=x
		public double getIntersection(int x)
		{
			if(x<x1||x>x2)
				return -1;
			if(isVertical)
				return y1;
			return m*x+b;
		}
		
	}
	
}


