import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;

public class KaleidoscopicPalindromes {

	public static void main(String[] args)
	{
		new KaleidoscopicPalindromes().run();
	}

	public void run()
	{
		Scanner input = new Scanner(System.in);

		TreeSet<Integer>[] kPalindromes = (TreeSet<Integer>[])new TreeSet[100001];
		for(int i = 2; i < kPalindromes.length; i++)
			kPalindromes[i] = new TreeSet<>();

		generateBinPalindromes(kPalindromes[2]);
		for(int k = 3; k < kPalindromes.length; k++) {
			filter(kPalindromes[k - 1], kPalindromes[k], k);
		}

		int min = input.nextInt();
		int max = input.nextInt();
		int k = input.nextInt();

		System.out.println(kPalindromes[k].headSet(max, true).tailSet(min, true).size());
	}

	public void filter(TreeSet<Integer> source, TreeSet<Integer> dest, int k) {
		for(Integer i : source) {
			if(testPalindrome(i, k)) dest.add(i);
		}
	}

	public void generateBinPalindromes(TreeSet<Integer> output) {
		generateBinPalindromes(output, "0");
		generateBinPalindromes(output, "1");
		generateBinPalindromes(output, "00");
		generateBinPalindromes(output, "11");
	}

	public void generateBinPalindromes(TreeSet<Integer> output, String base) {
		if(testPalindrome(Integer.valueOf(base, 2), 2)) output.add(Integer.valueOf(base, 2));
		if(base.length() < 20) {
			generateBinPalindromes(output, "0" + base + "0");
			generateBinPalindromes(output, "1" + base + "1");
		}
	}

	public boolean testPalindrome(int x, int k) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		while(x > 0) {
			digits.add(x % k);
			x /= k;
		}
		ArrayList<Integer> reversed = new ArrayList<Integer>(digits);
		Collections.reverse(reversed);

		return digits.equals(reversed);
	}

}
