import java.util.Scanner;
import java.util.TreeMap;


public class pet {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int best = 0;
        int score = 0;
        for(int i =1 ;i<=5;i++)
        {
            int sc = 0;
            for(int j = 0;j<4;j++)
            {
                sc+=file.nextInt();
            }
            if(sc>score)
            {
                best = i;
                score = sc;
            }
        }
        System.out.println(best+" "+score);
    }
}