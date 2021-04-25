package Backtrack;

/**
 * 结论：Java中只有值传递，没有引用传递
 * 1. 当方法参数为基本类型时，在方法体内修改仅仅是实参的拷贝，无法真正修改实参
 * 2. 当方法参数为对象引用时，可以改变对象的状态，不能让一个对象参数引用一个新的对象（后半句很重要）
 */
public class CallByValueOrCallByReferenceTest {

    static class TreeNode {
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

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyNode=new TreeNode(-1);
        helper(root, dummyNode);
        return dummyNode.right;
    }


    private void helper(TreeNode root,TreeNode pre){
        if(root==null){
            return ;
        }
        //pre是一个拷贝，当递归回溯时，栈被回收，设置引用失效
        helper(root.left,pre);
        pre.right=root;
        root.left=null;
        pre=root;
        if(root.val==5){
            root.val=342423;
        }
        helper(root.right,pre);
    }

    public static void main(String[] args) {
        CallByValueOrCallByReferenceTest fun = new CallByValueOrCallByReferenceTest();
        TreeNode left=new TreeNode(1,null,null);
        TreeNode right=new TreeNode(7,null,null);
        TreeNode root=new TreeNode(5,left,right);
        fun.increasingBST(root);
    }
}
