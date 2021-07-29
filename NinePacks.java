import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class NinePacks
{

	public static void main (String[] args) throws java.lang.Exception
	{
		new NinePacks().solve();
	}

	public void solve() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int a = file.nextInt();
		int[] H = new int[a];
		for(int i = 0;i<a;i++)
		{
			H[i] = file.nextInt();
		}
		int b = file.nextInt();
		int[] B = new int[b];
		for(int i = 0;i<b;i++)
		{
			B[i] = file.nextInt();
		}
		int[] dpH = new int[100001];
		int[] dpB = new int[100001];
		Arrays.fill(dpH, 201);
		Arrays.fill(dpB, 201);
		dpH[0] = 0;
		dpB[0] = 0;
		for(int x: H)
		{
			for(int i = dpH.length - 1;i>=x;i--)
			{
				dpH[i] = Math.min(dpH[i-x] + 1, dpH[i]);
			}
		}
		for(int x: B)
		{
			for(int i = dpB.length - 1;i>=x;i--)
			{
				dpB[i] = Math.min(dpB[i-x] + 1, dpB[i]);
			}
		}
		int min = 201;
		for(int i = 1;i<dpH.length;i++)
		{
			min = Math.min(min, dpH[i] + dpB[i]);
		}
		if(min > 200)
		{
			System.out.println("impossible");
		}else {
			System.out.println(min);
		}
	}
}
