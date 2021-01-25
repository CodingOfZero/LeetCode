#include<iostream>
#include<vector>
using std::vector;
//��λ�������棺��������max(n1,n2)��С����� O(n1+n2)
//������S1[lo1,lo1+n1)��S2[lo2,lo2+n2)�ֱ���������������ظ�
int trivialMedian(vector<int>& S1, int lo1, int n1, vector<int>& S2, int lo2, int n2) {
	vector<int> S;
	int hi1 = lo1 + n1, hi2 = lo2 + n2;
	while ((lo1 < hi1) && (lo2 < hi2))
	{
		if (lo2 == hi2)	break;//��ֹvector������Χ
		while ((lo1 < hi1) && (S1[lo1] <= S2[lo2]))
			S.push_back(S1[lo1++]);
		if (lo1 == hi1)	break;	//��ֹvector������Χ
		while ((lo2 < hi2) && (S2[lo2] <= S1[lo1]))
			S.push_back(S2[lo2++]);
	}
	while (lo1 < hi1)
		S.push_back(S1[lo1++]);
	while (lo2 < hi2)
		S.push_back(S2[lo2++]);
	return S[(n1 + n2) / 2];
}

//�ȳ����������鲢����λ��	O(logn)
int median(vector<int>& S1, int lo1, vector<int>& S2, int lo2, int n) {
	if (n < 3)	return trivialMedian(S1, lo1, n, S2, lo2, n);
	int mi1 = lo1 + n / 2, mi2 = lo2 + (n - 1) / 2;//����S1����λ���Լ�S2��������λ��
	if (S1[mi1] < S2[mi2])//S1�Ұ� S2���
		return median(S1, mi1, S2, lo2, n + lo1 - mi1);
	else if (S2[mi2] < S1[mi1])//S1��� S2�Ұ�
		return median(S1, lo1, S2, mi2, n + lo2 - mi2);
	else
		return S1[mi1];
}

//�ǵȳ���������S1��S2
int median_A(vector<int>& S1, int lo1, int n1, vector<int>& S2, int lo2, int n2) {
	if (n2 < n1) return median_A(S2, lo2, n2, S1, lo1, n1);	//ȷ��n1<=n2
	if (n2 < 6)//�ݹ����1<=n1<=n2<=5
		return trivialMedian(S1, lo1, n1, S2, lo2, n2);
	if (2 * n1 < n2)//��������������������⣬���ߵ������ֱ�ӽس�
		return median_A(S1, lo1, n1, S2, lo2 + (n2 - n1 - 1) / 2, n1 + 2 - (n2 - n1) % 2);
	int mi1 = lo1 + n1 / 2;
	int mi2a = lo2 + (n1 - 1) / 2;
	int mi2b = lo2 + n2 - 1 - n1 / 2;
	if (S1[mi1] < S2[mi2a])//S1�Ұ� S2���
		return median_A(S1, mi1, (n1 + 1) / 2, S2, lo2, n2 - n1 / 2);
	if (S2[mi2b] < S1[mi1])//S1��� S2�Ұ�
		return median_A(S1, lo1, n1 / 2 + 1, S2, mi2a, n2 - (n1 - 1) / 2);
	else//S1������S2����ͬʱ����
		return median_A(S1, lo1, n1, S2, mi2a, n2 - (n1 - 1) / 2 * 2);
}
int main() {
	vector<int> t = { 3,7 };
	vector<int> t1 = { 1,2,3,4 };
	vector<int> t2 = { 1,2,4 };
	vector<int> t3 = { 1,2,3,4,5,6,7 };
	std::cout << median(t1, 0, t2, 0, 3);


	/*std::cout << median_A(t, 0, 2, t3, 0, 7);*/

}