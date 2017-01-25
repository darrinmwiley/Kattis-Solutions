import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FairDivision {

    public static void main(String[] args) throws NumberFormatException, IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int zz = Integer.parseInt(br.readLine());
        for(int z = 0;z<zz;z++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int[] ints = new int[n];
            int[] indices = new int[n];
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            int broke = 0;
            for(int i = 0;i<n;i++)
            {
                sum += ints[i] = Integer.parseInt(st.nextToken());
                if(ints[i]==0)
                    broke++;
                indices[i] = i;
            }
            if(sum<p)
            {
                System.out.println("IMPOSSIBLE");
                continue;
            }
            for(int i = 1;i<ints.length;i++)
            {
                int j = i;
                while(j>0)
                {
                    if(ints[j-1]<ints[j])
                    {
                        int save = ints[j];
                        ints[j] = ints[j-1];
                        ints[j-1] = save;
                        int save2 = indices[j];
                        indices[j] = indices[j-1];
                        indices[j-1] = save2;
                        j--;
                    }else
                        break;
                }
            }
            int[] payments = new int[n];
            //System.out.println(Arrays.toString(ints));
            //System.out.println(Arrays.toString(indices));
            int debt = p;
            while(debt!=0)
            {
                int toPay = debt/(n-broke);
                if(toPay == 0)
                {
                    int q = debt;
                    for(int i = 0;i<q;i++)
                    {
                        payments[indices[i]]++;
                        debt--;
                        ints[i]--;
                    }
                }else{
                    for(int i = 0;i<n;i++)
                    {
                        if(ints[i]==0)
                            continue;
                        if(ints[i]<=toPay)
                        {
                            payments[indices[i]]+=ints[i];
                            debt-=ints[i];
                            ints[i] = 0;
                            broke++;
                        }else{
                            payments[indices[i]]+=toPay;
                            debt -= toPay;
                            ints[i] -= toPay;
                        }
                    }   
                }
            }
            for(int i = 0;i<payments.length;i++)
            {
                System.out.print(payments[i]+" ");
            }
            System.out.println();
        }
        out.close();
    }
    
}