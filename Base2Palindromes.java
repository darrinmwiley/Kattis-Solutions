import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;


public class Base2Palindromes {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        Comparator<String> comp = new Comparator<String>(){
            
            public int compare(String a, String b)
            {
                if(a.length()==b.length())
                    return a.compareTo(b);
                return a.length()-b.length();
            }
            
        };
        TreeSet<String> set = new TreeSet<String>(comp);
        set.add("1");
        for(int i = 1;i<N/2+1;i++)
        {
            String bin = Integer.toBinaryString(i);
            String rev = new StringBuffer(bin).reverse().toString();
            set.add(bin+rev);
            set.add(bin+"0"+rev);
            set.add(bin+"1"+rev);
        }
        //System.out.println(set);
        ArrayList<String> list = new ArrayList<String>(set);
        System.out.println(Integer.parseInt(list.get(N-1),2));
    }
}