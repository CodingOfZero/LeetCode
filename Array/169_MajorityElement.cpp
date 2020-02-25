/*
majority Element
此题假设必存在众数
*/
#include<iostream>
#include<vector>
using namespace std;
int majorityElement(vector<int>& nums) {
	int target = 0;
	int temp = 0;
	for (int i = 0; i < nums.size(); i++) {
		if (temp==0) {
			target = nums[i];
			temp = 1;
		}
		else {
			target == nums[i] ? temp++ : temp--;
		}	
	}
	/*
	int count = 0;
	for (int i = 0; i < nums.size(); i++)
		if (nums[i] == target)
			count++;
	if (count * 2 > nums.size()) return target;*/
	return target;
}
int main() {
	vector<int> nums = { 2,2,1,1,1,2,2 };
	cout << majorityElement(nums);
}