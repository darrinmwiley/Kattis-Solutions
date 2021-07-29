#include <bits/stdc++.h>
using namespace std;

#define f(i,a,b) for (int i = a; i < b; i++)

int main() {
    ios_base::sync_with_stdio(false);
	cin.tie(NULL);
    int n; while (cin>>n) {
        vector<int> a(n);
        f(i,0,n) cin>>a[i];
        stringstream ss;
        bool take = 0;
        f(i,1,a[0]) {
            take = 1;
            ss << i << endl;
        }
        f(i,0,n-1) {
            f(j,a[i]+1,a[i+1]) {
                ss << j << endl;
                take = 1;
            }
        }
        if (take)
        cout << ss.str();
        else 
        cout << "good job" << endl;
    }
    return 0;
}