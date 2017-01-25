import java.util.Arrays;
import java.util.Scanner;


public class Apaxians {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        System.out.println(file.next().replaceAll("([a-z])\\1+","$1"));
    }
}