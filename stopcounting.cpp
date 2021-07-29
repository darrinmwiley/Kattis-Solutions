#include <bits/stdc++.h>
using namespace std;

#define f(i,a,b) for (int i = a; i < b; i++)
#define fr(i,a,b) for (int i = b-1; i >= a; i--)

using ld = long double;
using ll = long long;
#define re(a,b) a=max(a,b)

int main() {
    ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int n; cin>>n;
	int a[n];
	f(i,0,n) cin>>a[i];
	ld ans = 0, res = 0;
	f(i,0,n) {
	    res += a[i];
	    re(ans,res/(i+1));
	}
	res = 0;
	fr(i,0,n) {
	    res += a[i];
	    re(ans,res/(n-i));
	}
	cout << setprecision(20) << ans << endl;
    return 0;
}