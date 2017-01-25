import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class BaconEggsAndSpam {
    
    int S;
    int L;
    
    public void go() throws IOException {
        Scanner file = new Scanner(System.in);
        while(file.hasNext())
        {
            int N = file.nextInt();
            file.nextLine();
            if(N==0) return;
            TreeMap<String,TreeSet<String>> map = new TreeMap();
            for(int i = 0;i<N;i++)
            {
                
                String[] line = file.nextLine().split(" ");
                for(int j = 1;j<line.length;j++)
                {
                    if(!map.containsKey(line[j]))
                        map.put(line[j],new  TreeSet<String>());
                    map.get(line[j]).add(line[0]);
                }
            }
            for(String s:map.keySet())
            {
                TreeSet<String> set = map.get(s);
                System.out.print(s+" ");
                for(String x:set)
                    System.out.print(x+" ");
                System.out.println();
            }
            System.out.println();
        }
    }
    
    
    public static void main(String[] args) {
        try {
            new BaconEggsAndSpam().go();
        } catch (IOException e) {
            
        }
    }
}
