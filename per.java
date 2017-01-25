import java.util.*;
class per{
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        String line = file.nextLine();
        int c = 0;
        for(int i = 0;i<line.length();i++)
        {
            if(line.charAt(i)!="PER".charAt(i%3))
                c++;
        }
        System.out.println(c);
    }
}