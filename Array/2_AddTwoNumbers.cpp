#include<iostream>
using namespace std;
struct ListNode {
    int val;
    ListNode* next;
    ListNode(int x) : val(x), next(NULL) {}
};

ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
    int d1 = 0, d2 = 0, sum = 0;
    int i = 1;
    while (l1) {
        d1 = d1 + l1->val*i;
        l1 = l1->next;
        i *= 10;
    }
    //while (l2) {
    //    d2 = d2 + l1->val * i;
    //    l1 = l1->next;
    //    i *= 10;
    //}
    sum = d1 + d2;
    cout << sum;
    return NULL;
}
int main() {
    ListNode* a = (ListNode*)malloc(sizeof(ListNode));
    int k = 0;
    while (cin>>k) {
        ListNode* b = (ListNode*)malloc(sizeof(ListNode));
        b->val = k;
        a->next = b;
        a = b;
    }
    addTwoNumbers(a, NULL);
}