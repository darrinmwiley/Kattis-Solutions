import java.util.Scanner;
import java.util.TreeSet;


public class Ladder {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int h = file.nextInt();
        double v = Math.toRadians(file.nextInt());
        System.out.println((int)(Math.ceil(h/Math.sin(v))));
    }
}