/*
��ת������
*/
struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
};
TreeNode* invertTree(TreeNode* root) {
    //1.����ýڵ㣬��������
    manipulate( root);
    return root;
}
void manipulate(TreeNode* root) {
    if (root) {
        TreeNode* temp = root->left;
        root->left = root->right;
        root->right = temp;
        invertTree(root->left);
        invertTree(root->right);
    }
}