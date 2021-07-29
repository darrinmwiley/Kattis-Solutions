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
import java.math.BigInteger;
import java.util.Scanner;

public class setnja{
    
	static BigInteger[] pow = new BigInteger[10001];
	
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        pow[0] = BigInteger.ONE;
        for(int i = 1;i<pow.length;i++)
        {
        	pow[i] = pow[i-1].multiply(BigInteger.valueOf(3));
        }
        BigInteger ans = bf(file.next().toCharArray(), 0, BigInteger.ONE,0);
        System.out.println(ans);
    }
    
    //
    
    public static BigInteger bf(char[] chars, int index, BigInteger current, int stars)
    {
    	if(index == chars.length)
    		return current;
    	BigInteger left = current.multiply(BigInteger.valueOf(2));
    	BigInteger right = current.multiply(BigInteger.valueOf(2)).add(pow[stars]);
    	BigInteger star = current.multiply(BigInteger.valueOf(5)).add(pow[stars]);
    	if(chars[index] == 'L')
    		return bf(chars, index+1, left,stars);
    	if(chars[index] == 'R')
    		return bf(chars, index+1, right,stars);
    	if(chars[index] == 'P')
    		return bf(chars, index+1, current,stars);
    	return bf(chars, index+1,star, stars+1);
    	//BigInteger a = bf(chars, index+1, left);
    	//BigInteger b = bf(chars, index+1, right);
    	//BigInteger c = bf(chars, index+1, current);
    	//return a.add(b).add(c);
    	
    }
    
}
