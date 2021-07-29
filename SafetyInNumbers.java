package solutions;

import java.util.Arrays;
import java.util.Scanner;

public class SafetyInNumbers {
	
	public static void main(String[] args) throws Exception
	{
		new SafetyInNumbers().run();
	}
	
	public void run() throws Exception{
		Scanner file = new Scanner(System.in);
		int T = file.nextInt();
		for(int z = 0;z<T;z++)
		{
			int N = file.nextInt();
			int[] J = new int[N];
			int X = 0;
			for(int i = 0;i<N;i++)
			{
				X += J[i] = file.nextInt();
			}
			String output = "Case #"+(z+1)+": ";
			for(int i = 0;i<N;i++)
			{
				double L = 0;
				double R = 1;
				double M = .5;
				while(R-L > .000000001)
				{
					M = (R+L)/2;
					double score = J[i] + X*M;
					double sum = M;
					for(int j = 0;j<N;j++)
					{
						if(i == j)
							continue;
						sum += Math.max(0, (score - J[j])/X);
					}
					if(sum > 1) {
						R = M;
					}else {
						L = M;
					}
				}
				output += String.format("%.6f ", M*100);
			}
			System.out.println(output.trim());
		}
	}
}
