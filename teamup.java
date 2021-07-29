package page;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class teamup {

	
	
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        new teamup().run();
    }
    
    public void run() throws NumberFormatException, IOException
    {
    	//long start = System.currentTimeMillis();
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(file.readLine());
        int N = Integer.parseInt(st.nextToken());//skills
        int M = Integer.parseInt(st.nextToken());//classes
        int P = Integer.parseInt(st.nextToken());//people
        role[] roles = new role[M];
        for(int i = 0;i<roles.length;i++)
        {
        	st = new StringTokenizer(file.readLine());
        	int sz = Integer.parseInt(st.nextToken());
        	int[] role = new int[sz];
        	for(int j = 0;j<sz;j++)
        	{
        		role[j] = Integer.parseInt(st.nextToken())-1;
        	}
        	roles[i] = new role(role);
        }
        int[] classes = new int[P];
        st = new StringTokenizer(file.readLine());;
        for(int i = 0;i<P;i++) {
        	int x = Integer.parseInt(st.nextToken());
        	classes[i] = x-1;
        	roles[x-1].add(i+1);
        }
        Arrays.sort(roles);
        ArrayList<ArrayList<Integer>> parties = new ArrayList<ArrayList<Integer>>();
        int[] spots = new int[N];
        for(int i = 0;i<roles.length;i++)
        {
        	int[] skills = roles[i].skills;
        	int weight = roles[i].weight;
        	int start = spots[skills[0]];
        	for(int j = 0;j<skills.length;j++)
        		spots[skills[j]]+=weight;
        	for(int j = 0;j<weight;j++)
        		add(parties,start+j,roles[i].person());
        }
        int min = spots[0];
        for(int i = 1;i<spots.length;i++)
        	min = Math.min(min,spots[i]);
        System.out.println(min);
        for(int i = 0;i<min;i++)
        {
        	StringBuilder sb = new StringBuilder("");
        	sb.append(parties.get(i).size()+" ");
        	for(int j = 0;j<parties.get(i).size();j++)
        		sb.append(parties.get(i).get(j)+" ");
        	System.out.println(sb.toString().trim());
        }
    }
    
    public void add(ArrayList<ArrayList<Integer>> parties, int party, int person)
    {
    	if(parties.size()<=party)
    		parties.add(new ArrayList<Integer>());
    	parties.get(party).add(person);
    }
    
    private class role implements Comparable<role>{
    	
		int[] skills;
		int weight;
		Stack<Integer> players;
		public role(int[] ints)
		{
			skills = ints;
			weight = 0;
			players = new Stack<Integer>();
		}
		public void add(int x)
		{
			players.add(x);
			weight++;
		}
		
		public int person()
		{
			return players.pop();
		}
		
		@Override
		public int compareTo(role arg0) {
			return Integer.compare(arg0.skills.length, skills.length);
		}
    }
}