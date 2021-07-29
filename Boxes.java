package page;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boxes {

	public static void main(String[] args) throws Exception
	{
		new Boxes().run();
	}
	
	public void run() throws Exception
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine());
		box[] boxes = new box[N+1];
		for(int i = 0;i<boxes.length;i++)
		{
			boxes[i] = new box(i);
		}
		StringTokenizer st = new StringTokenizer(file.readLine());
		for(int i = 0;i<N;i++)
		{
			int x = Integer.parseInt(st.nextToken());
			boxes[i+1].parents.add(boxes[x]);
			boxes[x].children.add(boxes[i+1]);
		}
		for(int i = 1;i<18;i++)
			for(int j = 1;j<boxes.length;j++)
				boxes[j].setParent(i);
		boxes[0].depthset = true;
		boxes[0].depth = 0;
		for(int i = 1;i<boxes.length;i++)
			if(!boxes[i].depthset)
				fillDepth(boxes[i]);
		fillChildren(boxes[0]);
		int M = (Integer.parseInt(file.readLine()));
		for(int xx = 0;xx<M;xx++)
		{
			st = new StringTokenizer(file.readLine());
			int q = Integer.parseInt(st.nextToken());
			int[] ints = new int[q];
			boolean[] exclude = new boolean[q];
			for(int i = 0;i<q;i++)
				ints[i] = Integer.parseInt(st.nextToken());
		loop:
			for(int i = 0;i<q;i++)
			{
				for(int j = 0;j<q;j++)
				{
					if(!exclude[j]&&i!=j)
					{
						boolean ret = contains(boxes[ints[i]],boxes[ints[j]]);
						//System.out.println(boxes[i]+" "+boxes[j]+" "+ret);
							if(ret)
							{
								exclude[j] = true;
							}
					}
				}	
			}
			int sum = 0;
			for(int i = 0;i<q;i++)
			{
				if(!exclude[i])
				{
					exclude[i] = true;
					sum+=boxes[ints[i]].numChildren;
				}
			}
			System.out.println(sum);
		}
		
	}
	
	public boolean contains(box a, box b)//-1 delete a, 0 delete none, 1 delete b
	{
		
		box current = b.getParent(0);
		
		while(current!=null)
		{
			if(current==a)
				return true;
			current = current.getParent(0);
		}
		return false;
		
		/*if(a.depth>=b.depth)
		{
			return false;
		}
		int parent = b.depth-a.depth;
		box par = b.getSpecificParent(parent);
		return par==a;*/
	}
	
	public int fillDepth(box b)//needs to be iterative to avoid exception
	{
		if(b.depthset)
			return b.depth;
		b.depthset = true;
		return b.depth = b.getParent(0).depth+1;
	}
	
	public int fillChildren(box current)//needs to be iterative to avoid exception
	{
		int ret = 1;
		for(box b:current.children)
			ret+=fillChildren(b);
		return current.numChildren = ret;
	}
	
	private class box{
		
		ArrayList<box> children;
		ArrayList<box> parents;//max 18
		int id;
		int numChildren;
		int depth;
		boolean depthset = false;
		
		public box(int id)
		{
			children = new ArrayList<box>();
			parents = new ArrayList<box>();
			this.id = id;
		}
		
		public box getParent(int x)
		{
			if(parents.size()<=x)
				return null;
			else
				return parents.get(x);
		}
		
		public void setParent(int x)
		{
			if(getParent(x-1)!=null)
			{
				box b = (getParent(x-1).getParent(x-1));
				if(b!=null)
					parents.add(b);
			}
		}
		
		public box getSpecificParent(int x)
		{
			box current = this;
			int bit = 0;
			while(x!=0)
			{
				if(current==null)
					return null;
				if(x%2==1)
					current = current.getParent(bit);
				bit++;
				x/=2;
			}
			return current;
		}
		
		public String toString()
		{
			return id+"";
		}
		
	}

}


