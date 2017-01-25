import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;


public class FunctionalFun {

    public static void main(String[] args) throws IOException
    {
        Scanner file = new Scanner(System.in);
        while(file.hasNextLine())
        {
            String[] domain = file.nextLine().split(" ");
            String[] codomain = file.nextLine().split(" ");
            TreeMap<String,Integer> dom = new TreeMap<String,Integer>();
            TreeMap<String,Integer> codom = new TreeMap<String,Integer>();
            for(int i = 1;i<domain.length;i++)
                dom.put(domain[i],i-1);
            for(int i = 1;i<codomain.length;i++)
                codom.put(codomain[i],i-1);
            int N = file.nextInt();file.nextLine();
            int[] pointing = new int[domain.length-1];
            int[] pointed = new int[codomain.length-1];
            for(int i = 0;i<N;i++)
            {
                String[] line = file.nextLine().split(" -> ");
                pointing[dom.get(line[0])]++;
                pointed[codom.get(line[1])]++;
            }
            boolean inj = true;
            boolean surj = true;
            boolean func = true;
            for(int i:pointing)
            {
                if(i>1)
                    func = false;
            }
            for(int i:pointed)
            {
                if(i==0)
                    surj = false;
                if(i>1)
                    inj = false;
            }
            if(func==false)
            {
                System.out.println("not a function");
            }else if(inj&&surj)
            {
                System.out.println("bijective");
            }else if(inj)
            {
                System.out.println("injective");
            }else if(surj)
            {
                System.out.println("surjective");
            }else
                System.out.println("neither injective nor surjective");
        }
    }
}