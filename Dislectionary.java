import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Dislectionary {

    public static void main(String[] args) throws FileNotFoundException
    {
        new Dislectionary().run();
    }

    public void run() throws FileNotFoundException
    {
        Scanner file = new Scanner(System.in);
        while(file.hasNext())
        {
            ArrayList<String> list = new ArrayList<String>();
            while(file.hasNextLine())
            {
                String line = file.nextLine();
                if(line.isEmpty())
                    break;
                else
                    list.add(line);
            }
            Comparator<String> comp = new Comparator<String>(){

                @Override
                public int compare(String arg0, String arg1) {
                    String rev1 = new StringBuffer(arg0).reverse().toString();
                    String rev2 = new StringBuffer(arg1).reverse().toString();
                    return rev1.compareTo(rev2);
                }

            };
            int max = 0;
            for(String s:list)
                max = Math.max(max,s.length());
            Collections.sort(list,comp);
            for(String s:list)
                System.out.printf("%"+max+"s%n",s);
            if(file.hasNext())
                System.out.println();
        }
    }
}
