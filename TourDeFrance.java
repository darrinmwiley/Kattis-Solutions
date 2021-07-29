import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class TourDeFrance {

	public static void main(String[] args)
	{
		new TourDeFrance().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(true)
		{
			int f = file.nextInt();
			if(f == 0) break;
			int r = file.nextInt();

			ArrayList<Integer> fronts = new ArrayList<>(), rears = new ArrayList<>();

			for(int i = 0; i < f; i++)
			{
				fronts.add(file.nextInt());
			}

			for(int i = 0; i < r; i++)
			{
				rears.add(file.nextInt());
			}

			ArrayList<Double> ratios = new ArrayList<>();

			for(Integer front : fronts)
				for(Integer rear : rears)
				{
					ratios.add((double)rear / front);
				}

			Collections.sort(ratios);

			double max = Double.NEGATIVE_INFINITY;
			for(int i = 0; i < ratios.size() - 1; i++)
			{
				max = Math.max(max, ratios.get(i+1) / ratios.get(i));
			}

			System.out.printf("%.2f%n", max);
		}
	}

}
