import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class RationalSequence {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int N = file.nextInt();
			String[] next = file.next().split("/");
			int index = getIndex(Integer.parseInt(next[0]),Integer.parseInt(next[1]));
			System.out.println(z+1+" "+index);
		}
	}
	
	public int getIndex(int num, int denom)
	{
		if(num==denom)
			return 1;
		if(num<denom)
			return getIndex(num,denom-num)*2;
		return getIndex(num-denom,denom)*2+1;
	}
	
	public int[] get(int N)
	{
		if(N==1)
			return new int[]{1,1};
		if(N%2==0)
		{
			int[] div = get(N/2);
			return new int[]{div[0],div[0]+div[1]};
		}
		int[] div = get(N/2);
		return new int[]{div[0]+div[1],div[1]};
	}
	
	public static void main(String[] args)
	{
		new RationalSequence().run();
	}
}

