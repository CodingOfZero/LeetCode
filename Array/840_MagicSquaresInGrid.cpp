/*
判断是否每行，每列，对角线都相等，数组为3X3，内容是1到9，不重复的数字。最中间的数字必须为5；45+3*x=60 x=5
*/
#include<iostream>
#include<set>
#include<vector>
using namespace std;
int magic(int a1,int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9) {
	set<int> t; int j = 1;
	t.insert(a1); t.insert(a2); t.insert(a3); t.insert(a4); t.insert(a5); t.insert(a6); t.insert(a7); t.insert(a8); t.insert(a9);
	for (auto i : t)
		if (i != j)
			return false;
		else
			j++;

	return a1 + a2 + a3 == 15 && a4 + a5 + a6 == 15 && a7 + a8 + a9 == 15 && a1 + a4 + a7 == 15 && a2 + a5 + a8 == 15 && a3 + a6 + a9 == 15 && a1 + a5 + a9 == 15 && a3 + a5 + a7 == 15;
}
int numMagicSquaresInside(vector<vector<int>>& grid) {
	int R = grid.size(), C = grid[0].size();
	int ans = 0;
	for (int r = 0; r < R - 2; ++r) {
		for (int c = 0; c < C - 2; ++c) {
			if (grid[r + 1][c + 1] != 5) continue;
			//判断和是否均为15
			if (magic(grid[r][c], grid[r][c+1], grid[r][c+2], 
				grid[r+1][c], grid[r+1][c+1], grid[r+1][c+2], 
				grid[r+2][c], grid[r+2][c+1],grid[r+2][c+2]))
				ans++;
		}
	}
	return ans;
}

int main() {

}