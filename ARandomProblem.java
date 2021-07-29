import java.io.IOException;
import java.util.Scanner;

public class ARandomProblem {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new ARandomProblem().run();
	}

	int P;
	int early;
	int bestN;
	int bestM;
	int dig1 = -1;
	int dig2 = -1;
	int dig3 = -1;

	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		P = file.nextInt();
		early = P+1;
		bestN = P+1;
		bestM = P+1;
		int[] ints = new int[P];
		for(int i = 0;i<P;i++)
			ints[i] = file.nextInt();
		//System.out.println(test(ints, 4,1,4,3,3));
		for(int i = 0;i<10000;i++)
		{
			int rand = (int)(Math.random() * P);
			int rand2 = (int)(Math.random() * P);
			int min = Math.min(rand, rand2);
			int max = Math.max(rand, rand2);
			for(int j = min+1;j<max;j++)
			{
				int n = j-min;
				int m = max-j;
				int test = test(ints, ints[min], n,ints[j], m, ints[max]);
				//System.out.println(ints[min]+"@"+ints[max]);
				//System.out.println(ints[min]+" "+n+" "+ints[j]+" "+m+" "+ints[max]+" -> "+test+" "+early);
				if(test != -1)
				{
					if(test < early)
					{
						early = test;
						bestN = n;
						bestM = m;
						dig1 = ints[min];
						dig2 = ints[j];
						dig3 = ints[max];
					}else if(test == early && n < bestN)
					{
						early = test;
						bestN = n;
						bestM = m;
						dig1 = ints[min];
						dig2 = ints[j];
						dig3 = ints[max];
					}else if(test == early && n == bestN && m < bestM)
					{
						early = test;
						bestN = n;
						bestM = m;
						dig1 = ints[min];
						dig2 = ints[j];
						dig3 = ints[max];
					}
				}
			}
		}
		if(early == P+1)
		{
			System.out.println("random sequence");
		}else {
			System.out.println("triple correlation "+dig1+"("+bestN+")"+dig2+"("+bestM+")"+dig3+" found");
		}
	}

	public int test(int[] ints, int a, int n, int b, int m, int c)
	{
		int occ = 0;
		int start = P;
		for(int i = 0;i<ints.length;i++)
		{
			//scenario 1
			if(ints[i] == a && i+n < ints.length && ints[i+n] == b)
			{
				if(i+n+m < ints.length && ints[i+n+m] != c)
					return -1;
			}

			//scenario 2
			if(ints[i] == b && i+m < ints.length && ints[i+m] == c)
			{
				if(i-n >= 0 && ints[i-n] != a)
					return -1;
			}

			//scenario 2
			if(ints[i] == a && i+n+m < ints.length && ints[i+n+m] == c)
			{
				if(ints[i+n] != b)
					return -1;
				else {
					occ++;
					start = Math.min(start,i);
				}
			}
		}
		if(occ >= Math.ceil(P/40.0)+1)
		{
			return start;
		}else
			return -1;
	}

}
