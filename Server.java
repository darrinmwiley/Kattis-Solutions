import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Server {
	
	public static void main(String[] args) throws Exception
	{
		new Server().run();
	}
	
	public void run() throws Exception
	{	
		Scanner file = new Scanner(System.in);
			int numTasks = file.nextInt();
			int[] tasks = new int[numTasks];
			int ans = 0;
			int sum = 0;
			int i = 0;
			int time = file.nextInt();
			ans = 0;
			sum = 0;
			for(int j = 0; j<numTasks; j++)
			{
				i = file.nextInt();
				if((sum+i)<= time)
				{
					sum+=i;
					ans++;
				}	
				else
					sum = time+1;
			}
			System.out.println(ans);
	}
}