import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class AndrewTheAnt {
    public static void main(String[] args)
    {
        Scanner file = new Scanner(System.in);
        while(file.hasNextInt())
        {
            int size = file.nextInt();
            int ants = file.nextInt();
            int[] positions = new int[ants];
            HashMap<Integer,Boolean> right = new HashMap<Integer,Boolean>();
            for(int i = 0;i<ants;i++)
            {
                positions[i] = file.nextInt();
                right.put(positions[i],file.next().charAt(0)=='R');
            }
            Arrays.sort(positions);
            boolean[] directions = new boolean[ants];
            //System.out.println(Arrays.toString(positions)+" "+right+" "+ants);
            for(int i = 0;i<ants;i++)
            {
                directions[i] = right.get(positions[i]);
                //System.out.println(i+" "+positions[i]+" "+right.get(positions[i]));
                //System.out.println(directions[i]);
            }

            //System.out.println(Arrays.toString(directions)+"adsf");
            int firstRight = getFirstPos(0,1,true,directions);
            int firstLeft = getFirstPos(directions.length-1,-1,false,directions);
            int moveRight = count(firstRight,1,false,directions);
            int moveLeft = count(firstLeft,-1,true,directions);
            int rightTime = -1;
            int leftTime = -1;
            if(firstRight!=-1)
                rightTime = size-positions[firstRight];
            if(firstLeft!=-1)
                leftTime = positions[firstLeft];
            if(firstRight==-1||rightTime<leftTime)
            {
                System.out.printf("The last ant will fall down in %d seconds - started at %d.%n", leftTime,positions[firstLeft-moveLeft]);
            }else if(firstLeft==-1||rightTime>leftTime)
            {
                //System.out.println(Arrays.toString(directions));
                //System.out.println(firstRight+" "+moveRight);
                System.out.printf("The last ant will fall down in %d seconds - started at %d.%n",rightTime, positions[firstRight+moveRight]);
            }else{
                int a = positions[firstRight+moveRight];
                int b = positions[firstLeft-moveLeft];
                System.out.printf("The last ant will fall down in %d seconds - started at %d and %d.%n",rightTime,Math.min(a, b),Math.max(a, b));
            }
        }
    }

    public static int getFirstPos(int startingIndex, int delta, boolean search,boolean[] arr)
    {
        for(int i = startingIndex;i>=0&&i<arr.length;i+=delta)
        {
            if(arr[i] == search)
                return i;
        }
        return -1;
    }

    public static int count(int startingIndex, int delta, boolean search, boolean[] arr)
    {
        int c = 0;
        for(int i = startingIndex;i>=0&&i<arr.length;i+=delta)
        {
            if(arr[i]==search)
                c++;
        }
        return c;
    }
}
