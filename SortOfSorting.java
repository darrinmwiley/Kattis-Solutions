import java.util.Arrays;
import java.util.Scanner;

public class SortOfSorting {
	public static final void main(final String[] args)throws Exception {
		final Scanner file = new Scanner(System.in);

		int n = file.nextInt();
		file.nextLine();

		while(true) {
			if(n == 0) break;

			String[] strs = new String[n];

			for(int i = 0; i < n; i++)
				strs[i] = file.nextLine();

			Arrays.sort(strs, (x, y) -> x.substring(0, 2).compareTo(y.substring(0, 2)));
			Arrays.stream(strs).forEach(System.out::println);

			int newN = file.nextInt();
			if(newN != 0) {
				System.out.println();
				file.nextLine();
				n = newN;
			}
			else break;
		}
	}
}
