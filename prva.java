import java.util.Scanner;
import java.util.TreeSet;

public class prva {

    public void run(){
        Scanner file = new Scanner(System.in);
        int R = file.nextInt();
        int C = file.nextInt();
        char[][] chars = new char[R][];
        file.nextLine();
        for(int i = 0;i<R;i++)
            chars[i] = file.nextLine().toCharArray();
        TreeSet<String> set = new TreeSet();
        for(int i = 0;i<chars.length;i++)
            for(int j =0 ;j<chars[i].length;j++)
            {
                if(i==0)
                {
                    String add = getDown(chars,i,j);
                    if(add.length()>1)
                        set.add(add);
                }
                if(j==0)
                {
                    String add = getAcross(chars,i,j);
                    if(add.length()>1)
                        set.add(add);
                }
                if(chars[i][j]=='#')
                {
                    String add1 = getDown(chars,i+1,j);
                    String add2 = getAcross(chars,i,j+1);
                    if(add1.length()>1)
                        set.add(add1);
                    if(add2.length()>1)
                        set.add(add2);
                }
            }
        System.out.println(set.first());
    }

    public String getDown(char[][] chars, int r, int c)
    {
        if(r>=chars.length)
            return "";
        char current = chars[r][c];
        String ret = "";
        while(r<chars.length&&current!='#')
        {
            ret+=chars[r][c];
            if(r!=chars.length-1)
            current = chars[++r][c];
            else
                r++;
        }
        return ret;
    }

    public String getAcross(char[][] chars, int r, int c)
    {
        if(c>=chars[r].length)
            return "";
        char current = chars[r][c];
        String ret = "";
        while(c<chars[r].length&&current!='#')
        {
            ret+=chars[r][c];
            if(c!=chars[0].length-1)
            current = chars[r][++c];
            else
                c++;
        }
        return ret;
    }

    public static void main(String[] args)
    {
       new prva().run();
    }
}
