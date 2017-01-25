import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class ToiletSeat {
    
    int S;
    int L;
    
    public void go() throws IOException {
        Scanner file = new Scanner(System.in);
        char[] line = file.nextLine().toCharArray();
        int a = 0;
        int b = 0;
        int c = 0;
        if(line[0] != line[1])
        {
            a++;
            b++;
            c++;
        }
        if(line[1]=='U')
            b++;
        else
            a++;
        for(int i = 2;i<line.length;i++)
        {
            if(line[i]=='D')
                a+=2;
            if(line[i]=='U')
                b+=2;
            if(line[i]!=line[i-1])
                c++;
        }
        System.out.println(a+"\n"+b+"\n"+c);
    }
    
    
    public static void main(String[] args) {
        try {
            new ToiletSeat().go();
        } catch (IOException e) {
            
        }
    }
}