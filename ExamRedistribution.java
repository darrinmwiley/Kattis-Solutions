import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.System.*;

public class ExamRedistribution {

    public void go() {
        Scanner in = new Scanner(System.in);
        int numRooms = in.nextInt();
        int[][] rooms = new int[numRooms][2];
        for (int i = 0; i < numRooms; i++) {
            rooms[i][0] = in.nextInt();
            rooms[i][1] = i+1;
        }
        Arrays.sort(rooms, (one, two) -> {
            return two[0] - one[0];
        });
        int sum = 0;
        for (int i = 1; i < numRooms; i++) {
            sum += rooms[i][0];
        }
        if (rooms[0][0] > sum) {
            System.out.println("impossible");
        } else {
            for (int i = 0; i < numRooms; i++) {
                if (i != 0) {
                    out.print(" ");
                }
                out.print(rooms[i][1]);
            }
        }
    }
    
    public static void main(String[] args) {
        new ExamRedistribution().go();
    }
}
