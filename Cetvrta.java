import java.util.*;

public class Cetvrta{
    
    public static void main(String[] args)
    {
        int x = 0;
        int y = 0;
        Scanner file = new Scanner(System.in);
        for(int i = 0;i<3;i++)
        {
            x^=file.nextInt();
            y^=file.nextInt();
        }
        System.out.println(x+" "+y);
    }
    
}