import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AlphabetAnimals {
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new AlphabetAnimals().run();		
	}
	
	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		char[] name = file.next().toCharArray();
		int N =file.nextInt();
		char ch = name[name.length-1];
		String[] strs = new String[N];
		HashMap<Character,ArrayList<String>> map = new HashMap<Character,ArrayList<String>>();
		for(int i =0;i<26;i++)
		{
			map.put((char)(i+'a'),new ArrayList<String>());
		}
		for(int i =0;i<strs.length;i++)
		{
			strs[i] = file.next();
			map.get(strs[i].charAt(0)).add(strs[i]);
		}
		for(int i =0;i<strs.length;i++)
		{
			if(strs[i].charAt(0) == ch)
			{
				if(other(map.get(strs[i].charAt(strs[i].length() - 1)),strs[i]) == null)
				{
					System.out.println(strs[i]+"!");
					return;
				}
			}
		}
		for(int i =0;i<strs.length;i++)
		{
			if(strs[i].charAt(0) == ch)
			{
				System.out.println(strs[i]);
				return;
			}
		}
		System.out.println("?");
	}
	
	public String other(ArrayList<String> list, String str)
	{
		if(list.size() == 0)
			return null;
		if(!list.get(0).equals(str))
			return list.get(0);
		if(list.size() == 1)
			return null;
		if(!list.get(1).equals(str))
			return list.get(1);
		return null;
	}
	
}
