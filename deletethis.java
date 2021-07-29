package page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class deletethis {

	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new deletethis().run();
	}
	
	public void run() throws NumberFormatException, IOException
	{
		Scanner file = new Scanner(System.in);
		int R = file.nextInt();
		int C = file.nextInt();
		int D = file.nextInt();
		int S = file.nextInt();
		if(D==0) {
			System.out.println(0);
			return;
		}
		icon[] icons = new icon[D+S];// r sorted
		for(int i = 0;i<D+S;i++)
			icons[i] = new icon(file.nextInt()+7,file.nextInt()+4, i<D);
		icon[] iconsC = icons.clone();
		Comparator<icon> col = new Comparator<icon>() {
			public int compare(icon a, icon b)
			{
				if( a.c-b.c !=0)
					return a.c-b.c;
				if(!a.delete)
					return -1;
				if(!b.delete)
					return 1;
				return 0;
			}
		};
		Arrays.sort(iconsC,col);
		Arrays.sort(icons);
		int min = Integer.MAX_VALUE;
		for(int i = 0;i<icons.length;i++)
		{
			for(int j = i;j<icons.length;j++)
			{
				if(val(icons[i],R,C)&&val(icons[j],R,C))
					min = Math.min(min,slice(icons[i],icons[j],iconsC,D,R,C));
			}
		}
		System.out.println(min);
	}
	
	public int slice(icon bottom,icon top, icon[] icons, int toDelete, int R, int C)// pass iconsC
	{
		int b = bottom.r;//less than t
		int t = top.r;
		ArrayList<icon> list = new ArrayList<icon>();
		ArrayList<Integer> unique = new ArrayList<Integer>();
		for(int i = 0;i<icons.length;i++) {
			if(icons[i].r>=b&&icons[i].r<=t&&val(icons[i],R,C)) {
				list.add(icons[i]);
				if(unique.isEmpty()||unique.get(unique.size()-1)!=icons[i].c)
					unique.add(icons[i].c);
			}
		}
		
		
		//make list of unique C positions
		int[] D = new int[unique.size()+1];
		int[] S = new int[unique.size()+1];
		int index = 0;
		for(int i = 1;i<=unique.size();i++)
		{
			D[i] = D[i-1];
			S[i] = S[i-1];
			while(index!=list.size()&&list.get(index).c==unique.get(i-1))
			{
				if(list.get(index).delete)
					D[i]++;
				else
					S[i]++;
				index++;
			}
		}
		int[] maxafter = new int[unique.size()+1];
		maxafter[unique.size()] = unique.size();
		for(int i = unique.size()-1;i>=0;i--) {
			maxafter[i] = maxafter[i+1];
			int ind = maxafter[i];
			int best = D[ind]-S[ind];
			if(D[i]-S[i]>best)
				maxafter[i] = i;
		}
		int min = Integer.MAX_VALUE;
		for(int i = 0;i<=unique.size();i++)
		{
			int right = maxafter[i];
			int cost = toDelete - (D[right]-D[i]) + (S[right]-S[i]);
			// total to delete - (number needed to delete inside) + number to save inside
			min = Math.min(min,cost);
		}
		
		return min;
	}
	
	public boolean val(icon i, int R, int C)
	{
		boolean val = i.r<=R&&i.c<=C;
		return val;
	}
	
	private class icon implements Comparable<icon>{
		
		int r, c;
		boolean delete;
		public icon(int cr, int cc, boolean d)
		{
			r = cr;
			c = cc;
			delete = d;
		}
		@Override
		public int compareTo(icon o) {
			return r-o.r;
		}
		
		public String toString()
		{
			return r+" "+c;
		}
	}
	
}