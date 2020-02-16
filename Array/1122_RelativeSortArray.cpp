/*
Given two arrays arr1 and arr2, the elements of arr2 are distinct, 
and all elements in arr2 are also in arr1.
Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  
Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
*/
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
/*Runtime: 8 ms, faster than 46.62% of C++ online submissions for Relative Sort Array.
Memory Usage : 8.7 MB, less than 100.00% of C++ online submissions for Relative Sort Array.
*/
vector<int> res;
void relativeSortArray(vector<int>& arr1, vector<int>& arr2) {
	
	vector<int>::iterator it;
	for (auto elem : arr2) {
		int m = count(arr1.begin(), arr1.end(), elem);
		for (size_t i = 0; i != m; i++)
			res.push_back(elem);
	}
	sort(arr1.begin(), arr1.end());
	for (auto elem : arr1) {
		it = find(arr2.begin(), arr2.end(), elem);
		if (it == arr2.end())
			res.push_back(elem);
	}
}
int main() {
	vector<int> arr1 = { 28, 6, 22, 8, 44, 17 };
	vector<int> arr2 = { 22, 28, 8, 6 };
	relativeSortArray(arr1, arr2);
	for (auto elem : res)
		cout << elem<<" ";
}