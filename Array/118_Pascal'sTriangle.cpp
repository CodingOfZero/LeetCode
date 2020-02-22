/*
杨辉三角
即 C(n+1,i)=C(n,i)+C(n,i-1)。
*/
#include<iostream>
#include<vector>
using namespace std;
vector<vector<int>> generate(int numRows) {
	vector<vector<int>> res;
	for (int i = 0; i < numRows; i++) {
		vector<int> temp;

		for (int j = 0; j < i + 1; j++) {
			if (j == 0) { 
				temp.push_back(1); 
				continue; //要提前终止此次循环，防止放入多余的1
			}
			if (j == i) temp.push_back(1);
			else {
				temp.push_back(res[i-1][j] + res[i-1][j - 1]);
			}
		}
		
		res.push_back(temp);
	}
	return res;
}

int main() {
	vector<vector<int>> v=generate(5);
	for (auto i : v) {
		for (auto j : i)
			cout << j;
		cout << endl;
	}
}