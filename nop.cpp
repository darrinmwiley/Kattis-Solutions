/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/hangingout
TAGS: easy
EXPLANATION:
parse each event and calculate answer accordingly
END ANNOTATION
*/
#include "bits/stdc++.h"

using namespace std;

#define pb push_back
#define boolean bool
#define li long long int

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    string str;
    cin >> str;
    int bytes = 0;
    int ans = 0;
    for(int i = 0;i<str.length();i++)
    {
        char ch = str[i];
        if(ch < 'a')
        {
            while(bytes % 4 != 0){
                bytes++;
                ans++;
            }
        }
        bytes++;
    }
    cout << ans;
}
