/*
*Given a binary tree, return the inorder traversal of its nodes' values.
*/ 

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    vector<int> inorderTraversal(TreeNode* root) {
        stack<TreeNode*> s;
        vector<int> inorder;
        while(true)
        {
            if(root){
                s.push(root);
                root=root->left;
            }else if(!s.empty()){
                root=s.top();
                s.pop();
                inorder.push_back(root->val);
                root=root->right;
            }else
                break;
        }
        return inorder;
    }
};
