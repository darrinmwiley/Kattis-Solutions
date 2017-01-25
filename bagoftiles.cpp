#include<stdio.h>
#include<iostream>
#include<stack>
#include<cmath>
#include<string.h>
using namespace std;

int main(){
	int zz;
	cin>>zz;
	int ncr[31][31];
	for(int i = 0;i<31;i++)
    {
        ncr[i][0] = 1;
        ncr[i][i] = 1;
    }
    for(int i = 2;i<31;i++)
        for(int j = 1;j<i;j++)
            ncr[i][j] = ncr[i-1][j]+ncr[i-1][j-1];
	for(int z = 0;z<zz;z++)
    {
        int M;
        cin>>M;
        int d[M];
        for(int i = 0;i<M;i++)
            scanf("%d",&d[i]);
        int N;
        int T;
        cin>>N;
        cin>>T;
        int dp[N+1][T+1];
        memset(dp,0,sizeof(dp));
        dp[0][0] = 1;
        for(int i = 0;i<M;i++)
        {
            for(int j = N;j>0;j--)
            {
                for(int k = T;k>=d[i];k--)
                {
                    dp[j][k]+=dp[j-1][k-d[i]];
                }
            }
        }
        printf("Game %d -- %d : %d\n",z+1,dp[N][T],ncr[M][N]-dp[N][T]);
    }
    return 0;
}