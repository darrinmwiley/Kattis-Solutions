#include<stdio.h>
#include<iostream>
#include<stack>
#include<cmath>
using namespace std;

int edit(string line)
{
    int sum = 0;
    for(int j = 0;j<line.length();j++)
        sum+=13-abs('N'-line[j]);
    //cout<<sum<<" ";
    int rightskip[line.length()];
    int leftskip[line.length()];
    fill(rightskip,rightskip+line.length(),0);
    fill(leftskip,leftskip+line.length(),0);
    for(int i = 0;i<line.length();i++)
    {

        if(line[i]=='A')
        {
            if(i==0)
                rightskip[i] = 1;
            else
                rightskip[i] = rightskip[i-1]+1;
        }

        if(line[line.length()-1-i]=='A')
        {
            if(i==0)
                leftskip[line.length()-1]=1;
            else
                leftskip[line.length()-1-i]=leftskip[(line.length()-i)%line.length()]+1;
        }
    }
    //for(int i = 0;i<line.length();i++)
    //    cout<<leftskip[i]<<" ";
    //cout<<endl;
    int mincost = sum+line.length()-1;
    mincost = min(mincost-rightskip[line.length()-1],mincost-leftskip[1]);
    for(int i = 1;i<line.length();i++)
    {
        int len = line.length();
        mincost = min(mincost,sum+(i-1)*2+1+(len)-i-leftskip[i]-1);
    }
    for(int i = 0;i<line.length();i++)
    {
        int skip = rightskip[line.length()-1-i];
        int two = max(0,i-1);
        int one = i>0?1:0;
        int len =line.length();
        mincost = min(mincost,sum+two*2+one+len-i-skip);
    }
    return mincost;
}
bool allA(string s)
{
    for(int i = 0;i<s.length();i++)
    {
        if(s[i]!='A')
            return false;
    }
    return true;
}

int main(){
	int zz;
	scanf("%d",&zz);
	string ignore;
	getline(cin,ignore);
	for(int z = 0;z<zz;z++)
    {
        string line;
        getline(cin,line);
        //cout<<endl<<endl;
        //cout<<line.length()<<" ";
        //cout<<line[315];
        //cout<<line[319];
        if(allA(line))
        {
            cout<<"0\n";
            continue;
        }
        if(line.length()==1)
        {
            cout<<(13-abs('N'-line[0]))<<endl;
            continue;
        }
        int n = edit(line);
        cout<<n<<endl;
    }
    return 0;
}
//1
//EBIAAAMAQOTAYAAAAUAADASAMVRORAQAAAPLWAASAAHAADWSAIWIAASBXTSYXAESRWGWTQAAKAHTAMDAE
