import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Arcade {
	public static void main(String[] args)
	{
		Scanner file = new Scanner(System.in);
		int H = file.nextInt();
		int[] payout = new int[H*(H+1)/2];
		for(int i = 0;i<payout.length;i++)
			payout[i] = file.nextInt();
		double[][] trans = new double[H*(H+1)/2][H*(H+1)/2];
		double[][] abs = new double[H*(H+1)/2][H*(H+1)/2];
		for(int i = 0;i<H;i++)
			for(int j = 0;j<=i;j++)
			{
				double tl = file.nextDouble();
				double tr = file.nextDouble();
				double bl = file.nextDouble();
				double br = file.nextDouble();
				double ab = file.nextDouble();
				int spot = i*(i+1)/2+j;
				if(tl!=0)
					trans[spot][spot-i-1] = tl;
				if(tr!=0)
					trans[spot][spot-i] = tr;
				if(bl!=0)
					trans[spot][spot+i+1] = bl;
				if(br!=0)
					trans[spot][spot+i+2] = br;
				abs[spot][spot] = ab;
			}
		for(int i = 0;i<trans.length;i++)
			for(int j = 0;j<trans.length;j++)
				if(i==j)
					trans[i][j] = 1-trans[i][j];
				else
					trans[i][j] = -trans[i][j];
		double[][] mat = new double[trans.length][trans.length*2];
		for(int i = 0;i<trans.length;i++)
		{
			for(int j = 0;j<trans.length;j++)
				mat[i][j] = trans[i][j];
			mat[i][i+trans.length] = 1;
		}
		for(int i = 0;i<mat.length;i++)
			for(int j = i+1;j<mat.length;j++)
			{
				double fact = -mat[j][i]/mat[i][i];
				for(int k = 0;k<mat[j].length;k++)
					mat[j][k]+=fact*mat[i][k];
			}
		for(int i = mat.length-1;i>=0;i--)
			for(int j = i-1;j>=0;j--)
			{
				double fact = -mat[j][i]/mat[i][i];
				for(int k = i;k<mat[j].length;k++)
					mat[j][k]+=mat[i][k]*fact;
			}
		double[][] inv = new double[trans.length][trans.length];
		for(int i = 0;i<trans.length;i++)
			for(int j = 0;j<trans.length;j++)
				inv[i][j] = mat[i][trans.length+j];
		double[] B = new double[abs.length];
		for(int i = 0;i<B.length;i++)
			for(int k = 0;k<abs.length;k++)
				B[i]+=inv[0][k]*abs[k][i];
		double pay = 0;
		for(int i = 0;i<B.length;i++)
			pay+=payout[i]*B[i];
		System.out.println(pay);
	}
}
