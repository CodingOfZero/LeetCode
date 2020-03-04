/*
*2018年7月21日 00:22:56
*You're now a baseball game point recorder.
Given a list of strings, each string can be one of the 4 following types:

Integer (one round's score): Directly represents the number of points you get in this round.
"+" (one round's score): Represents that the points you get in this round are the sum of the last two valid round's points.
"D" (one round's score): Represents that the points you get in this round are the doubled data of the last valid round's points.
"C" (an operation, which isn't a round's score): Represents the last valid round's points you get were invalid and should be removed.
Each round's operation is permanent and could have an impact on the round before and the round after.

You need to return the sum of the points you could get in all the rounds.

Example 1:
Input: ["5","2","C","D","+"]
Output: 30
Explanation: 
Round 1: You could get 5 points. The sum is: 5.
Round 2: You could get 2 points. The sum is: 7.
Operation 1: The round 2's data was invalid. The sum is: 5.  
Round 3: You could get 10 points (the round 2's data has been removed). The sum is: 15.
Round 4: You could get 5 + 10 = 15 points. The sum is: 30.
Example 2:
Input: ["5","-2","4","C","D","9","+","+"]
Output: 27
Explanation: 
Round 1: You could get 5 points. The sum is: 5.
Round 2: You could get -2 points. The sum is: 3.
Round 3: You could get 4 points. The sum is: 7.
Operation 1: The round 3's data is invalid. The sum is: 3.  
Round 4: You could get -4 points (the round 3's data has been removed). The sum is: -1.
Round 5: You could get 9 points. The sum is: 8.
Round 6: You could get -4 + 9 = 5 points. The sum is 13.
Round 7: You could get 9 + 5 = 14 points. The sum is 27.
*/
#include<iostream>
#include<stack>
#include<vector>
#include<string>
using std::stack; using std::vector; using std::string; using std::cout;
int main() {
	vector<string> ops = { "5","-2","4","C","D","9","+","+" };
	int sum=0;
	stack<int> s;
	for (size_t st = 0; st<ops.size(); ++st)			//遍历存放在vector对象中的string元素 
	{
		for (size_t it = 0; it<ops[st].size(); ++it)	//对每一字符串进行处理 
		{
			if (isdigit(ops[st][it]))				//无符号处理 
			{
				int N1 = ops[st][it] - '0';
				while (isdigit(ops[st][++it]))		//多位数计算
				{
					N1 = N1 * 10 + (ops[st][it] - '0');
				}
				sum = sum + N1;
				s.push(N1);
			}
			else if ( ops[st][it] == '-')		//负号
			{
					int N1 = ops[st][++it] - '0';
					while (isdigit(ops[st][++it]))
					{
						N1 = N1 * 10 + (ops[st][it] - '0');	//多位数时计算 
					}
					s.push(-N1);
					sum = sum + s.top();
			}
			else if (ops[st][it] == 'C' )
			{
				sum = sum - s.top();
				s.pop();
			}else if (ops[st][it] == 'D')
			{
				int num;
				if (!s.empty())
					num = s.top() << 1;
				sum = sum + num;
				s.push(num);
			}
			else if (ops[st][it] == '+')
			{	
				int num1, num2,num3;
				if (!s.empty())
				{
					num1 = s.top();
					s.pop();
					num2 = s.top();
					s.pop();
				}	
				num3 = num1 + num2;
				s.push(num2);
				s.push(num1);
				s.push(num3);
				sum = sum + s.top();
			}
		}
	}
	cout << sum;
	return 0;
}