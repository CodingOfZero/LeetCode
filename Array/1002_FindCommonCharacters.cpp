/*
时间：2019年12月29日 19:51:38
题目：Given an array A of strings made only from lowercase letters, 
return a list of all characters that show up in all strings within the list (including duplicates).  
For example, if a character occurs 3 times in all strings but not 4 times, 
you need to include that character three times in the final answer.
You may return the answer in any order.
*/
#include<string>
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
/*Runtime: 4 ms, faster than 99.83% of C++ online submissions for Find Common Characters.
Memory Usage : 9.8 MB, less than 54.17% of C++ online submissions for Find Common Characters.
*/
vector<string> commonChars(vector<string>& A) {
	vector < int > count(26, INT_MAX);//统计字符出现最小次数，为总的统计次数
	for (int i = 0; i<A.size(); i++)//每个字符串中每个字符出现的次数
	{
		vector < int > countch(26, 0);
		for (int j = 0; j<A[i].size(); j++)
			countch[A[i][j] - 'a']++;
		for (int j = 0; j<26; j++)
			count[j] = min(countch[j], count[j]);//取最小的次数
	}
	vector <string> res;
	for (int i = 0; i<26; i++)
		for (int j = 0; j<count[i]; j++)
			res.push_back(string(1, i + 'a'));
			//cout<<(string(1, i + 'a'));
	return res;
}
int main() {
	vector<string> k = { "bella","label","roller" };
	vector<string> res=commonChars(k);
	for (string k : res) {
		cout << k;
	}
}