import java.util.Scanner;

public class OtherSide {

	public static void main(String[] args)
	{
		new OtherSide().run();
	}

	public void run()
	{
		Scanner in = new Scanner(System.in);
		int w = in.nextInt();
		int s = in.nextInt();
		int c = in.nextInt();
		int k = in.nextInt();
		if (s < k)
		{
			System.out.println("YES");
			return;
		}
		else if (w + c < k)
		{
			System.out.println("YES");
			return;
		}
		else if (s == k && w + c <= k * 2)
		{
			System.out.println("YES");
			return;
		}
		else if (w + c == k && s <= k * 2)
		{
			System.out.println("YES");
			return;
		}
		else
		{
			System.out.println("NO");
		}
	}

}
