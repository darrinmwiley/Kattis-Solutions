import java.util.*;
class NastyHacks{

    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        for(int i = 0;i<N;i++)
        {
            int a = file.nextInt();//not using
            int b = file.nextInt();//using
            int c = file.nextInt();//cost of using
            if(a==b-c)
            {
                System.out.println("does not matter");
            }
            if(a>b-c)
            {
                System.out.println("do not advertise");
            }
            if(a<b-c)
            {
                System.out.println("advertise");
            }
        }
    }
}
