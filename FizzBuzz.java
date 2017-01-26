import java.util.Arrays;
import java.util.Scanner;

public class FizzBuzz {

	public void run(){
		Scanner file = new Scanner(System.in);
        int x = file.nextInt();
        int y = file.nextInt();
        int N = file.nextInt();
        for(int i = 1;i<=N;i++)
        {
        	if(i%x==0&&i%y==0)
        		System.out.println("FizzBuzz");
        	else if(i%x==0)
        		System.out.println("Fizz");
        	else if(i%y==0)
        		System.out.println("Buzz");
        	else
        		System.out.println(i);
        }
	}
	
    public static void main(String[] args)
    {
       new FizzBuzz().run();
    } 
}