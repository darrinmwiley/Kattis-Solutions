import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Recount {
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		String line;
		while(!(line = br.readLine()).equals("***"))
		{
			if(!map.containsKey(line))
			{
				map.put(line,0);
			}
			map.put(line,map.get(line)+1);
		}
		String winner = "null";
		int win = -1;
		boolean tie = false;
		for(String s:map.keySet())
		{
			if(map.get(s)==win)
			{
				tie = true;
			}
			if(map.get(s)>win)
			{
				tie = false;
				winner = s;
				win = map.get(s);
			}
		}
		if(tie)
			System.out.println("Runoff!");
		else
			System.out.println(winner);
	}
}
