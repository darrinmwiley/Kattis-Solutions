import java.util.Arrays;
import java.util.Scanner;

public class ThreeDPrinter {

	public static void main(String[] args)
	{
		new ThreeDPrinter().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		double ans = 0;
		for(int z = 0;z<zz;z++)
		{
			int N = file.nextInt();
			pt[][] sides = new pt[N][];
			double X = 0;
			double Y = 0;
			double Z = 0;
			int pts = 0;
			for(int i = 0;i<N;i++)
			{
				int M = file.nextInt();
				sides[i] = new pt[M];
				for(int j = 0;j<M;j++)
				{
					double x = file.nextDouble();
					double y = file.nextDouble();
					double zed = file.nextDouble();
					X+=x;
					Y+=y;
					Z+=zed;
					pts++;
					sides[i][j] = new pt(x,y,zed);
				}
			}
			double ret = 0;
			pt center = new pt(X/pts,Y/pts,Z/pts);
			for(int i = 0;i<N;i++)
			{
				int m = sides[i].length;
				for(int j = 0;j<m-2;j++)
				{
					pt a = sides[i][0];
					pt b = sides[i][j+1];
					pt c = sides[i][j+2];
					double[][] mat = new double[][]{{a.x,b.x,c.x,center.x},{a.y,b.y,c.y,center.y},{a.z,b.z,c.z,center.z},{1,1,1,1}};
					ret+=Math.abs(det(mat))/6;
				}
			}
			ans+=ret;
		}
		System.out.printf("%.2f%n",ans);
	}

	public double det(double[][] mat)
	{
		if(mat.length==2){
			return mat[0][0]*mat[1][1]-mat[1][0]*mat[0][1];
		}
		double ret = 0;
		for(int i = 0;i<mat.length;i++)
		{
			if(i%2==0){
				double det = det(cross(mat,i));
				ret+=mat[0][i]*det;
			}else{
				ret-=mat[0][i]*det(cross(mat,i));
			}
		}
		return ret;
	}

	public double[][] cross(double[][] mat, int x)
	{
		double[][] ret = new double[mat.length-1][mat.length-1];
		for(int i = 0;i<ret.length;i++)
		{
			int c = 0;
			for(int j = 0;j<mat.length;j++)
			{
				if(j!=x)
					ret[i][c++] = mat[i+1][j];
			}
		}
		return ret;
	}

	private class pt{

		double x,y,z;

		public pt(double a, double b, double c)
		{
			x = a;
			y = b;
			z = c;
		}

	}
}

/*
1
6
4 0 1 0 1 1 0 1 0 0 0 0 0
4 1 1 0 1 1 -1 1 0 -1 1 0 0
4 0 0 0 0 0 -1 1 0 -1 1 0 0
4 0 0 -1 0 1 -1 1 1 -1 1 0 -1
4 0 1 0 0 1 -1 1 1 -1 1 1 0
4 0 1 0 0 1 -1 0 0 -1 0 0 0
determinant {{0, 1, 1,.5},{1, 1, 0,.5},{0, 0, 0,-.5},{1,1,1,1}}
*/
