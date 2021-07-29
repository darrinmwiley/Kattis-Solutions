import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class CircuitMath {

	public static void main(String[] args)
	{
		new CircuitMath().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		char[] input = new char[N];
		for(int i =0;i<N;i++)
			input[i] = file.next().charAt(0);
		file.nextLine();
		String[] eq = file.nextLine().split(" ");
		HashMap<Character,Boolean> value = new HashMap<>();
		for(int i = 0;i<input.length;i++)
		{
			value.put((char)(i+'A'),input[i] == 'T');
		}
		Stack<Boolean> st = new Stack<Boolean>();
		for(String str:eq)
		{
			char ch = str.charAt(0);
			if(Character.isAlphabetic(ch))
			{
				st.push(value.get(ch));
			}else if(ch == '*')
			{
				boolean a = st.pop();
				boolean b = st.pop();
				st.push(a&&b);
			}else if(ch == '+')
			{
				boolean a = st.pop();
				boolean b = st.pop();
				st.push(a||b);
			}else if(ch == '-')
			{
				st.push(!st.pop());
			}
		}
		boolean ans = st.pop();
		System.out.println(ans?"T":"F");
	}

}
