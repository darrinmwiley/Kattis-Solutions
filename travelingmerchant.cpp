#include <bits/stdc++.h>
using namespace std;

#define f(i,a,b) for (int i = a; i < b; i++)
#define fr(i,a,b) for (int i = b-1; i >= a; i--)
#define pb push_back

using ld = long double;
using ll = long long;
using vi = vector<int>;
#define re(a,b) a=max(a,b)
#define ri(a,b) a=min(a,b)

const int oo = 1e9+7;

const int N = 1e5;
int n;
const int block = 500;
int mx[500], mi[500], bf[500];
int qs[N], qt[N];
int ans[N];

void solve(vi a, vi queries) {
    {
        int i = 0, b = 0;
        for (; i < n; i+=block, b++) {
            mx[b] = -1; mi[b] = oo; bf[b] = 0;
            int x = min(n,i+block);
            for (int j = i; j < x; j++) {
            	re(mx[b],a[j]), ri(mi[b],a[j]), re(bf[b],a[j]-mi[b]);
            }
        }
    }
    {
        for (int q : queries) {
            int s = qs[q], t = qt[q];
            int bs = s/block, bt = t/block;
            int mii = oo, mxi = -1;
            
            int res = 0;
            
            if (bs == bt) {
            	for (int i = s; i <= t; i++) {
            		re(mxi,a[i]); ri(mii,a[i]);
            		re(res,a[i]-mii);
            	}
            } else {
            	for (int i = s; i/block == bs; i++) {
	                re(res,a[i]-mii);
	                re(mxi,a[i]); ri(mii,a[i]);
	            }
	            for (int b = bs+1; b < bt; b++) {
	                re(res,mx[b]-mii); re(res,bf[b]);
	                re(mxi,mx[b]); ri(mii,mi[b]);
	            }
	            for (int i = t/block*block; i <= t; i++) {
	                re(res,a[i]-mii);
	                re(mxi,a[i]); ri(mii,a[i]);
	            }
            }
            ans[q] = res;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	cin>>n;
	vi v[7];
	f(i,0,7) v[i] = vi(n,0);
	f(i,0,n) {
	    cin>>v[0][i];
	    int d; cin>>d;
	    f(j,1,7) {
	        if (j<=3) v[j][i] = v[j-1][i]+d;
	        else v[j][i] = v[j-1][i]-d;
	    }
	}
	int q; cin>>q;
	f(i,0,q) {
	    cin>>qs[i]>>qt[i];
	    qs[i]--; qt[i]--;
	}
	f(_,0,2) {
	    f(g,0,7) {
    	    vi a(n); vi queries;
    	    f(i,0,n) a[i] = v[(i+g)%7][i];
    	    f(i,0,q)
    	       if (qs[i] < qt[i] && (qs[i]+g)%7 == 0)
    	            queries.pb(i);
    	    solve(a,queries);
    	}
    	f(g,0,7) reverse(begin(v[g]),end(v[g]));
    	f(i,0,q) {
    	    qs[i] = n-1-qs[i];
    	    qt[i] = n-1-qt[i];
    	}
	}
	
	stringstream ss;
	f(i,0,q) ss << ans[i] << endl;
	cout << ss.str();
	
    return 0;
}