package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目要求不使用额外空间，找到树中出现频率最多的结点，如果有多个，输出多个
 * 不使用额外空间，利用两次中序遍历
 */
public class FindModeinBinarySearchTree_501 {
    //次数
    private int modeTimes;
    //最大次数
    private int maxModeTimes;
    //上一个结点的值
    private int lastValue;
    public int[] findMode(TreeNode root) {
        if(root==null) return new int[]{};
        modeTimes=0;
        maxModeTimes=0;
        lastValue=root.val;
        findMaxFre(root);

        List<Integer> list = new ArrayList<>();
        //对变量进行还原，以便进行第二次中序遍历
        lastValue=root.val;
        modeTimes=0;
        inorder(root, list);

        int[] result=new int[list.size()];
        for(int i=0;i<result.length;i++){
            result[i]=list.get(i);
        }
        return result;
    }
    //第一次中序遍历，找到最大频数
    private void findMaxFre(TreeNode root) {
        if (root != null) {
            findMaxFre(root.left);
            if(root.val==lastValue){
                modeTimes ++;
            }else{
                modeTimes=1;
                lastValue=root.val;
            }
            maxModeTimes=Math.max(maxModeTimes,modeTimes);
            findMaxFre(root.right);
        }
    }
    private void inorder (TreeNode root,List<Integer> list){
        if (root != null) {
            inorder(root.left,list);
            if(root.val==lastValue){
                modeTimes++;
            }else{
                modeTimes=1;
                lastValue=root.val;
            }
            //找到次数等于最大次数的值，将其加入到链表中
            if(modeTimes==maxModeTimes){
                list.add(root.val);
            }
            inorder(root.right,list);
        }
    }

    /**
     * 以下利用中序遍历和HashMap，HashMap中存储值与它的频数
     */
    private HashMap<Integer, Integer> map;
    private Integer max = 0;
    private int count = 0;
    public int[] findMode1 (TreeNode root){
        map = new HashMap<>();
        inorder1(root);
        int[] result = new int[count];
        int j = 0;
        for (int i : map.keySet()) {
            if (map.get(i).equals(max)) {
                result[j++] = i;
            }
        }
        return result;
    }
    private void inorder1 (TreeNode root){
        if (root != null) {
            inorder1(root.left);
            int fre = map.getOrDefault(root.val, 0) + 1;
            if (fre == max) {
                count++;
            }
            if (fre > max) {
                max = fre;
                count = 1;
            }
            map.put(root.val, fre);
            inorder1(root.right);
        }
    }
}