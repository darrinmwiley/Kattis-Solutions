import java.util.*;
import java.io.*;
import java.math.*;

import static java.lang.System.*;

public class BundlesOfJoy {
    
    public void go() {
        Scanner io = new Scanner(System.in);
        int zzz = io.nextInt();
        io.nextLine();
        for (int zz = 0; zz < zzz; zz++) {
            int numCakes = io.nextInt();
            int numBundles = io.nextInt();
            Bundle[] allBundles = new Bundle[numBundles];
            for (int i = 0; i < numBundles; i++) {
                int cost = io.nextInt();
                int[] cakes = new int[io.nextInt()];
                for (int e = 0; e < cakes.length; e++) {
                    cakes[e] = io.nextInt()-1;
                }
                allBundles[i] = new Bundle(cakes, numCakes, cost);
            }
            Arrays.sort(allBundles);
            ArrayList<Bundle> bestBundles = new ArrayList<>();
            for (Bundle curr : allBundles) {
                int c = getCost(curr.cakes, bestBundles);
                curr.cost = Math.min(curr.cost,  c);
                bestBundles.add(curr);
            }
            ArrayList<Integer> cakes = new ArrayList<Integer>();
            for (int i = 0; i < numCakes; i++) {
                cakes.add(i);
            }
            out.println(getCost(cakes, bestBundles));
        }
    }
    
    public int getCost(ArrayList<Integer> cakes, ArrayList<Bundle> bestBundles) {
        int cost = 0;
        ArrayList<Integer> copy = (ArrayList<Integer>)cakes.clone();
        for (int i = bestBundles.size()-1; i >= 0; i--) {
            if (copy.size() >= bestBundles.get(i).cakes.size()) {
                boolean didRemove = copy.removeAll(bestBundles.get(i).cakes);
                if (didRemove) {
                    cost += bestBundles.get(i).cost;
                }
            }
            if (copy.isEmpty()) {
                return cost;
            }
        }
        return Integer.MAX_VALUE;
    }
    
    public static void main(String[] args) {
        new BundlesOfJoy().go();
    }
    
    private class Bundle implements Comparable<Bundle> {
        
        ArrayList<Integer> cakes;
        boolean[] hasCake;
        int cost;
        
        public Bundle(int[] c, int nc, int co) {
            hasCake = new boolean[nc];
            cakes = new ArrayList<>();
            for (int i = 0; i < c.length; i++) {
                cakes.add(c[i]);
                hasCake[c[i]] = true;
            }
            cost = co;
        }
        
        public int compareTo(Bundle other) {
            if (cakes.size() == other.cakes.size()) {
                if (cakes.get(0) == other.cakes.get(0)) {
                    return cost - other.cost;
                }
                return cakes.get(0) - other.cakes.get(0);
            }
            return cakes.size() - other.cakes.size();
        }
        
        public String toString() {
            return String.format("%d %s", cost, cakes);
        }
    }
}