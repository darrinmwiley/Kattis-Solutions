import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class BoundingRobots {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(file.hasNext())
		{
			int w = file.nextInt();
			int l = file.nextInt();
			if(w+l==0)
				return;
			int c = file.nextInt();
			int tx = 0;
			int ty = 0;
			int ax = 0;
			int ay = 0;
			for(int i = 0;i<c;i++)
			{
				String q = file.next();
				int n = file.nextInt();
				switch(q)
				{
				case "u":
					tx+=n;
					ax = Math.min(ax+n,l-1);
					break;
				case "d":
					tx-=n;
					ax = Math.max(0,ax-n);
					break;
				case "l":
					ty-=n;
					ay = Math.max(0,ay-n);
					break;
				default:
					ty+=n;
					ay = Math.min(ay+n,w-1);
				}
			}
			System.out.println("Robot thinks "+ty+" "+tx);
			System.out.println("Actually at "+ay+" "+ax);
		}
		
	}
	
	public static void main(String[] args)
	{
		new BoundingRobots().run();
	}
}

