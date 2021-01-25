/*
ÅÐ¶ÏÊÇ·ñµ¥µ÷
*/
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
bool isMonotonic(vector<int>& A) {
	if (A.size() < 2) return true;
	if (A[0] > A[A.size() - 1]) {
		for (int i = 0; i < A.size() - 1; i++)
			if (A[i] < A[i + 1])
				return false;
		return true;
	}
	else if (A[0] < A[A.size() - 1]) {
		for (int i = 0; i < A.size() - 1; i++)
			if (A[i] > A[i + 1])
				return false;
		return true;
	}
	else {
		for (int i = 0; i < A.size() - 1; i++)
			if (A[i] != A[i + 1])
				return false;
		return true;
	}
}