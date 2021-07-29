#include <bits/stdc++.h>
using namespace std;

#define f(i,a,b) for (int i = a; i < b; i++)
#define ri(a,b) a=min(a,b)

using ll = long long;
const ll oo = 3e18;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int n; cin>>n;
	while (n--) {
		int N = 3;
		ll w[N],h[N];
		f(i,0,N) cin>>w[i]>>h[i];
		int p[N];
		f(i,0,N) p[i] = i;
		
		ll ans = oo;
		do {
			f(mask,0,8) {
				f(j,0,N) if (mask>>j&1) swap(w[j],h[j]);
				// validate
				f(j,0,N) if (w[j] > w[p[0]]) goto end;
				// config p[1]
				ri(ans, w[p[0]]*(h[p[0]]+h[p[1]]+h[p[2]]));
				// config p[2]	
				ri(ans, max(h[p[2]],h[p[0]]+h[p[1]]) * 
						max(w[p[1]],w[p[0]]+w[p[2]]));
				// config 3
				ri(ans, (max(h[p[1]],h[p[2]])+h[p[0]]) * 
						(max(w[p[0]],w[p[1]]+w[p[2]])));
end:{}
				f(j,0,N) if (mask>>j&1) swap(w[j],h[j]);
			}
		} while (next_permutation(p,p+N));

		cout << ans << endl;
	}
	return 0;
}
