import java.util.Scanner;

public class Beehives {
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		while(file.hasNextDouble())
		{
			double dist = file.nextDouble();
			int N = file.nextInt();
			if(N==0)
				return;
			double[] x = new double[N];
			double[] y = new double[N];
			boolean[] sour = new boolean[N];
			for(int i = 0;i<N;i++)
			{
				x[i] = file.nextDouble();
				y[i] = file.nextDouble();
			}
			for(int i = 0;i<N;i++)
			{
				for(int j = i+1;j<N;j++)
				{
					if(Math.pow(x[i]-x[j],2)+Math.pow(y[i]-y[j],2)<(dist*dist))
						sour[i] = sour[j] = true;
				}
			}
			int c = 0;
			for(int i = 0;i<sour.length;i++)
				if(sour[i])
					c++;
			System.out.println(c+" sour, "+(N-c)+" sweet");
		}
	}
	
	public static void main(String[] args)
	{
		new Beehives().run();
	}
}
