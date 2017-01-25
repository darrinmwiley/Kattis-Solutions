import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class kemija {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		long[] ints = new long[N];
		long[] diff = new long[N];
		long S = 0;
		for(int i = 0;i<N;i++){
			ints[i] = file.nextLong();
			S+=ints[i];
		}
		if(N==1)
		{
			System.out.println(ints[0]/3);
			return;
		}
		if(N==2)
		{
			long b = (ints[1]-ints[0]*2)/-3;
			long a = ints[0]-b*2;
			System.out.println(a);
			System.out.println(b);
			return;
		}
		if(N%3!=0)
		{
			int smallY = 0;
			for(int i = 0;i<N;i++)
			{
				diff[(smallY+3)%N] = ints[(smallY+2)%N]-ints[(smallY+1)%N]+diff[smallY%N];
				smallY+=3;
			}
			long z = 0;
			for(int i = 0;i<diff.length;i++)
				z+=diff[i];
			long[] ans = new long[N];
			ans[0] = (S-z*3)/(3*N);
			for(int i = 0;i<ans.length;i++)
				ans[i] = ans[0]+diff[i];
			for(long i:ans)
				System.out.println(i);
			//System.out.println(testAns(ints,ans));
		}else
		{
			long[] mins = new long[3];
			for(int i = 0;i<3;i++)
			{
				int smallY = i;
				for(int j = 0;j<N/3-1;j++)
				{
					diff[(smallY+3)%N] = ints[(smallY+2)%N]-ints[(smallY+1)%N]+diff[smallY%N];
					mins[i] = Math.max(mins[i],-diff[smallY+3%N]);
					smallY+=3;
				}
			}
			long[] ans = new long[N];
			ans[0] = mins[0];
			ans[1] = mins[1];
			ans[2] = ints[1]-ans[0]-ans[1];
			for(int i = 3;i<ans.length;i++)
				ans[i] = ans[i%3]+diff[i];
			for(long i:ans)
				System.out.println(i);
			//System.out.println(Arrays.toString(diff));
			//System.out.println(testAns(ints,ans));
		}
	}
	
	public boolean testAns(long[] ints, long[] ans)
	{
		long[] test = new long[ints.length];
		for(int i = 0;i<ans.length;i++)
		{
			test[i] = ans[i]+ans[(i+1)%ans.length]+ans[(i+ans.length-1)%ans.length];
		}
		//System.out.println(Arrays.toString(test)+" "+Arrays.toString(ans));
		for(int i =0;i<ans.length;i++)
		{
			if(test[i]!=ints[i])
				return false;
		}
		return true;
	}
	
	public static void main(String[] args)
	{
		new kemija().run();
	}
}

