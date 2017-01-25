import java.util.Scanner;

public class Aaah {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        System.out.println(file.next().length()>=file.next().length()?"go":"no");
    }
    
    public static void main(String[] args)
    {
        new Aaah().run();
    }
    
}