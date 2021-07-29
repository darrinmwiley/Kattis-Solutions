import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class PurpleRain {
	
	public static void main(String[] args)
	{
		new PurpleRain().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		char[] line = file.nextLine().toCharArray();
		int[] pr = new int[line.length + 1];
		for(int i = 1;i<pr.length;i++)
		{
			char ch = line[i-1];
			if(ch == 'R')
			{
				pr[i] = pr[i-1]+1;
			}else{
				pr[i] = pr[i-1]-1;
			}
		}
		int ansStart = -1;
		int ansEnd = -1;
		int bestAns = -1;
		int smallestIndex = 0;
		int largestIndex = 0;
		for(int i = 1;i<pr.length;i++)
		{
			if(Math.abs(pr[i]-pr[smallestIndex])>bestAns)
			{
				bestAns = Math.abs(pr[i]-pr[smallestIndex]);
				ansStart = smallestIndex;
				ansEnd = i;
			}
			if(Math.abs(pr[i]-pr[largestIndex])>bestAns)
			{
				bestAns = Math.abs(pr[i]-pr[largestIndex]);
				ansStart = largestIndex;
				ansEnd = i;
			}
			if(pr[i] < pr[smallestIndex])
				smallestIndex = i;
			if(pr[i] > pr[largestIndex])
				largestIndex = i;
		}
		System.out.println(ansStart+1+" "+(ansEnd));
	}
}
