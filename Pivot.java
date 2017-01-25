import java.awt.*;
import java.io.*;
import java.util.*;
import java.math.*;
import java.util.regex.*;

import static java.lang.System.*;

public class Pivot {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int n = file.nextInt();
        int[] ints = new int[n];
        for(int i = 0;i<n;i++)
            ints[i] = file.nextInt();
        int[] largest = new int[n];
        int[] smallest = new int[n];
        smallest[n-1] = ints[n-1];
        largest[0] = ints[0];
        for(int i = 1;i<ints.length;i++)
        {
            smallest[n-1-i] = Math.min(smallest[n-i],ints[n-1-i]);
            largest[i] = Math.max(largest[i-1],ints[i]);
        }
        int c = 0;
        for(int i = 0;i<ints.length;i++)
            if(smallest[i]==ints[i]&&largest[i]==ints[i])
                c++;
        System.out.println(c);
        
    }
        
    
    public static void main(String[] args)
    {
        new Pivot().run(); 
    }
}