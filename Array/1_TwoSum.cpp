/*
时间：2020年2月16日17点03分
题意：Given an array of integers, 
return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, 
and you may not use the same element twice.
*/
#include<iostream>
#include<vector>
#include<unordered_map>
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
/*
借鉴:利用Map来解决问题,key为数字，value为索引
a+b=target;将target-a作为key存入map中，并将a的索引作为key对应的value存入map中。
若能count不为0，则说明存在b。输出索引。
*/
vector<int> twoSum_1(vector<int>& nums, int target) {
	unordered_map<int, int> t;
	for (int i = 0; i < nums.size(); i++) {
		if (t.count(nums[i]) == 0) {
			t[target - nums[i]] = i;
		}
		else {
			return { t[nums[i]],i };
		}
	}
	return {};
}
int main() {
	vector<int> t = { 2, 7, 11, 15 };
	//vector<int> res=twoSum(t, 9);
	vector<int> res = twoSum_1(t, 9);
	for (int k : res)
		cout << k << endl;
}