import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PaperCuts2 {	
	
	tri goal;
	
	public void run() throws FileNotFoundException
	{
		Scanner file = new Scanner(System.in);
	loop:
		while(true)
		{
			int X = file.nextInt();
			if(X==0)
			{
				System.out.println();
				return;	
			}
			goal = new tri(X,file.nextInt(),file.nextInt(),null,null);
			tri a = new tri(file.nextInt(),file.nextInt(),file.nextInt(),null,null);
			tri b = new tri(file.nextInt(),file.nextInt(),file.nextInt(),null,null);
			tri c = new tri(file.nextInt(),file.nextInt(),file.nextInt(),null,null);
			tri d = new tri(file.nextInt(),file.nextInt(),file.nextInt(),null,null);
			a.id = 1;
			b.id = 2;
			c.id = 4;
			d.id = 8;
			ArrayList<shape> A = new ArrayList();
			ArrayList<shape> B = new ArrayList();
			ArrayList<shape> C = new ArrayList();
			ArrayList<shape> D = new ArrayList();
			A.add(a);
			B.add(b);
			C.add(c);
			D.add(d);
			ArrayList<shape> ab = new ArrayList();
			ArrayList<shape> ac = new ArrayList();
			ArrayList<shape> ad = new ArrayList();
			ArrayList<shape> bc = new ArrayList();
			ArrayList<shape> bd = new ArrayList();
			ArrayList<shape> cd = new ArrayList();
			cross(A,B,ab);
			cross(A,C,ac);
			cross(A,D,ad);
			cross(B,C,bc);
			cross(B,D,bd);
			cross(C,D,cd);
			ArrayList<shape> abc = new ArrayList();
			ArrayList<shape> abd = new ArrayList();
			ArrayList<shape> acd = new ArrayList();
			ArrayList<shape> bcd = new ArrayList();
			cross(A,bc,abc);
			cross(B,ac,abc);
			cross(C,ab,abc);
			cross(A,bd,abd);
			cross(B,ad,abd);
			cross(D,ab,abd);
			cross(A,cd,acd);
			cross(C,ac,acd);
			cross(D,ac,acd);
			cross(B,cd,bcd);
			cross(C,bd,bcd);
			cross(D,bc,bcd);
			ArrayList<shape> abcd = new ArrayList<shape>();
			cross(ab,cd,abcd);
			cross(ac,bd,abcd);
			cross(ad,bc,abcd);
			cross(A,bcd,abcd);
			cross(B,acd,abcd);
			cross(C,abd,abcd);
			cross(D,abc,abcd);
			unique(abcd);
			valid(abcd);
			for(shape sh:abcd)
			{
				if(sh instanceof tri)
					if(equals((tri)sh,goal))
					{
						System.out.println("yes");
						continue loop;
					}
			}
			System.out.println("no");
		}
		
/*
60 70 50 30 100 50 75 70 35 75 60 45 45 65 70			Y	Y
40 75 65 60 40 80 20 120 40 45 85 50 25 55 100			Y	Y
60 60 60 30 60 90 30 60 90 90 60 30 90 60 30			Y	Y
30 60 90 30 120 30 30 120 30 30 120 30 30 120 30		N	N
60 70 50 30 100 50 75 70 35 75 60 45 70 65 45			N	N
30 130 20 100 55 25 40 30 110 35 15 130 20 40 120		Y	Y
30 110 40 120 35 25 20 70 90 90 60 30 15 75 90			Y	Y
50 70 60 100 15 65 70 15 95 15 80 85 50 115 15			Y	N
60 50 70 55 50 75 45 30 105 60 50 70 110 45 25			Y	Y
35 85 60 20 30 130 40 60 80 50 70 60 25 140 15			Y	N
65 55 60 20 85 75 30 130 20 95 20 65 55 50 75			Y	Y
65 70 45 55 20 105 75 65 40 10 125 45 115 35 30			Y	Y
50 70 60 95 30 55 85 65 30 35 30 115 35 125 20			Y	N
50 60 70 105 40 35 25 60 95 30 50 100 85 75 20			Y	Y
60 70 50 40 90 50 90 10 80 100 50 30 20 100 60			Y	Y
70 20 90 10 80 90 80 25 75 20 105 55 100 20 60			Y	Y
130 20 30 140 30 10 160 10 10 150 20 10 130 10 40		Y	N
120 6 54 80 5 95 145 1 34 85 35 60 40 120 20			N	N
60 60 60 30 60 90 30 60 90 30 60 90 30 60 90			N	N
30 60 90 30 60 90 30 60 90 30 60 90 30 60 90			Y	Y
130 30 20 100 55 25 40 30 110 35 15 130 20 40 120		N	N
110 30 40 120 35 25 20 70 90 90 60 30 15 75 90			N	N
70 50 60 100 15 65 70 15 95 15 80 85 50 115 15			N	N
50 60 70 110 45 25 55 50 75 60 50 70 45 30 105			N	N
85 35 60 20 30 130 40 60 80 50 70 60 25 140 15			N	N
65 55 60 20 85 75 30 130 20 95 20 65 55 75 50			N	N
65 70 45 55 20 105 75 65 40 10 125 45 115 30 35			N	N
50 70 60 95 30 55 85 65 30 35 30 115 35 20 125			N	N
50 60 70 105 40 35 25 60 95 30 50 100 85 20 75			N	N
60 70 50 40 90 50 90 10 80 100 50 30 20 60 100			N	N	
0 0 180
 */
/*
60 70 50 30 100 50 75 70 35 75 60 45 45 65 70
40 75 65 60 40 80 20 120 40 45 85 50 25 55 100
60 60 60 30 60 90 30 60 90 90 60 30 90 60 30
30 60 90 30 120 30 30 120 30 30 120 30 30 120 30
60 70 50 30 100 50 75 70 35 75 60 45 70 65 45
30 130 20 100 55 25 40 30 110 35 15 130 20 40 120
30 110 40 120 35 25 20 70 90 90 60 30 15 75 90
50 70 60 100 15 65 70 15 95 15 80 85 50 115 15
60 50 70 55 50 75 45 30 105 60 50 70 110 45 25
35 85 60 20 30 130 40 60 80 50 70 60 25 140 15
65 55 60 20 85 75 30 130 20 95 20 65 55 50 75
65 70 45 55 20 105 75 65 40 10 125 45 115 35 30
50 70 60 95 30 55 85 65 30 35 30 115 35 125 20
50 60 70 105 40 35 25 60 95 30 50 100 85 75 20
60 70 50 40 90 50 90 10 80 100 50 30 20 100 60
70 20 90 10 80 90 80 25 75 20 105 55 100 20 60
130 20 30 140 30 10 160 10 10 150 20 10 130 10 40
120 6 54 80 5 95 145 1 34 85 35 60 40 120 20
60 60 60 30 60 90 30 60 90 30 60 90 30 60 90
30 60 90 30 60 90 30 60 90 30 60 90 30 60 90
130 30 20 100 55 25 40 30 110 35 15 130 20 40 120
110 30 40 120 35 25 20 70 90 90 60 30 15 75 90
70 50 60 100 15 65 70 15 95 15 80 85 50 115 15
50 60 70 110 45 25 55 50 75 60 50 70 45 30 105
85 35 60 20 30 130 40 60 80 50 70 60 25 140 15
65 55 60 20 85 75 30 130 20 95 20 65 55 75 50
65 70 45 55 20 105 75 65 40 10 125 45 115 30 35
50 70 60 95 30 55 85 65 30 35 30 115 35 20 125
50 60 70 105 40 35 25 60 95 30 50 100 85 20 75
60 70 50 40 90 50 90 10 80 100 50 30 20 60 100
0 0 180
 */
	}
	
	public boolean equals(tri a, tri b)
	{
		if(a.a==b.a&&a.b==b.b&&a.c==b.c)
			return true;
		b = b.rotate();
		if(a.a==b.a&&a.b==b.b&&a.c==b.c)
			return true;
		b = b.rotate();
		if(a.a==b.a&&a.b==b.b&&a.c==b.c)
			return true;
		return false;
	}
	
	public boolean equals(trap a, trap b)
	{
		if(a.a==b.a&&a.b==b.b&&a.c==b.c&&a.d==b.d)
			return true;
		b = b.rotate();
		if(a.a==b.a&&a.b==b.b&&a.c==b.c&&a.d==b.d)
			return true;
		b = b.rotate();
		if(a.a==b.a&&a.b==b.b&&a.c==b.c&&a.d==b.d)
			return true;
		b = b.rotate();
		if(a.a==b.a&&a.b==b.b&&a.c==b.c&&a.d==b.d)
			return true;
		return false;
	}
	
	public boolean equals(shape a, shape b)
	{
		if(a instanceof tri && b instanceof tri)
			return equals((tri)(a),(tri)(b));
		if(a instanceof trap && b instanceof trap)
			return equals((trap)(a),(trap)(b));
		return false;
	}
	
	public void unique(ArrayList<shape> list)
	{
		for(int i = 0;i<list.size();i++)
		{
			for(int j = i+1;j<list.size();j++)
			{
				if(equals(list.get(i),list.get(j)))
				{
					list.remove(j--);
				}
			}
		}
	}
	
	public void valid(ArrayList<shape> list)
	{
		for(int i = 0;i<list.size();i++)
		{
			if(list.get(i).invalid())
			{
				list.remove(i--);
				continue;
			}	
		}
		
	}
	
	public void cross(ArrayList<shape> a, ArrayList<shape> b, ArrayList<shape> c)
	{
		for(int i = 0;i<a.size();i++)
		{
			for(int j = 0;j<b.size();j++)
			{
				shape x = a.get(i);
				shape y = b.get(j);
				if(x instanceof trap && y instanceof tri)
				{
					combine(c,(tri)y,(trap)x);
				}
				if(x instanceof tri && y instanceof trap)
				{
					combine(c,(tri)x,(trap)y);
				}
				if(x instanceof tri && y instanceof tri)
				{
					combine(c,(tri)x,(tri)y);
				}
			}
		}
	}
	
	public void combine(ArrayList<shape> result, tri A,trap B)
	{
		for(int i = 0;i<3;i++)
		{
			A = A.rotate();
			if(A.c+B.a==180&&A.b+B.b!=180)
			{
				result.add(new trap(A.a,A.b+B.b,B.c,B.d,A,B));
			}
			if(A.c+B.a!=180&&A.b+B.b==180)
			{
				result.add(new trap(A.a,B.c,B.d,A.c+B.d,A,B));
			}
			if(A.a+B.b==180&&A.c+B.c!=180)
			{
				result.add(new trap(B.a,A.b,A.c+B.c,B.d,A,B));
			}
			if(A.a+B.b!=180&&A.c+B.c==180)
			{
				result.add(new trap(B.a,A.a+B.b,A.b,B.d,A,B));
			}
			if(A.a+B.d==180&&A.b+B.c!=180)
			{
				result.add(new trap(B.a,B.b,B.c+A.b,A.c,A,B));
			}
			if(A.a+B.d!=180&&A.b+B.c==180)
			{
				result.add(new trap(B.a,B.b,A.c,A.a+B.d,A,B));
			}
			if(A.a+B.a==180&&A.b+B.d!=180)
			{
				result.add(new trap(A.c,B.b,B.c,A.b+B.d,A,B));
			}
			if(A.a+B.a!=180&&A.b+B.d==180)
			{
				result.add(new trap(A.a+B.a,B.b,B.c,A.c,A,B));
			}
			if(A.a+B.b==180&&A.c+B.c==180)
			{
				result.add(new tri(B.a,A.b,B.d,A,B));
			}
			if(A.a+B.c==180&&A.c+B.d==180)
			{
				result.add(new tri(B.b,A.b,B.a,A,B));
			}
			if(A.b+B.b==180&&A.c+B.a==180)
			{
				result.add(new tri(A.a,B.c,B.d,A,B));
			}
			if(A.a+B.a==180&&A.b+B.d==180)
			{
				result.add(new tri(B.b,B.c,A.c,A,B));
			}
		}
	}
	
	public void combine(ArrayList<shape> result, tri A,tri B)
	{
		for(int i = 0;i<3;i++)
		{
			A = A.rotate();
			if(A.a+B.a==180)
			{
				result.add(new tri(A.c,B.b,A.b+B.c,A,B));
				result.add(new tri(A.b,A.c+B.b,B.c,A,B));
			}
			if(A.a+B.b==180)
			{
				result.add(new tri(A.c,B.c,A.b+B.a,A,B));
				result.add(new tri(A.b,A.c+B.c,B.a,A,B));
			}
			if(A.a+B.c==180)
			{
				result.add(new tri(A.c,B.a,A.b+B.b,A,B));
				result.add(new tri(A.b,A.c+B.a,B.b,A,B));
			}
			if(A.a+B.b!=180&&A.b+B.a!=180)
			{
				result.add(new trap(A.c,A.a+B.b,B.c,A.b+B.a,A,B));
			}
			if(A.a+B.c!=180&& A.b+B.b!=180)
			{
				result.add(new trap(B.a,A.b+B.b,A.c,A.a+B.c,A,B));
			}
			if(A.b+B.a!=180&&A.b+B.c!=180)
			{
				result.add(new trap(A.a+B.a,B.b,A.b+B.c,A.c,A,B));
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		new PaperCuts2().run();
	}
	
	private class shape{
		
		shape pred1;
		shape pred2;
		int id;
		
		public shape(shape p1, shape p2)
		{
			pred1 = p1;
			pred2 = p2;
			if(p1!=null&&p2!=null)
				id = pred1.id|pred2.id;
		}
		
		public boolean invalid()
		{
			if(pred1!=null&&pred2!=null)
			{
				if(pred1.invalid()||pred2.invalid())
					return true;
				return (pred1.id&pred2.id)!=0;
			}
			return false;
		}
		
	}
	
	private class tri extends shape{
		
		int a,b,c;
		
		public tri(int x, int y, int z, shape p1, shape p2)
		{
			super(p1,p2);
			a = x;
			b = y;
			c = z;
		}
		
		public tri(int x, int y, int z, shape p1, shape p2, int q)
		{
			super(p1,p2);
			id = q;
			a = x;
			b = y;
			c = z;
		}
		
		public tri rotate()
		{
			return new tri(b,c,a,pred1,pred2,id);
		}
		
		public String toString()
		{
			return "("+a+","+b+","+c+")-"+id;
		}
	}
	
	private class trap extends shape{
		
		int a,b,c,d;
		
		public trap(int w, int x, int y, int z,shape pred1, shape pred2)
		{
			super(pred1, pred2);
			a = w;
			b = x;
			c = y;
			d = z;
		}
		
		public trap(int w, int x, int y, int z,shape pred1, shape pred2, int q)
		{
			super(pred1, pred2);
			id = q;
			a = w;
			b = x;
			c = y;
			d = z;
		}
		
		public trap rotate()
		{
			return new trap(b,c,d,a,pred1, pred2,id);
		}
		
		public String toString()
		{
			return "("+a+","+b+","+c+","+d+")-"+id;
		}
	}
	
}

