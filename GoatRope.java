import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Scanner;

public class GoatRope {

	public static void main(String[] args)
	{
		new GoatRope().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		double x = file.nextDouble();
		double y = file.nextDouble();
		double x1 = file.nextDouble();
		double y1 = file.nextDouble();
		double x2 = file.nextDouble();
		double y2 = file.nextDouble();

		Area rect = new Area(new Rectangle2D.Double(x1, y1, (x2-x1), (y2-y1)));

		double max = 5000;
		double min = 0;
		double mid = (max+min)/2;
		while(max - min > .000000001)
		{
			mid = (max+min)/2;
			Area circle = new Area(new Ellipse2D.Double(x - mid, y - mid, mid*2, mid * 2));
			circle.intersect(rect);
			if(!circle.isEmpty())
			{
				max = mid;
			}else {
				min = mid;
			}
		}
		System.out.println(mid);
	}

}
