import java.util.*;

class Spavanac{
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int A = file.nextInt();
        int B = file.nextInt();
        if(B<45)
            A--;
        B-=45;
        System.out.println((A+24)%24+" "+((B+60)%60));
    }
    
}