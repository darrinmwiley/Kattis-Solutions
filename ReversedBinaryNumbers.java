import java.util.Arrays;
import java.util.Scanner;


public class ReversedBinaryNumbers {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        System.out.println(Integer.parseInt(new StringBuffer(Integer.toBinaryString(file.nextInt())).reverse().toString(),2));
    }
}