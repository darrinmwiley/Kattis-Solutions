import java.util.Scanner;


public class DiceGame {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int g1 = file.nextInt();
        int g2 = file.nextInt();
        int g3 = file.nextInt();
        int g4 = file.nextInt();
        int e1 = file.nextInt();
        int e2 = file.nextInt();
        int e3 = file.nextInt();
        int e4 = file.nextInt();
        int[] g = new int[201];
        int[] e = new int[201];
        for(int a = g1;a<=g2;a++)
            for(int b = g3;b<=g4;b++)
                g[a+b]++;
        for(int a = e1;a<=e2;a++)
            for(int b = e3;b<=e4;b++)
                e[a+b]++;
        long win = 0;
        long lose = 0;
        for(int i = 0;i<g.length;i++)
        {
            for(int j = 0;j<e.length;j++)
                if(j<i)
                    win+=g[i]*e[j];
                else if(j>i)
                    lose+=g[i]*e[j];
        }
        if(win>lose)
            System.out.println("Gunnar");
        if(lose>win)
            System.out.println("Emma");
        if(lose==win)
            System.out.println("Tie");
    }
}
