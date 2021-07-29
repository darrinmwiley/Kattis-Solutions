import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FoldingACube {

	public static void main(String[] args) throws NumberFormatException, IOException
	{
		new FoldingACube().run();
	}

	char[][][] bad = new char[][][] {

		new char[][] {
			".#.".toCharArray(),
			"###".toCharArray(),
			".#.".toCharArray(),
			".#.".toCharArray()
		},
		new char[][] {
			"###".toCharArray(),
			".#.".toCharArray(),
			".#.".toCharArray(),
			".#.".toCharArray()
		},
		new char[][] {
			"##.".toCharArray(),
			".#.".toCharArray(),
			".##".toCharArray(),
			"..#".toCharArray()
		},
		new char[][] {
			"##.".toCharArray(),
			".#.".toCharArray(),
			".##".toCharArray(),
			".#.".toCharArray()
		},
		new char[][] {
			".#".toCharArray(),
			".#".toCharArray(),
			"##".toCharArray(),
			"#.".toCharArray(),
			"#.".toCharArray()
		},
		new char[][] {
			".#.".toCharArray(),
			"##.".toCharArray(),
			".##".toCharArray(),
			".#.".toCharArray()
		},
		new char[][] {
			".##".toCharArray(),
			".#.".toCharArray(),
			".#.".toCharArray(),
			"##.".toCharArray()
		},
		new char[][] {
			".##".toCharArray(),
			"##.".toCharArray(),
			".#.".toCharArray(),
			".#.".toCharArray()
		},
		new char[][] {
			"..#".toCharArray(),
			".##".toCharArray(),
			"##.".toCharArray(),
			"#..".toCharArray()
		},
			new char[][] {
			".#.".toCharArray(),
			".##".toCharArray(),
			"##.".toCharArray(),
			"#..".toCharArray()
		},
			new char[][] {
			".#.".toCharArray(),
			".#.".toCharArray(),
			"###".toCharArray(),
			"#..".toCharArray()
		}
	};

	public void run() throws NumberFormatException, IOException
	{
		BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		char[][] chars = new char[6][6];
		for(int i = 0;i<6;i++)
		{
			chars[i] = file.readLine().toCharArray();
		}
		boolean test = test(chars);
		if(test)
		{
			System.out.println("can fold");
		}else {
			System.out.println("cannot fold");
		}
	}

	public boolean test(char[][] chars)
	{
		for(int i = 0;i<4;i++)
		{
			chars = rotate(chars);
			for(char[][] ch:bad)
			{
				if(search(chars, ch))
					return true;
				chars = reflect(chars);
				if(search(chars, ch))
					return true;
				chars = reflect(chars);
			}
		}
		return false;
	}

	public boolean search(char[][] chars, char[][] sub)
	{
		for(int i = 0;i+sub.length<=chars.length;i++)
		{
		outer:
			for(int j = 0;j+sub[0].length<=chars.length;j++)
			{
				//System.out.println(i+" "+j);
				for(int si = 0;si<sub.length;si++)
				{
					for(int sj = 0;sj<sub[si].length;sj++)
					{
						//System.out.println(i+" "+j+" "+si+" "+sj);
						if(chars[i+si][j+sj] != sub[si][sj])
							continue outer;
					}
				}
				return true;
			}
		}
		return false;
	}

	public char[][] reflect(char[][] chars)
	{
		char[][] ret = new char[6][6];
		for(int i = 0;i<chars.length;i++)
		{
			for(int j = 0;j<chars.length;j++)
			{
				ret[i][j] = chars[i][chars.length - 1 - j];
			}
		}
		return ret;
	}

	public char[][] rotate(char[][] chars)
	{
		char[][] ret = new char[6][6];
		for(int i = 0;i<chars.length;i++)
		{
			for(int j = 0;j<chars.length;j++)
			{
				ret[i][j] = chars[chars.length -1- j][i];
			}
		}
		return ret;
	}


}
