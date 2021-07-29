import java.io.*;
import java.util.*;

public class TravelTheSkies
{
	public static void main(String[] args)
	{
		new TravelTheSkies().go();
	}

	public void go()
	{
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();
		int n = in.nextInt();
		int m = in.nextInt();
		int[] state = new int[k];
		int[][] add = new int[n][k];
		HashMap<Integer, ArrayList<Flight>> map = new HashMap<>();
		for (int i = 0; i < m; i++)
		{
			int start = in.nextInt()-1;
			int end = in.nextInt()-1;
			int day = in.nextInt()-1;
			int amount = in.nextInt();
			if (!map.containsKey(day))
			{
				map.put(day, new ArrayList<>());
			}
			map.get(day).add(new Flight(start, end, amount));
		}
		for (int i = 0; i < n * k; i++)
		{
			int start = in.nextInt()-1;
			int day = in.nextInt()-1;
			int amount = in.nextInt();

			add[day][start] = amount;
		}
		boolean optimal = true;
		for (int i = 0; i < n && optimal; i++)
		{
			for (int e = 0; e < k; e++)
			{
				state[e] += add[i][e];
			}
//			System.out.println(Arrays.toString(state));
			int[] newState = Arrays.copyOf(state, state.length);
			if (map.containsKey(i))
			{
				for (Flight f : map.get(i))
				{
					if (f.amount > state[f.start])
					{
						optimal = false;
						break;
					}
					state[f.start] -= f.amount;
					newState[f.start] -= f.amount;
					newState[f.end] += f.amount;
				}
			}
			state = newState;
		}
		if (optimal)
		{
			System.out.println("optimal");
		}
		else
		{
			System.out.println("suboptimal");
		}
	}

	private class Flight
	{
		int start, end, amount;

		public Flight(int s, int e, int a)
		{
			start = s;
			end = e;
			amount = a;
		}
	}
}
