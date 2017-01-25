import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Yoda {
    
    int S;
    int L;
    
    public void go() throws IOException {
        Scanner file = new Scanner(System.in);
        String x = file.next();
        String y = file.next();
        while(y.length()<x.length())
            y = "0"+y;
        while(x.length()<y.length())
            x = "0"+x;
        char[] a = x.toCharArray();
        char[] b = y.toCharArray();
        String ansa = "";
        String ansb = "";
        for(int i = 0;i<a.length;i++)
        {
            if(a[i]>=b[i])
                ansa+=a[i];
            if(b[i]>=a[i])
                ansb+=b[i];
        }
        if(ansa.isEmpty())
            System.out.println("YODA");
        else
            System.out.println(Integer.parseInt(ansa));
        if(ansb.isEmpty())
            System.out.println("YODA");
        else
            System.out.println(Integer.parseInt(ansb));
    }
    
    
    public static void main(String[] args) {
        try {
            new Yoda().go();
        } catch (IOException e) {
            
        }
    }
}