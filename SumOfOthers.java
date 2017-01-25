import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class SumOfOthers {
    
    int S;
    int L;
    
    public void go() throws IOException {
        Scanner file = new Scanner(System.in);
        while(file.hasNext())
        {
            Scanner chop = new Scanner(file.nextLine());
            int sum = 0;
            while(chop.hasNext())
            {
                sum+=chop.nextInt();
            }
            System.out.println(sum/2);
        }
    }
    
    
    public static void main(String[] args) {
        try {
            new SumOfOthers().go();
        } catch (IOException e) {
            
        }
    }
}
