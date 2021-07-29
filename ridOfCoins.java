import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class ridOfCoins {

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(System.in);      
        int N = file.nextInt();
        int[] v = new int[4];
        for(int i = 0;i<v.length;i++)
            v[i] = file.nextInt();
        v[0] = Math.min(v[0],N);
        v[1] = Math.min(v[1],N/5);
        v[2] = Math.min(v[2],N/10);
        v[3] = Math.min(v[3],N/25);
        int max = 0;    
        for(int p = 0;p<25;p++)
            for(int n = 0;n<5;n++)
                for(int d = 0;d<5;d++)
                {
                    int numPennies = Math.max(0,v[0]-p);
                    int numNickels = Math.max(0,v[1]-n);
                    int numDimes = Math.max(0,v[2]-d);
                    int total = numPennies+numNickels*5+numDimes*10;
                    if(total>N)
                        continue;
                    int need = N-total;
                    
                    if(need%25==0&&need/25<=v[3])
                        max = Math.max(numPennies+numNickels+numDimes+need/25,max);
                }
        System.out.println(max==0?"Impossible":max);            
    }
}