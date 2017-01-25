import java.util.ArrayList;
import java.util.Scanner;


public class WordCloud{
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int cas = 1;
        while(true)
        {
            int wid = file.nextInt();
            int n = file.nextInt();
            if(wid==0&&n==0)
                return;
            String[] strs = new String[n];
            int[] ints = new int[n];
            int max = 0;
            for(int i = 0;i<n;i++)
            {
                strs[i] = file.next();
                ints[i] = file.nextInt();
                max = Math.max(ints[i],max);
            }
            int ht = 0;
            int currentWidth = 0;
            int currentHeight = 0;
            for(int i = 0;i<strs.length;i++)
            {
                int font = getFont(ints[i],max);
                int width = getWidth(strs[i],font);
                if(currentWidth+width>wid)
                {
                    ht+=currentHeight;
                    currentWidth = width+10;
                    currentHeight = font;
                }else{
                    currentHeight = Math.max(font,currentHeight);
                    currentWidth+=width+10;
                }
            }
            System.out.println("CLOUD "+cas+++": "+ (ht+currentHeight));
        }
    }
    
    public int getFont(int current, int max)
    {
        return (int)Math.ceil(8+40.0*(current-4)/(max-4));
    }
    
    public int getWidth(String wd, int font)
    {
        return (int)Math.ceil(9.0*font*wd.length()/16);
    }
    
    public static void main(String[] args)
    {
        new WordCloud().run();
    }
    
}
