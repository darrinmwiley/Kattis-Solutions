#include <iostream>
#include <bits/stdc++.h>
using namespace std;

#define ll long long

ll ints[500000];
int N;

ll gcd(ll a , ll b)
{
   if(b==0) return a;
   a%=b;
   return gcd(b,a);
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> N;
    for(int i = 0;i<N;i++)
    {
    	cin >> ints[i];
    }
    unordered_set<ll> ans;
    unordered_set<ll> current;
    for(int i = N-1;i>=0;i--)
    {
    	unordered_set<ll> next;
    	for(ll x: current){
    		next.insert(__gcd(x, ints[i]));
    	}
    	next.insert(ints[i]);
    	current = next;
    	for(ll x: current){
    		ans.insert(x);
    	}
    }
    cout << ans.size();
}