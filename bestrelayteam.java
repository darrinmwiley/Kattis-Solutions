import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class bestrelayteam {
	
	public static void main(String[] args)
	{
		new bestrelayteam().run();		
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		Comparator<person> two = new Comparator<person>() {
			public int compare(person a, person b)
			{
				return Double.compare(a.time2, b.time2);
			}
		};
		person[] people = new person[N];
		for(int i = 0;i<N;i++)
		{
			String n = file.next();
			double a = file.nextDouble();
			double b = file.nextDouble();
			people[i] = new person(n,a,b);
		}
		Arrays.sort(people, two);
		person bestFirst = null;
		person[] bestSecond = null;
		double bestTime = Double.POSITIVE_INFINITY;
		for(int i = 0;i<people.length;i++)
		{
			double time = people[i].time1;
			person[] second = new person[3];
			int selected = 0;
		loop:
			for(int j = 0;true;j++)
			{
				if(j != i)
				{
					second[selected++] = people[j];
					time += people[j].time2;
					if(selected == 3)
						break loop;
				}
			}
			if(time < bestTime)
			{
				bestTime = time;
				bestSecond = second;
				bestFirst = people[i];
			}
		}
		System.out.println(bestTime);
		System.out.println(bestFirst.name);
		for(int i = 0;i<3;i++)
		{
			System.out.println(bestSecond[i].name);
		}
	}
	
	private class person{
		
		double time1;
		double time2;
		String name;
		
		public person(String n, double a, double b)
		{
			name = n;
			time1 = a;
			time2 = b;
		}
	}
	
}
