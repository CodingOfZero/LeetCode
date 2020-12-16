package Stack_Queue;


import java.util.*;

/**
 * 给定两个 没有重复元素 的数组nums1 和nums2，
 * 其中nums1是nums2的子集。找到nums1中每个元素在nums2中的下一个比其大的值。
 * 单调栈
 */
public class NextGreaterElement_496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack=new ArrayDeque<>();
        Map<Integer,Integer> hashmap=new HashMap<>();
        int[] res=new int[nums1.length];

        for(int i=0;i<nums2.length;i++){
            while (!stack.isEmpty()&&nums2[i]>nums2[stack.peek()]){
                hashmap.put(nums2[stack.pop()],nums2[i]);
            }
            stack.push(i);
        }

        for(int i=0;i<nums1.length;i++){
            Integer value = hashmap.get(nums1[i]);
            res[i]=value==null?-1:value;
        }
        return res;
    }
}
