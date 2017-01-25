import java.awt.*;
import java.io.*;
import java.util.*;
import java.math.*;
import java.util.regex.*;

import static java.lang.System.*;

public class ICPC_Tutorial {
    
    public void run()
    {
        Scanner in = new Scanner(System.in);
        long max = in.nextLong();
        long n = in.nextLong();
        int t = in.nextInt();
        switch (t) {
        case 1: 
            for (long i = n-1; i > 1; i--) {
                n *= i;
                if (n > max) {
                    out.println("TLE");
                    return;
                }
            }
            out.println("AC");
            break;
        case 2:
            if (n > 30) {
                out.println("TLE");
            } else if (Math.pow(2, n) <= max) {
                out.println("AC");
            } else {
                out.println("TLE");
            }
            break;
        case 3:
            if (Math.pow(n, 4) <= max) {
                out.println("AC");
            } else {
                out.println("TLE");
            }
            break;
        case 4:
            if (Math.pow(n, 3) <= max) {
                out.println("AC");
            } else {
                out.println("TLE");
            }
            break;
        case 5:
            if (Math.pow(n, 2) <= max) {
                out.println("AC");
            } else {
                out.println("TLE");
            }
            break;
        case 6:
            if (n * Math.log(n)/Math.log(2) <= max) {
                out.println("AC");
            } else {
                out.println("TLE");
            }
            break;
        case 7:
            if (n <= max) {
                out.println("AC");
            } else {
                out.println("TLE");
            }
            break;
        }
    }
    
    public static void main(String[] args)
    {
        new ICPC_Tutorial().run();
    }
}