#include <stdio.h>
#include <iostream>
#include <cmath>
#include <cstring>
using namespace std;

int main()
{
   int ans =0;
   bool bools[42];
   memset(bools,0,sizeof(bools));
   for(int i = 0;i<10;i++)
   {
       int z;
       scanf("%d",&z);
       if(!bools[z%42])
       {
           ans++;
           bools[z%42] = 1;
       }
   }
   cout<<ans;
}
