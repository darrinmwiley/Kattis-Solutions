import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Ternarian {
	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int N = file.nextInt();
			ArrayList<Integer> left = new ArrayList<>();
			ArrayList<Integer> right = new ArrayList();
			int bit = 1;
			while(N!=0)
			{
				int rem = N%3;
				if(rem==1)
					right.add(0,bit);
				if(rem==2){
					left.add(0,bit);
					N++;
				}
				N/=3;
				bit*=3;
			}
			System.out.print("left pan: ");
			for(int i:left)
				System.out.print(i+" ");
			System.out.print("\nright pan: ");
			for(int i:right)
				System.out.print(i+" ");
			System.out.println("\n");
		}
	}
}
