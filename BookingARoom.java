import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class BookingARoom {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        boolean[] rooms = new boolean[N+1];
        Arrays.fill(rooms,true);
        int M = file.nextInt();
        for(int i = 0;i<M;i++)
        {
            rooms[file.nextInt()]=false;
        }
        if(M==N)
            System.out.println("too late");
        else{
            for(int i = 1;i<=N;i++){
                if(rooms[i])
                {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}