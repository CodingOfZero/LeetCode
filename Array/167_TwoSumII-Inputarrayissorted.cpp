/*

Runtime: 4 ms, faster than 96.58% of C++ online submissions for Two Sum II - Input array is sorted.
Memory Usage: 9.8 MB, less than 11.76% of C++ online submissions for Two Sum II - Input array is sort
*/

#include<iostream>
#include<vector>
#include<unordered_map>
using namespace std;
vector<int> twoSum(vector<int>& numbers, int target) {
	unordered_map<int, int> fun;
	vector<int> res;
	for (int i = 0; i < numbers.size(); i++) {
		if (fun.count(numbers[i]) == 0) {
			fun[target - numbers[i]] = i;
		}
		else {
			res.push_back(fun[numbers[i]]+1);
			res.push_back(i+1);	
		}
	}
	return res;
}
int main() {
	vector<int> v = { 2,7,11,15 };
	vector<int> v1 = twoSum(v, 9);
	for (int k : v1)
		cout << k;
}