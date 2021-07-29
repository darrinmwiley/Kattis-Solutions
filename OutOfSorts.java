import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OutOfSorts {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new OutOfSorts().run();		
	}

	long [] arr;

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		long M = file.nextLong();
		long a = file.nextLong();
		long c = file.nextLong();
		long x0 = file.nextLong();
		arr = new long[N];
		arr[0] = (x0 * a + c) % M;
		for(int i = 1;i<arr.length;i++)
		{
			arr[i] = (arr[i-1] * a + c) % M;
		}
		int ans = 0;
		for(int i = 0;i<arr.length;i++)
		{
			if(bin(arr[i]))
				ans++;
		}
		System.out.println(ans);
	}

	public boolean bin(long x)
	{
		int low = -1;
		int high = arr.length;
		int mid = (low+high)/2;
		while(high - low > 1)
		{
			mid = ((low+high)/2);
			if(arr[mid] == x)
				return true;
			if(arr[mid] < x)
			{
				low = mid;
			}else
				high = mid;
		}
		return arr[mid] == x;
	}

}
