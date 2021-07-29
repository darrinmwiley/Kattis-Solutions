#include <bits/stdc++.h>
using namespace std;

#define f(i,a,b) for (int i = a; i <= b; i++)

using ld = long double;
using ll = long long;
const long long oo = 1e11;
long long n,m,t,d;
long long g[505][505],d1[10000],a[505],w[505][505];
int main() {
    ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cin >> n >> m >> t >> d;
	f(i,0,n) f(j,0,n) g[i][j] = w[i][j] = oo;
	f(i,0,n) g[i][i] = w[i][i] = 0; 
    for (int i = 1 ; i <= t ; i++)
    {
        cin >> a[i];
        d1[a[i]]++;
    }
    t++;
    a[t] = 1;
    if (d1[n] == 0) t++,a[t] = n;
    for (int i = 1 ; i <= m ; i++)
    {
        int u , v;
        cin >> u >> v;
        cin >> w[u][v];
        w[v][u] = w[u][v];
    }
    f(k,1,n) f(i,1,n) f(j,1,n) if (w[i][k] + w[k][j] < w[i][j])
        w[i][j] = w[i][k]+w[k][j];
     for (int i = 1 ; i <= t ; i++)
     for (int e = i+1 ; e <= t ; e++)
     {
         int u = a[i] , v = a[e];
         if (w[a[i]][a[e]] > d) g[u][v] = g[v][u] = oo;
         else g[v][u] = g[u][v] = w[v][u];
     }
    f(i,1,n) f(j,1,n) f(k,1,n) if (g[i][k] + g[k][j] < g[i][j])
        g[i][j] = g[i][k]+g[k][j];
    if (g[1][n] >= oo) cout <<"stuck";
    else cout<<g[1][n];
    return 0;
}