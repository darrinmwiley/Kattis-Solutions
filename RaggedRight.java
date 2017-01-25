import java.util.ArrayList;
import java.util.Scanner;


public class RaggedRight {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int max = 0;
        ArrayList<Integer> lengths = new ArrayList<Integer>();
        while(file.hasNext())
        {
            lengths.add(file.nextLine().length());
            max = Math.max(max,lengths.get(lengths.size()-1));
        }
        int penalty = 0;
        for(int i = 0;i<lengths.size()-1;i++)
            penalty+=Math.pow((lengths.get(i)-max),2);
        System.out.println(penalty);
    }
}