/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/bearlymadeit
TAGS: geometry, dijkstra, sweep, epsilon
EXPLANATION:
The observation to make is that the most efficient path is comprised of straight lines 
between the starting point, intersection points of any circles, and the ending point.

Since there are at most 25 circles, and any two circles have at most 2 intersections,
the number of locations to travel between are 2*25choose2 + 2 = 602.

Since there are a small number of vertices, we can check every pair to see if it is possible
to travel between the two without crossing the water

To determine if you can travel between two points on ice:

let segment S = the line segment between the two points.
Each circle that intersects S does so from some X = x1 to X = x2, or from some Y = y1 to Y = y2
For each circle, determine either the X or Y interval of intersection with S.
It is possible to move between two points if the union of all intervals fully covers S.

Union of a set of intervals can be done in NlogN with a sweep.

There is a degenerate case where two points have the same X or Y coordinate:
If two points have the same X coordinate, use Y intervals to determine if S is covered
If two points have the same Y coordinate, use X intervals to determine if S is covered

After constructing the graph of interesting locations and determining which you can move
between, perform dijkstras algorithm from the starting point to the ending point to get
the answer.

using an epsilon value is also important in this problem as some precision is lost throughout.

END ANNOTATION
 */
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class buka {
    
    public void run() throws IOException
    {
        Scanner file = new Scanner(System.in);
        BigInteger a = file.nextBigInteger();
        char op = file.next().charAt(0);
        BigInteger b = file.nextBigInteger();
        if(op == '+')
        {
        	System.out.println(a.add(b));
        }else {
        	System.out.println(a.multiply(b));
        }
    } 
    
    public static void main(String[] args) throws IOException
    {
        new buka().run();
    }
} 