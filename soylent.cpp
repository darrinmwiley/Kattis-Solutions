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
        int x;
        scanf("%d", &x);
        cout<<(x+399)/400<<endl;
   }
}