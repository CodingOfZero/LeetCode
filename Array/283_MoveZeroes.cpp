/*
�����Ƶ����,�ұ������˳��*/
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
void moveZeroes(vector<int>& nums) {
	int zeros = count(nums.begin(), nums.end(), 0);//ͳ����
	nums.erase(remove(nums.begin(), nums.end(), 0), nums.end());//ɾ����
	for (int i = 0; i < zeros; i++)
		nums.push_back(0);
}