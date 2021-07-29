/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/filip
TAGS: easy
EXPLANATION:
read in strings, reverse, parse, compare
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
	string line;
	int ans = 0;
	while(getline(cin, line, ';'))
    {
        int dashLocation = line.find("-");
        if(dashLocation != string::npos)
        {
            string first = line.substr(0,dashLocation);
            string last = line.substr(dashLocation+1);
            ans += stoi(last) - stoi(first) + 1
            ;
        }else
            ans++;
    }
    cout << ans << endl;
}
