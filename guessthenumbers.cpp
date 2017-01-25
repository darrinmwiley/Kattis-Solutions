#include<stdio.h>
#include<iostream>
#include<stack>
using namespace std;

bool prec(char a, char b)
{
    if(b=='('||b==')')
        return false;
    if(a=='*'&&b!='*')
        return false;
    return true;
}

int eval(string str,int ints[])
{
    int x = 0;
    stack<char> ops;
    stack<int> vals;
    for(int i = 0;i<str.length();i++)
    {
        char next = str[i];
        if(isalpha(next)){
            vals.push(ints[x++]);
        }
        else if(next=='(')
            ops.push(next);
        else if(next==')')
        {
            while(ops.top()!='(')
            {
                char op = ops.top();
                ops.pop();
                int a = vals.top();
                vals.pop();
                int b = vals.top();
                vals.pop();
                switch(op)
                {
                    case '+' :vals.push(a+b);break;
                    case '-' :vals.push(b-a);break;
                    case '*' :vals.push(a*b);break;
                }
            }
            ops.pop();
        }else{
            while(!ops.empty()&&prec(next,ops.top())){
                char op = ops.top();
                ops.pop();
                int a = vals.top();
                vals.pop();
                int b = vals.top();
                vals.pop();
                switch(op)
                {
                    case '+' :vals.push(a+b);break;
                    case '-' :vals.push(b-a);break;
                    case '*' :vals.push(a*b);break;
                }
            }
            ops.push(next);
        }
    }
    while(!ops.empty())
    {
        char op = ops.top();
        ops.pop();
        int a = vals.top();
        vals.pop();
        int b = vals.top();
        vals.pop();
        switch(op)
        {
            case '+' :vals.push(a+b);break;
            case '-' :vals.push(b-a);break;
            case '*' :vals.push(a*b);break;
        }
    }
    return vals.top();
}

bool bruteForce(int N,int vals[], bool used[], int current[], int x, int goal, string str)
{
    if(x==N)
        return eval(str,current)==goal;
    for(int i = 0;i<N;i++)
    {
        if(!used[i]){
            current[x] = vals[i];
            used[i] = true;
            if(bruteForce(N,vals,used,current,x+1,goal,str))
                return true;
            used[i] = false;
        }
    }
    return false;
}

int main(){
	while(true)
    {
        int N;
        cin>>N;
        if(N==0)
            return 0;
        int ints[N];
        for(int i = 0;i<N;i++)
            cin>>ints[i];
        int M;
        cin>>M;
        string line;
        cin.ignore();
        getline(cin,line);
        bool bools[N];
        fill(bools,bools+sizeof(bools),false);
        if(bruteForce(N,ints,bools,new int[N],0,M,line))
            printf("YES\n");
        else
            printf("NO\n");
    }
    return 0;
}
