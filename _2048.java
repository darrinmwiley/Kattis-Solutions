import java.awt.*;
import java.io.*;
import java.util.*;
import java.math.*;
import java.util.regex.*;

import static java.lang.System.*;

public class _2048{
    
    public void run()
    {
        Scanner in = new Scanner(System.in);
        int[][] mat = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int e = 0; e < 4; e++) {
                mat[i][e] = in.nextInt();
            }
        }
        int dir = in.nextInt();
        switch (dir) {
        case 0:
            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 3; c++) {
                    if (mat[r][c] == 0 && mat[r][c+1] != 0) {
                        swap(mat, r, c, r, c+1);
                        c = 0;
                    }
                }
                for (int c = 0; c < 3; c++) {
                    if (mat[r][c] == mat[r][c+1]) {
                        mat[r][c] *= 2;
                        mat[r][c+1] = 0;
                    }
                }
                for (int c = 0; c < 3; c++) {
                    if (mat[r][c] == 0 && mat[r][c+1] != 0) {
                        swap(mat, r, c, r, c+1);
                        c = 0;
                    }
                }
            }
            break;
        case 1:
            for (int c = 0; c < 4; c++) {
                for (int r = 0; r < 3; r++) {
                    if (mat[r][c] == 0 && mat[r+1][c] != 0) {
                        swap(mat, r, c, r+1, c);
                        r = 0;
                    }
                }
                for (int r = 0; r < 3; r++) {
                    if (mat[r][c] == mat[r+1][c]) {
                        mat[r][c] *= 2;
                        mat[r+1][c] = 0;
                    }
                }
                for (int r = 0; r < 3; r++) {
                    if (mat[r][c] == 0 && mat[r+1][c] != 0) {
                        swap(mat, r, c, r+1, c);
                        r = 0;
                    }
                }
            }
            break;
        case 2:
            for (int r = 0; r < 4; r++) {
                for (int c = 3; c > 0; c--) {
                    if (mat[r][c] == 0 && mat[r][c-1] != 0) {
                        swap(mat, r, c, r, c-1);
                        c = 3;
                    }
                }
                for (int c = 3; c > 0; c--) {
                    if (mat[r][c] == mat[r][c-1]) {
                        mat[r][c] *= 2;
                        mat[r][c-1] = 0;
                    }
                }
                for (int c = 3; c > 0; c--) {
                    if (mat[r][c] == 0 && mat[r][c-1] != 0) {
                        swap(mat, r, c, r, c-1);
                        c = 3;
                    }
                }
            }
            break;
        case 3:
            for (int c = 0; c < 4; c++) {
                for (int r = 3; r > 0; r--) {
                    if (mat[r][c] == 0 && mat[r-1][c] != 0) {
                        swap(mat, r, c, r-1, c);
                        r = 3;
                    }
                }
                for (int r = 3; r > 0; r--) {
                    if (mat[r][c] == mat[r-1][c]) {
                        mat[r][c] *= 2;
                        mat[r-1][c] = 0;
                    }
                }
                for (int r = 3; r > 0; r--) {
                    if (mat[r][c] == 0 && mat[r-1][c] != 0) {
                        swap(mat, r, c, r-1, c);
                        r = 3;
                    }
                }
            }
            break;
        }
        for (int[] ar : mat) {
            out.println(Arrays.toString(ar).replaceAll("[\\[\\],]", ""));
        }
    }
    
    public void swap(int[][] mat, int r1, int c1, int r2, int c2) {
        int temp = mat[r1][c1];
        mat[r1][c1] = mat[r2][c2];
        mat[r2][c2] = temp;
    }
    
    public static void main(String[] args)
    {
        new _2048().run();
    }
}