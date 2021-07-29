import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SpinningUp {

	int[] hash;
	boolean[] hashed;
	int[] line;

    public static void main(String[] args) throws NumberFormatException, IOException
    {
    	int[] x = new int[] {0,0,0,0,0};
    	ArrayList<int[]> list = new ArrayList<int[]>();
    	//int ans = new Spinners().solve(new int[] {9,9,9,4});
    	new SpinningUp().run();
    	//Spinners s = new Spinners();
    	//s.hash = new int[1<<11];
    	//s.hashed = new boolean[1<<11];
    	//int other = s.solve(new int[] {4,4,9,9,9});
    	//int other = s.answer(new int[] {3,9,9,9}, 2, false, false, 4);
    	//int other = s.answer(new int[] {4,9,9}, 2, true, false, 4);
    	//int other = s.answer(new int[] {9}, 1, true, false, 0);
    	//System.out.println(other);
    	//System.out.println(ans);
    	/*new Spinners().permute(x,list,0);
    	for(int[] in:list)
    	{
    		int brute = new Spinners().bruteForce(in);
    		int solve = new Spinners().solve(in);
    		if(brute != solve)
    		{
    			System.out.println(Arrays.toString(in)+" "+brute+" "+solve);
    		}
    	}*/
    }



    public void permute(int[] ints, ArrayList<int[]> list, int index)
    {
    	if(index == ints.length)
    		list.add(ints.clone());
    	else
    	{
    		for(int i = 0;i<=9;i++)
    		{
    			ints[index] = i;
    			permute(ints,list,index+1);
    		}
    	}
    }

    public int bruteForce(int[] ints)
    {
    	ArrayList<int[]> list = new ArrayList<int[]>();
    	int[] x = new int[ints.length];
    	permute(x,list,0);
    	int min = 5000;
    	int[] ans = null;
    	for(int[] in:list)
    	{
    		if(pal(in))
    		{
    			if(min > test(ints,in)) {
    			min = Math.min(min, test(ints,in));
    			ans = in;
    			}
    		}
    	}
    	return min;
    }

    public boolean pal(int[] ints)
    {
    	for(int i = 0;i<ints.length;i++)
    		if(ints[i] != ints[ints.length - i - 1])
    			return false;
    	return true;
    }

    public int test(int[] ints, int[] ans)
    {
    	int[] clone = ints.clone();
    	int x = 0;
    	for(int i = ans.length - 1;i >= 0;i--)
    	{
    		while(clone[i] != ans[i])
    		{
    			increment(clone,i);
    			x++;
    		}
    	}
    	return x;
    }

    public void increment(int[] ints, int index)
    {
    	ints[index]++;
    	if(ints[index]==10)
    	{
    		ints[index] = 0;
    		if(index != 0)
    			increment(ints,index - 1);
    	}
    }

    public void run() throws NumberFormatException, IOException
    {
    	hash = new int[1<<11];
    	hashed = new boolean[1<<11];
    	Scanner file = new Scanner(System.in);
    	char[] chars = file.nextLine().toCharArray();
    	int[] line = new int[chars.length];
    	for(int i = 0;i<line.length;i++)
    		line[i] = chars[i]-'0';
    	int x =solve(line);
    	System.out.println(x);
    }

    public int solve(int[] line)
    {
    	//System.out.println(Arrays.toString(line));
    	hash = new int[1<<11];
    	hashed = new boolean[1<<11];
    	int min = 5000;
    	this.line = line;
    	for(int i = 0;i<=9;i++)
    	{
    		min = Math.min(min,answer((line.length - 1) / 2 + 1, false, false, i));
    		min = Math.min(min,answer((line.length - 1) / 2 + 1, false, true, i));
    	}
    	return min;
    }

    public int hashify(int len, boolean rightCarry, boolean leftCarry, int digit)
    {
    	int ret = len << 6;
    	if(rightCarry)
    		ret += 1<<5;
    	if(leftCarry)
    		ret += 1 << 4;
    	ret += digit;
    	return ret;
    }
    //5,1,1,4 == 11
    public int answer(int len, boolean rightCarry, boolean leftCarry, int digit)
    {
    	int h = hashify(len,rightCarry,leftCarry,digit);
    	if(hashed[h])
    		return hash[h];
    	//System.out.println(len+" "+rightCarry+" "+leftCarry+" "+digit);
    	int currentRight = line[line.length/2 + len - 1];
    	int currentLeft = line[(line.length - 1)/2 - len + 1];
    	boolean rightCarryGenerated = false;
    	boolean leftCarryGenerated = false;
    	if(rightCarry) {
    		currentRight++;
    		currentRight %= 10;
    		if(currentRight == 0) {
    			rightCarryGenerated = true;
    			if(len == 1 && line.length % 2 == 1)
    				leftCarryGenerated = true;
    		}
    	}
    	if(len == 1)
    	{
    		if(line.length % 2 == 1)//odd length
    		{
    			int ans = 0;
    			if(leftCarry && !leftCarryGenerated)//force left to carry
    			{
    				ans += 10 - currentLeft;//wrap around
    				currentRight = 0;
    			}
    			int ret = ans + (((digit - currentRight) % 10 + 10 ) % 10);//go from current to goal after (maybe) wrapping
    			if(!leftCarry && leftCarryGenerated)
    			{
    				hashed[h] = true;
					hash[h] = 5000;
					return 5000;
    			}
    			hashed[h] = true;
    			hash[h] = ret;
    			return ret;
    		}else {
    			int ans = (((digit - currentRight) % 10 + 10 ) % 10);//add dist from current right to goal right to ans
    			if(rightCarryGenerated || currentRight > digit)//if right carried to left or will do so
    			{
    				currentLeft++;//carry
    				currentLeft %= 10;
    				if(currentLeft == 0)//set carry to generated
    					leftCarryGenerated = true;
    				if(leftCarry && !leftCarryGenerated)
    				{
    					ans += 10 - currentLeft;//wrap around
    					currentLeft = 0;
    					leftCarryGenerated = true;
    				}
    				if((leftCarryGenerated || currentLeft > digit) && !leftCarry)//if there will be a carry when you don't want one, return impossible
    				{
    					hashed[h] = true;
    					hash[h] = 5000;
    					return 5000;
    				}
    			}
    			if(!leftCarry && currentLeft > digit)
    			{
    				hashed[h] = true;
					hash[h] = 5000;
					return 5000;
    			}
    			int ret = ans + (((digit - currentLeft) % 10 + 10 ) % 10);//otherwise, go from current to goal after (maybe) wrapping
    			if(leftCarry && !leftCarryGenerated)
    				ret = ans + 10 - currentLeft + digit;
    			//System.out.println(ans+" "+currentLeft +" "+digit);
    			hashed[h] = true;
				hash[h] = ret;
				return ret;
    		}
    	}
    	int bestAnswer = 5000;
    	for(int i = 0;i<=9;i++)
    	{
    		if(currentRight > digit)
    			rightCarryGenerated = true;
    		int right = (((digit - currentRight) % 10 + 10 ) % 10);
    		int withCarry = answer(len - 1, rightCarryGenerated, true, i);
    		int withoutCarry = answer(len - 1, rightCarryGenerated, false, i);
    		boolean verbose = false;
    		//with inside carry
    		if(currentLeft + 1 > digit)//carry must happen
    		{
    			if(leftCarry)
    			{
    				bestAnswer = Math.min(bestAnswer,right + withCarry + 10 - (currentLeft + 1) + digit);//cost of right + cost of inside + cost of left with incoming carry
    			}//otherwise answer is impossible bc carry is not allowed
    		}else {
    			int ans = 0;
    			if(leftCarry)
    			{
    				bestAnswer = Math.min(bestAnswer, right + withCarry + 10 - (currentLeft + 1) + digit);//cost of right + cost of inside + cost of left with incoming carry and forced wrap
    			}else {
    				bestAnswer = Math.min(bestAnswer, right + withCarry + (((digit - (currentLeft + 1)) % 10 + 10 ) % 10));
    			}
    		}
    		//without inside carry
    		if(currentLeft > digit)//carry must happen
    		{
    			if(leftCarry)
    			{
    				bestAnswer = Math.min(bestAnswer,right + withoutCarry + 10 - (currentLeft) + digit);//cost of right + cost of inside + cost of left with incoming carry
    			}//otherwise answer is impossible bc carry is not allowed
    		}else {
    			int ans = 0;
    			if(leftCarry)
    			{
    				bestAnswer = Math.min(bestAnswer, right + withoutCarry + (10 - (currentLeft) + digit));//cost of right + cost of inside + cost of left with incoming carry and forced wrap
    			}else {
    				bestAnswer = Math.min(bestAnswer, right + withoutCarry + (((digit - (currentLeft)) % 10 + 10 ) % 10));
    			}
    		}
    		//if(len ==  2 && bestAnswer == 4)
    		//{
    		//	System.out.println(len+" "+digit + " "+i);
    		//}
    	}
    	hashed[h] = true;
    	hash[h] = bestAnswer;
    	return bestAnswer;
    }

    public int cost(int dig, int dig2, boolean carry)
    {
    	if(dig == dig2 && carry)
    	{
    		return 10;
    	}
    	return ((dig2 - dig)%10 + 10) % 10;
    }
}
