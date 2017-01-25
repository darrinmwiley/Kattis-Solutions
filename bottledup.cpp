#include<stdio.h>
#include <string>
#include<math.h>
#include<iostream>
using namespace std;

int main()
{
    int N,a,b;
    scanf("%d %d %d",&N,&a,&b);
    for(int x = N/a;x>=0;x--)
    {
        if((N-x*a)%b==0)
        {
            cout<<x<<" "<<((N-x*a)/b);
            return 0;
        }
    }
    cout<<"Impossible";
}
