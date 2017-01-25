import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Absurdistan2 {
	BigDecimal[] fact = new BigDecimal[141];
	BigDecimal[][] choose = new BigDecimal[141][141];
	public void run()
	{
		Scanner file = new Scanner(System.in);
		fact[0] = BigDecimal.ONE;
		for(int i = 1;i<141;i++)
			fact[i] = new BigDecimal(i+"").multiply(fact[i-1]);
		for(int i = 0;i<141;i++)
			choose[i][0] = BigDecimal.ONE;
		for(int i = 1;i<141;i++)
			for(int j = 1;j<=i;j++)
				choose[i][j] = new BigDecimal(i+"").multiply(choose[i-1][j-1]).divide(new BigDecimal(j+""));
		int n = file.nextInt();
		System.out.println(getConnected(n).divide(new BigDecimal(n-1+"").pow(n),MathContext.DECIMAL64));
	}
	
	public BigDecimal getConnected(int k)
	{
		BigDecimal[] con = new BigDecimal[k+2];
		con[2] = new BigDecimal("1");
		con[3] = new BigDecimal("8");
		for(int i = 4;i<=k;i++)
		{
			BigDecimal total = new BigDecimal(i-1+"").pow(i);
			BigDecimal sum = BigDecimal.ZERO;
			for(int j = 2;j<=i-2;j++)
				sum = sum.add(con[j].multiply(choose[i-1][j-1]).multiply(new BigDecimal(i-j-1+"").pow(i-j)));
			con[i] = total.subtract(sum);
		}
		return con[k];
	}
	
	public static void main(String[] args)
	{
		new Absurdistan2().run();
	}
	
}
