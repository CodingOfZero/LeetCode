package SlidingWindow;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum_239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] max=new int[nums.length-k+1];
        int index=0;
        Deque<Integer> deque=new LinkedList<>();
        if(nums.length>=k&&k>=1){
            for(int i=0;i<k;i++){
                while (!deque.isEmpty()&&nums[i]>=nums[deque.peekLast()])
                    deque.pollLast();
                deque.addLast(i);
            }
            for(int i=k;i<nums.length;i++){
                max[index++]=nums[deque.getFirst()];
                while (!deque.isEmpty()&&nums[i]>=nums[deque.peekLast()])
                    deque.pollLast();
                if(!deque.isEmpty()&&deque.getFirst()<=(i-k))
                    deque.pollFirst();
                deque.addLast(i);
            }
            max[index++]=nums[deque.getFirst()];
        }
        return max;
    }

    public static void main(String[] args) {
        int[] s={1,3,-1,-3,5,3,6,7};
        SlidingWindowMaximum_239 sw=new SlidingWindowMaximum_239();
        int[] ints = sw.maxSlidingWindow(s, 3);
        for(int i:ints)
            System.out.println(i);
    }
}
