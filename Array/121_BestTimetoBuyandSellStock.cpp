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
	int profit = 0;
	int low = prices[0];

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
