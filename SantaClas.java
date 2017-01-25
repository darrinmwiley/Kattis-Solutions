import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class SantaClas {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int h = file.nextInt();
        int v = file.nextInt();
        if(v<=180)
            System.out.println("safe");
        else
            System.out.println(Math.abs((int)(h/Math.sin(Math.toRadians(v)))));
    }
}