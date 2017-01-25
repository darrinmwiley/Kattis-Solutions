#include<stdio.h>
#include<iostream>
#include<stack>
#include<cmath>
#include<string.h>
using namespace std;

int main(){
    long int N;
    cin>>N;
    while(N!=0){
        if(N==3)
            cout<<"4"<<endl;
        else if(N<7)
            cout<<"No such base"<<endl;
        else{
            int M = N-3;
            int root = (int)(sqrt(N));
            int ans = M;
            for(int i = 2;i<=root;i++){
                if(M%i==0)
                {
                    if(i<4)
                    {
                        if(M/i>3)
                            ans = min(ans,M/i);
                    }else{
                        ans = min(ans,i);
                    }

                }
            }
            cout<<ans<<endl;
        }
        cin>>N;
    }
}
