import java.util.Arrays;
import java.util.Scanner;


public class AplusB {

    public void run(){
        Scanner file = new Scanner(System.in);
        int N = file.nextInt();
        double[] degrees = new double[100001];
        for(int i = 0;i<N;i++)
            degrees[file.nextInt()+50000]++;
        double z = degrees[50000];
        degrees[50000] = 0;
        double[] mult = multiply(degrees,degrees);
        for(int i = 0;i<degrees.length;i++)
            mult[i*2]-=degrees[i];
        long ans = 0;
        degrees[50000] = z;
        for(int i = 0; i<degrees.length;i++)
        {
            ans+=Math.round(mult[i+50000])*Math.round(degrees[i]);
        }
        for(int i = 0;i<degrees.length;i++)
        {
            if(i!=50000)
                ans+=Math.round(z)*Math.round(degrees[i])*Math.round(degrees[i]-1)*2;
        }
        ans+=Math.round(z)*Math.round(z-1)*Math.round(z-2);
        System.out.println(ans);
    }

    public double[] multiply(double[] a, double[] b)
    {
        int pad = a.length+b.length-1;
        int len = Integer.highestOneBit(pad);
        if(len!=pad)
            len*=2;
        complex[] A = new complex[len];
        complex[] B = new complex[len];
        for(int i = 0;i<a.length;i++)
            A[i] = new complex(a[i],0);
        for(int i = a.length;i<A.length;i++)
            A[i] = new complex(0,0);
        for(int i = 0;i<b.length;i++)
            B[i] = new complex(b[i],0);
        for(int i = b.length;i<B.length;i++)
            B[i] = new complex(0,0);
        complex[] A2 = FFT(A.length,A);
        complex[] B2 = FFT(B.length,B);
        complex[] C = new complex[A.length];
        for(int i = 0;i<C.length;i++)
            C[i] = A2[i].times(B2[i]);
        complex[] ans = IFFT(C.length,C);
        double[] ret = new double[A.length];
        for(int i = 0;i<ret.length;i++)
            ret[i] = ans[i].realPart;
        return ret;
    }

    //degree must be of length 2^n
    public complex[] FFT(int len, complex[] degrees)
    {
        if(len==1)
            return new complex[]{degrees[0]};
        complex[] evens = new complex[len/2];
        complex[] odds = new complex[len/2];
        for(int i = 0;i<degrees.length/2;i++)
        {
            evens[i] = degrees[i*2];
            odds[i] = degrees[i*2+1];
        }
        complex[] e = FFT(len/2,evens);
        complex[] d = FFT(len/2,odds);
        complex[] ret = new complex[len];
        for(int k = 0;k<len/2;k++)
        {
            complex wk = eix(2*Math.PI*k/len);
            ret[k] = e[k].plus(wk.times(d[k]));
            ret[k+len/2] = e[k].minus(wk.times(d[k]));
        }
        return ret;
    }

    //degree must be of length 2^n
    public complex[] IFFT(int len, complex[] degrees)
    {
        complex[] ret = new complex[len];
        for(int i = 0;i<len;i++){
            ret[i] = degrees[i].conjugate();
        }

        ret = FFT(len,ret);
        for(int i = 0;i<len;i++){
            ret[i] = ret[i].conjugate().times(1.0/len);
        }
        return ret;
    }

    public complex eix(double x)
    {
        return new complex(Math.cos(x),Math.sin(x));
    }

    public static void main(String[] args)
    {
        new AplusB().run();
    }

    private class complex{

        double realPart;
        double imagPart;

        // makes complex number of the form r + di
        public complex(double r, double d)
        {
            realPart = r;
            imagPart = d;
        }

        public complex plus(complex c)
        {
            return new complex(realPart+c.realPart,imagPart+c.imagPart);
        }

        public complex minus(complex c)
        {
            return new complex(realPart-c.realPart,imagPart-c.imagPart);
        }

        public complex times(complex c)
        {
            return new complex(realPart*c.realPart-imagPart*c.imagPart,imagPart*c.realPart+realPart*c.imagPart);
        }

        public complex times(double scale)
        {
            return new complex(realPart*scale, imagPart*scale);
        }

        public complex conjugate()
        {
            return new complex(realPart,-imagPart);
        }
    }

}
