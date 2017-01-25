import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class WeakVertices {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(true)
		{
			int sz = file.nextInt();
			if(sz==-1)
				return;
			boolean[][] bools = new boolean[sz][sz];
			for(int i = 0;i<bools.length;i++)
			{
				for(int j = 0;j<bools.length;j++)
				{
					bools[i][j] = file.next().equals("1");
				}
			}
			for(int i = 0;i<bools.length;i++)
			{
				boolean flag = true;
				for(int j = 0;j<bools.length;j++)
				{
					for(int k = 0;k<bools.length;k++)
					{
						if(bools[i][k]&&bools[i][j]&&bools[k][j])
							flag = false;
					}
				}
				if(flag)
					System.out.print(i+" ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args)
	{
		new WeakVertices().run();
	}
}

