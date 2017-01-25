import java.util.Scanner;


public class SpeedLimit {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        while(zz!=-1)
        {
            int last = 0;
            int total = 0;
            for(int z = 0;z<zz;z++)
            {
                int a = file.nextInt();
                int b = file.nextInt();
                total+=a*(b-last);
                last = b;
            }
            System.out.println(total+" miles");
            zz = file.nextInt();
        }
    }
}