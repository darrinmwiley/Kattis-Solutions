import java.util.Scanner;

public class Exam {

	public static void main(String[] args)
	{
		new Exam().run();
	}

	public void run()
	{
		Scanner file = new Scanner(System.in);
		int right = file.nextInt();
		char[] a = file.next().toCharArray();
		char[] b = file.next().toCharArray();
		int wrong = a.length - right;
		int ans = 0;
		for(int i = 0;i<a.length;i++)
		{
			if(a[i] == b[i])
			{
				if(right > 0)
				{
					right --;
					ans  ++;
				}else {
					wrong--;
				}
			}else {
				if(wrong > 0)
				{
					wrong --;
					ans++;
				}else {
					right --;
				}
			}
		}
		System.out.println(ans);
	}

}
