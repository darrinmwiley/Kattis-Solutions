import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DecisionsDecisions {

    public static void main(String[] args) throws IOException
    {
        new DecisionsDecisions().run();
    }

    public void run() throws IOException
    {
        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(file.readLine());
        StringTokenizer st = new StringTokenizer(file.readLine());
        ArrayList<node> nodes = new ArrayList<node>();
        nodes.add(new node());
        node root = nodes.get(0);
        for(int i = 0;i<N;i++)
        {
        		ArrayList<node> toAdd = new ArrayList<node>();
        		for(int j = 0;j<nodes.size();j++)
        		{

        			if(nodes.get(j).L == null)
        			{
        				nodes.get(j).L = new node();
        				nodes.get(j).R = new node();
        				toAdd.add(nodes.get(j).L);
        				toAdd.add(nodes.get(j).R);
        			}
        		}
        		nodes.addAll(toAdd);
        }
        for(int i = 0;i<(1<<N);i++)
        {
        		boolean b = st.nextToken().equals("1");
        		node leaf = root.traverse(i);
        		leaf.hasValue = true;
        		leaf.value = b;
        }
        root.simplify();
        System.out.println(root.size());
    }

    private class node{

    		public node L;
    		public node R;
    		boolean hasValue;
    		boolean value;
    		int dep;
    		String hash;

    		public int size()
    		{
    			int sum = 1;
    			if(L != null)
    				sum += L.size();
    			if(R != null)
    				sum += R.size();
    			return sum;
    		}

    		public void simplify()
    		{
    			if(L != null)
    			{
    				L.simplify();
    				R.simplify();
    				if(L.hasValue && R.hasValue && L.value == R.value)
        			{
        				this.hasValue = true;
        				this.value = L.value;
        				L = null;
        				R = null;
        			}
    			}
    		}

    		public node traverse(int i)
    		{
    			if(L == null)
    			{
    				return this;
    			}else {
    				if(i%2==0)
    					return L.traverse(i/2);
    				return R.traverse(i/2);
    			}
    		}

    }
}
