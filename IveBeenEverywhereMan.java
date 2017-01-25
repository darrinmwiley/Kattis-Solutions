import java.util.Scanner;
import java.util.TreeSet;


public class IveBeenEverywhereMan {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            TreeSet<String> set = new TreeSet<String>();
            int q = file.nextInt();
            for(int x = 0;x<q;x++)
            {
                set.add(file.next());
            }
            System.out.println(set.size());
        }
    }
}
