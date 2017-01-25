import java.util.ArrayList;
import java.util.Scanner;


public class FridayThe13 {
    
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        int zz = file.nextInt();
        for(int z = 0;z<zz;z++)
        {
            int days = file.nextInt();
            int months = file.nextInt();
            int[] numDays = new int[months];
            for(int i = 0;i<months;i++)
            {
                numDays[i] = file.nextInt();
            }
            int currentDay = 0;
            int currentDayOfMonth = 1;
            int currentMonth = 0;
            int ans = 0;
            while(currentMonth<months)
            {
                while(currentDayOfMonth<=numDays[currentMonth])
                {
                    if(currentDay==5&&currentDayOfMonth==13)
                        ans++;
                    currentDay++;
                    currentDay%=7;
                    currentDayOfMonth++;
                }
                currentDayOfMonth = 1;
                currentMonth++;
            }
            System.out.println(ans);
        }
    }
}