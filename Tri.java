import java.util.Scanner;
import java.util.TreeMap;


public class Tri {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int a = file.nextInt();
        int b = file.nextInt();
        int c = file.nextInt();
        if(a+b==c)
        {
            System.out.println(a+"+"+b+"="+c);
            return;
        }
        if(a-b==c)
        {
            System.out.println(a+"-"+b+"="+c);
            return;
        }
        if(a*b==c)
        {
            System.out.println(a+"*"+b+"="+c);
            return;
        }
        if(a/b==c)
        {
            System.out.println(a+"/"+b+"="+c);
            return;
        }
        if(a==b+c)
        {
            System.out.println(a+"="+b+"+"+c);
            return;
        }
        if(a==b-c)
        {
            System.out.println(a+"="+b+"-"+c);
            return;
        }
        if(a==b*c)
        {
            System.out.println(a+"="+b+"*"+c);
            return;
        }
        if(a==b/c)
        {
            System.out.println(a+"="+b+"/"+c);
            return;
        }
    }
}
