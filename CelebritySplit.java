import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CelebritySplit {

    public static void main(String[] args)
    {
        new CelebritySplit().run();
    }

    public void run()
    {
        Scanner file = new Scanner(System.in);
        while(file.hasNext())
        {
            int N = file.nextInt();
            if(N==0)
                return;
            int[] ints = new int[N];
            int total = 0;
            for(int i = 0;i<N;i++)
            {
                ints[i] = file.nextInt();
                total+=ints[i];
            }
            int halfLength = ints.length/2;
            int[] left = new int[halfLength];
            int[] right = new int[N-halfLength];
            for(int i = 0;i<left.length;i++)
            {
                left[i] = ints[i];
            }
            for(int i =0 ;i<right.length;i++)
            {
                right[right.length-1-i] = ints[ints.length-1-i];
            }
            ArrayList<triple> leftTriples = new ArrayList<triple>();
            ArrayList<triple> rightTriples = new ArrayList<triple>();
            permute(left,0,0,0,0,leftTriples);
            permute(right,0,0,0,0,rightTriples);
            int best = total;
            Comparator<triple> comp = new Comparator<triple>(){

                @Override
                public int compare(triple o1, triple o2) {
                    int d1 = o1.getDif();
                    int d2 = o2.getDif();
                    if(d1-d2==0)
                        return o1.S-o2.S;
                    else
                        return d1-d2;
                }
            };
            Collections.sort(rightTriples,comp);
            for(triple t:leftTriples)
            {
                triple tr =findBest(rightTriples,-t.getDif());
                if(tr!=null)
                    best = Math.min(best,t.S+tr.S);
            }
            System.out.println(best);
        }
    }

    public triple findBest(ArrayList<triple> list, int dif)
    {
        int L = -1;
        int R = list.size();
        triple best = null;
        while(R-L>1)
        {
            int M = (R+L)/2;
            triple t = list.get(M);
            if(t.getDif()==dif)
                best = t;
            if(t.getDif()>=dif)
                R = M;
            else
                L = M;
        }
        return best;
    }

    public void permute(int[] houses,int A, int B, int S, int index, ArrayList<triple> triples)
    {
        if(index==houses.length){
            triples.add(new triple(A,B,S));
            return;
        }
        int x = houses[index];
        permute(houses,A+x,B,S,index+1,triples);
        permute(houses,A,B+x,S,index+1,triples);
        permute(houses,A,B,S+x,index+1,triples);
    }

    private class triple{
        int A,B,S;
        public triple(int a, int b, int s)
        {
            A = a;
            B = b;
            S = s;
        }
        public int getDif()
        {
            return A-B;
        }
        public String toString()
        {
            return "(A="+A+",B="+B+",S="+S+")";
        }
    }

}
