import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;


public class TouchscreenKeyboard {
    char[][] chars;
    public void run()
    {
        Scanner file = new Scanner(System.in);
        chars = new char[][]{"qwertyuiop".toCharArray(),"asdfghjkl".toCharArray(),"zxcvbnm".toCharArray()};
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            String wd = file.next();
            int n = file.nextInt();
            String[] wds = new String[n];
            Comparator<String> comp = new Comparator<String>(){
                @Override
                public int compare(String arg0, String arg1) {
                    int i =dist(wd,arg0)-dist(wd,arg1);
                    if(i==0)
                        return arg0.compareTo(arg1);
                    return i;
                }       
            };
            for(int i = 0;i<n;i++)
                wds[i] = file.next();
            Arrays.sort(wds,comp);
            for(String s:wds)
            {
                System.out.println(s+" "+dist(s,wd));
            }
        }
    }
    
    public int dist(String a, String b)
    {
        int sum = 0;
        for(int i = 0;i<a.length();i++)
        {
            sum+=dist(a.charAt(i),b.charAt(i));
        }
        return sum;
    }
    
    public int dist(char a, char b){
        int r1 = 0;
        int r2 = 0;
        int c1 = 0;
        int c2 = 0;
        for(int r = 0;r<chars.length;r++)
            for(int c= 0;c<chars[r].length;c++)
            {
                if(chars[r][c]==a)
                {
                    r1 = r;
                    c1 = c;
                }
                if(chars[r][c]==b)
                {
                    r2 = r;
                    c2 = c;
                }
            }
        return Math.abs(r1-r2)+Math.abs(c1-c2);
    }
    
    public static void main(String[] args)
    {
        new TouchscreenKeyboard().run();
    }
    
}