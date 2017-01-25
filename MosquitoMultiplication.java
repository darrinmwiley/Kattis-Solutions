import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class MosquitoMultiplication {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        while(file.hasNextInt())
        {
            int M = file.nextInt();
            int P = file.nextInt();
            int L = file.nextInt();
            int E = file.nextInt();
            int R = file.nextInt();
            int S = file.nextInt();
            int N = file.nextInt();
            for(int i = 0;i<N;i++)
            {
                int newL = M*E;
                M = P/S;
                P = L/R;
                L = newL;
            }
            System.out.println(M);
        }   
    }
}