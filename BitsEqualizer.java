package page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BitsEqualizer {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new BitsEqualizer().run();
	}

	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		for(int z = 0;z<N;z++)
		{
			char[] a = file.next().toCharArray();
			char[] b = file.next().toCharArray();
			int oz = 0;
			int zo = 0;
			int qz = 0;
			int qo = 0;
			int ao = 0;
			int bo = 0;
			for(int i = 0;i<a.length;i++)
			{
				if(a[i]=='1') {
					ao++;
					if(b[i]=='0')
						oz++;
				}
				if(a[i]=='0')
				{
					if(b[i]=='1')
						zo++;
				}
				if(a[i]=='?')
				{
					if(b[i]=='0')
						qz++;
					else
						qo++;
				}
				if(b[i]=='1')
					bo++;
			}
			if(ao>bo)
			{
				System.out.printf("Case %d: -1%n",z+1);
				continue;
			}
			int ans = 0;
			ans+= Math.min(oz,zo);
			oz-=ans;
			zo-=ans;
			int x = Math.min(oz,qo);
			oz-=x;
			qo-=x;
			ans+=x*2;
			System.out.printf("Case %d: %d%n",z+1,ans+qo+zo+qz);
		}
	}

}
