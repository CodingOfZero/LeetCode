#include<iostream>
#include<vector>
#include<set>
using namespace std;
/*
Runtime: 48 ms, faster than 35.15% of C++ online submissions for Contains Duplicate.
Memory Usage: 18.1 MB, less than 5.49% of C++ online submissions for Contains Duplicate.
*/
bool containsDuplicate(vector<int>& nums) {
	//����set�����У������ȷ����ı䣬˵�����ظ�Ԫ��
	set<int> container;
	for (int i = 0; i < nums.size(); i++)
		container.insert(nums[i]);
	bool res=container.size() == nums.size() ? false : true;
	return res;
}
int main() {

}