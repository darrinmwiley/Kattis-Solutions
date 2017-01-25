import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Biggest {
	
	int S;
	int L;
	
	public void go() throws IOException {
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			boolean[] cut = new boolean[360*3600];
			int r = file.nextInt();
			int n = file.nextInt();
			int theta = file.nextInt()*3600+file.nextInt()*60+file.nextInt();
			int spot = 0;
			for(int i = 0;i<n;i++)
			{
				if(cut[spot])
					break;
				cut[spot] = true;
				spot+=theta;
				spot%=cut.length;
			}
			int best = -1;
			int last = 0;
			for(int i = 1;i<cut.length;i++)
			{
				if(cut[i])
				{
					best = Math.max(i-last,best);
					last = i;
				}
			}
			best = Math.max(best,cut.length-last);
			double area = Math.PI*r*r*best/(360*3600);
			System.out.println(area);
		}
	}
	
	
	public static void main(String[] args) {
		try {
			new Biggest().go();
		} catch (IOException e) {
			
		}
	}
}
