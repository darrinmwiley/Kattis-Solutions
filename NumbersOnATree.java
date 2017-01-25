import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class NumbersOnATree {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int size = (2<<N)-1;
        char[] line = file.nextLine().trim().toCharArray();
        int spot = 0;
        for(char c:line)
        {
            if(c=='L')
                spot = spot*2+1;
            else
                spot = spot*2+2;
        }
        System.out.println(size-spot);
    }
}