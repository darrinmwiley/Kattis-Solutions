#include <stdio.h>
#include <iostream>
#include <cmath>
#include <cstring>
using namespace std;

int sum(int n)
{
    int x = 0;
    while(n!=0)
    {
        x+=n%10;
        n/=10;
    }
    return x;
}

int main()
{
   int L,D,X;
   scanf("%d %d %d",&L,&D,&X);
   bool flag = 0;
   int m = 0;
   for(int i = L;i<=D;i++)
   {
       if(sum(i)==X)
       {
           m = i;
           if(!flag)
           {
               cout<<i<<endl;
               flag = 1;
           }
       }
   }
   cout<<m;
}
