import java.util.Scanner;

public class needforspeed {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int T = file.nextInt();
        double [] miles = new double[N];
        double [] readings = new double[N];
        double min = Integer.MAX_VALUE;
        for(int i = 0;i<N;i++){
            miles[i] = file.nextInt();
            readings[i] = file.nextInt();
            min = Math.min(min,readings[i]);
        }
        double L = -min;
        double R = 1000000000;
        double M = (L+R)/2;
        while(R-L>=.000000001)
        {
            double sum = 0;
            for(int i = 0;i<N;i++)
                sum+=miles[i]/(M+readings[i]);
            if(sum<=T)
                R = M;
            else
                L = M;
            M = (L+R)/2;
        }
        System.out.println(M);
    }


}
