import java.util.Scanner;

public class Gerrymandering {

	public static void main(String[] args)
	{
		new Gerrymandering().run();
	}


	int[] A;
	int[] B;
	int V;
	int wa;
	int wb;

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int P = file.nextInt();
		int D = file.nextInt();
		A = new int[D];
		B = new int[D];
		for(int i = 0;i<P;i++)
		{
			int d = file.nextInt();
			int a = file.nextInt();
			int b = file.nextInt();
			A[d-1] += a;
			B[d-1] += b;
			V += a+b;
		}
		for(int i = 0;i<D;i++)
		{
			if(A[i] > B[i])
			{
				System.out.print("A ");
			}
			else System.out.print("B ");
			int[] waste = wasted(A[i],B[i]);
			System.out.println(waste[0]+" "+waste[1]);
			wa+=waste[0];
			wb+=waste[1];
		}
		System.out.println(Math.abs(wa-wb)/(V+0.0));
	}

	public int[] wasted(int A, int B)
	{
		if(A>B)
		{
			return new int[] {A-((A+B)/2+1), B};
		}else {
			return new int[] {A, B-((A+B)/2+1)};
		}
	}

}
