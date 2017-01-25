import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ClassyProblem {
	public static void main(String[] args) throws IOException
	{
		Scanner file = new Scanner(System.in);
		int zz = file.nextInt();
		for(int z = 0;z<zz;z++)
		{
			int N = file.nextInt();
			file.nextLine();
			PriorityQueue<person> que = new PriorityQueue<person>();
			for(int i = 0;i<N;i++)
			{
				que.add(new person(file.nextLine()));
			}
			while(!que.isEmpty())
				System.out.println(que.poll());
			for(int i = 0;i<30;i++)
				System.out.print("=");
			System.out.println();
		}
	}
}

class person implements Comparable<person>{
	String name;
	int spot;
	public person(String s)
	{
		name = s.substring(0,s.indexOf(':'));
		String rest = s.substring(s.indexOf(':')+2,s.indexOf(" class"));
		String[] classes = rest.split("-");
		for(int i = classes.length-1;i>=0;i--)
		{
			if(classes[i].charAt(0)=='u')
				spot+=2;
			if(classes[i].charAt(0)=='m')
				spot+=1;
			spot*=3;
		}
		for(int i = 0;i<10-classes.length;i++)
		{
			spot++;
			spot*=3;
		}	
	}
	@Override
	public int compareTo(person arg0) {
		if(spot==arg0.spot)
			return name.compareTo(arg0.name);
		return -(spot-arg0.spot);
	}
	
	public String toString()
	{
		return name;
	}
	
}
