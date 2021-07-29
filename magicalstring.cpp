#include "bits/stdc++.h"
#include <algorithm>

using namespace std;

#define pb push_back
#define boolean bool
#define li long long int

string chars;
char become[1001];
int N;
int K;

bool canSpecialProp(char previous, int start)
{
        if(start >= chars.length())
            return false;
        return become[start] != previous && become[start] != ' ';
}

char whatBecome(int start)
{
        if(start >= chars.length() - 2)
            return ' ';
        char ch1 = chars[start];
        char ch2 = ' ';
        int occ1 = 1;
        int occ2 = 0;
        for(int i = start+1;i<chars.length();i++)
        {
            if(chars[i] == ch1)
            {
                occ1++;
            }else if(ch2 == ' ')
            {
                ch2 = chars[i];
                occ2++;
            }else if(ch2 == chars[i])
            {
                occ2++;
            }else {
                if(i-start < 3)
                    return ' ';
                if(occ1 > occ2)
                    return ch1;
                if(occ2 > occ1)
                    return ch2;
            }
            if(occ1 >= 2 && occ2 >= 2)
            {
                if(chars[i] == ch1)
                    return ch2;
                return ch1;
            }
        }
        if(occ1 == 1)
            return ch2;
        return ch1;
}

int solve()
{
    int L1 = chars.length() + 1;
    int L2 = K+1;
    int L3 = 2;
    int dp[chars.length()+1][K+1][2];//location, K, flag
    for(int i = 0;i<L1;i++)
    {
        for(int j = 0;j<L2;j++)
        {
            for(int k = 0;k<2;k++)
                dp[i][j][k] = 0;
        }
    }
    for(int loc = L1 - 1;loc>=0;loc--)
    {
    loop:
        for(int k = 0;k<L2;k++)
        {
            if(k == 0 || chars.length() - loc < 3)//no swaps left or not enough chars
            {
                dp[loc][k][0] = 0;//if not forced replacement, you just don't do anything
                dp[loc][k][1] = -1001;//if forced replacement, being here is illegal so -inf
            }else {
                boolean cont = false;
                boolean flag = false;//have you seen second char
                char ch1 = chars[loc];//first char
                char ch2 = ' ';//second char
                int origNum = 1;//how many of first you have seen
                int newNum = 0;//how many of second
                for(int i = loc+1;i<chars.length();i++)//from start+1..end, look at chars
                {

                    if(cont)
                        break;
                        //if(loc == 0 && k == )
                    if(chars[i] == ch1)//if it's original, just bump count
                    {
                            //adaaddada
                        origNum++;
                        if(i - loc >= 2 && newNum != 2 && canSpecialProp(chars[i], i+1))//if you have seen at least 2 of first character, you can do special propogation
                        {
                            dp[loc][k][0] = max(dp[loc][k][0], i-loc+1+dp[i+1][k-1][1]);//replace
                            dp[loc][k][1] = max(dp[loc][k][1], i-loc+1+dp[i+1][k-1][1]);//replace
                        }
                    }else {//not original character
                        if(!flag)//if you haven't seen second char yet
                        {
                            flag = true;//you've now seen second char
                            ch2 = chars[i];
                            if(i - loc >= 2 && canSpecialProp(chars[i], i+1)){//if you have seen at least 2 of first character, you can do special propogation
                                dp[loc][k][0] = max(dp[loc][k][0], i-loc+1+dp[i+1][k-1][1]);//replace
                                dp[loc][k][1] = max(dp[loc][k][1], i-loc+1+dp[i+1][k-1][1]);//replace
                            }
                            newNum = 1;
                        }else if(chars[i]==ch2)
                        {
                            newNum++;
                        }else{//you've seen some new character that is not ch1 or ch2
                            if(i - loc >=3) {//if you've look at at least 3 characters before this, you can replace them
                                dp[loc][k][0] = max(dp[loc][k][0], (i-loc) + dp[i][k-1][0]);//replace
                                dp[loc][k][1] = max(dp[loc][k][1], (i-loc) + dp[i][k-1][0]);//replace
                            }
                            else//
                            {//
                                dp[loc][k][1] = -1001;
                            }//
                            //if not forced, you can throw away 1 char
                            dp[loc][k][0] = max(dp[loc+1][k][0], dp[loc][k][0]);//throw 1 away
                            cont = true;
                        }
                    }
                    //if you've seen a second anomaly, make replacement
                    if(origNum >= 2 && newNum >= 2)
                    {
                        //System.out.println(loc+" "+k+" "+i+" "+dp[loc][k][0]);
                        dp[loc][k][0] = max(dp[loc][k][0], i-loc + dp[i][k-1][0]);//replace
                        dp[loc][k][1] = max(dp[loc][k][1], i-loc + dp[i][k-1][0]);//replace
                        dp[loc][k][0] = max(dp[loc][k][0], dp[loc+1][k][0]);//throw 1 away
                        cont = true;
                    }
                }
                if(!cont)
                {
                    dp[loc][k][0] = max(dp[loc][k][0], (int)(chars.length() - loc));//replace
                    dp[loc][k][1] = max(dp[loc][k][1], (int)(chars.length() - loc));//replace
                }

            }
        }
    }
    return dp[0][K][0];
}

