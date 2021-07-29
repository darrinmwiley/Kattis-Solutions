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
    int L = 0;
    int H = 1001;
    int M = (L+H)/2;
    string str;
    while(true)
    {
        M = (L+H)/2;
        cout << M << endl;
        cin >> str;
        if(str == "lower")
        {
            H = M;
        }else if(str == "higher")
        {
            L = M;
        }else{
            return 0;
        }
    }

}
