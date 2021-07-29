import java.util.Scanner;

public class BattleSimulation {
	public static final void main(final String[] arg)throws Exception {
		new BattleSimulation().run();
	}
	
	public void run()
	{
		Scanner in = new Scanner(System.in);
		char[] ar = in.nextLine().trim().toCharArray();
		for (int i = 0; i < ar.length; i++)
		{
			boolean rake = false, bite = false, laser = false;
			for (int e = 0; e < 3 && e+i < ar.length; e++)
			{
				switch (ar[i+e])
				{
				case 'R':
					rake = true;
					break;
				case 'B':
					bite = true;
					break;
				case 'L':
					laser = true;
					break;
				}
			}
			if (rake && bite && laser)
			{
				System.out.print('C');
				i += 2;
			}
			else
			{
				switch (ar[i])
				{
				case 'R':
					System.out.print('S');
					break;
				case 'B':
					System.out.print('K');
					break;
				case 'L':
					System.out.print('H');
					break;
				}
			}
		}
	}
}
