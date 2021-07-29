#include <bits/stdc++.h>

using namespace std;

#define f(i,a,b) for (int i = a;i<b;i++)
#define parseInt(s, b) stoi(s, nullptr, b);
#define parseInt(s) stoi(s, nullptr, 10);
#define parseLong(s, b) stoll(s, nullptr, b);
#define parseLong(s) stoll(s, nullptr, 10);
#define readInt(x) int x; cin >> x;
#define readLong(x) long long int x; cin >> x;
#define read(x) string x; cin >> x;
#define readLine(x) string x; getline(cin, x);
#define readInts(arr, n) int arr[n];for(int asdf = 0;asdf<n;asdf++){cin >> arr[asdf];}
#define readLongs(arr, n) long long int arr[n];for(int asdf = 0;asdf<n;asdf++){cin >> arr[asdf];}
#define printArr(arr, n) for(int asdf = 0;asdf<n;asdf++){cout << arr[asdf];if(asdf != n-1)cout<<" ";}cout << endl;


int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int n, q;
    cin >> n >> q;
    int ints[n];
    unordered_map<string, int> mp;
    string next;
    f(i,0,n){
        cin >> next;
        if(!mp.count(next))
        	mp[next] = mp.size();
		ints[i] = mp[next];
	}
    f(z, 0, q)
    {
        int a,b;
        cin >> a >> b;
        int curr = ints[a-1];
        int ct = 1;
        f(i, a, b)
        {
            if(curr == -1)
            {
                curr = ints[i];
                ct = 1;
            }
            else if(ints[i] != curr)
            {
                ct--;
                if(ct == 0)
                    curr = -1;
            }else if(ints[i] == curr)
            {
                ct++;
            }
        }
        int occ = 0;
        f(i, a-1, b)
        {
            if(ints[i] == curr)
                occ++;
            else
                occ--;
        }
        if(occ > 0)
            cout << "usable" << endl;
        else
            cout << "unusable" << endl;
    }
}
