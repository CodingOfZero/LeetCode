#include<iostream>
#include<vector>
#include<set>
#include<algorithm>
using namespace std;
int thirdMax(vector<int>& nums) {
	set<int> container;
	for (int i : nums)
		container.insert(i);
	set<int>::reverse_iterator it;
	if (container.size() < 3) return *container.rbegin();
	int enums = 1,res=0;
	for (it = container.rbegin(); it != container.rend(); it++) {
		if (enums == 3)
			res = *it;
		enums++;
	}
	return res;
}
int thirdMax_1(vector<int>& nums) {
	sort(nums.begin(), nums.end());
	reverse(nums.begin(), nums.end());

	int c = 1;
	for (int i = 1; i < nums.size(); i++) {
		if (nums[i] < nums[i - 1]) c++;
		if (c == 3) return nums[i];
	}
	return nums[0];
}
