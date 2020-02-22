/*
nums1与nums2为有序数组，将nums2插入到nums1数组中
*/
#include<iostream>
#include<vector>
using namespace std;
void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
	int len = m + n;
	int a_index = m - 1;
	int b_index = n - 1;

	for (int i = len - 1; i >= 0; --i) {
		if (n == 0) break; //如果nums2已空，则停止比较排序
		if (m == 0) { 
			nums1[i] = nums2[b_index--];
		}
		else {
			if (nums1[a_index] >= nums2[b_index]) {
				nums1[i] = nums1[a_index--];
				m--;
			}
			else {
				nums1[i] = nums2[b_index--];
				n--;
			}
		}
	}
}
int main() {
	vector<int> v = { 1,2,3,0,0,0 };
	vector<int> v1 = { 0 };
	vector<int> v2 = { 2,5,6 };
	merge(v, 3, v2, 3);
	for (int i : v)
		cout << i << "  ";


	//merge(v1, 0, v2, 1);
	//for (int i : v1)
	//	cout << i << "  ";
}