import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class PervasiveHeartMonitor {

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(System.in);
        while(file.hasNextLine())
        {
            String line = file.nextLine();
            StringTokenizer st = new StringTokenizer(line);
            double ave = 0;
            int N = 0;
            String name = "";
            while(st.hasMoreTokens())
            {
                String str = st.nextToken();
                if(str.matches("[A-Za-z]+"))
                    name+=str+" ";
                else{
                    ave+=Double.parseDouble(str);
                    N++;
                }
            }
            System.out.println(ave/N+" "+name);
        }
    }
}