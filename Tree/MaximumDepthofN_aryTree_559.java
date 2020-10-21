package Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MaximumDepthofN_aryTree_559 {
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
    public int maxDepth(Node root) {
        if(root==null) {
            return 0;
        }
        int currMax=0;
        for(Node node:root.children){
            int temp=maxDepth(node);
            if(temp>currMax) {
                currMax=temp;
            }
        }
        return currMax +1;
    }
    public int maxDepth1(Node root) {
        if(root==null) {
            return 0;
        }
        if(root.children.isEmpty()) {
            return 1;
        }
        List<Integer> list=new LinkedList<>();
        for(Node node:root.children){
            list.add(maxDepth1(node));
        }

        return Collections.max(list) +1;
    }

}
