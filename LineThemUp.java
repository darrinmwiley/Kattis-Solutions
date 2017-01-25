import java.util.Arrays;
import java.util.Scanner;


public class LineThemUp {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int n = file.nextInt();
        String[] strs = new String[n];
        String[] sorted = new String[n];
        for(int i = 0;i<n;i++)
            strs[i] = sorted[i] = file.next();
        Arrays.sort(sorted);
        boolean inc = true;
        boolean dec = true;
        for(int i =0 ;i<n;i++)
        {
            if(!strs[i].equals(sorted[i]))
                inc = false;
            if(!strs[i].equals(sorted[n-1-i]))
                dec = false;
        }
        if(inc)
            System.out.println("INCREASING");
        else if(dec)
            System.out.println("DECREASING");
        else
            System.out.println("NEITHER");
    }
}