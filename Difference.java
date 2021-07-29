import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Difference {
	
	public static void main(String[] args)
	{
		new Difference().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		long A = file.nextLong();
		long B = file.nextLong();
		ArrayList<Long> list = new ArrayList<Long>();
		boolean[] used = new boolean[200000000];
		used[(int)A] = true;
		list.add(A);
		long next = 1;
		int N = 1;
		while(true)
		{
			if(used[(int)B])
			{
				System.out.println(N);
				return;
			}
			while(used[(int)next])
			{
				next++;
			}
			long add = (list.get(list.size()-1) + next);
			for(int i = 0;i<list.size();i++)
			{
				used[(int)(add - list.get(i))] = true;
			}
			used[(int)add] = true;
			list.add(add);
			N++;
		}
	}
}
