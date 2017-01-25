import java.util.Scanner;
import java.util.TreeMap;


public class Volim {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int start = file.nextInt()-1;
        int N = file.nextInt();
        int time = 0;
        for(int i = 0;i<N;i++)
        {
            int M = file.nextInt();
            String ans = file.next();
            time+=M;
            if(time>=210){
                System.out.println(start+1);
                return;
            }
            if(ans.equals("T"))
                start = (start+1)%8;
        }
    }
}