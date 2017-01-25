import java.util.Scanner;
import java.util.TreeMap;


public class ACMContestScoring {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        String next = file.nextLine();
        int score = 0;
        int solved = 0;
        TreeMap<Character,Integer> wrong = new TreeMap<Character,Integer>();
        while(!next.equals("-1"))
        {
            String[] strs = next.split(" ");
            if(strs[2].equals("wrong"))
            {
                char ch = strs[1].charAt(0);
                if(!wrong.containsKey(ch))
                    wrong.put(ch,0);
                wrong.put(ch,wrong.get(ch)+1);
            }else{
                solved++;
                char ch = strs[1].charAt(0);
                if(!wrong.containsKey(ch))
                    wrong.put(ch,0);
                score+=Integer.parseInt(strs[0])+wrong.get(ch)*20;
            }
            next = file.nextLine();
        }
        System.out.println(solved+" "+score);
    }
}