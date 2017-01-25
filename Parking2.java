import java.util.Scanner;

public class Parking2 {
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            int N = file.nextInt();
            int min = 100;
            int max = 0;
            for(int i = 0;i<N;i++)
            {
                int q = file.nextInt();
                min = Math.min(q,min);
                max = Math.max(q,max);
            }
            System.out.println((max-min)*2);
        }
    }
}
