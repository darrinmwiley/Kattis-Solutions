import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;


public class Circuit_Counting {
	HashMap<Point,weight> map;
	int n;
	public void run()
	{
		map = new HashMap<Point,weight>();
		HashSet<Point> set = new HashSet<Point>();
		Scanner file = new Scanner(System.in);
		n = file.nextInt();
		int[] x = new int[n];
		int[] y = new int[n];
		for(int i = 0;i<n;i++)
		{
			x[i] = file.nextInt();
			y[i] = file.nextInt();
		}
		Stack<weight> stack = new Stack<weight>();
		stack.add(new weight(0,0,1));
		map.put(new Point(0,0),stack.peek());
		set.add(new Point(0,0));
		for(int i = 0;i<n;i++)
		{
			Stack<weight> stack2 = new Stack<weight>();//weights to modify after pass
			Stack<Long> add = new Stack<Long>();//how much to add to weights. 0 means add a new weight to the stack
			for(weight w:stack)
			{
				int nx = w.x+x[i];
				int ny = w.y+y[i];
				if(set.contains(new Point(nx,ny)))
				{
					stack2.add(map.get(new Point(nx,ny)));
					add.add(w.wt);
				}else{
					set.add(new Point(nx,ny));
					weight q = new weight(nx,ny,w.wt);
					stack2.add(q);
					add.add(0l);
					map.put(new Point(nx,ny),q);
				}
			}
			while(!stack2.isEmpty())
			{
				long n = add.pop();
				if(n!=0)
					stack2.pop().wt+=n;
				else
					stack.add(stack2.pop());
			}
		}
		System.out.println(map.get(new Point(0,0)).wt-1);
	}
	
	public static void main(String[] args)
	{
		new Circuit_Counting().run();
	}
	
}
class weight{
	
	int x,y;
	long wt;
	public weight(int a, int b, long c)
	{
		x = a;
		y = b;
		wt = c;
	}
}
