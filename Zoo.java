import java.util.Scanner;
import java.util.TreeMap;

public class Zoo {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		int c = 1;
		while(N!=0)
		{
			file.nextLine();
			TreeMap<String,Integer> map = new TreeMap();
			for(int i = 0;i<N;i++)
			{
				String[] line = file.nextLine().split(" ");
				String next = line[line.length-1].toLowerCase();
				if(!map.containsKey(next))
					map.put(next,0);
				map.put(next,map.get(next)+1);
			}
			System.out.println("List "+c+++":");
			for(String s:map.keySet())
			{
				System.out.println(s+" | "+map.get(s));
			}
			N = file.nextInt();
		}
	}
	
	public static void main(String[] args)
	{
		new Zoo().run();
	}
}

