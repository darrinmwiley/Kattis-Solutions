import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Eligibility {
	public void run() throws Exception
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();file.nextLine();
		for(int z = 0;z<zz;z++)
		{
			String[] line = file.nextLine().split(" ");
			String name = line[0];
			String date1 = line[1];
			String date2 = line[2];
			int classes = Integer.parseInt(line[3]);
			String[] d1 = date1.split("/");
			String[] dt = date2.split("/");
			int year1 = Integer.parseInt(d1[0]);
			if(year1>=2010)
			{
				System.out.println(name +" eligible");
				continue;
			}
			int year2 = Integer.parseInt(dt[0]);
			if(year2>=1991)
			{
				System.out.println(name +" eligible");
				continue;
			}
			if(classes>40)
			{
				System.out.println(name +" ineligible");
				continue;
			}
			System.out.println(name+" coach petitions");
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		new Eligibility().run();
	}
}
