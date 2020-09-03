package Lists;

/**
 * 将一个有序链表转换为平衡二叉排序树
 * 思路：二分查找的判定树也是平衡二叉排序树
 * 空间复杂度为O(n)
 * 将链表存放到数组中，对其进行二分查找
 */
public class ConvertSortedListtoBinarySearchTree_109 {
    public TreeNode sortedListToBST(ListNode head) {
        ListNode p=head;
        int len=0;
        while (p!=null){
            len++;
            p=p.next;
        }
        int[] temp=new int[len];
        p=head;
        for(int i=0;i<len;i++){
            temp[i]=p.val;
            p=p.next;
        }
        return createTreeByBinarySearch(temp,0,len);
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
