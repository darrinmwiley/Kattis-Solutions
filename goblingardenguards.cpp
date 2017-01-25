#include<stdio.h>
#include<iostream>
#include<math.h>

using namespace std;

int main(){

    int N;
    scanf("%d",&N);
    int gx[N];
    int gy[N];
    for(int i = 0;i<N;i++)
        scanf("%d %d",&gx[i],&gy[i]);
    int M;
    cin>>M;
    int sx[M];
    int sy[M];
    int r[M];
    for(int i = 0;i<M;i++)
        scanf("%d %d %d", &sx[i], &sy[i], &r[i]);
    int hit = 0;
    for(int i = 0;i<N;i++)
        for(int j = 0;j<M;j++)
        {
            if((gx[i]-sx[j])*(gx[i]-sx[j])+(gy[i]-sy[j])*(gy[i]-sy[j])<=r[j]*r[j])
            {
                hit++;
                break;
            }
        }
    cout<<N-hit;
}
