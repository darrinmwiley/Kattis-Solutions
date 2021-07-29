import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class taxing {
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new taxing().run();
	}
	//pad thresholds with 0 0
	public BigDecimal getIncome(BigDecimal[] thresholds, BigDecimal[] amounts, BigDecimal over, BigDecimal earned, BigDecimal m)
	{
		BigDecimal h = new BigDecimal("100");
		BigDecimal sub = new BigDecimal("0");
		for(int i = 1;i<thresholds.length;i++)
		{
			if(thresholds[i-1].compareTo(earned.add(m))>0)
				continue;
			if(earned.compareTo(thresholds[i])>0)
				continue;
			if(earned.compareTo(thresholds[i-1])>0)
			{
				BigDecimal right = (earned.add(m).min(thresholds[i]));
				sub = sub.add((right.subtract(earned)).multiply(amounts[i]).divide(h));
			}else{
				BigDecimal right = earned.add(m).min(thresholds[i]);
				sub = sub.add((right.subtract(thresholds[i-1])).multiply(amounts[i]).divide(h));
			}
		}
		if(earned.compareTo(thresholds[thresholds.length-1])>0)
			sub= sub.add(m.multiply(over).divide(h));
		else if(earned.add(m).compareTo(thresholds[thresholds.length-1])>0)
			sub = sub.add((earned.add(m).subtract(thresholds[thresholds.length-1])).multiply(over).divide(h));
		return m.subtract(sub);
	}
	
	public void run() throws NumberFormatException, IOException
	{
		BigDecimal delta = new BigDecimal(".0000000001");
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		BigDecimal[] thresholds = new BigDecimal[N+1];
		BigDecimal[] amounts = new BigDecimal[N+1];
		thresholds[0] = amounts[0] = new BigDecimal("0");
		for(int i = 0;i<N;i++)
		{
			thresholds[i+1] = file.nextBigDecimal().add(thresholds[i]);
			amounts[i+1] = file.nextBigDecimal();
		}
		BigDecimal over = file.nextBigDecimal();
		int X = file.nextInt();
		for(int i =0;i<X;i++)
		{
			BigDecimal earned = file.nextBigDecimal();
			BigDecimal give = file.nextBigDecimal();
			BigDecimal L = BigDecimal.ZERO;
			BigDecimal R = new BigDecimal("10000000000000");
			BigDecimal M = L.add(R).divide(new BigDecimal("2"));
			while(R.subtract(L).compareTo(delta)>0)
			{
				M = L.add(R).divide(new BigDecimal("2"));
				BigDecimal income = getIncome(thresholds, amounts,over, earned,M);
				if(income.compareTo(give)<0)
					L = M;
				else
					R = M;
			}
			//System.out.println(getIncome(thresholds, amounts, over,earned, M));
			//System.out.println(getIncome(thresholds,amounts,over,earned,new BigDecimal("1340781.726133855")));
			System.out.printf("%.10f%n",R);
		}
	}
}
