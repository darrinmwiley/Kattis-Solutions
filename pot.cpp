#include <stdio.h>
#include <iostream>
#include <cmath>
using namespace std;

int main()
{
   int zz;
   scanf("%d", &zz);
   long int sum = 0;
   for(int z = 0;z<zz;z++)
   {
       int n;
       scanf("%d", &n);
       sum+=pow(n/10,n%10);
   }
   cout<<sum;
}