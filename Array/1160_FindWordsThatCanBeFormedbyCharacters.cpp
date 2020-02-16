/*
时间：2019年12月21日 20:36:26
题目：给定一个字符串数组、单词和字符串字符，
如果字符串可以由字符组成(每个字符只能使用一次)，那么它就是一个好字符串。
返回所有字符串长度的总和。
*/
#include<iostream>
#include<vector>
#include<algorithm>
/*
Runtime: 88 ms, faster than 53.06% of C++ online submissions for Find Words That Can Be Formed by Characters.
Memory Usage: 17.6 MB, less than 100.00% of C++ online submissions for Find Words That Can Be Formed by Characters.
*/
#define MAX 102
using namespace std;
bool vis[MAX];
int countCharacters(vector<string>& words, string chars) {
	int len = 0;
	for (auto elem : words) {
		for (size_t i = 0; i < chars.size(); i++)//标记数组，访问过变为false，
			vis[i] = true;
		//每个单词
		size_t j = 0;
		for (; j < elem.size();j++) {
			//查看chars中是否有这个字符
			size_t k = 0;
			for (; k < chars.size(); k++) {
				if (chars[k] == elem[j]&&vis[k]) {//如果找到，将标记数组置为false，表示已访问
					vis[k] = false;
					break;
				}		
			}
			if (k == chars.size())break;//表示未能找到字符
		}
		if (j == elem.size())len += elem.size();//表示该单词的所有字符都能在chars中找到，此时记录长度
	}
	return len;
}
/*
Runtime: 84 ms, faster than 57.59% of C++ online submissions for Find Words That Can Be Formed by Characters.
Memory Usage: 15 MB, less than 100.00% of C++ online submissions for Find Words That Can Be Formed by Characters.
利用std::count函数，该函数包含一对迭代器和一个值做参数，返回这个值出现的统计次数。头文件为algorithm
判断单词中每个字符出现的次数是否大于chars中的字符，若大于，则失败
*/
int countCharacters_2(vector<string>& words, std::string chars) {
	int res = 0;
	for (const auto & w : words)
	{
		bool flg = true;
		for (const auto & c : w)
		{
			if (std::count(w.begin(), w.end(), c) > std::count(chars.begin(), chars.end(), c))
			{
				flg = false;
				break;
			}
		}
		if (flg) res += w.size();
	}
	return res;
}
int main() {
	vector<string> words = { "cat","bt","hat","tree" };
	vector<string> words2 = { "hello","world","leetcode" };

	string chars = "atach";
	string c = "welldonehoneyr";
	int i = countCharacters(words, chars);
	int j= countCharacters(words2, c);
	int k = countCharacters_2(words2, c);
	cout << k << endl;
}