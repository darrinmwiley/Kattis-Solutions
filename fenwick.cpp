#include<stdio.h>
#include<iostream>
#include<stack>
#include<cmath>
#include<string.h>
using namespace std;

int main(){
    int N;
    int Q;
    cin>>N;
    cin>>Q;
    long long int ints[N+1];
    fill(ints,ints+N+1,0);
    for(int i = 0;i<Q;i++)
    {
        string asdf;
        getline(cin,asdf);
        if(cin.get()=='+')
        {
            int spot;
            long int add;
            cin>>spot;
            cin>>add;
            while(spot<N+1)
            {
                ints[spot]+=add;
                spot+=(spot&-spot);
                if(spot==0)
                    break;
            }
        }else{
            int spot;
            cin>>spot;
            spot--;
            if(spot==-1)
            {
                cout<<0<<endl;
                continue;
            }
            long long int ans = 0;
            while(spot!=0)
            {
                ans+=ints[spot];
                spot-=(spot&-spot);
            }
            cout<<(ans+ints[0])<<endl;
        }
    }
    //printf("Game %d -- %d : %d\n",z+1,dp[N][T],ncr[M][N]-dp[N][T]);
    return 0;
}
