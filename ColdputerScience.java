import java.util.Scanner;


public class ColdputerScience {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        int n = 0;
        for(int z = 0;z<zz;z++)
        {
            if(file.nextInt()<0)
                n++;
        }
        System.out.println(n);
    }
}