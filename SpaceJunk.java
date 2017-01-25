import java.util.*;
import java.io.*;
import java.math.*;

import static java.lang.System.*;

public class SpaceJunk {
    
    public void go() {
        Scanner io = new Scanner(System.in);
        int zzz = io.nextInt();
        io.nextLine();
        for (int zz = 0; zz < zzz; zz++) {
            double x1 = io.nextDouble();
            double y1 = io.nextDouble();
            double z1 = io.nextDouble();
            double r1 = io.nextDouble();
            double vx1 = io.nextDouble();
            double vy1 = io.nextDouble();
            double vz1 = io.nextDouble();
            double x2 = io.nextDouble();
            double y2 = io.nextDouble();
            double z2 = io.nextDouble();
            double r2 = io.nextDouble();
            double vx2 = io.nextDouble();
            double vy2 = io.nextDouble();
            double vz2 = io.nextDouble();
            double d = (r1+r2)*(r1+r2);
            double a = Math.pow(vx1-vx2, 2) + Math.pow(vy1-vy2, 2) + Math.pow(vz1-vz2, 2);
            double b = 2*((vx1-vx2)*(x1-x2) + (vy1-vy2)*(y1-y2) + (vz1-vz2)*(z1-z2));
            double c = Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2) + Math.pow(z1-z2, 2) - d;
            if (a == 0 || b*b-4*a*c < 0) {
                out.println("No collision");
            } else {
                double t1 = (-b - Math.sqrt(b*b-4*a*c)) / (2*a);
                double t2 = (-b + Math.sqrt(b*b-4*a*c)) / (2*a);
                if (t1 < 0 && t2 >= 0) {
                    out.println(t2);
                } else if (t2 < 0) {
                    out.println("No collision");
                } else {
                    out.println(t1);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new SpaceJunk().go();
    }
}