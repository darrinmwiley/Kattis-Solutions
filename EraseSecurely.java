import java.util.ArrayList;
import java.util.Scanner;


public class EraseSecurely {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        String a = file.next();
        String b = file.next();
        if(N%2==0)
            if(a.equals(b))
                System.out.println("Deletion succeeded");
            else
                System.out.println("Deletion failed");
        else
            if(a.equals(b.replaceAll("0","a").replaceAll("1","0").replaceAll("a","1")))
                System.out.println("Deletion succeeded");
            else
                System.out.println("Deletion failed");
    }
}