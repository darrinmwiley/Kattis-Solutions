import java.util.Scanner;

public class DiceCup {

	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		int x = file.nextInt();
		int y = file.nextInt();
		int min = Math.min(x,y);
		int max = Math.max(x,y);
		for(int i = min;i<=max;i++)
		{
			System.out.println(i+1);
		}
	}
	
}
