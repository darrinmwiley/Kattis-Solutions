import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;


public class IdentifyingMapTiles {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        String key = file.next();
        int unit = 1<<key.length()-1;
        int x = 0;
        int y = 0;
        for(int i = 0;i<key.length();i++)
        {
            char ch = key.charAt(i);
            switch(ch)
            {
            case '1':x+=unit;break;
            case '2':y+=unit;break;
            case '3':x+=unit;y+=unit;
            }
            unit/=2;
        }
        System.out.println(key.length()+" "+x+" "+y);
    }
}