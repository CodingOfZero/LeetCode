/*
时间:2019年12月29日 20:47:40
题意：返回距离最小的整数对
*/
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
/*
Runtime: 116 ms, faster than 83.12% of C++ online submissions for Minimum Absolute Difference.
Memory Usage: 17.9 MB, less than 100.00% of C++ online submissions for Minimum Absolute Difference.
*/
vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
	sort(arr.begin(), arr.end());
	vector<int> m,n;
	vector<vector<int>> res;
	int min_dis = INT_MAX;
	for (size_t i = 1; i < arr.size(); i++) {
		min_dis=min(min_dis,arr[i] - arr[i - 1]);
	}
	for (size_t i = 1; i < arr.size(); i++) {
		if ((arr[i] - arr[i - 1]) == min_dis) {
			vector<int> b;
			b.push_back(arr[i-1]);
			b.push_back(arr[i]);
			res.push_back(b);
		}
	}
	return res;
}