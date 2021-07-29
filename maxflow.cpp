#include "bits/stdc++.h"

using namespace std;

#define pb push_back
#define boolean bool
#define li long long int

int N, M, S, T;

li INF = LONG_MAX/4;

struct edge{
    int to;
    li cap;
    //if we block together edges/reverses in groups of 2, we can get reverse by xor with 1
};

vector<int> adj[500];
vector<edge> e;

void trace(vector<int> p, int t, int flow)
{
    int curr = t;
    while(p[curr] != -1)
    {
        e[p[curr]].cap -= flow;
        e[p[curr]^1].cap += flow;
        curr = e[p[curr]^1].to;
    }
}

li aug(int s, int t)
{
    vector<bool> vis(500, 0);
    vis[s] = 1;
    vector<int> p(500, -1);
    queue<pair<int, li> > que;//(location, flow)
    que.push(make_pair(s, INF));
    while(!que.empty())
    {
        auto curr = que.front();
        int loc = curr.first;
        li flow = curr.second;
        que.pop();

        if(loc == t)
        {
            trace(p, t, flow);
            return flow;
        }

        for(int x: adj[loc])
        {
            edge ed = e[x];
            int next = ed.to;
            if(!vis[next] && ed.cap > 0)
            {
                que.push(make_pair(next, min(flow, ed.cap)));
                vis[next] = true;
                p[next] = x;
            }
        }
    }
    return 0;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cin >> N >> M >> S >> T;
	int edges = 0;
	for(int i = 0;i<M;i++)
    {
        int u, v;
        li x;
        cin >> u >> v;
        cin >> x;
        struct edge e1 = {v,x};
        struct edge e2 = {u,0};
        e.pb(e1);
        e.pb(e2);
        adj[u].pb(edges);
        adj[v].pb(edges+1);
        edges+=2;
    }
    li flow = 0;
    while(true){
        li increase = aug(S, T);
        if(increase != 0)
        flow += increase;
        else{
            int used = 0;
            for(int i = 0;i<M;i++)
            {
                if(e[i*2+1].cap != 0)
                    used++;
            }
            cout << N << " "<<flow<<" "<<used<<endl;
            for(int i = 0;i<M;i++)
            {
                int amt = e[i*2+1].cap;
                int from = e[i*2+1].to;
                int to = e[i*2].to;
                if(amt != 0)
                    cout << from << " "<<to<<" "<<amt<<endl;
            }
            break;
        }
    }
}
