package Stack_Queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 单调栈+循环数组
 */

public class NextGreaterElementII_503 {
    public static int[] nextGreaterElements(int[] nums) {
        int len=nums.length;
        int[] ans=new int[len];
        Arrays.fill(ans,-1);
        Deque<Integer> index=new ArrayDeque<>();

        for(int i=0;i<2*len-1;i++){
            while (!index.isEmpty()&&nums[i%len]>nums[index.peek()]){
                int pop = index.pop();
                ans[pop]=nums[i%len];
            }
            index.push(i%len);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ints = nextGreaterElements(new int[]{1, 2, 1});
        Arrays.stream(ints).forEach(System.out::println);
    }
}
