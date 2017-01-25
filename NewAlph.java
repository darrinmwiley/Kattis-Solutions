import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class NewAlph {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		String[] strs = "@ 8 ( |) 3 # 6 [-] | _| |< 1 []\\/[] []\\[] 0 |D (,) |Z $ '][' |_| \\/ \\/\\/ }{ `/ 2".split(" ");
		String str = file.nextLine();
		String ans = "";
		for(char ch:str.toCharArray())
		{
			if(Character.isAlphabetic(ch))
			{
				char asdf = Character.toLowerCase(ch);
				int index = asdf-97;
				ans+=strs[index];
			}else
				ans+=ch;
		}
		System.out.println(ans);
	}
	
	public static void main(String[] args)
	{
		new NewAlph().run();
	}
}

