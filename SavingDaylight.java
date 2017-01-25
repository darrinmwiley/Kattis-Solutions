import java.awt.Point;
import java.util.Scanner;
import java.util.TreeMap;


public class SavingDaylight {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        while(file.hasNext())
        {
            System.out.print(file.next()+" "+file.next()+" "+file.next()+" ");
            String[] a = file.next().split(":");
            String[] b = file.next().split(":");
            int x = Integer.parseInt(a[0])*60+Integer.parseInt(a[1]);
            int y = Integer.parseInt(b[0])*60+Integer.parseInt(b[1]);
            System.out.println((y-x)/60+" hours "+(y-x)%60+" minutes");
        }
    }
}