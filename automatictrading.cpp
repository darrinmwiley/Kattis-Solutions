#include <iostream>

using namespace std;

int main() {
    string trade;
    cin >> trade;
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        int s = 0;
        int e1, e2;
        scanf("%d %d", &e1, &e2);
        for (; e1+s < trade.length() && e2+s < trade.length(); s++) {
            if (trade[e1+s] != trade[e2+s]) {
                break;
            }
        }
        cout << s << endl;
    }

    return 0;
}
