#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
vector<vector<int>> largeGroupPositions(string S) {
	vector<vector<int>> res;
	int i = 0, j = 0;
	for (; j < S.size(); j++) {
		if (S[i] != S[j]) {
			if (j - i >= 3) {
				vector<int> temp;
				temp.push_back(i);
				temp.push_back(j-1);
				i = j;
				res.push_back(temp);
			}
			else {
				i = j;
			}
		}
	}
	return res;
}
int main() {
	string s = "aaa";
	vector<vector<int>> v=largeGroupPositions(s);
	for (auto i : v) {
		for (auto j : i)
			cout << j;
		cout << endl;
	}
}