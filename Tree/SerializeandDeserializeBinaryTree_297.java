package Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 二叉树的序列化与反序列化
 */
public class SerializeandDeserializeBinaryTree_297 {
    public String serialize(TreeNode root) {
        StringBuilder s=new StringBuilder();
        serializeHelper(root,s);
        return s.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder s) {
        if(s.length()>0)
            s.append(",");
        if(root==null){
            s.append("#");
            return;
        }
        s.append(root.val);
        serializeHelper(root.left,s);
        serializeHelper(root.right,s);
    }

    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        String[] res=data.split(",");
//        AtomicInteger index=new AtomicInteger(0);
//        return deserializeHelper(res,index);
//    }
//    private TreeNode deserializeHelper(String[] res,AtomicInteger index) {
//        String num=res[index.get()];
//        index.set(index.get()+1);
//        if(num.equals("#"))
//            return null;
//        TreeNode root=new TreeNode(Integer.parseInt(num));
//        root.left=deserializeHelper(res,index);
//        root.right=deserializeHelper(res,index);
//        return root;
//    }
//solution2
    public TreeNode deserialize(String data) {
        String[] res=data.split(",");
        return deserializeHelper(res,new int[1]);
    }
    private TreeNode deserializeHelper(String[] res, int[] index) {
        if(index[0]==res.length)
            return null;
        String num=res[index[0]++];
        if(num.equals("#"))
            return null;
        TreeNode root=new TreeNode(Integer.parseInt(num));
        root.left=deserializeHelper(res,index);
        root.right=deserializeHelper(res,index);
        return root;
    }





}


