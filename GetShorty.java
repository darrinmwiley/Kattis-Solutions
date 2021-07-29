import java.util.Scanner;
import java.util.Map;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

public class GetShorty {

	public static void main(String[] args)
	{
		new GetShorty().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(true) {
			int n = file.nextInt(), m = file.nextInt();
			if(n == 0 && m == 0) break;

			Map<Integer, Map<Integer, Double>> corridors = new HashMap<>();
			for(int i = 0; i < n; i++)
			{
				corridors.put(i, new HashMap<>());

			}
			for(int i = 0; i < m; i++)
			{
				int a = file.nextInt();
				int b = file.nextInt();
				double cost = Math.log(file.nextDouble());

				corridors.get(a).put(b, cost);
				corridors.get(b).put(a, cost);
			}
			double[] costs = new double[n];
			Arrays.fill(costs, Double.NEGATIVE_INFINITY);
			Queue<Integer> pos = new LinkedList<>();
			Queue<Double> dists = new LinkedList<>();
			pos.add(0); dists.add(0.0);
			while(!pos.isEmpty())
			{
				int cur = pos.remove();
				double d = dists.remove();
				if(d > costs[cur]) {
					costs[cur] = d;
					for(int next : corridors.get(cur).keySet()) {
						pos.add(next);
						dists.add(d + corridors.get(cur).get(next));
					}
				}
			}
			System.out.printf("%.4f%n", Math.exp(costs[n-1]));
		}
	}

}
