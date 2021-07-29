package page;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class Granica {
    
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new Granica().run();
    }
    
    public void run() throws NumberFormatException, IOException
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int[] ints = new int[N];
        for(int i = 0;i<N;i++)
        		ints[i] = file.nextInt();
        int diff = Math.abs(ints[0]-ints[1]);
        StringBuilder sb = new StringBuilder("");
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for(int i = 1;i<=Math.sqrt(diff);i++)
        {
        		if(diff%i==0)
        		{
        			if(test(ints,i))
        				ans.add(i);
        			if(i*i!=diff && test(ints,diff/i))
        				ans.add(diff/i);
        		}
        }
        Collections.sort(ans);
        if(ans.get(0)==1)
        		ans.remove(0);
        for(int x:ans)
        		sb.append(x+" ");
        System.out.println(sb.toString().trim());
    }
    
    public boolean test(int[] ints, int x)
    {
    		int desired = ints[0]%x;
    		for(int y:ints)
    			if(y%x!=desired)
    				return false;
    		return true;
    }
}