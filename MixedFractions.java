import java.util.Scanner;
import java.util.TreeSet;


public class MixedFractions {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int n = file.nextInt();
        int d = file.nextInt();
        while(n!=0||d!=0)
        {
            System.out.println(n/d+" "+n%d+" / "+d);
            n = file.nextInt();
            d = file.nextInt();
        }
    }
}
