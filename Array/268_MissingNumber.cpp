
#include<iostream>
#include<vector>
#include<algorithm>
#include<unordered_set>
using namespace std;
int missingNumber(vector<int>& nums) {
	sort(nums.begin(), nums.end());
	for (int i = 0; i < nums.size(); i++)
		if (nums[i] != i)
			return i;
	return nums.size();
}
/*
*/
int missingNumber_1(vector<int>& nums) {
	unordered_set<int> container;
	for (int i : nums) {
		container.insert(i);
	}
	for (int i = 0; i < nums.size()+1; i++)
		if (!container.count(i))
			return i;
}
/*
利用异或运算，
Index	0	1	2	3
Value	0	1	3	4
missing	=4∧(0∧0)∧(1∧1)∧(2∧3)∧(3∧4)
		=(4∧4)∧(0∧0)∧(1∧1)∧(3∧3)∧2
		=0∧0∧0∧0∧2
		=2
​4为向量长度
*/
int missingNumber_2(vector<int>& nums) {
	int t = nums.size();
	for (int i = 0; i < nums.size(); i++) {
		t ^= i ^ nums[i];
	}
	return t;
}
/*
利用高斯公式
*/
int missingNumber_3(vector<int>& nums) {
	int sum = nums.size() * (nums.size() + 1) / 2;
	int temp = 0;
	for (int i : nums) {
		temp += i;
	}
	return sum-temp;
}
