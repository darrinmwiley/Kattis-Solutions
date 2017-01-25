import java.util.ArrayList;
import java.util.Scanner;


public class Timebomb {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        String[] a = "***,  *,***,***,* *,***,***,***,***,***".split(",");
        String[] b = "* *,  *,  *,  *,* *,*  ,*  ,  *,* *,* *".split(",");
        String[] c = "* *,  *,***,***,***,***,***,  *,***,***".split(",");
        String[] d = "* *,  *,*  ,  *,  *,  *,* *,  *,* *,  *".split(",");
        String[] e = "***,  *,***,***,  *,***,***,  *,***,***".split(",");
        String[] strs = new String[]{file.nextLine(),file.nextLine(),file.nextLine(),file.nextLine(),file.nextLine()};
        String num = "";
        while(!strs[0].isEmpty())
        {
            
            String f = strs[0].substring(0,3);
            String g = strs[1].substring(0,3);
            String h = strs[2].substring(0,3);
            String i = strs[3].substring(0,3);
            String j = strs[4].substring(0,3);
            for(int k = 0;k<10;k++)
            {
                if(f.equals(a[k])&&g.equals(b[k])&&h.equals(c[k])&&i.equals(d[k])&&j.equals(e[k]))
                    num+=k;
            }
            strs[0] = strs[0].substring(3);
            strs[1] = strs[1].substring(3);
            strs[2] = strs[2].substring(3);
            strs[3] = strs[3].substring(3);
            strs[4] = strs[4].substring(3);
            if(!strs[0].isEmpty())
            {
                strs[0] = strs[0].substring(1);
                strs[1] = strs[1].substring(1);
                strs[2] = strs[2].substring(1);
                strs[3] = strs[3].substring(1);
                strs[4] = strs[4].substring(1);
            }
        }
        System.out.println(Integer.parseInt(num)%6==0?"BEER!!":"BOOM!!");
    }
}