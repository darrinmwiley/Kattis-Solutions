import java.util.Arrays;
import java.util.Scanner;

public class StackingCups {

	public void run(){
		Scanner file = new Scanner(System.in);
        int x = file.nextInt();
        Cup[] cups = new Cup[x];
        for(int i = 0;i<x;i++)
        {
        	cups[i] = new Cup(file.next(),file.next());
        }
        Arrays.sort(cups);
        for(Cup c:cups)
        	System.out.println(c.color);
	}
	
    public static void main(String[] args)
    {
       new StackingCups().run();
    } 
    
    private class Cup implements Comparable<Cup>{
    	
    	String color;
    	int diameter;
    	
    	public Cup(String a, String b)
    	{
    		if(a.matches("\\d+"))
    		{
    			diameter = Integer.parseInt(a);
    			color = b;
    		}else
    		{
    			color = a;
    			diameter = Integer.parseInt(b)*2;
    		}
    	}
    	
    	public int compareTo(Cup o)
    	{
    		return diameter-o.diameter;
    	}
    	
    }
}