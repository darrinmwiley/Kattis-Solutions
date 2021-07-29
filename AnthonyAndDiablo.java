

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

public class AnthonyAndDiablo {

    public static void main(String[] args) throws Exception
    {
        new AnthonyAndDiablo().run();
    }

    //square: .0625
    //pi*d/pi*r*r
    //d/r*r

    public void run() throws Exception
    {
        Scanner file = new Scanner(System.in);

        double a = file.nextDouble();
        double b = file.nextDouble();
        double req = 2*Math.PI*Math.sqrt(a/Math.PI);
        if(b >= req)
        		System.out.println("Diablo is happy!");
        else
        		System.out.println("Need more materials!");
    }
}
