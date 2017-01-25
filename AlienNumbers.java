import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class AlienNumbers {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            String num1 = file.next();
            String d1 = file.next();
            char[] digits1 = d1.toCharArray();
            char[] digits2 = file.next().toCharArray();
            int num = 0;
            while(!num1.isEmpty())
            {
                num*=digits1.length;
                num+=d1.indexOf(num1.charAt(0));
                num1 = num1.substring(1);
            }
            String ans = "";
            while(num!=0)
            {
                ans = digits2[num%digits2.length]+ans;
                num/=digits2.length;
            }
            System.out.printf("Case #%d: %s%n",z+1,ans);
        }
    }
}