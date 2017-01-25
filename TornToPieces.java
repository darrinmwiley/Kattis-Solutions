import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class TornToPieces {
    
    public void run()
    {
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        TreeMap<String,TreeSet<String>> map = new TreeMap<>();
        for(int i = 0;i<N;i++)
        {
        	String nam = file.next();
        	String[] rest = file.nextLine().trim().split(" ");
        	TreeSet<String> set = new TreeSet<>();
        	for(String s:rest)
        	{
        		set.add(s); 
        		if(map.get(s)==null)
        			map.put(s,new TreeSet<String>());
        		map.get(s).add(nam);
        	}
        	if(map.get(nam)==null)
        		map.put(nam,new TreeSet<String>());
        	map.get(nam).addAll(set);
        }
        try{
	        String source = file.next();
	        String dest = file.next();
	        TreeSet<String> vis = new TreeSet<String>();
	        vis.add(source);
	        TreeMap<String,String> pred = new TreeMap<String,String>();
	        Queue<String> que = new LinkedList<String>();
	        que.add(source);
	        while(!que.isEmpty())
	        {
	        	String current = que.poll();
	        	TreeSet<String> con = map.get(current);
	        	if(con!=null)
	        		for(String s:con)
	        			if(vis.add(s))
	        			{
	        				pred.put(s,current);
	        				que.add(s);
	        			}
	        }
	        if(pred.get(dest)==null)
	        	System.out.println("no route found");
	        else{
	        	String current = dest;
	        	String ans = "";
	        	while(current!=null)
	        	{
	        		ans = current+" "+ans;
	        		current = pred.get(current);
	        	}
	        	System.out.println(ans.trim());
	        }
        }catch(Exception ex){System.out.println("exception happened");}
        
    }
    
    public static void main(String[] args)
    {
        new TornToPieces().run();
    }
}