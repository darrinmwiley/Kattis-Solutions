import java.util.Scanner;

public class SGCoin {
	
	public static void main(String[] args) throws Exception {
		new SGCoin().run();
	}
	
	public void run()
	{
		Scanner file = new Scanner(System.in);
		long hash = file.nextLong();
		String str = "yeet";
		long token = getToken(hash,str);
		long firstHash = H(hash, str, token);
		long token2 = getToken(firstHash, str);
		long secondHash = H(firstHash,str,token2);
		System.out.println(str+" "+token);
		System.out.println(str+" "+token2);
	}
	
	public long getToken(long previousHash, String transaction)
	{
		long hash = H(previousHash, transaction, 0);
		long next = next107(hash);
		return next-hash;
	}
	
	public long next107(long l)
	{
		return ((long)Math.ceil(l/10000000.0))*10000000;
	}
	
	static long H(long previousHash, String transaction, long token) 
	{
		long v = previousHash;
		for (int i = 0; i < transaction.length(); i++) {
		  v = (v * 31 + transaction.charAt(i)) % 1000000007;
		}
		return (v * 7 + token) % 1000000007;
	}
}
