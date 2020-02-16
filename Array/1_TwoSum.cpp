/*
时间：2020年2月16日17点03分
题意：Given an array of integers, 
return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, 
and you may not use the same element twice.
*/
#include<iostream>
#include<vector>
using namespace std;
vector<int> twoSum(vector<int>& nums, int target) {
	int x = 0, y = 0,i,j;
	bool flag = false;
	vector<int> res;
	for (int i = 0; i < nums.size(); i++) {
		x = nums[i];
		y = target - x;
		for(j=i+1;j<nums.size();j++)
			if (y == nums[j]) {
				flag = true;
				break;
			}
		if (flag) {
			res.push_back(i);
			res.push_back(j);
			break;
		}
	}
	return res;
}
int main() {
	vector<int> t = { 2, 7, 11, 15 };
	vector<int> res=twoSum(t, 9);
	for (int k : res)
		cout << k << endl;
}