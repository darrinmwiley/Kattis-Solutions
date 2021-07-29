import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class RomanHolidays {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new RomanHolidays().run();
	}

	String[] m = new String[]{"","M","MM","MMM","MMMM"};
	String[] hundreds = new String[]{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM","M"};
	String[] tens = new String[]{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"};
	String[] ones = new String[]{"","I","II","III","IV","V","VI","VII","VIII","IX","X"};
	HashMap<Integer,Integer> ans;
	TreeMap<String,Integer> index;

	public void run() throws NumberFormatException, IOException//bfs by shortest connection number
	{
		ArrayList<String> strs = new ArrayList<String>();
		for(int M = 0;M<5;M++){
			for(int i = 0;i<10;i++)
			{
				for(int j = 0;j<10;j++)
				{
					for(int k = 0;k<10;k++)
					{
						strs.add(m[M]+hundreds[i]+tens[j]+ones[k]);
					}
				}
			}
		}
		HashMap<String,Integer> num = new HashMap<String,Integer>();
		for(int i = 0;i<=1000;i++)
			num.put(rom(i), i);
		Collections.sort(strs);
		int V = (strs.indexOf("V"));
		ans = new HashMap<Integer,Integer>();
		index = new TreeMap<String,Integer>();
		for(int i = 0;i<strs.size();i++)
		{
			index.put(strs.get(i),i);
		}
		int a = -1;
		for(int i = strs.size() - 1;i>=V;i--)
		{
			ans.put(num.get(strs.get(i)),a--);
		}
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int N = file.nextInt();
			System.out.println(answer(N));
		}
	}

	public int answer(int N)
	{
		int thou = N/1000;
		int hun = N % 1000;
		if(ans.containsKey(hun))
		{
			return ans.get(hun) - 54 * thou;
		}
		else
		{
			String rom = rom(hun);
			return (index.get(rom) + 946 * thou);
		}
	}

	public String rom(int N)
	{
		StringBuilder sb = new StringBuilder("");
		while(N >= 1000)
		{
			sb.append("M");
			N-=1000;
		}
		if(N >= 100)
		{
			sb.append(hundreds[N/100]);
			N = N % 100;
		}
		if(N >= 10)
		{
			sb.append(tens[N/10]);
			N = N % 10;
		}
		if(N >= 0)
		{
			sb.append(ones[N]);
		}
		return sb.toString();
	}
}
