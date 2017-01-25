import java.util.Scanner;
import java.util.TreeSet;


public class PizzaHawaii {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            TreeSet<String> sols = new TreeSet<String>();
            int p = file.nextInt();
            String[][] eng = new String[p][];
            String[][] strs = new String[p][];
            for(int i = 0;i<p;i++)
            {
                file.next();
                int n = file.nextInt(); 
                strs[i] = new String[n];
                for(int j = 0;j<n;j++)
                    strs[i][j] = file.next();
                int m = file.nextInt();
                eng[i] = new String[m];
                for(int j = 0;j<m;j++)
                    eng[i][j] = file.next();
            }
            for(int i = 0;i<strs.length;i++)
            {
                for(int j = 0;j<strs[i].length;j++)
                {
                    loop:
                    for(int k = 0;k<eng[i].length;k++)
                    {
                        String a = strs[i][j];
                        String b = eng[i][k];
                        for(int l = 0;l<p;l++)
                        {
                            if(in(a,strs[l])!=in(b,eng[l]))
                            {
                                continue loop;
                                
                            }
                                
                        }
                        sols.add("("+a+", "+b+")");
                    }
                }
            }
            for(String s:sols)
                System.out.println(s);
            System.out.println();
        }
    }
    
    public boolean in(String st, String[] strs)
    {
        for(String s:strs)
            if(s.equals(st))
                return true;
        return false;
    }
    
    public static void main(String[] args)
    {
        new PizzaHawaii().run();
    }
    
}