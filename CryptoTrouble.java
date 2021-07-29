import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class CryptoTrouble {
	public static final void main(final String[] args)throws Exception {
		final Scanner file = new Scanner(System.in);

		final int nDigits = file.nextInt();
		file.nextLine();
		String line = file.nextLine();
		int[] digits = new int[nDigits];
		for(int i = 0; i < nDigits; i++) {
			digits[i] = line.charAt(i) - '0';
		}

		long[] modSums = new long[3];
		long zeroes = 0;

		for(int i = 0; i < nDigits; i++) {
			long[] newModSums = Arrays.stream(modSums).map(x -> x % 1000000007).toArray();

			if(digits[i] != 0) {
				for(int j = 0; j < 3; j++) {
					newModSums[(digits[i] + j) % 3] += modSums[j];
				}

				newModSums[digits[i] % 3]++;
			} else {
				zeroes++;
			}

			modSums = newModSums;
		}

		BigInteger modulus = new BigInteger("1000000007");
		BigInteger nonZeroSolutions = BigInteger.valueOf(modSums[0]).mod(modulus);
		BigInteger zeroWays = BigInteger.ONE.shiftLeft((int)zeroes).mod(modulus);

		System.out.println(nonZeroSolutions.multiply(zeroWays).mod(modulus));
	}
}
