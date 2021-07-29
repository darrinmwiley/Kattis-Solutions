#include <bits/stdc++.h>
using namespace std;

#define f(i,a,b) for (int i = a; i < b; i++)
#define fr(i,a,b) for (int i = b-1; i >= a; i--)
#define pb push_back

using ld = long double;
using ll = long long;
#define re(a,b) a=max(a,b)

const int N = 500;
int n;
bool vis[N][N];
int dp[N][N];

int main() {
    ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cin>>n;
	f(i,0,n) f(j,0,n) cin>>vis[i][j];
	f(i,0,n) f(j,0,n) dp[i][j] = -1;
	function<int(int,int)> fu = [&](int i, int j) {
	  if (i > j) return 0;
	  if (dp[i][j] != -1) return dp[i][j];
	  dp[i][j] = fu(i+1,j);
	  f(jp,i+1,j+1) if (vis[i][jp])
	      re(dp[i][j],1+fu(i+1,jp-1) + fu(jp+1,j));
	  return dp[i][j];
	};
	cout << fu(0,n-1) << endl;
    return 0;
}