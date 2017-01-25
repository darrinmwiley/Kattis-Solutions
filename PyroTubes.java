import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PyroTubes {
    
    public void run()
    {   
        Scanner file = new Scanner(System.in);
        ArrayList<Integer> ints = new ArrayList<Integer>();
        boolean[] allowed = new boolean[250001];
        while(true)
        {
            int x = file.nextInt();
            if(x==-1)
                break;
            ints.add(x);
            allowed[x] = true;
        }
        for(int i:ints)
        {
            ArrayList<Integer> poss = getPoss(i);
            int ans = 0;
            for(int j:poss)
            {
                if(allowed[j])
                    ans++;
            }
            System.out.println(i+":"+ans);
        }
    }
    
    public ArrayList<Integer> getPoss(int n)
    {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for(int i = 0;i<21;i++)
        {
            for(int j = i;j<21;j++)
            {
                int mask = 1<<i|1<<j;
                int next = n^mask;
                if(next>n&&next<=250000)
                    ret.add(next);
            }
        }
        return ret;
    }
    
    public static void main(String[] args)
    {
        new PyroTubes().run();
    }
    
}