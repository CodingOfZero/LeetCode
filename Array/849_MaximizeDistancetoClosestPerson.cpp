/*
判断两者1之间的距离大小
考虑两种情况：边界以及1与1 之间距离  ....1....1....
*/
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int maxDistToClosest(vector<int>& seats) {
	int dis[20002] = { 0 };
	int k = 0;
	int i = 0, j = 0;
	for (;j<seats.size(); j++) {//将距离存入数组中
		if (seats[j] == 1) {
			dis[k++] = j - i;
			i = j;
		}
	}
	dis[k++] = j - i-1;
	int maxdis = dis[0] > dis[k - 1] ? dis[0] : dis[k - 1];//选取边界最大值
	for (int i = 1; i < k - 1; i++) {//考虑1与1 之间的距离
		if (dis[i] / 2 > maxdis)
			maxdis = dis[i] / 2;
	}
	return maxdis;
}
int main() {
	vector<int> v = { 1,0,1 };
	cout << maxDistToClosest(v);
}