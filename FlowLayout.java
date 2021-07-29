import java.util.*;

public final class FlowLayout {
	public static final void main(final String[] args)throws Exception {
		final Scanner file = new Scanner(System.in);

		int maxW = file.nextInt();
		int runningH = 0, runningW = 0, runningMaxH = 0, runningMaxW = 0;

		while(true) {
			int w = file.nextInt();
			int h = file.nextInt();

			if(w == -1 && h == -1) {
				System.out.printf("%d x %d%n", Math.max(runningW, runningMaxW), runningH + runningMaxH);
				runningH = 0;
				runningW = 0;
				runningMaxH = 0;
				runningMaxW = 0;
				maxW = file.nextInt();
				if(maxW == 0) return;
				continue;
			}

			if(runningW + w > maxW) {
				runningH += runningMaxH;
				runningMaxW = Math.max(runningW, runningMaxW);
				runningW = w;
				runningMaxH = h;
			} else {
				runningW += w;
				runningMaxH = Math.max(runningMaxH, h);
			}
		}
	}
}
