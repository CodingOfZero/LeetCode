/*
杨辉三角
即 C(n+1,i)=C(n,i)+C(n,i-1)。
返回第k行，从0开始
*/
#include<iostream>
#include<vector>
using namespace std;
vector<int> getRow(int rowIndex) {
	vector<vector<int>> res;
	for (int i = 0; i < rowIndex+1; i++) {
		vector<int> temp;
		for (int j = 0; j < i + 1; j++) {
			if (j == 0) {
				temp.push_back(1);
				continue; //要提前终止此次循环，防止放入多余的1
			}
			if (j == i) temp.push_back(1);
			else {
				temp.push_back(res[i - 1][j] + res[i - 1][j - 1]);
			}
		}
		if(i== rowIndex) return temp;
		res.push_back(temp);
	}
}

int main() {
	vector<int> v = getRow(4);
	for (auto i : v) {
			cout << i;
	}
}