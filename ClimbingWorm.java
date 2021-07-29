import java.util.Scanner;

public class ClimbingWorm {
	public static void main(String[] args)
	{
		new ClimbingWorm().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int a = file.nextInt();
		int b = file.nextInt();
		int h = file.nextInt();
		int x = 0;
		for(int i = 0;i<300000;i++)
		{
			x+=a;
			if(x>=h) {
				System.out.println(i+1);
				return;
			}
			x-=b;
		}
	}
}
