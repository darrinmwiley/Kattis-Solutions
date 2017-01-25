import java.util.*;
import java.io.*;
import java.math.*;

import static java.lang.System.*;

public class ScalingRecipes {
    
    public void go() {
        Scanner io = new Scanner(System.in);
        int zzz = io.nextInt();
        io.nextLine();
        for (int zz = 0; zz < zzz; zz++) {
            int numIng = io.nextInt();
            int startPort = io.nextInt();
            double mult = io.nextDouble()/startPort;
            HashMap<String, Double> map = new HashMap<>();
            String[] ings = new String[numIng];
            double mainWeight = 0;
            for (int i = 0; i < numIng; i++) {
                String ing = io.next();
                double weight = io.nextDouble();
                double percent = io.nextDouble();
                if (percent == 100) {
                    mainWeight = weight*mult;
                }
                map.put(ing, percent);
                ings[i] = ing;
            }
            out.printf("Recipe # %d%n", zz+1);
            for (int i = 0; i < numIng; i++) {
                out.printf("%s %.1f%n", ings[i], map.get(ings[i])*mainWeight/100);
            }
            out.println("----------------------------------------");
        }
    }
    
    public static void main(String[] args) {
        new ScalingRecipes().go();
    }
}