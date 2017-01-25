#include<stdio.h>
#include <string>
#include<math.h>
#include<iostream>
using namespace std;

int main()
{
    int R;
    int C;
    scanf("%d %d",&R,&C);
    bool mat[R][C];
    cin.ignore();
    for(int i = 0;i<R;i++)
    {
        string line;
        getline(cin,line);
        for(int j = 0;j<C;j++)
            mat[i][j] = line[j]=='o';
    }
    int maximum = 0;
    int sum = 0;
    int d[2][8] = {{-1,-1,-1,0,0,1,1,1},{-1,0,1,-1,1,-1,0,1}};
    for(int i = 0;i<R;i++)
        for(int j = 0;j<C;j++)
        {
            int x = 0;
            for(int k =0;k<8;k++)
            {
                int r = i+d[0][k];
                int c = j+d[1][k];
                if(min(r,c)>=0&&r<R&&c<C&&mat[r][c])
                {
                    if(mat[i][j])
                        sum++;
                    else
                        x++;
                }
            }
            maximum = max(maximum,x);
        }
    cout<<sum/2+maximum;
}
