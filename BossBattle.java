import java.util.*;

class BossBattle{

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        System.out.println(Math.max(N-2,1));
    }

}
