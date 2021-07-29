import java.util.ArrayList;
import java.util.Scanner;

public class EightQueens {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        ArrayList<Integer> x = new ArrayList<Integer>();
        ArrayList<Integer> y = new ArrayList<Integer>();
        for(int i = 0;i<8;i++)
        {
            char[] chars = file.next().toCharArray();
            for(int j = 0;j<8;j++)
            {
                if(chars[j]=='*')
                {
                    x.add(i);
                    y.add(j);
                }
            }
        }
        if(x.size()!=8)
            System.out.println("invalid");
        else{
            for(int i = 0;i<x.size();i++)
            {
                int xpos = x.get(i);
                int ypos = y.get(i);
                for(int j = i+1;j<x.size();j++)
                {
                    int x2 = x.get(j);
                    int y2 = y.get(j);
                    if(xpos==x2||ypos==y2||Math.abs(xpos-x2)==Math.abs(ypos-y2))
                    {
                        System.out.println("invalid");
                        return;
                    }
                }
            }
            System.out.println("valid");
        }
        
    }
    
    public static void main(String[] args)
    {
        new EightQueens().run();
    }
}