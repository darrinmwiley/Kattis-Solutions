import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HotHike {
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new HotHike().run();		
	}
	
	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int[] ints = new int[N];
		for(int i = 0;i<N;i++)
			ints[i] = file.nextInt();
		int start = -1;
		int min = 1000;
		for(int i =0 ;i<ints.length - 2;i++)
		{
			int max = Math.max(ints[i],ints[i+2]);
			if(max < min)
			{
				min = max;
				start = i+1;
			}
		}
		System.out.println(start+" "+min);
	}
	
}
