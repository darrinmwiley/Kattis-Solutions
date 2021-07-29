import java.util.*;

public class CheckmateInOne {
	public static final void main(final String[] args)throws Exception
	{
		final Scanner file = new Scanner(System.in);
		char[][] field = new char[8][8];
		for(int i = 0; i < 8; i++)
		{
			field[i] = file.nextLine().toCharArray();
		}
		int rr = -1, rc = -1, kr = -1, kc = -1, er = -1, ec = -1;
		for(int r = 0; r < 8; r++)
			for(int c = 0; c < 8; c++)
				switch(field[r][c]) {
				case 'k':
					er = r;
					ec = c;
					break;
				case 'K':
					kr = r;
					kc = c;
					break;
				case 'R':
					rr = r;
					rc = c;
					break;
				default:
					break;
				}
		boolean success = false;
		for(int v = 0; v < 8; v++)
		{
			if(v != rr && !(Math.abs(v - er) <= 1 && Math.abs(rc - ec) <= 1)) success |= paint(v, rc, kr, kc, er, ec);
			if(v != rc && !(Math.abs(rr - er) <= 1 && Math.abs(v - ec) <= 1)) success |= paint(rr, v, kr, kc, er, ec);
		}
		for(int a = -1; a <= 1; a++)
			for(int b = -1; b <= 1; b++) {
				if(a != 0 || b != 0)
				{
					if(!oob(kr + a, kc + b) && !(Math.abs(kr + a - er) <= 1 && Math.abs(kc + b - ec) <= 1)) success |= paint(rr, rc, kr + a, kc + b, er, ec);
				}
			}
		System.out.println(success ? "Yes" : "No");
	}

	static final boolean paint(int rr, int rc, int kr, int kc, int er, int ec)
	{
		int painted = 0;
		for(int a = -1; a <= 1; a++)
			for(int b = -1; b <= 1; b++) {
				int nr = er + a;
				int nc = ec + b;
				if(oob(nr, nc) || ((nr == rr || nc == rc))
						|| (Math.abs(kr - nr) <= 1 && Math.abs(kc - nc) <= 1))
				{
					painted++;
				}
			}
		return painted == 9;
	}

	static final boolean oob(int r, int c)
	{
		return r < 0 || c < 0 || r >= 8 || c >= 8;
	}
}
