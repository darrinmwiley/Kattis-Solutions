#include<stdio.h>
#include<iostream>
#include<stack>
#include<cmath>
#include<string.h>
using namespace std;

int main(){
    int N;
    int P;
    double p;
    scanf("%d %lf",&N,&p);
    P = (int)(p*10000+.5);
    long int dp[10001] ;
    fill(dp,dp+10001,20000000);
    dp[0] = 0;
    int wts[N];
    int vals[N];
    for(int i = 0;i<N;i++){
        cin>>wts[i];
        double x;
        cin>>x;
        vals[i] = (int)(x*10000+.5);
    }
    for(int i = 0;i<N;i++)
        for(int j = 10000;j>=vals[i];j--)
            dp[j] = min(dp[j],dp[j-vals[i]]+wts[i]);
    long int m = 20000000;
    for(int i = P;i<10001;i++)
         m = min(dp[i],m);
    cout<<m<<endl;
}
