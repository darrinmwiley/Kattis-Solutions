#include<stdio.h>
#include<iostream>
#include<stack>
#include<cmath>
#include<string>

using namespace std;
int wrong[5];
bool check(int ints[])
{
    if(ints[0]>=0&&ints[1]>=0&&ints[2]>=0&&ints[3]>=0&&ints[4]>=0)
        return ints[0]==ints[1]+ints[2]+ints[3]&&ints[4]==ints[1]*3+ints[2];
    return false;
}

int * solve(int ints[])
{
    bool G = ints[0]!=-1;
    bool W= ints[1]!=-1;
    bool T = ints[2]!=-1;
    bool L = ints[3]!=-1;
    bool P = ints[4]!=-1;
    int g = ints[0];
    int w = ints[1];
    int t = ints[2];
    int l = ints[3];
    int p = ints[4];
    if(g<-1||w<-1||t<-1||l<-1||p<-1)
        return wrong;
    if(P&&G&&p>g*3)
        return wrong;
    int loop = 0;
    while(!(G&&W&&T&&L&&P))
    {
        //printf("%d %d %d %d %d\n",g,w,t,l,p);
        loop++;
        if(loop>5){
            for(int i = 0;i<=100;i++)
            {
                bool flag = false;
                int q[5];
              for(int j = 0;j<5;j++)
                {
                    if(ints[j]==-1&&!flag)
                    {
                        flag = true;
                        q[j] = i;
                    }else
                        q[j] = ints[j];
                }
                int * in = solve(q);
                if(in!=wrong&&check(solve(q)))
                    return in;
            }
            return wrong;
        }
        if(G&&W&&L)
        {
            T = true;
            t = g-w-l;
        }
        if(G&&W&&T)
        {
            L = true;
            l = g-w-t;
        }
        if(G&&L&&T)
        {
            W = true;
            w = g-l-t;
        }
        if(W&&L&&T)
        {
            G = true;
            g = w+l+t;
        }
        if(W&&P&&G)
        {
            L = true;
            l = w*2-p+g;
        }
        if(W&&G&&L)
        {
            P = true;
            p = w*2+g-l;
        }
        if(W&&P&&L)
        {
            G = true;
            g = p-w*2+l;
        }
        if(P&&G&&L)
        {
            W = true;
            w = (p-g+l)/2;
        }
        if(P&&W)
        {
            T = true;
            t = p-w*3;
        }
        if(P&&T)
        {
            W = true;
            w = (p-t)/3;
        }
        if(W&&T)
        {
            P = true;
            p = w*3+t;
        }
        if(g==0)
        {
            L = T = W = P = true;
            l = t = w = p = 0;
        }
        if(L&&G&&l == g)
        {
            W = T = P = true;
            w = t = p = 0;
        }
        if(T&&G&&t == g)
        {
            W = L = P = true;
            p = G;
            w = l = 0;
        }
        if(W&&G&&w==g)
        {
            T = L = P = true;
            p = g*3;
            t = l = 0;
        }
        if(p==0&&L)
        {
            G = T = W = true;
            g = l;
            t = w = 0;
        }
        if(p==0&&G)
        {
            L = G = W = true;
            l = g;
            t = w = 0;
        }
        if(P&&G&&p == g*3&&p>0)
        {
            W = T = L = true;
            w = p/3;
            t = l = 0;
        }
        if(t==0&&P)
        {
            W = true;
            w = p/3;
        }
        if(T&&t==100)
        {
            W = L = T = G = P = true;
            t = 100;
            g = 100;
            w = 0;
            p = 100;
            l = 0;
        }
        if(P&&p<3)
        {
            T = W = true;
            t = p;
            w = 0;
        }
        if(w+t==100)
        {
            l = 0;
            g = 100;
            L = true;
            G = true;
        }
        if(p==300)
        {
            w = 100;
            l = 0;
            t = 0;
            g = 100;
            W = L = T = G = true;
        }
    }
    int ret[] = {g,w,t,l,p};
    for(int i = 0;i<5;i++)
        if(ints[i]!=-1)
            ret[i] = ints[i];
    if(check(ret))
        return ret;
    return ints;
}

int main(){
    std::ios_base::sync_with_stdio (false);
    int ZZ;
    cin>>ZZ;
    for(int z = 0;z<ZZ;z++)
    {
        int ints[5];
        for(int i = 0;i<5;i++)
        {
            string next;
            cin>>next;
            if(next.compare("?")!=0)
            {
                const char * x = next.c_str();
                sscanf(x,"%d",&ints[i]);
            }
            else
                ints[i] = -1;
        }
        int * ans = solve(ints);
        if(!check(ans)||ans==wrong)
           for(int i = 0;i<100;i++)
                cout<<"ISSUE HERE";
        printf("%d %d %d %d %d\n",ans[0],ans[1],ans[2],ans[3],ans[4]);
    }
}
