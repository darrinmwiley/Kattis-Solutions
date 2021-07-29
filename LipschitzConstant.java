import java.util.Scanner;
import java.util.TreeMap;

public class LipschitzConstant {

	public static void main(String[] args)
	{
		new LipschitzConstant().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		file.nextLine();

		TreeMap<Double, Double> pts = new TreeMap<>();

		for(int z = 0;z<zz;z++)
		{
			pts.put(file.nextDouble(), file.nextDouble());
		}

		double currentX = pts.firstKey();
		double maxSlope = Double.NEGATIVE_INFINITY;

		while(pts.higherKey(currentX) != null) {
			double nextX = pts.higherKey(currentX);

			maxSlope = Math.max(maxSlope, Math.abs((pts.get(currentX) - pts.get(nextX)) / (currentX - nextX)));
			currentX = nextX;
		}

		System.out.println(maxSlope);
	}

}
