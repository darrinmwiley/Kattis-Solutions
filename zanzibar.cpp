#include <stdio.h>
#include <iostream>
#include <cmath>
#include <cstring>
using namespace std;

int main()
{
   int zz;
   scanf("%d",&zz);
   for(int z = 0;z<zz;z++)
   {
       int ans = 0;
       int last;
       scanf("%d",&last);
       while(1)
       {
          int next;
          scanf("%d", &next);
          if(!next)
            break;
          ans+=max(0,next-last*2);
          last = next;
       }
       cout<<ans<<endl;
   }
}