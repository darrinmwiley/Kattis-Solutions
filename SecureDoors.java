import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class SecureDoors {
    
    int S;
    int L;
    
    public void go() throws IOException {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        TreeSet<String> set = new TreeSet<>();
        for(int i = 0;i<zz;i++)
        {
            if(file.next().equals("entry"))
            {
                String s = file.next();
                System.out.print(s+" entered");
                if(!set.add(s))
                    System.out.print(" (ANOMALY)");
            }else{
                String s = file.next();
                System.out.print(s+" exited");
                if(!set.remove(s))
                    System.out.print(" (ANOMALY)");
            }
            System.out.println();
        }
    }
    
    
    public static void main(String[] args) {
        try {
            new SecureDoors().go();
        } catch (IOException e) {
            
        }
    }
}
