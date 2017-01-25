import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Peragrams {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        String str = file.next();
        String next = str.replaceAll("([a-z])(.*)\\1","$2");
        while(!next.equals(str))
        {
            str = next;
            next = str.replaceAll("([a-z])(.*)\\1","$2");
        }
        System.out.println(Math.max(0,next.length()-1));
    }
}