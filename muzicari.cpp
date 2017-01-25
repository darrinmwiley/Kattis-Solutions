#include<stdio.h>
#include<iostream>
#include<math.h>

using namespace std;

int main(){
    int T;
    int N;
    cin>>T;
    cin>>N;
    int v[N];
    for(int i = 0;i<N;i++)
        cin>>v[i];
    int last[T+1];
    fill(last,last+T+1,-2);
    last[0] = -1;
    int start = 0;
    for(int i = 0;i<N;i++)
    {
        for(int j = start;j>=0;j--)
        {
            if(last[j]!=-2&&j+v[i]<T+1&&last[j+v[i]]==-2)
                last[j+v[i]] = i;
        }
        if(start+v[i]<T+1)
            start = max(start,start+v[i]);
        else
            start = T;
    }
    int ans[N];
    int spot = T;
    while(last[spot]==-2)
        spot--;
    fill(ans,ans+N,-1);
    while(spot!=0)
    {
        ans[last[spot]]=spot-=v[last[spot]];
    }
    int spot2 = 0;
    for(int i = 0;i<N;i++)
    {
        if(ans[i]==-1)
        {
            ans[i] = spot2;
            spot2+=v[i];
        }
    }
    for(int i = 0;i<N;i++)
        cout<<ans[i]<<" ";
    cout<<endl;
}
