/*
BEGIN ANNOTATION
PROBLEM URL: open.kattis.com/problems/soundex
TAGS: easy
EXPLANATION:
iterate through each letter, mapping it to it's number and outputting if not repeated
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
    int ints[] = {-1,1,2,3,-1,1,2,-1,-1,2,2,4,5,5,-1,1,2,6,2,3,-1,1,-1,2,-1,2};
    while(getline(cin, line))
    {
        string output = "";
        int last = -1;
        for(int i = 0;i<line.length();i++)
        {
            int index = line[i] - 'A';
            if(ints[index] != last && ints[index] != -1)
                cout << ints[index];
            last = ints[index];
        }
        cout << endl;
    }

}
