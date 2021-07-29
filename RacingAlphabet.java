import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RacingAlphabet {

	double circ = Math.PI*60.0;
	double unit = circ/28;
	String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ '";

	public static void main(String[] args) throws FileNotFoundException
	{
		new RacingAlphabet().run();
	}

	public void run() throws FileNotFoundException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();file.nextLine();
		for(int i = 0;i<N;i++)
		{
			char[] chars = file.nextLine().toCharArray();
			double sum = 0;
			for(int j = 0;j<chars.length-1;j++)
				sum+=distance(chars[j],chars[j+1]);
			System.out.println(sum/15+chars.length);
		}
	}

	public double distance(char x, char y)
	{
		int xi = alph.indexOf(x);
		int yi = alph.indexOf(y);
		int min = Math.min(xi,yi);
		int max = Math.max(xi,yi);
		return Math.min((max-min)*unit,((28-max)+min)*unit);
	}

}
