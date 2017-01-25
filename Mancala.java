import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Mancala {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int cas = file.nextInt();
			int N = file.nextInt();
			int[] ints = new int[80];
			int last = 0;
			for(int i = 0;i<N;i++)
			{
				//System.out.println(Arrays.toString(ints));
				for(int j = 0;j<ints.length;j++)
				{
					if(ints[j]==0)
					{
						last = Math.max(last,j);
						ints[j]=j+1;
						for(int k = j-1;k>=0;k--)
						{
							ints[k]--;
						}
						break;
					}
				}
			}
			System.out.println(cas+" "+(last+1));
			for(int i = 0;i<=last;i++)
			{
				System.out.print(ints[i]+" ");
				if((i+1)%10==0&&i!=last)
					System.out.println();
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args)
	{
		new Mancala().run();
	}
}

