/*
Given a sorted array nums, remove the duplicates in-place 
such that each element appear only once and return the new length.
Do not allocate extra space for another array, 
you must do this by modifying the input array in-place with O(1) extra memory.
*/
#include<iostream>
#include<vector>
using namespace std;
int removeDuplicates(vector<int>& nums) {
	int i = 1, j = 0;
	if (nums.size() >= 2) {
		while (i < nums.size()) {
			if (nums[i] == nums[j]) {
				i++;
			}
			else {
				if (i - j != 1) {
					nums[++j] = nums[i];
					i++;
				}
				else {
					i++;
					j++;
				}
			}
		}
		j++;
	}
	else
		j = nums.size();
	return j;
}
int removeDuplicates_1(vector<int>& nums) {
	int begin = 0, end = 1;
	if (nums.size() < 2) return nums.size();
	while (end < nums.size()) {
		if (nums[begin] != nums[end]) {	
			begin++;
			nums[begin] = nums[end];
		}
		else {
			end++;
		}
	}
	begin++;	
	return begin;
}
int main() {
	vector<int> n = { 0,0,1,1,1,2,2,3,3,4 };
	//cout << removeDuplicates(n);
	cout << removeDuplicates_1(n);

}