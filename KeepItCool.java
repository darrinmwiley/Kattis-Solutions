import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class KeepItCool {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new KeepItCool().run();		
	}

	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int M = file.nextInt();
		int S = file.nextInt();
		int D = file.nextInt();
		int[] ints = new int[S];
		boolean[] used = new boolean[S];
		for(int i = 0;i<S;i++)
		{
			ints[i] = file.nextInt();
		}
		int[] aux = ints.clone();
		Arrays.sort(aux);
		int[] ans = new int[S];
		for(int i =0 ;i<aux.length;i++)
		{
			int index = find(aux[i],ints,used);
			int cap = Math.min(N, D - ints[index]);
			used[index] = true;
			ans[index] = cap;
			N -= cap;
		}
		int sum = 0;
		for(int i = 0;i<ans.length;i++)
		{
			if(ans[i] ==0)
				sum += ints[i];
		}
		if(sum < M)
			System.out.println("Impossible");
		else {
			for(int i = 0;i<ans.length;i++)
			{
				System.out.print(ans[i]+" ");
			}
		}
	}

	public int find(int x, int[] ints, boolean[] used)
	{
		for(int i = 0;i<ints.length;i++)
		{
			if(ints[i] == x && !used[i])
				return i;
		}
		return -1;
	}

	public void split(ArrayList<String> rem, ArrayList<String> A, ArrayList<String> B, int N)
	{
		int index = 0;
		boolean x = true;
		while(!rem.isEmpty())
		{
			index += N - 1;
			index %= rem.size();
			String s = rem.remove(index % rem.size());
			if(!rem.isEmpty())
				index %= rem.size();
			if(x)
			{
				A.add(s);
			}else {
				B.add(s);
			}
			x = !x;
		}
	}

}
