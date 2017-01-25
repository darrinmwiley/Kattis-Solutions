import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

class PrinceAndPrincess{
     public static void main(String[] args)throws Exception
      {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int zz = Integer.parseInt(st.nextToken());
            for(int z = 0;z<zz;z++)
            {
                st = new StringTokenizer(bf.readLine());
                int n = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken())+1;
                int q = Integer.parseInt(st.nextToken())+1;
                int[] map = new int[250*250+1];
                Arrays.fill(map, -1);
                int[] prince = new int[p];
                st = new StringTokenizer(bf.readLine());
                for(int i = 0;i<p;i++)
                {
                    prince[i] = Integer.parseInt(st.nextToken());
                    map[prince[i]] = i;
                }
                ArrayList<Integer> list = new ArrayList<>();
                st = new StringTokenizer(bf.readLine());
                for(int i = 0;i<q;i++)
                {
                    int x = Integer.parseInt(st.nextToken());
                    if(map[x]!=-1)
                        list.add(map[x]);
                }
                Integer[] princess = new Integer[list.size()];
                princess = list.toArray(princess);
                int[] tails = new int[princess.length];
                int len = 1;
                tails[0] = princess[0];
                for(int i = 1;i<princess.length;i++)
                {   
                    if(princess[i]<tails[0])
                    {
                        tails[0] = princess[i];
                    }
                    else if(princess[i]>tails[len-1])
                    {
                        tails[len++] = princess[i];
                    }else{
                        int low = -1;
                        int high = len-1;
                        while(high-low>1)
                        {
                            int mid = low+(high-low)/2;
                            if(tails[mid]>=princess[i])
                                high = mid;
                            else
                                low = mid;
                        }
                        tails[high] = princess[i];
                    }
                }
                System.out.printf("Case %d: %d%n",z+1,len);
            }
      }
     
     
     
}