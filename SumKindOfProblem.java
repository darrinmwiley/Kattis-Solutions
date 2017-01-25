import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SumKindOfProblem {
    
    int S;
    int L;
    
    public void go() throws IOException {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int i = 0;i<zz;i++)
        {
            file.nextInt();
            int n = file.nextInt();
            System.out.println(i+1+" "+(n+1)*n/2+" "+n*n+" "+(n*n+n));
        }
    }
    
    
    public static void main(String[] args) {
        try {
            new SumKindOfProblem().go();
        } catch (IOException e) {
            
        }
    }
}
