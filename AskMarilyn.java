

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

public class AskMarilyn {

    public static void main(String[] args) throws Exception
    {
        new AskMarilyn().run();
    }

    public String randLet()
    {
    		String[] strs = new String[]{"A","B","C"};
    		return strs[(int)(Math.random()*3)];
    }

    public String other(String a, String b)
    {
    		TreeSet<String> set = new TreeSet<String>();
    		set.add("A");
    		set.add("B");
    		set.add("C");
    		set.remove(a);
    		set.remove(b);
    		return set.first();
    }

    public void run() throws Exception
    {
        Scanner file = new Scanner(System.in);

        for(int i = 0;i<1000;i++)
        {
        		String choice = randLet();
        		System.out.println(choice);
        		System.out.flush();
        		String let = file.next();
        		int x = file.nextInt();
        		if(x == 1)
        		{
        			System.out.println(let);
        		}else {
        			System.out.println(other(choice, let));
        		}
        		System.out.flush();
        		file.next();
        		file.next();
        }
    }
}
