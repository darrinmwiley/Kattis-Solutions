import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class CreditCard {
    
    public static void main(String[] args) throws Exception
    {
        new CreditCard().run();
    }

   //1
   //14.78 40181.09 46119.85
    
    public void run()
    {
    	Scanner file = new Scanner(System.in);
    	int zz = file.nextInt();
    loop:
    	for(int z = 0;z<zz;z++)
    	{
    		double R = file.nextDouble();
    		int Bcents = Integer.parseInt(file.next().replace(".", ""));
    		int Mcents = Integer.parseInt(file.next().replace(".", ""));
    		if(Bcents == 0)
    		{
    			System.out.println(0);
    			continue loop;
    		}
    		//apply interest and then payment;
    		for(int i = 1;i<=1200;i++)
    		{
    			double interest = BigDecimal.valueOf(R).multiply(BigDecimal.valueOf(Bcents)).divide(BigDecimal.valueOf(100)).doubleValue();
    			interest = Math.round(interest);
    			if(interest >= Mcents)
    			{
    				break;
    			}
    			Bcents += interest;
    			Bcents -= Mcents;
    			if(Bcents <= 0) {
    				System.out.println(i);
    				continue loop;
    			}
    		}
    		System.out.println("impossible");
    	}
    }
    
}