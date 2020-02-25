/*
Ñ­»·ÒÆÎ»
*/
#include<iostream>
#include<vector>
using namespace std;

void rotate(vector<int>& nums, int k) {
	k = k % nums.size();
	for (int i = 0 , j= nums.size() - k-1; i < j;) {
		swap(nums[i], nums[j]);
		i++; j--;
	}
	for (int i = nums.size() - k, j = nums.size() - 1; i < j; ) {
		swap(nums[i], nums[j]);
		i++; j--;
	}
	for (int i = 0, j = nums.size() - 1; i < j; ) {
		swap(nums[i], nums[j]);
		i++; j--;
	}
}
int main() {
	vector<int> nums = { 1,2,3,4,5,6,7 };
	vector<int> nums2 = { -1 };
	rotate(nums2, 2);
	for (int i : nums2)
		cout << i;
}