#include <stdio.h>
#include <iostream>
#include <cmath>
#include <cstring>
using namespace std;

int main()
{
   int a,b;
   scanf("%d\n%d",&a,&b);
   int x = ((360-a+b)+360)%360;
   int y = 360-x;
   if(x<=y)
    cout<<x;
   else
    cout<<-y;
}
