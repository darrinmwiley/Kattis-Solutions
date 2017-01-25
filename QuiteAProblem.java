import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class QuiteAProblem {

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(System.in);
        while(file.hasNextLine())
        {
            System.out.println(file.nextLine().matches(".*[pP][rR][oO][bB][lL][eE][mM].*")?"yes":"no");
        }
    }
}