import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import static java.lang.System.*;

public class Agglomerator {
    
    public void run()
    {   
        Scanner io = new Scanner(System.in);
        int numCircles = io.nextInt();
        ArrayList<Circle> circles = new ArrayList<>();
        for (int i = 0; i < numCircles; i++) {
            int x = io.nextInt();
            int y = io.nextInt();
            int vx = io.nextInt();
            int vy = io.nextInt();
            int r = io.nextInt();
            r = r*r;
            circles.add(new Circle(x, y, vx, vy, r));
        }
        double currTime = 0;
        while (true) {
            double t = getTime(circles);
            if (t != Double.POSITIVE_INFINITY) {
                currTime += t;
            } else {
                break;
            }
        }
        out.printf("%d %f%n", circles.size(), currTime);
    }
    
    public double getTime(ArrayList<Circle> circles) {
        double bestTime = Double.POSITIVE_INFINITY;
        int bestI=0, bestE=0;
        for (int i = 0; i < circles.size(); i++) {
            for (int e = i+1; e < circles.size(); e++) {
                double time = timeFor(circles.get(i), circles.get(e));
                if (time < bestTime) {
                    bestTime = time;
                    bestI = i;
                    bestE = e;
                }
            }
        }
        if (bestTime != Double.POSITIVE_INFINITY) {
            for (int i = 0; i < circles.size(); i++) {
                circles.get(i).move(bestTime);
            }
            Circle one = circles.get(bestI);
            Circle two = circles.get(bestE);
            circles.remove(bestE);
            circles.remove(bestI);
            double size = one.size() + two.size();
            double x = one.size()/size*one.x + two.size()/size*two.x;
            double y = one.size()/size*one.y + two.size()/size*two.y;
            double vx = one.size()/size*one.vx + two.size()/size*two.vx;
            double vy = one.size()/size*one.vy + two.size()/size*two.vy;
            Circle combine = new Circle(x, y, vx, vy, size);
//          out.println(bestTime + " " + bestI + " " + bestE + " " + combine.toString());
            circles.add(combine);
        }
        return bestTime;
    }
    
    public double timeFor(Circle one, Circle two) {
        double a = Math.pow(one.vx-two.vx, 2) + Math.pow(one.vy-two.vy, 2);
        double b = 2*((one.x-two.x)*(one.vx-two.vx) + (one.y-two.y)*(one.vy-two.vy));
        double c = Math.pow(one.x-two.x, 2) + Math.pow(one.y-two.y, 2) - Math.pow(one.r+two.r, 2);
        if (b*b-4*a*c >= 0) {
            double t1 = (-b - Math.sqrt(b*b-4*a*c)) / (2*a);
            double t2 = (-b + Math.sqrt(b*b-4*a*c)) / (2*a);
            if (t1 >= 0) {
                return t1;
            } else if (t2 >= 0) {
                return t2;
            }
        }
        return Double.POSITIVE_INFINITY;
    }
    
    public static void main(String[] args)
    {
        new Agglomerator().run();
    }
    
    private class Circle {
        
        double x, y, vx, vy, r2, r;
        
        public Circle(double x, double y, double vx, double vy, double r2) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.r2 = r2;
            r = Math.sqrt(r2);
        }
        
        public double size() {
            return r2;
        }
        
        public void move(double t) {
            x += vx*t;
            y += vy*t;
        }
        
        public String toString() {
            return String.format("%.2f %.2f %.2f %.2f %.2f", x, y, vx, vy, r2);
        }
    }
}