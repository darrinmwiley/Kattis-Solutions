import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GwensGift {
	char[] colors = "ROYGBIV".toCharArray();
	HashMap<Character,Integer> mask;
	BigInteger[] fact  = new BigInteger[1001];
	BigInteger current;

	public static void main(String[] args) {
		new GwensGift().run();
	}

	//1 3 2 should not come before 1 3 49

	public void run()
	{
		Scanner file = new Scanner(System.in);
		fact[0] = BigInteger.ONE;
		for(int i = 1;i<fact.length;i++)
			fact[i] = new BigInteger(i+"").multiply(fact[i-1]);
		int mod = file.nextInt();
		BigInteger goal = file.nextBigInteger().subtract(BigInteger.ONE);
		boolean[] used = new boolean[mod];
		int[] ans = new int[mod-1];
		current = BigInteger.ZERO;
		ans[0] = nextNumber(goal, 0, ans.length,used);
		for(int i = 1;i<ans.length;i++)
		{
			ans[i] = nextNumber(goal, ans[i-1], ans.length-i,used);
		}
		int[] trans = new int[ans.length];
		trans[0] = ans[0];
		for(int i = 1;i<trans.length;i++)
		{
			trans[i] = ((ans[i]-ans[i-1])+mod)%mod;
		}
		for(int x:trans)
			System.out.print(x+" ");
	}

	//initially call with lastnum of 1
	//implicit current
	public int nextNumber(BigInteger goal, int lastNum, int lengthLeft, boolean[] used)
	{
		int best = -1;
		int index = 0;
		BigInteger highestPotential = BigInteger.ZERO;
		for(int i = 0;i<used.length;i++)
		{
			int next = (lastNum+i+1) % used.length;
			if(next == 0)
				continue;
			if(!used[next]) {
				BigInteger potential = current.add(fact[lengthLeft-1].multiply(new BigInteger(index+"")));
				if(potential.compareTo(goal)<=0) {
					highestPotential = potential;
					best = next;
				}
				index++;
			}
		}
		used[best] = true;
		current = highestPotential;
		return best;
	}
}
