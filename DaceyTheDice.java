import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.System.*;

public class DaceyTheDice {

    public void go() {
        Scanner file = new Scanner(System.in);
        int zzz = file.nextInt();
    loop:
        for (int zz = 0; zz < zzz; zz++) {
            int N = file.nextInt();
            char[][] chars = new char[N][N];
            boolean[][][] vis = new boolean[24][N][N];
            int[] state = new int[]{1,6,5,2,4,3};
            file.nextLine();
            for(int i = 0;i<chars.length;i++)
            {
                chars[i] = file.nextLine().toCharArray();
            }
            int[] start = find(chars,'S');
            int[] end = find(chars,'H');
            state initial = new state(state,start[0],start[1]);
            Queue<state> que = new LinkedList<state>();
            que.add(initial);
            vis[getState(state)][start[0]][start[1]] = true;
            while(!que.isEmpty())
            {
                state st = que.poll();
                int Q = getState(st.state);
                if(st.r==end[0]&&st.c==end[1]&&Q>=16&&Q<=19)
                {
                    System.out.println("Yes");
                    continue loop;
                }
                int r =st.r;
                int c = st.c;
                if(val(r-1,c,chars))
                {
                    int[] newstate = up(st.state);
                    int num = getState(newstate);
                    if(chars[r-1][c]!='*'&&!vis[num][r-1][c])
                    {
                        vis[num][r-1][c] = true;
                        que.add(new state(newstate,r-1,c));
                    }
                }
                if(val(r+1,c,chars))
                {
                    int[] newstate = down(st.state);
                    int num = getState(newstate);
                    if(chars[r+1][c]!='*'&&!vis[num][r+1][c])
                    {
                        vis[num][r+1][c] = true;
                        que.add(new state(newstate,r+1,c));
                    }
                }
                if(val(r,c-1,chars))
                {
                    int[] newstate = left(st.state);
                    int num = getState(newstate);
                    if(chars[r][c-1]!='*'&&!vis[num][r][c-1])
                    {
                        vis[num][r][c-1] = true;
                        que.add(new state(newstate,r,c-1));
                    }
                }
                if(val(r,c+1,chars))
                {
                    int[] newstate = right(st.state);
                    int num = getState(newstate);
                    if(chars[r][c+1]!='*'&&!vis[num][r][c+1])
                    {
                        vis[num][r][c+1] = true;
                        que.add(new state(newstate,r,c+1));
                    }
                }
            }
            System.out.println("No");
        }
    }
    
    public boolean val(int r, int c, char[][] chars)
    {
        return Math.min(r,c)>=0&&Math.max(r,c)<chars.length;
    }
    
    public int[] find(char[][] chars, char ch)
    {
        for(int i = 0;i<chars.length;i++)
        {
            for(int j = 0;j<chars.length;j++)
            {
                if(chars[i][j]==ch)
                    return new int[]{i,j};
            }
        }
        return null;
    }
    
    public int getState(int[] st)
    {
        
        int bottom = st[1];
        int right = st[3];
        int[] bottoms = new int[]{1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6};
        int[] tops = new int[]{2,3,4,5,1,3,4,6,1,2,5,6,1,2,5,6,1,3,4,6,2,3,4,5};
        for(int i = 0;i<bottoms.length;i++)
        {
            if(bottoms[i]==bottom&&tops[i]==right)
                return i;
        }
        return -1;
    }
    
    public int[] right(int[] st)
    {
        return new int[]{st[2],st[3],st[1],st[0],st[4],st[5]};
    }
    
    public int[] left(int[] st)
    {
        return new int[]{st[3],st[2],st[0],st[1],st[4],st[5]};
    }
    
    public int[] up(int[] st)
    {
        return new int[]{st[5],st[4],st[2],st[3],st[0],st[1]};
    }
    
    public int[] down(int[] st)
    {
        return new int[]{st[4],st[5],st[2],st[3],st[1],st[0]};
    }
    
    private class state
    {
        int[] state;
        int r,c;
        public state(int[] st,int a, int b)
        {
            state = st;
            r = a;
            c = b;
        }
    }
    
    public static void main(String[] args) throws IOException {
        new DaceyTheDice().go();
    }
}