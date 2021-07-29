import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Racetrack {
	
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new Racetrack().run();
    }

    public void run() throws NumberFormatException, IOException
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        Deque<car> cars = new LinkedList<car>();
        car[] arr = new car[N];
        for(int i = 0;i<N;i++)
        {
        		int t = file.nextInt();
        		int l = file.nextInt();
        		car c = new car(t,l,i+1);
        		arr[i] = c;
        		cars.addFirst(c);
        }
        PriorityQueue<car> sort = new PriorityQueue<car>();
        while(!cars.isEmpty())
        {
        		//System.out.println(cars);
        		car slow = cars.pollLast();
        		slow.totalTime += slow.lapTime;
        		slow.lapsLeft--;
        		if(slow.lapsLeft != 0)
        			sort.add(slow);
        		//System.out.println(slow + " finished");
        		while(!cars.isEmpty() && cars.peekLast().totalTime + cars.peekLast().lapTime <= slow.totalTime)
        		{
        			car faster = cars.pollLast();
        			//System.out.println(faster +" overtakes "+slow);
        			faster.totalTime = slow.totalTime;
        			faster.lapsLeft --;
        			if(faster.lapsLeft!= 0)
        				sort.add(faster);
        		}
        		while(!sort.isEmpty())
        		{
        			cars.addFirst(sort.poll());
        		}
        }
        for(car c:arr)
        {
        		System.out.println(c.totalTime);
        }
    }
    
    private class car implements Comparable<car>{
    	
    		int lapTime;
    		int lapsLeft;
    		int totalTime;
    		int id;
    	
    		public car(int time, int left, int id) {
    			lapTime = time;
    			lapsLeft = left;
    			totalTime = 0;
    			this.id = id;
    		}
    		
    		public String toString()
    		{
    			return id+" "+totalTime;
    		}

		@Override
		public int compareTo(car o) {
			return Integer.compare(lapTime, o.lapTime);
		}
    	
    }
    
}