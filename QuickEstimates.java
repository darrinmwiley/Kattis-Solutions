import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class QuickEstimates {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			System.out.println(file.next().length());
		}
	}
	
	public static void main(String[] args)
	{
		new QuickEstimates().run();
	}
}

