import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MissingGnomes {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new MissingGnomes().run();
	}

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(file.readLine());
		int N = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		ArrayList<Integer> gnomes = new ArrayList<Integer>();
		boolean[] occ = new boolean[N+1];
		LinkedList<Integer> missing = new LinkedList<Integer>();
		for(int i = 0;i<G;i++)
		{
			gnomes.add(Integer.parseInt(file.readLine()));
			occ[gnomes.get(i)] = true;
		}
		for(int i = 1;i<occ.length;i++)
		{
			if(!occ[i])
				missing.add(i);
		}
		for(int i = 0;i<gnomes.size();i++)
		{
			if(missing.isEmpty())
				break;
			if(missing.getFirst() < gnomes.get(i))
			{
				gnomes.add(i, missing.removeFirst());
			}
		}
		while(!missing.isEmpty())
			gnomes.add(missing.removeFirst());
		PrintWriter pout = new PrintWriter(System.out);
		for(int i:gnomes)
			pout.println(i);
		pout.flush();
	}

}
