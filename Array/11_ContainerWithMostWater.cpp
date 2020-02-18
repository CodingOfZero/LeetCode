/*
Given n non-negative integers a1, a2, ..., an , 
where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
Find two lines, which together with x-axis forms a container, such that the container contains the most water.
Note: You may not slant the container and n is at least 2.
*/
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int maxArea(vector<int>& height) {
	int area = 0;
	int tempx = 0, tempa = 0;
	//从左侧扫描，找到离当前直线最远的一条直线，且该直线比它长
	for (size_t i = 0; i < height.size(); i++) {
		tempx = 0;
		for (size_t j = i + 1; j < height.size(); j++) {
			if (height[j] >= height[i]) {
				tempx = j;
			}
		}
		if (tempx) {
			tempa = (tempx - i) * height[i];
			if (tempa > area)
				area = tempa;
		}	
	}
	
	//从右侧扫描，找到离当前直线最远的一条直线，且该直线比它长
	for (int i = height.size()-1; i > 0; --i) {
		tempx = -1;
		for (int  j = i - 1; j >= 0; --j) {
			if (height[j] >= height[i]) {
				tempx = j;
			}
		}
		if (tempx!=-1) {
			tempa = (i - tempx) * height[i];
			if (tempa > area)
				area = tempa;
		}
	}
	return area;
}
/*
*Runtime: 20 ms,
*/
int maxArea_1(vector<int>& height) {
	int max_capacity = 0;
	int l = 0, r = height.size() - 1;

	while (l < r) {
		int min_height = min(height[l], height[r]);

		if (max_capacity < (r - l) * (min_height)) {
			max_capacity = (r - l) * (min_height);
		}

		if (min_height == height[l]) {
			l++;
		}
		else {
			r--;
		}
	}

	return max_capacity;
}
int main() {
	vector<int> a = { 1,8,6,2,5,4,8,3,7 };
	vector<int> b = { 2,1 };
	//cout<<maxArea(a)<<endl;
	cout << maxArea_1(b) << endl;
}