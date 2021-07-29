import java.util.ArrayList;
import java.util.Scanner;


public class ReverseRot {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        while(true)
        {
            int n = file.nextInt();
            if(n==0)
                return;
            String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_.";
            String next = new StringBuffer(file.next()).reverse().toString();
            String ans = "";
            for(char ch:next.toCharArray())
                ans+=alph.charAt((alph.indexOf(ch)+n)%alph.length());
            System.out.println(ans);
        }
    }
    
    public static void main(String[] args)
    {
        new ReverseRot().run();
    }
    
}