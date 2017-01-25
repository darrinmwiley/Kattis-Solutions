import java.util.*;
import java.io.*;
import java.math.*;

import static java.lang.System.*;

public class HoneycombWalk {
    
    public void go()
    {
        int[] ints = new int[]{0,0,6,12,90,360,2040,10080,54810,290640,1588356,8676360,47977776,266378112,1488801600,234231236,141301772};
        Scanner in = new Scanner(System.in);
        int zz = in.nextInt();
        for(int z = 0;z<zz;z++)
        System.out.println(ints[in.nextInt()]);
    }
    
    public void go2() throws IOException {
        TreeMap<String,Integer> map = new TreeMap();
        triple[] triples = new triple[169];
        boolean[][][] used = new boolean[15][15][15];
        int[][] d = new int[][]{{-1,-1,0,0,1,1},{1,1,0,0,-1,-1},{0,1,-1,1,-1,0}};
        int[][] mat = new int[169][169];
        int c = 1;
        triples[0] = new triple(0,0,0);
        map.put("(0 0 0)",0);
        used[7][7][7] = true;
        for(int i = 0;i<7;i++)
        {
            int ccopy = c;
            for(int j = 0;j<ccopy;j++)
            {
                triple t = triples[j];
                for(int k = 0;k<6;k++)
                {
                    triple t2 = new triple(t.a+d[0][k],t.b+d[1][k],t.c+d[2][k]);
                    
                    if(!used[t2.a+7][t2.b+7][t2.c+7])
                    {
                        used[t2.a+7][t2.b+7][t2.c+7] = true;
                        map.put(t2.toString(),c);
                        triples[c++] = t2;
                    }
                    mat[j][map.get(t2.toString())] = 1;
                    mat[map.get(t2.toString())][j] = 1;
                }
            }
            System.out.println(Arrays.toString(triples));
        }
        System.out.println(triples[91]);
        for(int i =0;i<mat.length;i++)
            if(mat[91][i]==1)
                System.out.print(triples[i]+",");
        System.out.println();
        int[][] newMat = new int[triples.length][];
        for(int i = 0;i<mat.length;i++)
            newMat[i] = mat[i].clone();
        
        System.out.println("1-0");
        for(int q = 0;q<15;q++)
        {
            int[][] mult = new int[169][169];
            for(int i = 0;i<mat.length;i++)
            {
                for(int j = 0;j<mat.length;j++)
                {
                    for(int k = 0;k<mat.length;k++)
                    {
                        mult[i][j]+=mat[i][k]*newMat[k][j];
                    }
                }
            }
            newMat = mult;
            System.out.println(q+2+"-"+newMat[0][0]);
        }
        System.out.println(checkMat(mat));
    }
    
    public boolean checkMat(int[][] mat)
    {
        boolean flag = true;
        for(int i = 0;i<127;i++)
        {
            int row = 0;
            int col = 0;
            for(int j = 0;j<mat.length;j++)
            {
                if(mat[i][j]==1)
                    row++;
            }
            if(row!=6||col!=6)
            {
                flag = false;
                if(row!=6)
                    System.out.println("row "+i+" issue "+row);
            }
        }
        return flag;
    }
    
    public static void main(String[] args) throws IOException {
        new HoneycombWalk().go();
    }
    
    private class triple
    {
        int a,b,c;
        public triple(int a, int b, int c)
        {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public String toString()
        {
            return "("+a+" "+b+" "+c+")";
        }
    }
    
    
    
}