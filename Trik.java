import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Trik {
	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		char[] next = file.next().toCharArray();
		boolean[] bools = new boolean[]{true,false,false};
		for(char ch:next)
		{
			if(ch=='A')
			{
				boolean save = bools[0];
				bools[0] = bools[1];
				bools[1] = save;
			}
			if(ch=='B')
			{
				boolean save = bools[1];
				bools[1] = bools[2];
				bools[2] = save;
			}
			if(ch=='C')
			{
				boolean save = bools[0];
				bools[0] = bools[2];
				bools[2] = save;
			}
		}
		if(bools[0])
			System.out.println(1);
		if(bools[1])
			System.out.println(2);
		if(bools[2])
			System.out.println(3);
	}
}
