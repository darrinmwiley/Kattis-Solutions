#include "bits/stdc++.h"

using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    string line;
    cin >> line;
    string word;
    stringstream ss(line);
    while(getline(ss, word, '-'))
    {
        cout << word[0];
    }
    cout << endl;
}
