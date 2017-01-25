import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.System.*;

public class CityDestruction {

    public void go() {
        Scanner file = new Scanner(System.in);
        long zzz = file.nextLong();
        for (long zz = 0; zz < zzz; zz++) {
            int N = file.nextInt();
            int D = file.nextInt();
            long[] h = new long[N];
            long[] e = new long[N];
            for(int i = 0;i<N;i++)
                h[i] = file.nextLong();
            for(int i = 0;i<N;i++)
                e[i] = file.nextLong();
            if(N==1)
            {
                System.out.println((h[0]+D-1)/D);
                continue;
            }
            long[] min = new long[N];
            long[] minLE = new long[N];
            min[min.length-1] = Math.max(0,h[min.length-1]+D-1)/D;
            minLE[min.length-1] = Math.max(0,h[min.length-1]-e[min.length-2]+D-1)/D;
            for(int x = min.length-2;x>=0;x--)
            {
                min[x] = Math.min((h[x]+D-1)/D+minLE[x+1],min[x+1]+Math.max(0,h[x]-e[x+1]+D-1)/D);
                if(x!=0)
                    minLE[x] = Math.min((Math.max(0,h[x]-e[x-1])+D-1)/D+minLE[x+1],(Math.max(0,h[x]-e[x-1]-e[x+1])+D-1)/D+min[x+1]);
            }
            System.out.println(min[0]);
        }
    }
    
    public static void main(String[] args) throws IOException {
        new CityDestruction().go();
    }
}