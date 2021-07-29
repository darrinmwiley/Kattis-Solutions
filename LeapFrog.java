
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LeapFrog {

	BigInteger minX = null;
	int maxStack = 0;

	public static void main(String[] args) throws IOException
	{
		new LeapFrog().run();
	}

	public void run() throws IOException
	{
		Scanner file = new Scanner(System.in);
		int N = file.nextInt();
		ArrayList<frog> frogs = new ArrayList<frog>();
		for(int i = 0;i<N;i++)
		{
			frogs.add(new frog(file.next(),file.next(),1));
		}
		Collections.sort(frogs);

		try{
			//combines identical D's with good modulus's
			for(int i = 0;i<frogs.size();i++)
			{
				for(int j = i+1;j<frogs.size();j++)
				{
					if(frogs.get(i).equivalent(frogs.get(j)))
					{
						frogs.set(i,frogs.get(i).combine(frogs.get(j)));
						frogs.remove(j--);
					}
				}
			}

			ArrayList<ArrayList<frog>> lists = new ArrayList<ArrayList<frog>>();
			int index = 0;
			BigInteger d = frogs.get(0).d;
			while(index!=frogs.size())
			{
				lists.add(new ArrayList<frog>());
				int l = lists.size()-1;
				while(index!=frogs.size() && frogs.get(index).d.equals(d))
				{
					lists.get(l).add(frogs.get(index));
					index++;
				}
				if(index!=frogs.size())
					d = frogs.get(index).d;
			}

			bruteForce(lists,0,null);
			System.out.println(minX+" "+maxStack);
		}catch(Exception ex){System.out.println("it's in here");}


	}

	public void bruteForce(ArrayList<ArrayList<frog>> lists, int index, frog current)
	{
		if(index==lists.size())
		{
			if(current.weight>=maxStack)
			{
				if(minX==null)
				{
					maxStack = current.weight;
					minX = current.x;
				}else{
					maxStack = current.weight;
					if(current.x.compareTo(minX)<0)
						minX = current.x;
				}
			}
		}else{
			for(frog f:lists.get(index))
			{
				if(current==null)
					bruteForce(lists,index+1,f);
				else
					bruteForce(lists,index+1,current.combine(f));
			}
		}
	}

	private class frog implements Comparable<frog>{

		BigInteger x;
		BigInteger d;
		int weight;

		public frog(String x, String d, int w)
		{
			this(new BigInteger(x),new BigInteger(d),w);
		}

		public frog(BigInteger x, BigInteger d, int weight)
		{
			this.x = x;
			this.d = d;
			this.weight = weight;
		}

		public boolean equivalent(frog f)
		{
			return f.d.equals(d) && f.x.mod(f.d).equals(x.mod(d));
		}

		public frog combine(frog f)
		{
			frog left;
			frog right;
			if(x.compareTo(f.x)<0)
			{
				left = this;
				right = f;
			}else {
				left = f;
				right = this;
			}
			if(left.d.equals(right.d))
			{
				if(left.x.mod(left.d).equals(right.x.mod(right.d)))
				{
					right.weight+=left.weight;
					return right;
				}else
					return null;
			}
			BigInteger hops = solveMod(right.d,wrap(left.x.subtract(right.x),left.d),left.d);
			return new frog(right.x.add(hops.multiply(right.d)),right.d.multiply(left.d),left.weight+right.weight);
		}

		public BigInteger wrap(BigInteger neg, BigInteger mod)
		{
			return neg.mod(mod).add(mod).mod(mod);
		}

		public BigInteger solveMod(BigInteger A, BigInteger B, BigInteger M) //find x such that Ax = B mod M
		{
			BigInteger inverse = A.modInverse(M);
			return B.multiply(inverse).mod(M);
		}

		public String toString()
		{
			return x+" "+d+" "+weight;
		}

		@Override
		public int compareTo(frog o) {
			return d.compareTo(o.d);
		}
	}
}
