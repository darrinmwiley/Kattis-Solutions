import java.util.Scanner;
import java.util.TreeMap;


public class FalseSenseOfSecurity {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        String[] strs = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. --.- .-. ... - ..- ...- .-- -..- -.-- --.. ..-- .-.- ---. ----".split(" ");
        String alph = "abcdefghijklmnopqrstuvwxyz_,.?".toUpperCase();
        while(file.hasNext())
        {
            String line = file.nextLine();
            int[] numbers = new int[line.length()];
            String morse = "";
            for(int i = 0;i<line.length();i++)
            {
                String add = strs[alph.indexOf(line.charAt(i))];
                morse+=add;
                numbers[i] = add.length();
            }
            String ans = "";
            for(int i = numbers.length-1;i>-1;i--)
            {
                String snippet = morse.substring(0,numbers[i]);
                morse = morse.substring(numbers[i]);
                for(int j =0 ;j<strs.length;j++)
                {
                    String s = strs[j];
                    if(s.equals(snippet))
                        ans+=alph.charAt(j);
                }
            }
            System.out.println(ans);
        }
    }
}