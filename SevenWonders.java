import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SevenWonders {
    
    int S;
    int L;
    
    public void go() throws IOException {
        Scanner file = new Scanner(System.in);
        String next = file.next();
        int T = next.length()-next.replace("T", "").length();
        int G = next.length()-next.replace("G", "").length();
        int C = next.length()-next.replace("C", "").length();
        int x = Math.min(Math.min(T,G),C);
        System.out.println(T*T+G*G+C*C+x*7);
    }
    
    
    public static void main(String[] args) {
        try {
            new SevenWonders().go();
        } catch (IOException e) {
            
        }
    }
}