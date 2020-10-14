package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 考点为层次遍历，只不过给的是Node，而非树的结点，
 */
public class N_aryTreeLevelOrderTraversal_429 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result=new LinkedList<>();
        if(root==null) {
            return result;
        }
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> list=new LinkedList<>();
            int size=queue.size();
            for(int i=0;i<size;i++){
                Node pollNode = queue.poll();
                list.add(pollNode.val);
                //将链表中的结点依次加入队列中
                queue.addAll(pollNode.children);
            }
            if(list.size()>0) {
                result.add(list);
            }
        }
        return result;
    }
}
