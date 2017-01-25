import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SafePassage {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        int[] costs = new int[N];
        for(int i = 0;i<N;i++)
        	costs[i] = file.nextInt();
        Arrays.sort(costs);
        boolean[] state = new boolean[N];
        int[] dp = new int[1<<N];
        Arrays.fill(dp,1000000);
        dp[0] = 0;
        bruteForce(costs,state,dp,0,0);
        System.out.println(dp[dp.length-1]);
    }
    
    public void bruteForce(int[] costs, boolean[] state, int[] dp, int ones, int st)
    {
    	for(int i = 0;i<costs.length;i++)
    	{
    		for(int j = i+1;j<costs.length;j++)
    		{
    			if(!state[i]&&!state[j])
    			{
    				int currentState = st;
    				int cost1 = Math.max(costs[i],costs[j]);
    				state[i] = true;
    				state[j] = true;
    				int cost2 = 0;
    				int k = 0;
    				for(;k<costs.length;k++)
    					if(state[k])
    					{
    						cost2 = costs[k];
    						break;
    					}
    				int newState = 0;
    				boolean updated = false;
    				if(ones+2==state.length){
    					newState = currentState^(1<<state.length-i-1)^(1<<state.length-j-1);
    					if(dp[newState]>dp[currentState]+cost1)
    					{
    						dp[newState] = dp[currentState]+cost1;
    						updated = true;	
    					}	
    				}
    				else
    				{
    					state[k] = false;
    					newState = currentState^(1<<state.length-i-1)^(1<<state.length-j-1)^(1<<state.length-k-1);
    					if(dp[newState]>dp[currentState]+cost1+cost2)
    					{
    						dp[newState] = dp[currentState]+cost1+cost2;
    						updated = true;
    					}
    				}
    				if(updated)
    					bruteForce(costs,state,dp,ones+1,newState);
    				state[k] = true;
    				state[i] = false;
    				state[j] = false;
    			}
    		}
    	}	
    }
    
    public boolean all1(boolean[] state)
    {
    	for(boolean b:state)
    		if(!b)
    			return false;
    	return true;
    }
    public int getState(boolean[] state)
    {
    	int ret = 0;
    	for(boolean b:state)
    	{
    		ret*=2;
    		if(b)
    			ret++;	
    	}
    	//System.out.println(Arrays.toString(state));
    	//System.out.println(ret);
    	return ret;
    }
    
    public static void main(String[] args)
    {
        new SafePassage().run();
    }
}