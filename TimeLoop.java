import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class TimeLoop {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
        	System.out.println(z+1+" Abracadabra");
        }
    }
    
    public static void main(String[] args)
    {
        new TimeLoop().run();
    }
}