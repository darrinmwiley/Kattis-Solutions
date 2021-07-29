/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>

using namespace std;
long long a,m,n;
bool check1(long long a)
{
    if (a % n == 0 || a % m == 0) return true;
    return false;
}
bool check2(long long a)
{
    for (long long i = 1 ; i < n ; i++)
    {
        long long k = a / i;
        if (a % i != 0)
        {
            continue;
        }
        if (k <= m)
        {
            return true;
        }
    }
    return false;
}
int main()
{
    cin >> n >> m >> a;
    if (check1(a) || check1(m*n - a))
    {
        cout <<1;
    }
    else
    if (check2(a) || check2(m*n - a))
    {
        cout <<2;
    }
    else
    {
        cout <<3;
    }
}