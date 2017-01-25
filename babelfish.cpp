#include<stdio.h>
#include<iostream>
#include<stack>
#include<cmath>
#include<string.h>
#include<map>
using namespace std;

int main(){
	map<string, string> M;
	string line;
	getline(cin,line);
	while(!line.empty())
    {
        int space = line.find(" ");
        string a = line.substr(0,space);
        string b = line.substr(space+1,line.length()-space-1);
        M[b] = a;
        getline(cin,line);
    }
    while(getline(cin,line))
    {
        string x = M[line];
        if(x.empty())
            cout<<"eh"<<endl;
        cout<<x<<endl;
    }
}
