import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class SettlersOfCatan {
    HashMap<Integer,Point> map;
    HashMap<Point,Integer> map2;
    int[] used = new int[]{0,1,2,2,1,1};
    int[] tiles;
    public void run()
    {
        Scanner file = new Scanner(System.in);
        tiles = new int[10000];
        map = new HashMap<Integer,Point>();
        map2 = new HashMap<Point,Integer>();
        int[] start = new int[]{1,2,3,4,5,2,3};
        int[] px = new int[]{0,1,0,-1,-1,0,1};
        int[] py = new int[]{0,-1,-1,0,1,1,0};
        for(int i = 0;i<7;i++)
        {
            Point p = new Point(px[i],py[i]);
            map.put(i,p);
            map2.put(p,i);
            tiles[i] = start[i];
        }
        int[][] d = new int[][]{{0,-1,-1,0,1,1},{-1,0,1,1,0,-1}};
        int step = 1;
        boolean ringComplete = true;
        int rep = 0;
        int dir = 0;
        int x = 1;
        int y = 0;
        for(int i = 7;i<10000;i++)
        {
            if(ringComplete)
            {
                x++;
                y--;
                tiles[i] = chooseTile(x,y);
                Point p = new Point(x,y);
                map.put(i,p);
                map2.put(p,i);
                step++;
                rep = 0;
                dir = 0;
                ringComplete = false;
            }
            else{
                rep++;
                x+=d[0][dir];
                y+=d[1][dir];
                tiles[i] = chooseTile(x,y);
                Point p = new Point(x,y);
                map.put(i,p);
                map2.put(p,i);
                if(dir==0&&rep==step-1||rep==step)
                {
                    dir++;
                    rep = 0;
                    if(dir==d[0].length)
                    {
                        ringComplete=true;
                    }
                }
            }
            
        }
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            System.out.println(tiles[file.nextInt()-1]);
        }
    }
    
    public int chooseTile(int x, int y)
    {
        int[][] d = new int[][]{{0,-1,-1,0,1,1},{-1,0,1,1,0,-1}};
        boolean[] available = new boolean[6];
        Arrays.fill(available,true);
        for(int i = 0;i<6;i++)
        {
            int nx = d[0][i]+x;
            int ny = d[1][i]+y;
            if(map2.containsKey(new Point(nx,ny)))
            {
                int index = map2.get(new Point(nx,ny));
                available[tiles[index]] = false;
            }
            
        }
        int min = Integer.MAX_VALUE;
        int current = 0;
        for(int i = 1;i<available.length;i++)
        {
            if(available[i]&&used[i]<min)
            {
                min = used[i];
                current = i;
            }
        }
        used[current]++;
        return current;
    }
    
    public static void main(String[] args)
    {
        new SettlersOfCatan().run();
    }
}