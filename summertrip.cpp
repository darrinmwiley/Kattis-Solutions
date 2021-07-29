#include <bits/stdc++.h>
using namespace std;
long long kq,near[200000][250],cd;
string s;
int main()
{
    cin >> s;
    int cd = s.size();
    s = '0' + s;
    for (int i = 1 ; i <= cd ; i++)
    {
        for (int e = 'a' ; e <= 'z' ; e++)
        {
            near[i][e] = near[i-1][e];
            if (e == s[i]) near[i][e] = i;
        }
    }
    for (int i = 2 ; i <= cd ; i++)
    {
        int pre = near[i-1][s[i]];
        for (int e = 'a' ; e <= 'z' ; e++)
        {
            if (e == s[i]) continue;
            if (near[i][e] > pre) kq++;
        }
    }
    cout <<kq;
}