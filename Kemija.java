import java.util.Scanner;
import java.util.TreeMap;


public class Kemija{
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        String line = file.nextLine();
        System.out.println(line.replaceAll("([aeiou])p\\1","$1"));
    }
}
