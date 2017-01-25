#include<stdio.h>
#include<iostream>
#include<stack>
#include<cmath>
#include<string.h>
#include<map>
using namespace std;

int main(){
	long long N;
	cin>>N;
	stack<long long> st;
	int root = (int)sqrt(N);
	for(int i = 1;i<=root;i++)
        if(N%i==0)
        {
            st.push(N/i);
            cout<<i-1<<" ";
        }
    long long M = root;
    if(M*root == N)
        st.pop();
    while(!st.empty())
    {
        cout<<st.top()-1<<" ";
        st.pop();
    }
}
