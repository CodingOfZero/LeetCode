package Stack_Queue;

import java.util.*;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值。
 *
 *
 *
 */
public class TrappingRainWater_42 {
    /**
     * 双指针法
     * 时间为O（n）空间为O(1)
     *
     * 把最高点想象成山顶
     * 如果leftMax < rightMax则存储水的高度等于左边最大值减去当前高度
     * 反之，存储水的高度等于右边最大值减去当前高度
     */

    public int trapDoublePoint(int[] height){
        int left=0;
        int right=height.length-1;
        int leftMax=0,rightMax=0;
        int res=0;
        while (left<=right){
            //左边leftMax是可信的
            if(leftMax<rightMax){
                res+=Math.max(0,leftMax-height[left]);
                leftMax=Math.max(leftMax,height[left]);
                left++;
            }else{
                res+=Math.max(0,rightMax-height[right]);
                rightMax=Math.max(rightMax,height[right]);
                right--;
            }
        }
        return res;
    }

    /**
     * 单调栈
     * 在遍历数组时维护一个栈。如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈，
     * 意思是当前的条形块被栈中的前一个条形块界定。
     * 如果我们发现一个条形块长于栈顶，我们可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，
     * 因此我们可以弹出栈顶元素并且累加答案到 res
     * 时间空间复杂度均为O(n)
     */
    public int trap(int[] height) {
        int len=height.length;
        Deque<Integer> stack=new LinkedList<>();
        int res=0;
        for(int i=0;i<len;i++){
            int hi=height[i];
            while (!stack.isEmpty()&&hi>height[stack.peek()]){
                int top = stack.pop();
                if(stack.isEmpty()){
                    break;
                }
                int dis=i-stack.peek()-1;
                int boundHeight=Math.min(hi,height[stack.peek()])-height[top];
                res+=dis*boundHeight;
            }
            stack.push(i);
        }
        return res;
    }

}
