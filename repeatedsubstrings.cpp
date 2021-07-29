#include <bits/stdc++.h>
using namespace std;
struct suffix
{
    long long index;
    long long Rank[2];
};
long long cmp(struct suffix a, struct suffix b)
{
    return (a.Rank[0] == b.Rank[0])? (a.Rank[1] < b.Rank[1] ?1: 0):
           (a.Rank[0] < b.Rank[0] ?1: 0);
}
vector<long long> buildSuffixArray(string txt, long long n)
{
    struct suffix suffixes[n];
    for (long long i = 0; i < n; i++)
    {
        suffixes[i].index = i;
        suffixes[i].Rank[0] = txt[i] - 'a';
        suffixes[i].Rank[1] = ((i+1) < n)? (txt[i + 1] - 'a'): -1;
    }
    sort(suffixes, suffixes+n, cmp);
    long long ind[n];
    for (long long k = 4; k < 2*n; k = k*2)
    {
        long long Rank = 0;
        long long prev_Rank = suffixes[0].Rank[0];
        suffixes[0].Rank[0] = Rank;
        ind[suffixes[0].index] = 0;
        for (long long i = 1; i < n; i++)
        {
            if (suffixes[i].Rank[0] == prev_Rank && suffixes[i].Rank[1] == suffixes[i-1].Rank[1])
            {
                prev_Rank = suffixes[i].Rank[0];
                suffixes[i].Rank[0] = Rank;
            }
            else
            {
                prev_Rank = suffixes[i].Rank[0];
                suffixes[i].Rank[0] = ++Rank;
            }
            ind[suffixes[i].index] = i;
        }
        for (long long i = 0; i < n; i++)
        {
            long long nextindex = suffixes[i].index + k/2;
            suffixes[i].Rank[1] = (nextindex < n)?
                                  suffixes[ind[nextindex]].Rank[0]: -1;
        }
        sort(suffixes, suffixes+n, cmp);
    }
    vector<long long>suffixArr;
    for (long long i = 0; i < n; i++)
        suffixArr.push_back(suffixes[i].index);
    return  suffixArr;
}
vector<long long> kasai(string txt, vector<long long> suffixArr)
{
    long long n = suffixArr.size();
    vector<long long> lcp(n, 0);
    vector<long long> invSuff(n, 0);
    for (long long i=0; i < n; i++)
        invSuff[suffixArr[i]] = i;
    long long k = 0;
    for (long long i=0; i<n; i++)
    {
        if (invSuff[i] == n-1)
        {
            k = 0;
            continue;
        }
        long long j = suffixArr[invSuff[i]+1];
        while (i+k<n && j+k<n && txt[i+k]==txt[j+k])
            k++;
        lcp[invSuff[i]] = k;
        if (k>0)
            k--;
    }
    return lcp;
}
#define pi pair<int,int>
pi kq;
long long base = 311;
long long mod = 1e9+7;
vector <long long> ruler[2];
long long sz[2],p[300001];
unordered_map <long long , long long> got;
string s[2];
    vector<long long> sa;
void  Hash(long long ind)
{
    ruler[ind].push_back(0);
    for (long long i = 1 ; i <= sz[ind] ; i++)
    {
        ruler[ind].push_back((ruler[ind][i-1] * base + s[ind][i]) % mod);
    }
}
long long get(long long i , long long l , long long r)
{
    return (ruler[i][r] - ruler[i][l-1] * p[r - l + 1] % mod + mod * 9) % mod;
}
int main() 
{
    cin >> s[0];
    
    sa = buildSuffixArray(s[0], s[0].length());
  
    vector<long long >lcp = kasai(s[0], sa); 
    pi kq = pi(0 , 1e9);
    for (int i = 0 ; i < s[0].length() ; i++)
    {
        //cout <<sa[i]<<" ";
        //cout <<lcp[i]<<'\n';
        if (lcp[i] > kq.first)
        kq  = max(kq , pi(lcp[i] , sa[i]));
    }
    //cout <<kq.first<<" "<<kq.second;
    for (int i = kq.second ; i < kq.second + kq.first ; i++) cout <<s[0][i];
    
}
