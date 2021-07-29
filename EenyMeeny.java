import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EenyMeeny {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new EenyMeeny().run();		
	}

	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		String[] strs = file.nextLine().split(" ");
		int N = file.nextInt();
		ArrayList<String> names = new ArrayList<String>();
		for(int i = 0;i<N;i++)
		{
			names.add(file.next());
		}
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		split(names,a,b,strs.length);
		System.out.println(a.size());
		for(String s:a)
			System.out.println(s);
		System.out.println(b.size());
		for(String s:b)
			System.out.println(s);
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
