#include <bits/stdc++.h>
using namespace std;
int cd , k , s;
long double f[10001][501] , kq;
int main()
{
    cin >> cd >> s >> k;
    long double n = s;
    f[1][1] = 1;
    //cout <<f[1][1]<<'\n';
    for (int i = 2 ; i <= cd ; i++)
    {
        for (int e = 1 ; e <= min(s,i) ; e++)
        {
            long double sp = e;
            f[i][e] += f[i-1][e-1] * (n - e + 1);
            f[i][e] += f[i-1][e] * sp;
            f[i][e] /= n;
            //cout <<"f["<<i<<"]["<<e<<"] : "<<f[i][e]<<" "<<f[2][2]/n<<'\n';
        }
    }
    for (int i = k ; i <= s ; i++)
    {
        kq += f[cd][i];
    }
    cout <<fixed<<setprecision(10);
    cout <<kq;
}