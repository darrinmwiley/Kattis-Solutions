#include <bits/stdc++.h>
using namespace std;
long long fact[20] , cnt[300][300];
long long kq , need[300][300] , cd;
string s[100001];
long long bigmod(long long base , long long hat)
{
    if (base == 0) return 0;
    if (hat == 0) return 1;
    if (hat == 1) 
    {
        return base;
    }
    if (hat == 2) 
    {
        return base*(base-1);
    }
    if (hat == 3) 
    {
        return base*(base-1)*(base-2);
    }
    if (hat == 4)
    {
        return base*(base-1)*(base-2)
                *(base-3);
    }
    
}
long long get(long long a , long long b , long long c , long long d)
{
    long long kq;
    need[a][b] ++;
    need[b][c] ++;
    need[d][c] ++;
    need[a][d] ++;
    kq = bigmod(cnt[a][b] , need[a][b]);
    need[a][b] = 0;
    kq *= bigmod(cnt[b][c] , need[b][c]);
    need[b][c] = 0;
    kq *= bigmod(cnt[d][c] , need[d][c]);
    need[d][c] = 0;
    kq *= bigmod(cnt[a][d] , need[a][d]);
    need[a][d] = 0;
    return kq;
}
int main()
{
    cin >> cd;
    for (long long i = 1 ; i <= cd ; i++)
    {
        cin >> s[i];
        if (s[i].size() == 1)
        {
            cout <<0;
            return 0;
        }
        s[i] = '0' + s[i];
    }
    for (long long i = 1 ; i <= cd ; i++)
    {
        cnt[s[i][1]][s[i][s[i].size()-1]]++;
    }
    for (long long i = 'A' ; i <= 'Z' ; i++)
    for (long long e = 'A' ; e <= 'Z' ; e++)
    for (long long f = 'A' ; f <= 'Z' ; f++)
    for (long long g = 'A' ; g <= 'Z' ; g++)
    {
        char t = i;
        char t1 = e;
        char t2 = f;
        char t3 = g;
        kq += get(i , e , f , g);
        //if (get(i,e,f,g) != 0)
        //cout <<t<<" "<<t1<<" "<<t2<<" "<<t3<<" "<<get(i,e,f,g)<<'\n';
    }
    cout <<kq<<'\n';
}