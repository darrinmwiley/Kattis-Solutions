import java.util.ArrayList;
import java.util.Scanner;


public class Preludes {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int c = 0;
    loop:
        while(file.hasNext())
        {
            String line = file.nextLine();
            String[] rep = "A# Bb C# Db D# Eb F# Gb G# Ab".split(" ");
            for(int i = 0;i<rep.length;i++)
            {
                if(line.contains(rep[i]))
                {
                    if(i%2==0)
                        line = line.replace(rep[i],rep[i+1]);
                    else
                        line = line.replace(rep[i],rep[i-1]);
                    System.out.printf("Case %d: %s%n",++c,line);
                    continue loop;
                }
            }
            System.out.printf("Case %d: UNIQUE%n",++c);
        }
    }
}