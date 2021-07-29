import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Jetpack {
	public static final void main(final String[] arg)throws Exception {
		new Jetpack().run();
	}
	
	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(file.readLine().trim());
		boolean[][] bools = new boolean[10][N];
		bools[9][0] = true;
		char[][] chars = new char[10][];
		for(int i = 0;i<chars.length;i++)
		{
			chars[i] = file.readLine().trim().toCharArray();
		}
		for(int i = 1;i<N;i++) {
			for(int j = 0;j<10;j++)
			{
				if(chars[j][i]=='X')
					continue;
				if(j==0||j==9)
					bools[j][i] |= bools[j][i-1];
				if(j!=0)
					bools[j][i] |= bools[j-1][i-1];
				if(j!=9)
					bools[j][i] |= bools[j+1][i-1];
			}
		}
		int start = -1;
		for(int i = 0;i<10;i++)
		{
			if(bools[i][N-1])
			{
				start = i;
				break;
			}
		}
		int pos = N-1;
		Stack<Integer> moves = new Stack<Integer>();
		while(true)
		{
			moves.add(start);
			if(pos==0)
				break;
			if(start!=0)
			{
				if(bools[start-1][pos-1])
				{
					start--;
					pos--;
					continue;
				}
			}
			if(start!=9)
			{
				if(bools[start+1][pos-1])
				{
					start++;
					pos--;
					continue;
				}
			}
			pos--;
		}
		boolean[] tapping = new boolean[N];
		int c = 0;
		while(true)
		{
			int x = moves.pop();
			if(moves.isEmpty())
				break;
			int y = moves.peek();
			if((x==0&&y==0)||(y==x-1))
				tapping[c] = true;
			c++;
		}
		boolean tap = false;
		int lastTap = -1;
		Queue<String> que = new LinkedList<String>();
		for(int i = 0;i<tapping.length;i++)
		{
			if(!tapping[i]&&tap)
			{
				que.add(lastTap+" "+(i-lastTap));
				tap = false;
			}
			if(tapping[i]&&!tap)
			{
				lastTap = i;
				tap = true;
			}
		}
		System.out.println(que.size());
		while(!que.isEmpty())
			System.out.println(que.poll());
	}
	
}
