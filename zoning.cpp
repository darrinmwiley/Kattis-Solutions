#include <bits/stdc++.h>
using namespace std;

#define f(i,a,b) for (int i =a; i < b; i++)
#define IN(i,a,b) (a<=i&&i<=b)

using pii = pair<int,int>;
int di[] = {1,-1,0,0};
int dj[] = {0,0,1,-1};

const int oo = 1e9;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	int n; cin>>n;

	char city[n][n];
	int d[n][n];
	queue<pii> pq;
	f(i,0,n) f(j,0,n) {
		cin>>city[i][j];
		d[i][j] = oo;
		if (city[i][j] == '3')
			pq.push({i,j}), d[i][j] = 0;
	}
	while (pq.size()) {
		pii pp = pq.front(); pq.pop();
		int i = pp.first, j = pp.second;
		f(k,0,4) {
			int ni = i+di[k], nj = j+dj[k];
			if (IN(ni,0,n-1) && IN(nj,0,n-1) && d[ni][nj] == oo)
				d[ni][nj] = d[i][j] + 1, pq.push({ni,nj});
		}
	}
	int ans = 0;
	f(i,0,n) f(j,0,n)
		if (city[i][j] == '1')
			ans = max(ans,d[i][j]);
	cout << ans << endl;
	
	return 0;
}
