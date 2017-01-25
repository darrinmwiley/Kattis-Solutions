import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class TimeTravelingTemperatures {
    
    int S;
    int L;
    
    public void go() throws IOException {
        Scanner file = new Scanner(System.in);
        int x = file.nextInt();
        int y = file.nextInt();
        if(x==0&&y==1)
            System.out.println("ALL GOOD");
        else if(y==1)
            System.out.println("IMPOSSIBLE");
        else
            System.out.println(-x/(y-1.0));
    }
    
    
    public static void main(String[] args) {
        try {
            new TimeTravelingTemperatures().go();
        } catch (IOException e) {
            
        }
    }
}