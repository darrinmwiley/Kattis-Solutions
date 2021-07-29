import java.util.Scanner;

public class AverageSpeed {
	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		double lastTime = 0;
		int currentSpeed = 0;
		double distance = 0;
		while(file.hasNext())
		{
			String next = file.nextLine();
			String[] split = next.split("[: ]");
			int hr = Integer.parseInt(split[0]);
			int min = Integer.parseInt(split[1]);
			int sec = Integer.parseInt(split[2]);
			double time = convertToHours(hr,min,sec);
			distance+=currentSpeed*(time-lastTime);
			lastTime = time;
			if(next.contains(" "))
			{
				currentSpeed = Integer.parseInt(split[3]);
			}else{
				System.out.printf("%s %.2f km%n",next,distance);
			}
		}
	}

	public static double convertToHours(int hr, int min, int sec)
	{
		return hr+min/60.0+sec/3600.0;
	}

}
