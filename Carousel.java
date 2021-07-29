import java.util.Scanner;

public class Carousel {
	public static final void main(final String[]arg) throws Exception {
		final Scanner file = new Scanner(System.in);
		while(true) {
			int n = file.nextInt(), m = file.nextInt();
			if(n == 0 && m == 0) break;
			long bestN = 0, bestC = Long.MAX_VALUE;
			while(n-->0) {
				long nn = file.nextInt(), nc = file.nextInt();
				if(nn <= m) {
					if((double)nc / nn < (double)bestC / bestN || (double)nc / nn == (double)bestC / bestN && nn > bestN) {
						bestN = nn;
						bestC = nc;
					}
				}
			}
			if(bestN == 0) System.out.println("No suitable tickets offered");
			else System.out.printf("Buy %d tickets for $%d%n", bestN, bestC);
		}
	}
}
