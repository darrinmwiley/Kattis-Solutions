import java.util.ArrayList;
import java.util.Scanner;

public class BusNumbers {
	public static final void main(final String[] arg)throws Exception {
		final Scanner file = new Scanner(System.in);
		boolean[] buses = new boolean[2000];
		int n = file.nextInt();
		while(n-->0) {
			buses[file.nextInt()] = true;
		}
		Integer prev = null;
		ArrayList<String> output = new ArrayList<String>();
		for(int b = 0; b < buses.length; b++)
			if(buses[b] && prev == null)
				prev = b;
			else if(!buses[b] && prev != null) {
				if((b-1) - prev > 1) output.add(prev + "-" + (b-1));
				else if((b-1) - prev == 1) output.add(prev + " " + (b-1));
				else output.add("" + (b-1));
				prev = null;
			}
		System.out.print(output.toString().replaceAll("[\\[\\],]", ""));
	}
}