int main()
{
    ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cin >> N;
	for(int z = 0;z<N;z++)
    {
        cin >> chars;
        for(int i = 0;i<chars.length();i++)
            become[i] = whatBecome(i);
        cin >> K;
        int solution = solve();
        cout << solution << endl;
    }

}

    /*public static void main(String[] args) throws Exception
    {
        new magicalstring4().run();
    }

    public void run() throws Exception{
        file = new BufferedReader(new InputStreamReader(System.in));
        pout = new PrintWriter(System.out);
        int N = Integer.parseInt(file.readLine());
        for(int i = 0;i<N;i++)
        {
            st = new StringTokenizer(file.readLine());
            chars = st.nextToken().toCharArray();
            become = new char[chars.length];
            Arrays.fill(become, ' ');
            for(int j = 0;j<become.length-2;j++)
            {
            		become[j] = this.whatBecome(chars, j);
            }
            int K = Integer.parseInt(st.nextToken());
            pout.println(solve(K, chars));
        }
        pout.flush();
    }

    public boolean canSpecialProp(char[] chars, char previous, int start)
    {
    		if(start >= become.length)
    			return false;
    		return become[start] != previous && become[start] != ' ';
    }

    public char whatBecome(char[] chars, int start)
    {
    		if(start >= chars.length - 2)
    			return ' ';
    		char ch1 = chars[start];
    		char ch2 = ' ';
    		int occ1 = 1;
    		int occ2 = 0;
    		for(int i = start+1;i<chars.length;i++)
    		{
    			if(chars[i] == ch1)
    			{
    				occ1++;
    			}else if(ch2 == ' ')
    			{
    				ch2 = chars[i];
    				occ2++;
    			}else if(ch2 == chars[i])
    			{
    				occ2++;
    			}else {
    				if(i-start < 3)
    					return ' ';
    				if(occ1 > occ2)
    					return ch1;
    				if(occ2 > occ1)
    					return ch2;
    			}
    			if(occ1 >= 2 && occ2 >= 2)
    			{
    				if(chars[i] == ch1)
    					return ch2;
    				return ch1;
    			}
    		}
    		if(occ1 == 1)
    			return ch2;
    		return ch1;
    }

    public int solve(int K, char[] chars)
    {
        int[][][] dp = new int[chars.length+1][K+1][2];//location, K, flag
        for(int loc = dp.length - 1;loc>=0;loc--)
        {
        loop:
            for(int k = 0;k<dp[loc].length;k++)
            {
                if(k == 0 || chars.length - loc < 3)//no swaps left or not enough chars
                {
                    dp[loc][k][0] = 0;//if not forced replacement, you just don't do anything
                    dp[loc][k][1] = -1001;//if forced replacement, being here is illegal so -inf
                    continue loop;
                }else {
                    boolean flag = false;//have you seen second char
                    char ch1 = chars[loc];//first char
                    char ch2 = ' ';//second char
                    int origNum = 1;//how many of first you have seen
                    int newNum = 0;//how many of second
                    for(int i = loc+1;i<chars.length;i++)//from start+1..end, look at chars
                    {
                    		//if(loc == 0 && k == )
                        if(chars[i] == ch1)//if it's original, just bump count
                        {
                        		//adaaddada
                            origNum++;
                            if(i - loc >= 2 && newNum != 2 && canSpecialProp(chars, chars[i], i+1))//if you have seen at least 2 of first character, you can do special propogation
                            {
                                dp[loc][k][0] = Math.max(dp[loc][k][0], i-loc+1+dp[i+1][k-1][1]);//replace
                                dp[loc][k][1] = Math.max(dp[loc][k][1], i-loc+1+dp[i+1][k-1][1]);//replace
                            }
                        }else {//not original character
                            if(!flag)//if you haven't seen second char yet
                            {
                                flag = true;//you've now seen second char
                                ch2 = chars[i];
                                if(i - loc >= 2 && canSpecialProp(chars, chars[i], i+1)){//if you have seen at least 2 of first character, you can do special propogation
                                    dp[loc][k][0] = Math.max(dp[loc][k][0], i-loc+1+dp[i+1][k-1][1]);//replace
                                    dp[loc][k][1] = Math.max(dp[loc][k][1], i-loc+1+dp[i+1][k-1][1]);//replace
                                }
                                newNum = 1;
                            }else if(chars[i]==ch2)
                            {
                                newNum++;
                            }else{//you've seen some new character that is not ch1 or ch2
                                if(i - loc >=3) {//if you've look at at least 3 characters before this, you can replace them
                                    dp[loc][k][0] = Math.max(dp[loc][k][0], (i-loc) + dp[i][k-1][0]);//replace
                                    dp[loc][k][1] = Math.max(dp[loc][k][1], (i-loc) + dp[i][k-1][0]);//replace
                                }
                                else//
                                {//
                                    dp[loc][k][1] = -1001;
                                }//
                                //if not forced, you can throw away 1 char
                                dp[loc][k][0] = Math.max(dp[loc+1][k][0], dp[loc][k][0]);//throw 1 away
                                continue loop;
                            }
                        }
                        //if you've seen a second anomaly, make replacement
                        if(origNum >= 2 && newNum >= 2)
                        {
                            //System.out.println(loc+" "+k+" "+i+" "+dp[loc][k][0]);
                            dp[loc][k][0] = Math.max(dp[loc][k][0], i-loc + dp[i][k-1][0]);//replace
                            dp[loc][k][1] = Math.max(dp[loc][k][1], i-loc + dp[i][k-1][0]);//replace
                            dp[loc][k][0] = Math.max(dp[loc][k][0], dp[loc+1][k][0]);//throw 1 away
                            continue loop;
                        }
                    }
                    dp[loc][k][0] = Math.max(dp[loc][k][0], chars.length - loc);//replace
                    dp[loc][k][1] = Math.max(dp[loc][k][1], chars.length - loc);//replace
                }
            }
        }
        return dp[0][K][0];
    }

}*/
