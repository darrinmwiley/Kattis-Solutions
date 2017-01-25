import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class BusNumbers {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        ArrayList<Integer> ints = new ArrayList<Integer>();
        for(int i = 0;i<N;i++)
        {
            ints.add(file.nextInt());
        }
        Collections.sort(ints);
        while(!ints.isEmpty())
        {
            int start = ints.remove(0);
            int next = start;
            while(!ints.isEmpty()&&ints.get(0)-next==1)
                next = ints.remove(0);
            if(next==start)
                System.out.print(start+" ");
            else if(next-start==1)
                System.out.print(start+" "+next+" ");
            else
                System.out.print(start+"-"+next+" ");
        }
        
    }
}