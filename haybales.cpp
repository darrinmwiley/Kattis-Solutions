#include <bits/stdc++.h>
using namespace std;

#define f(i,a,b) for (int i = a; i < b; i++)

using ll = long long;

const ll oo = 1e18;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);	

	string s; 
	while (cin>>s) {
		int n = s.length();
		int a[n];
		f(i,0,n) a[i] = s[i]=='P', a[i] += i?a[i-1]:0;

		ll dp[n][n];
		f(i,0,n) f(j,0,n) dp[i][j] = oo;

		f(l,0,n) f(i,0,n) if (i+l < n) {
			int j = i+l;
			int cnt = a[j]-a[i]+(s[i]=='P');
			if (!cnt || cnt == j-i+1)
				dp[i][j] = 0;
			else if (j-i+1 <= 3)
				dp[i][j] = 1;
			else {
				f(k,i,j) {
					ll inversions =  1LL*(a[k]-a[i]+(s[i]=='P')) * (j-k-(a[j]-a[k]));
					dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j] + (inversions+1)/2);
				}
			}
		}
		cout << dp[0][n-1] << endl;
	}
	return 0;
}
