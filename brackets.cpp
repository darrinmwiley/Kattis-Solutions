#include <stdio.h>
#include <string>
#include <iostream>

using namespace std;

int main()
{
    string brackets;
    cin>>brackets;
    int L = brackets.length()+1;
    int L2 =L/2+1;
    bool dp[L][L2][3] = {};
    dp[0][0][0] = dp[0][0][1] = 1;
    if(brackets.length()%2!=0)
    {
        printf("impossible");
        return 0;
    }
    for(int i = 1;i<L;i++)
        {
            char ch = brackets[i-1];
            for(int j = (i+1)/2;j<L2;j++)
            {
                if(ch=='(')
                {
                    dp[i][j][1] = dp[i-1][j][0]||dp[i-1][j][1];
                    if(j!=0)
                    {
                        dp[i][j][0] = dp[i-1][j-1][0];
                        dp[i][j][2] = dp[i-1][j-1][1]||dp[i-1][j-1][2];
                    }
                }else{
                    dp[i][j][0] = dp[i-1][j][0];
                    if(j!=0)
                        dp[i][j][1] = dp[i-1][j-1][0]||dp[i-1][j-1][1];
                    dp[i][j][2] = dp[i-1][j][1]||dp[i-1][j][2];
                }
            }
        }
        if(dp[brackets.length()][brackets.length()/2][0]||dp[brackets.length()][brackets.length()/2][1]||dp[brackets.length()][brackets.length()/2][2])
        {
            printf("possible");
        }else
            printf("impossible");
}
