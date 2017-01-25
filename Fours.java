import java.util.ArrayList;
import java.util.Scanner;


public class Fours {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
    loop:
        for(int z = 0;z<zz;z++)
        {
            int n = file.nextInt();
            char[] s =  "+-*/".toCharArray();
            for(int i = 0;i<4;i++)
                for(int j = 0;j<4;j++)
                    for(int k = 0;k<4;k++)
                        if(eval("4 "+s[i]+" 4 "+s[j]+" 4 "+s[k]+" 4")==n)
                        {
                            System.out.println("4 "+s[i]+" 4 "+s[j]+" 4 "+s[k]+" 4 = "+n);
                            continue loop;
                        }
            System.out.println("no solution");                      
        }
    }
    
    public int eval(String s)
    {
        String[] strs = s.split(" ");
        ArrayList<String> list = new ArrayList<String>();
        for(String q:strs)
            list.add(q);
        for(int i = 0;i<list.size();i++)
        {
            if(list.get(i).equals("*"))
            {
                int a = Integer.parseInt(list.get(i-1));
                int b = Integer.parseInt(list.get(i+1));
                list.remove(i-1);
                list.remove(i-1);
                list.set(i-1,a*b+"");
                i-=2;
            }
            else if(list.get(i).equals("/"))
            {
                int a = Integer.parseInt(list.get(i-1));
                int b = Integer.parseInt(list.get(i+1));
                list.remove(i-1);
                list.remove(i-1);
                list.set(i-1,a/b+"");
                i-=2;
            }
        }
        for(int i = 0;i<list.size();i++)
        {
            if(list.get(i).equals("+"))
            {
                int a = Integer.parseInt(list.get(i-1));
                int b = Integer.parseInt(list.get(i+1));
                list.remove(i-1);
                list.remove(i-1);
                list.set(i-1,a+b+"");
                i-=2;
            }
            else if(list.get(i).equals("-"))
            {
                int a = Integer.parseInt(list.get(i-1));
                int b = Integer.parseInt(list.get(i+1));
                list.remove(i-1);
                list.remove(i-1);
                list.set(i-1,a-b+"");
                i-=2;
            }
        }
        return Integer.parseInt(list.get(0));
    }
    
    public static void main(String[] args)
    {
        new Fours().run();
    }
    
}