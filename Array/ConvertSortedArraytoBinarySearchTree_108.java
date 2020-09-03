package Array;

/**
 * 有序数组转为平衡二叉排序树
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
public class ConvertSortedArraytoBinarySearchTree_108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return createTreeByBinarySearch(nums,0,nums.length);
    }
    private TreeNode  createTreeByBinarySearch(int[] temp,int lo,int hi){//左闭右开
        if(lo>=hi) return null;
        int mi=(lo+hi)/2;
        TreeNode root=new TreeNode(temp[mi]);
        root.left=createTreeByBinarySearch(temp,lo,mi);
        root.right=createTreeByBinarySearch(temp,mi+1,hi);
        return root;
    }
}
