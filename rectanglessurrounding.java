/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/abstractart
TAGS: java, geometry
EXPLANATION:
This problem is just finding the area of shapes with potential holes. We can lean on java's geometry library to solve this easily.
END ANNOTATION
*/
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.Scanner;

public class rectanglessurrounding{
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        while(true)
        {
        	int N = file.nextInt();
        	if(N == 0)
        		return;
        	int[][] rects = new int[N][4];
        	for(int i =0;i<N;i++)
        	{
        		for(int j = 0;j<4;j++)
        			rects[i][j] = file.nextInt();
        	}
        	int ans = 0;
        	for(int i = 0;i<502;i++)
        	{
        		loop:
        		for(int j = 0;j<502;j++)
        		{
        			for(int k = 0;k<N;k++)
        			{
        				if(rects[k][0] <= i && rects[k][1] <= j && rects[k][2] > i && rects[k][3] > j)
        				{
        					ans++;
        					continue loop;
        				}
        			}
        		}
        	}
        	System.out.println(ans);
        }
    }
    
}
