import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class Dunglish {
	
	public static void main(String[] args) throws FileNotFoundException
	{
		new Dunglish().run();
	}

	public void run() throws FileNotFoundException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		file.nextLine();
		String[] line = file.nextLine().split(" ");
		int M = file.nextInt();
		TreeMap<String,Integer> correct = new TreeMap<>();
		TreeMap<String,Integer> incorrect = new TreeMap<>();
		TreeMap<String,String> trans = new TreeMap<>();
		for(int i = 0;i<M;i++)
		{
			String a = file.next();
			String b = file.next();
			String c = file.next();
			if(!correct.containsKey(a))
				correct.put(a, 0);
			if(!incorrect.containsKey(a))
				incorrect.put(a,0);
			if(c.equals("correct"))
			{
				correct.put(a,correct.get(a)+1);
			}else
				incorrect.put(a,incorrect.get(a)+1);
			trans.put(a, b);
		}
		long[][] dp = new long[N+1][2];
		dp[0][0] = 1l;
		for(int i = 1;i<dp.length;i++)
		{
			String current = line[i-1];
			int numCorrect = correct.get(current);
			int numIncorrect = incorrect.get(current);
			dp[i][0] = numCorrect*dp[i-1][0];
			dp[i][1] = numIncorrect*(dp[i-1][0]+dp[i-1][1])+numCorrect*dp[i-1][1];
		}
		long nCorrect = dp[dp.length-1][0];
		long nIncorrect = dp[dp.length-1][1];
		if(nCorrect+nIncorrect == 1)
		{
			String ans = "";
			for(int i = 0;i<line.length;i++)
			{
				ans+= trans.get(line[i])+" ";
			}
			ans = ans.trim();
			System.out.println(ans);
			if(nCorrect == 1)
			{
				System.out.println("correct");
			}
			else
				System.out.println("incorrect");
		}else
		{
			System.out.println(nCorrect+" correct");
			System.out.println(nIncorrect+" incorrect");
		}
	}
}
