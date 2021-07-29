import java.util.Arrays;
import java.util.Scanner;

public class Pavers {
	public static void main(String[] args) {
		new Pavers().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		//ways, 1, 2, 3
		long[][][] dp = new long[N+4][4][4];
		for(int i = 0;i<dp.length;i++)
			for(int j = 0;j<dp[i].length;j++)
				dp[i][j] = new long[] {0,0,0,0};
		dp[0][0] = new long[] {1,0,0,0};
		dp[0][1] = new long[] {1,1,0,0};
		dp[0][2] = new long[] {1,1,0,0};
		dp[0][3] = new long[] {1,2,0,0};
		for(int i = 0;i<N+1;i++)
		{
			//0 -> 0
			addPlus(dp[i+1][0], dp[i][0],0,1,0);//l
			addPlus(dp[i+1][3], dp[i][0],0,2,0);//l
			
			//0 -> 1
			addPlus(dp[i+1][1], dp[i][0],0,0,1);//L
			addPlus(dp[i+1][1], dp[i][0],1,1,0);//l U
			
			//0 -> 2
			addPlus(dp[i+1][2], dp[i][0],0,0,1);//L
			addPlus(dp[i+1][2], dp[i][0],1,1,0);//l U
			
			
			//0 -> 3
			addPlus(dp[i+1][3], dp[i][0],2,1,0);//L
			addPlus(dp[i+1][3], dp[i][0],1,0,1);//L U
			addPlus(dp[i+1][3], dp[i][0],1,0,1);//L U
			
			//1 -> 2
			addPlus(dp[i+1][2], dp[i][1],0,1,0);//l
			
			//1 -> 3
			addPlus(dp[i+1][3], dp[i][1],1,1,0);//lU
			addPlus(dp[i+1][3], dp[i][1],0,0,1);//L
			
			//2 -> 1
			addPlus(dp[i+1][1], dp[i][2],0,1,0);//l
			
			//2 -> 3
			addPlus(dp[i+1][3], dp[i][2],1,1,0);//lU
			addPlus(dp[i+1][3], dp[i][2],0,0,1);//L
			
			//3 -> 0
			addPlus(dp[i+1][0], dp[i][3],0,0,0);//lambda
			
			//3 -> 1
			addPlus(dp[i+1][1], dp[i][3],1,0,0);//U
			
			//3 -> 2
			addPlus(dp[i+1][2], dp[i][3],1,0,0);//U
			
			//3 -> 3
			addPlus(dp[i+1][3], dp[i][3],2,0,0);//UU
			
		}
		//System.out.println(Arrays.toString(dp[1][0]));
		//System.out.println(Arrays.toString(dp[2][2]));
		System.out.println(dp[N][0][0]+" "+dp[N][0][1]+" "+dp[N][0][2]+" "+dp[N][0][3]);
		
	}
	
	public void addPlus(long[] arr, long[] arr2, int a, int b, int c)
	{
		for(int i = 0;i<arr.length;i++)
		{
			arr[i] += arr2[i];
		}
		arr[1] += arr2[0]*a;
		arr[2] += arr2[0]*b;
		arr[3] += arr2[0]*c;
	}
}
