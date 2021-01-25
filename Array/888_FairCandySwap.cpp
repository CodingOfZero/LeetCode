/*
列方程，解题
*/
#include<iostream>
#include<vector>
#include<set>
#include<algorithm>
using namespace std;
vector<int> fairCandySwap(vector<int>& A, vector<int>& B) {
	int sa=0, sb=0, temp=0;
	vector<int> res;
	for (int i : A)sa += i;
	for (int i : B)sb += i;
	temp = (sb - sa) / 2;
	set<int> setB;
	for (int x : B) setB.insert(x);

	for(int x:A )
		if (setB.count(x + temp)) {
			res.push_back(x);
			res.push_back(x + temp);
			break;
		}
	return res;
}
