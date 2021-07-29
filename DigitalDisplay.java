

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DigitalDisplay {

	BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;
	String index = "0123456789:";
	String[][] strs = new String[][] {
		"+---+,    +,+---+,+---+,+   +,+---+,+---+,+---+,+---+,+---+, ".split(","),
		"|   |,    |,    |,    |,|   |,|    ,|    ,    |,|   |,|   |, ".split(","),
		"|   |,    |,    |,    |,|   |,|    ,|    ,    |,|   |,|   |,o".split(","),
		"+   +,    +,+---+,+---+,+---+,+---+,+---+,    +,+---+,+---+, ".split(","),
		"|   |,    |,|    ,    |,    |,    |,|   |,    |,|   |,    |,o".split(","),
		"|   |,    |,|    ,    |,    |,    |,|   |,    |,|   |,    |, ".split(","),
		"+---+,    +,+---+,+---+,    +,+---+,+---+,    +,+---+,+---+, ".split(",")
	};

    public static void main(String[] args) throws Exception
    {
        new DigitalDisplay().run();
    }

    public void run() throws Exception
    {
       while(true)
       {
    	    String next = file.readLine();
    	    if(next.equals("end")) {
	    	    	System.out.println("end");
	    	    	return;
    	    }
    	    for(int i = 0;i<7;i++)
    	    {
    	    		int xx = 0;
    	    		for(char ch: next.toCharArray())
    	    		{
    	    			System.out.print(strs[i][index.indexOf(ch)]);
    	    			if(xx++ != 4)
    	    				System.out.print("  ");
    	    		}
    	    		System.out.println();
    	    }
    	    System.out.println();
    	    System.out.println();
       }
    }
}
