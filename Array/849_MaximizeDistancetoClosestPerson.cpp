/*
�ж�����1֮��ľ����С
��������������߽��Լ�1��1 ֮�����  ....1....1....
*/
#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
int maxDistToClosest(vector<int>& seats) {
	int dis[20002] = { 0 };
	int k = 0;
	int i = 0, j = 0;
	for (;j<seats.size(); j++) {//���������������
		if (seats[j] == 1) {
			dis[k++] = j - i;
			i = j;
		}
	}
	dis[k++] = j - i-1;
	int maxdis = dis[0] > dis[k - 1] ? dis[0] : dis[k - 1];//ѡȡ�߽����ֵ
	for (int i = 1; i < k - 1; i++) {//����1��1 ֮��ľ���
		if (dis[i] / 2 > maxdis)
			maxdis = dis[i] / 2;
	}
	return maxdis;
}
int main() {
	vector<int> v = { 1,0,1 };
	cout << maxDistToClosest(v);
}