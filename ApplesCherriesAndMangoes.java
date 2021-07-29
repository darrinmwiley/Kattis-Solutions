import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ApplesCherriesAndMangoes {

    int MOD = 1000000007;
    long[] factmod = new long[600001];
    long[] powmod = new long[600001];
    long[] invmod = new long[600001];

    public static void main(String[] args) throws Exception
    {
        new ApplesCherriesAndMangoes().run();
    }

    //have to add modular arithmetic after it is functioning without

    public void run() throws Exception
    {
        factmod[0] = 1;
        powmod[0] = 1;
        invmod[0] = invmod[1] = 1;
        for(int i = 1;i<factmod.length;i++)
        {
            factmod[i] = (factmod[i-1]*i)%MOD;
            powmod[i] = (2*powmod[i-1])%MOD;
        }
        invmod[1]=1;
        invmod[0]=1;
        for(int i=2;i<factmod.length;i++) {
          invmod[i]=(MOD-(MOD/i)*invmod[MOD%i]%MOD);//calculate modular inverses of 1 through 600000
        }
        for(int i=1;i<factmod.length;i++)  {//multiply them out to get the inverse factorial
          invmod[i]=invmod[i-1]*invmod[i]%MOD;
        }

        Scanner file = new Scanner(System.in);
        int A = file.nextInt();
        int C = file.nextInt();
        int M = file.nextInt();
        if(C<M)
        {
            int save = C;
            C = M;
            M = save;
        }
        int N = A+C+M;
        long ans = partition(A-1,C,M);
        ans += partition(A,C,M)*2;
        ans += partition(A+1,C,M);
        ans %= MOD;
        System.out.println(ans);
    }

    public long partition(int K, int C, int M)
    {
        if(K==0)
            return 0;
        int diff = C-M;
        long sum = 0;
        for(int i = 0;i<=(K-diff)/2;i++)//i is number of cancellations
        {
            int Odds = diff;//does not include cancellation odds
            int Cancellations = i;
            int E = K-Odds-Cancellations*2;
            int totalOdds = Odds+2*Cancellations;
            int remaining = C+M-totalOdds-2*E;
            if(remaining<0)
                continue;
            long configurations = ncrmod(K+remaining/2-1,remaining/2);
            long arrangeEOCSpec = arrangements(K,E,Odds+Cancellations,Cancellations);//even, more C, more M
            sum += arrangeEOCSpec * configurations % MOD * powmod[E] % MOD;
        }
        return sum;
    }

    public long ncrmod(int n, int r)
    {
        if(r==0)
            return 1;
        return (factmod[n] * invmod[r] % MOD * invmod[n-r] % MOD) % MOD;
    }

    public long arrangements(int k, int a, int c, int m)
    {
        return factmod[a+c+m]*invmod[a] % MOD * invmod[c] % MOD * invmod[m] % MOD;
    }

    public long modInverse(long x)
    {
        return new BigInteger(x+"").modInverse(new BigInteger(MOD+"")).longValue();
    }
}
