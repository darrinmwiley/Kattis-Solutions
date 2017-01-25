import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class PaulEigon {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int P = file.nextInt();
        int Q = file.nextInt();
        if((P+Q)%(N*2)<N)
            System.out.println("paul");
        else
            System.out.println("opponent");
    }
}