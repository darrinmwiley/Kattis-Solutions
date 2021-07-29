package codejam;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AnthonyAndCora {

	public static void main(String[] args) throws IOException
	{
		new AnthonyAndCora().run();
	}

	public void run() throws IOException
	{
		Scanner file = new Scanner(System.in);
		int A = file.nextInt();
		int B = file.nextInt();
		double[] success = new double[A+B-1];
		for(int i = 0;i<success.length;i++)
			success[i] = file.nextDouble();
		double[][] prob = new double[A+1][B+1];
		Queue<Integer> current = new LinkedList<Integer>();
		current.add(A);
		current.add(B);
		prob[A][B] = 1;
		boolean[][] processed = new boolean[A+1][B+1];
		while(!current.isEmpty())
		{
			int a = current.poll();
			int b = current.poll();
			if(processed[a][b]||a==0||b==0)
				continue;
			processed[a][b] = true;
			int currentTurn = A+B-a-b;
			double winchance = success[currentTurn];
			double losechance = 1-success[currentTurn];
			prob[a][b-1]+= winchance*prob[a][b];
			prob[a-1][b]+= losechance*prob[a][b];
			current.add(a);
			current.add(b-1);
			current.add(a-1);
			current.add(b);
		}
		double ret = 0;
		for(int i = 1;i<=A;i++)
		{
			ret+=prob[i][0];
		}
		System.out.println(ret);
	}
}
