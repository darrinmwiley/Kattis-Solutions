import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class BestCompromise {

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();file.nextLine();
        for(int z = 0;z<zz;z++)
        {
            int r = file.nextInt();
            int c = file.nextInt();
            char[][] chars = new char[r][c];
            file.nextLine();
            for(int i = 0;i<r;i++)
            {
                chars[i] = file.nextLine().toCharArray();
            }
            for(int i = 0;i<c;i++)
            {
                int sum =0;
                for(int j = 0;j<r;j++)
                {
                    if(chars[j][i]=='0')
                        sum--;
                    else
                        sum++;
                }
                if(sum>0)
                    System.out.print("1");
                else
                    System.out.print("0");
            }
            System.out.println();
        }
    }
}