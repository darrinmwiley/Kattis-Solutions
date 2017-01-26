import java.util.Arrays;
import java.util.Scanner;

public class GrassSeedInc {

	public void run(){
		Scanner file = new Scanner(System.in);
        double C = file.nextDouble();
        int N = file.nextInt();
        double ans = 0;
        for(int i = 0;i<N;i++)
        {
        	ans+=file.nextDouble()*file.nextDouble()*C;
        }
        System.out.println(ans);
	}
	
    public static void main(String[] args)
    {
       new GrassSeedInc().run();
    } 
}