/*

只能从前到后，判断两数之差最大值

*/
#include<iostream>
#include<vector>
using namespace std;
int maxProfit(vector<int>& prices) {
	int max = 0;
	for (int i = 0; i < prices.size(); i++) {
		for (int j = i+1; j < prices.size(); j++) {
			if (prices[j] > prices[i]) {
				int temp = prices[j] - prices[i];
				if (temp > max)
					max = temp;
			}
		}
	}
	return max;
}
int maxProfit_1(vector<int>& prices) {
	if (prices.empty()) return 0;
	int profit = 0;
	int low = prices[0];//需先判断prices是否为空
	for (int i = 0; i < prices.size(); i++) {
		if (prices[i] < low)
			low = prices[i];
		else {
			if (prices[i] - low > profit)
				profit = prices[i] - low;
		}
	}
	return profit;
}

int main() {
	vector<int> v = { 7,6,4,3,1 };
	
	cout << maxProfit_1(v);
}
