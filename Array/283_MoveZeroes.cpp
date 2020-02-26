/*
将零移到最后,且保持相对顺序*/
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
void moveZeroes(vector<int>& nums) {
	int zeros = count(nums.begin(), nums.end(), 0);//统计零
	nums.erase(remove(nums.begin(), nums.end(), 0), nums.end());//删除零
	for (int i = 0; i < zeros; i++)
		nums.push_back(0);
}