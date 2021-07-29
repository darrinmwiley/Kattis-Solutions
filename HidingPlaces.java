import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class HidingPlaces {
	public static final int[] M = {-2, -1, 1, 2};
	public static final void main(final String[] args)throws Exception {
		final Scanner file = new Scanner(System.in);
		int n = file.nextInt();
		while(n-->0) {
			int[][] lmat = new int[8][8];
			Arrays.stream(lmat).forEach(x -> Arrays.fill(x, Integer.MAX_VALUE));
			String rpos = file.next();
			int rs = rpos.charAt(1) - '1', cs = rpos.charAt(0) - 'a';
			Queue<Integer> space = new LinkedList<>();
			space.add(rs);
			space.add(cs);
			space.add(0);
			while(!space.isEmpty()) {
				int r = space.remove(), c = space.remove(), s = space.remove();
				if(Math.min(r, c) >= 0 && Math.max(r, c) < 8 && s < lmat[r][c])
				{
					lmat[r][c] = s;
					for(int dr : M)
						for(int dc : M)
							if(Math.abs(dr) != Math.abs(dc)) {
								space.add(dr + r);
								space.add(dc + c);
								space.add(s + 1);
							}
				}
			}
			int b = 0;
			List<String> p = new ArrayList<String>();
			for(int r = 0; r < 8; r++)
				for(int c = 0; c < 8; c++)
					if(lmat[r][c] != Integer.MAX_VALUE) {
						if(lmat[r][c] > b) {
							b = lmat[r][c];
							p.clear();
						}
						if(lmat[r][c] == b) p.add((char)('a' + c) + "" + (r+1));
					}
			Collections.sort(p, (x,y)->x.charAt(1)==y.charAt(1)?x.charAt(0)-y.charAt(0):y.charAt(1)-x.charAt(1));
			System.out.printf("%d %s%n", b, p.toString().replaceAll("[\\[\\],]", ""));
		}
	}
}
