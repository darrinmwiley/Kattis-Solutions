/* This is an example solution to the "Cheating Luck" problem from the
 * Spotify Programming Challenge, held on May 29, 2010.
 *
 * The solution is provided as-is, is not documented, and may be
 * generally difficult to read.
 *
 * This work by Scrool (http://scrool.se/) is licensed under a
 * Creative Commons Attribution-Share Alike 2.5 Sweden License
 * (http://creativecommons.org/licenses/by-sa/2.5/se/deed.en).  You
 * are free to redistribute it as you like as long as this license
 * information is not removed.
 */

//all other things equal, there is never a reason to bet less if you have more money
#include <cstdio>
#include <cstring>
#include <cmath>
#include <algorithm>

using namespace std;

typedef long long ll;

int tot;
int bet[2001][51][51];
int val[2001][51][51];

int Val(int m, int n, int k);

int Bet(int m, int n, int k) {
  if (!k || !n || !m) return m;
  Val(m, n, k);
  return bet[m][n][k];
}

int Val(int m, int n, int k) {
  if (m >= tot) return tot;
  if (!k || !n || !m) return min((ll)tot, m*(1LL << n));
  int &res = val[m][n][k];
  if (res == 0) {
    res = m;
    //mlo is minimum feasible bet
    //mhi is maximum feasible bet
    int mlo = Bet(m-1, n, k), mhi = min(m, tot-m);
    if (mlo > mhi) mlo = mhi;
    bet[m][n][k] = mlo;
    // this may look bad, but an amortized analysis shows that
    // this loop is O(1) amortized time
    for (int b = mlo; b <= mhi; ++b) {
      //v is max value attainable from betting b, playing n-1 more turns with k cheats
      int v = min(Val(m-b, n-1, k-1), Val(m+b, n-1, k));
      //if betting b is most profitable, then update our minimum feasible bet
      if (v < res) break;
      bet[m][n][k] = b;
      res = v;
    }
  }
  return res;
}

int main(void) {
  int md, mg, n, k;
  scanf("%d%d%d%d", &md, &mg, &n, &k);
  tot = md + mg;
  memset(val, 0, sizeof(val));
  printf("%d\n", Val(md, n, n-k));
  return 0;
}

