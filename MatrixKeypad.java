import java.util.*;
import java.io.*;
import java.math.*;

import static java.lang.System.*;

public class MatrixKeypad {
    
    public void go() {
        Scanner io = new Scanner(System.in);
        int zzz = io.nextInt();
        io.nextLine();
        for (int zz = 0; zz < zzz; zz++) {
            int numRows = io.nextInt();
            int numCols = io.nextInt();
            boolean[][] mat = new boolean[numRows][numCols];
            for (int i = 0; i < numRows; i++) {
                char[] line = io.next().toCharArray();
                for (int e = 0; e < numCols; e++) {
                    if (line[e] == '1') {
                        mat[i][e] = true;
                    }
                }
            }
            boolean[] rows = new boolean[numRows];
            boolean[] cols = new boolean[numCols];
            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    if (mat[r][c]) {
                        cols[c] = true;
                        rows[r] = true;
                    }
                }
            }
            char[][] ans = new char[numRows][numCols];
            boolean works = true;
            for (int r = 0; r < numRows && works; r++) {
                for (int c = 0; c < numCols && works; c++) {
                    if (rows[r] && cols[c] && !mat[r][c]) {
                        works = false;
                        break;
                    } else if (rows[r] && cols[c] && onlyrc(r,c,rows,cols)) {
                        ans[r][c] = 'P';
                    } else if (rows[r] && cols[c]) {
                        ans[r][c] = 'I';
                    } else {
                        ans[r][c] = 'N';
                    }
                }
            }
            if (works) {
                for (char[] ar : ans) {
                    out.println(ar);
                }
            } else {
                out.println("impossible");
            }
            out.println("----------");
        }
    }
    
    public boolean onlyrc(int r, int c, boolean[] rows, boolean[] cols) {
        boolean works = true;
        for (int i = 0; i < rows.length; i++) {
            if (i != r && rows[i]) {
                works = false;
            }
        }
        if (works) {
            return true;
        }
        works = true;
        for (int i = 0; i < cols.length; i++) {
            if (i != c && cols[i]) {
                works = false;
            }
        }
        return works;
    }
    
    public static void main(String[] args) {
        new MatrixKeypad().go();
    }
}