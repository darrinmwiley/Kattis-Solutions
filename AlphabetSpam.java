import java.util.Scanner;
import java.util.TreeMap;


public class AlphabetSpam {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        String line = file.nextLine();
        double lower = 0;
        double upper = 0;
        double white = 0;
        double symbol = 0;
        double total = 0;
        for(char ch:line.toCharArray())
        {
            if(Character.isLowerCase(ch))
                lower++;
            else if(Character.isUpperCase(ch))
                upper++;
            else if(ch=='_')
                white++;
            else
                symbol++;
            total++;
        }
        System.out.printf("%f%n%f%n%f%n%f%n",white/total,lower/total,upper/total,symbol/total);
    }
}