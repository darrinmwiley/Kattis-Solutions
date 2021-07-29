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
    string line;
    int N;
    cin >> N;
    for(int i = 0;i<N;i++)
    {
        int T;
        cin >> T;
        int sum = 1;
        for(int i = 0;i<T;i++)
        {
            int X;
            cin >> X;
            sum += X-1;
        }
        cout << sum << endl;
    }

}
