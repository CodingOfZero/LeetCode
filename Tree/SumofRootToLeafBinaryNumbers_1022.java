package Tree;


import java.util.ArrayList;

public class SumofRootToLeafBinaryNumbers_1022 {
    //方法一：也适用于一般情况。
    private int sum=0;
    private int sum_2=0;
//    public int sumRootToLeaf(TreeNode root) {
//        int[] sum=new int[1];
//        ArrayList<Integer> path=new ArrayList<>();
//        sumRootToLeafHelper(root,path,sum);
//        return sum[0];
//    }
//
//    private void sumRootToLeafHelper(TreeNode root,ArrayList<Integer> path,int[] sum) {
//        if(root==null) return;
//        path.add(root.val);
//        boolean isLeaf=root.left==null&&root.right==null;
//        if(isLeaf)
//            sum[0]+=count(path);
//        if(root.left!=null)
//            sumRootToLeafHelper(root.left,path,sum);
//        if(root.right!=null)
//            sumRootToLeafHelper(root.right,path,sum);
//        path.remove(path.size()-1);
//    }
    public int sumRootToLeaf(TreeNode root) {
        ArrayList<Integer> path=new ArrayList<>();
        sumRootToLeafHelper(root,path);
        return sum;
    }

    private void sumRootToLeafHelper(TreeNode root,ArrayList<Integer> path) {
        if(root==null) return;
        path.add(root.val);
        boolean isLeaf=root.left==null&&root.right==null;
        if(isLeaf)
            sum+=count(path);
        if(root.left!=null)
            sumRootToLeafHelper(root.left,path);
        if(root.right!=null)
            sumRootToLeafHelper(root.right,path);
        path.remove(path.size()-1);
    }
    private static int count(ArrayList<Integer> path){
        int number=0;
        int len=path.size()-1;
        for(int n:path){
            number+=n*Math.pow(2,len--);
        }
        return number;
    }


    //方法二：根据题目特点
    public int sumRootToLeaf_2(TreeNode root){
        getSum(root,0);
        return sum_2;
    }
    private void getSum(TreeNode root, int sumTemp){
        if(root==null) return;
        boolean isLeaf=root.left==null&&root.right==null;
        if(isLeaf){
            int sum1=2*sumTemp+root.val;
            sum_2+=sum1;
            return;
        }
        if(root.left!=null)
            getSum(root.left,2*sumTemp+root.val);
        if(root.right!=null)
            getSum(root.right,2*sumTemp+root.val);
    }
}
