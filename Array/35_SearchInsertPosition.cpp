/*
�������ڣ��ҵ�����λ��,���򣬷�������*/
#include<iostream>
#include<vector>
using namespace std;
int searchInsert(vector<int>& nums, int target) {
	int i = 0;
	while (nums[i] < target) {
			i++;
			if (i == nums.size())//ע���Ƿ�Խ��
				return i;
	}
	return i;
}
int main() {
	vector<int> v = { 1,3,5,6 };
	cout << searchInsert(v, 7);
}