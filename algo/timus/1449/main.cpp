#include <iostream>
#include <vector>
#include <limits.h>

using namespace std;

typedef std::vector<int> vi;
typedef std::vector<char> vc;
typedef std::vector<vi> mi;

int main()
{
    size_t n = 0;
    cin >> n;
    size_t a[n + 1][n + 1];

    for (int i = 0; i < n ; ++i) {
        for (int j = 0; j <n ; ++j) {
            int k = 0;
            cin >> k;
            a[i + 1][j + 1] = -k;
        }
    }


    int INF = INT_MAX;
    vector<int> u (n+1), v (n+1), p (n+1), way (n+1);
    for (int i = 1; i <= n; ++i) {
        p[0] = i;
        int j0 = 0;
        vi minv (n+1, INF);
        vc used (n+1, false);
        do {
            used[j0] = true;
            int i0 = p[j0],  delta = INF,  j1;
            for (int j = 1; j <= n; ++j)
                if (!used[j]) {
                    int cur = a[i0][j]-u[i0]-v[j];
                    if (cur < minv[j])
                        minv[j] = cur,  way[j] = j0;
                    if (minv[j] < delta)
                        delta = minv[j],  j1 = j;
                }
            for (int j = 0; j <= n; ++j)
                if (used[j])
                    u[p[j]] += delta,  v[j] -= delta;
                else
                    minv[j] -= delta;
            j0 = j1;
        } while (p[j0] != 0);
        do {
            int j1 = way[j0];
            p[j0] = p[j1];
            j0 = j1;
        } while (j0);
    }


    for (int i = 1; i < u.size() ; ++i)
        cout << -u[i]<< " ";

    cout << endl;
    for (int i = 1; i < v.size() ; ++i)
        cout << -v[i]<< " ";

    cout << endl;
    return 0;
}

