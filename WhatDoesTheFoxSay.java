import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class WhatDoesTheFoxSay {
    
    int S;
    int L;
    
    public void go() throws IOException {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();file.nextLine();
        for(int z = 0;z<zz;z++)
        {
            String ans = file.nextLine();
            while(true)
            {
                String next = file.nextLine();
                if(next.equals("what does the fox say?"))
                {
                    System.out.println(ans);
                    break;
                }else{
                    int oldLen = ans.length();
                    while(true)
                    {
                        ans = ans.replaceAll("( |^)"+next.split(" ")[2]+"( |$)"," ").replaceAll(" +", " ").trim();
                        if(ans.length()==oldLen)
                            break;
                        oldLen = ans.length();
                    }
                    }
            }
        }
    }
    
    
    public static void main(String[] args) {
        try {
            new WhatDoesTheFoxSay().go();
        } catch (IOException e) {
            
        }
    }
}