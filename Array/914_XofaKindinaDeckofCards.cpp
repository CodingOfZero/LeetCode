/*
将数字分组，组内均相同，组长为X，X>=2
申请空间，统计每个数字出现次数，判断次数之间有无最大公约数，若存在非1最大公约数，则能够分组成功
*/

#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int fun(int x, int y) {
	return x == 0 ? y : fun(y % x, x);
}
bool hasGroupsSizeX(vector<int>& deck) {
	if (deck.size() < 2) return false;
	int count[10000] = { 0 };
	int g = -1;
	for (int i : deck)
		count[i]++;
	for (int i = 0; i < 10000; i++) {
		if (count[i] > 0) {
			if (g == -1)
				g = count[i];
			else
				g = fun(g, count[i]);
		}
	}
	return g >= 2;
}

int main() {
	vector<int> v = { 1,2,3,4,4,3,2,1 };
	cout << hasGroupsSizeX(v);
}