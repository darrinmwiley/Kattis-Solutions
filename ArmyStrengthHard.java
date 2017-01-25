import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ArmyStrengthHard {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int zz = Integer.parseInt(file.readLine());
		for(int z = 0;z<zz;z++)
		{
			file.readLine();
			StringTokenizer st = new StringTokenizer(file.readLine());
			int G = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			PriorityQueue<Integer> g = new PriorityQueue<Integer>();
			PriorityQueue<Integer> m = new PriorityQueue<Integer>();
			st = new StringTokenizer(file.readLine());
			while(st.hasMoreTokens())
			{
				g.add(Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(file.readLine());
			while(st.hasMoreTokens())
			{
				m.add(Integer.parseInt(st.nextToken()));
			}
			while(!(g.isEmpty()||m.isEmpty()))
			{
				int a = g.peek();
				int b = m.peek();
				if(a<b)
					g.poll();
				else
					m.poll();
			}
			if(g.isEmpty())
				System.out.println("MechaGodzilla");
			else
				System.out.println("Godzilla");
		}
	}
	
	public static int count(char ch, String str)
	{
		int c = 0;
		for(int i = 0;i<str.length();i++)
			if(str.charAt(i)==ch)
				c++;
		return c;
	}
}
