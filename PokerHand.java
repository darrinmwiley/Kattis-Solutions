import java.util.Arrays;
import java.util.Scanner;

public class PokerHand {

	public static void main(String[] args)
	{
		new PokerHand().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		String[] strs = file.nextLine().split(" ");
		int[] ints = new int[1000000];
		for(String s:strs)
		{
			ints[s.charAt(0)]++;
		}
		Arrays.sort(ints);
		System.out.println(ints[999999]);
	}

}
