import java.util.Scanner;


public class Oddities {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        file.nextLine();
        for(int z = 0;z<zz;z++)
        {
            int n = file.nextInt();
            System.out.println(n+" is "+((n&1)==0?"even":"odd"));
        }
    }
}