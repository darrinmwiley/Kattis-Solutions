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
	while (n--) {
	    ll w,g,h,r; cin>>w>>g>>h>>r;
	    auto fu = [&](ld x) {
	        return sqrt(x*x+(g-r)*(g-r))+
	        sqrt((w-x)*(w-x)+(h-r)*(h-r));
	    };
	    ld lo = 0, hi = w;
	    f(_,0,100) {
	        ld m1 = lo+(hi-lo)/3,m2 = hi-(hi-lo)/3;
	        if (fu(m1) >= fu(m2))
	            lo = m1;
	        else hi = m2;
	    }
	    
	    cout << setprecision(20) << sqrt((h-g)*(h-g)+w*w) << " " << setprecision(20) << fu(hi) << endl;
	}
    return 0;
}