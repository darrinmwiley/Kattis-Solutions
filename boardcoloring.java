import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class boardcoloring {

    char[][] chars;
    //X will equal wildcard
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int r = file.nextInt();
        int c = file.nextInt();
        chars = new char[r][];
        for(int i = 0;i<r;i++)
            chars[i] = file.next().toCharArray();
        boolean changed = false;
        do{
            changed = false;
            for(int i = 0;i<chars.length-2;i++)
                for(int j = 0;j<chars[i].length-2;j++)
                {
                    if(homogenous(chars,i,j)){
                        fill(chars,i,j,'X');
                        changed = true;
                    }
                }
        }while(changed);
        boolean ans = test();
        if(ans)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    public boolean test()
    {
        for(char[] ch:chars)
            for(char c:ch)
                if(c!='W'&&c!='X')
                    return false;
        return true;
    }

    public static void main(String[] args)
    {
        new boardcoloring().run();
    }

    public boolean homogenous(char[][] chars, int tlr, int tlc)
    {
        char encountered = 'X';
        for(int r = 0;r<3;r++)
            for(int c = 0;c<3;c++)
            {
                char ch = chars[tlr+r][tlc+c];
                if(ch=='W')
                    return false;
                if(encountered=='X'&&ch!='X')
                    encountered = ch;
                if(ch!='X'&&ch!=encountered)
                    return false;
            }
        return encountered!='X';
    }

    public boolean wild(char[][] chars, int tlr, int tlc)
    {
        boolean x = false;
        for(int r = 0;r<3;r++)
            for(int c = 0;c<3;c++)
                if(chars[tlr+r][tlc+c]!='X'&&chars[tlr+r][tlc+c]!='W')
                    return false;
                else if (chars[tlr+r][tlc+c]=='X')
                    x = true;
        return x;
    }

    public boolean notContains(int tlr, int tlc, String exclude)
    {
        for(int r = 0;r<3;r++)
            for(int c = 0;c<3;c++)
                if(exclude.contains(""+chars[tlr+r][tlc+c]))
                    return false;
        return true;
    }

    public void fill(char[][] chars, int tlr, int tlc, char ch)
    {
        for(int r = 0;r<3;r++)
            for(int c = 0;c<3;c++)
                chars[tlr+r][tlc+c] = ch;
    }

    public boolean equals(char[][] ch)
    {
        for(int i = 0;i<chars.length;i++)
            for(int j = 0;j<chars[i].length;j++)
                if(chars[i][j]!=ch[i][j])
                    return false;
        return true;
    }
}
