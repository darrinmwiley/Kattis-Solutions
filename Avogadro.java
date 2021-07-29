import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Avogadro {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new Avogadro().run();
	}
	
	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine().trim());
		int[][] ints = new int[3][N];
		int[][] num = new int[3][N+1];
		StringTokenizer st;
		for(int i = 0;i<3;i++){
			st = new StringTokenizer(file.readLine());
			for(int j = 0;j<N;j++)
			{
				int x = Integer.parseInt(st.nextToken());
				ints[i][j] = x;
				num[i][x]++;
			}
		}
		col[] cols = new col[N];
		for(int i = 0;i<N;i++)
			cols[i] = new col(ints[0][i],ints[1][i],ints[2][i],i);
		col[] first = cols.clone();
		col[] second = cols.clone();
		col[] third = cols.clone();
		Comparator<col> a = new Comparator<col>(){
			
			public int compare(col a, col b)
			{
				return Integer.compare(a.a,b.a);
			}
			
		};
		Comparator<col> b = new Comparator<col>(){
			
			public int compare(col a, col b)
			{
				return Integer.compare(a.b,b.b);
			}
			
		};
		Comparator<col> c = new Comparator<col>(){
			
			public int compare(col a, col b)
			{
				return Integer.compare(a.c,b.c);
			}
			
		};
		Arrays.sort(first,a);
		Arrays.sort(second,b);
		Arrays.sort(third,c);
		Stack<Integer> toDelete = new Stack<Integer>();
		boolean[] shad = new boolean[N+1];
		boolean[] deleted = new boolean[N];
		for(int i = 0;i<3;i++)
			for(int j = 1;j<=N;j++)
				if(num[i][j]==0&&!shad[j])
				{
					shad[j] = true;
					toDelete.add(j);
				}
		while(!toDelete.isEmpty())
		{
			int x = toDelete.pop();
			delete(x,0,first,deleted,shad,num,toDelete);
			delete(x,1,second,deleted,shad,num,toDelete);
			delete(x,2,third,deleted,shad,num,toDelete);
		}
		int ans = 0;
		for(boolean x:deleted)
			if(x)
				ans++;
		System.out.println(ans);
	}	
	
	public void delete(int x,int v, col[] cols, boolean[] deleted,boolean[] shad,int[][] num, Stack<Integer> toDelete)
	{
		int begin = findFirst(x,v,cols);
		int end = findLast(x,v,cols);
		if(begin==-1)
			return;
		for(int i = begin;i<=end;i++)
		{
			if(!deleted[cols[i].id])
			{
				col co = cols[i];
				int a = co.a;
				int b = co.b;
				int c = co.c;
				num[0][a]--;
				if(num[0][a]==0&&!shad[a])
				{
					toDelete.add(a);
					shad[a] = true;
				}	
				num[1][b]--;
				if(num[1][b]==0&&!shad[b])
				{
					toDelete.add(b);
					shad[b] = true;
				}	
				num[2][c]--;
				if(num[2][c]==0&&!shad[c])
				{
					toDelete.add(c);
					shad[c] = true;
				}	
				deleted[cols[i].id] = true;
			}
		}
	}
	
	public int findFirst(int x, int v, col[] cols)
	{
		int L = -1;
		int R = cols.length;
		int M = (L+R)/2;
		int first = -1;
		while(R-L>1)
		{
			M = (L+R)/2;
			int val = getValue(M,v,cols);
			if(val==x)
				first = M;
			if(val>=x)
				R = M;
			else
				L = M;
		}
		return first;
	}
	
	public int findLast(int x, int v, col[] cols)
	{
		int L = -1;
		int R = cols.length;
		int M = (L+R)/2;
		int last = -1;
		while(R-L>1)
		{
			M = (L+R)/2;
			int val = getValue(M,v,cols);
			if(val==x)
				last = M;
			if(val<=x)
				L = M;
			else
				R = M;
		}
		return last;
	}
	
	public int getValue(int i,int v, col[] cols)
	{
		if(v==0)
			return cols[i].a;
		if(v==1)
			return cols[i].b;
		return cols[i].c;
	}
	
	private class col{
		
		int a,b,c,id;
		public col(int x,int y, int z,int id)
		{
			a = x;
			b = y;
			c = z;
			this.id = id;
		}
		
	}
	
}