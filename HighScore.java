import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class HighScore {
	
	public static void main(String[] args) throws FileNotFoundException
	{
		new HighScore().run();
	}

	public void run() throws FileNotFoundException
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			long a = file.nextLong();
			long b = file.nextLong();
			long c = file.nextLong();
			long d = file.nextLong();
			long[] ints = new long[]{a,b,c};
			Arrays.sort(ints);
			long add = d-8;
			if(add>0)
				ints[2]+=add;
			add = Math.max(add, 0);
			d-=add;
			System.out.println(bruteForce(ints,d));
		}
	}
	
	public long bruteForce(long[] longs, long left)
	{
		if(left==0)
			return eval(longs);
		longs[0]++;
		long a = bruteForce(longs,left-1);
		longs[0]--;
		longs[1]++;
		long b = bruteForce(longs,left-1);
		longs[1]--;
		longs[2]++;
		long c = bruteForce(longs,left-1);
		longs[2]--;
		return Math.max(Math.max(a,b),c);
	}
	
	public long eval(long[] ints)
	{
		long min = Math.min(Math.min(ints[0],ints[1]),ints[2]);
		long ans = ints[0]*ints[0]+ints[1]*ints[1]+ints[2]*ints[2]+min*7;
		//System.out.println(Arrays.toString(ints)+" "+ans);
		return ans;
	}
}
