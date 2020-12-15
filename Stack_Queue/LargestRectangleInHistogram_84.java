package Stack_Queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 单调栈
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class LargestRectangleInHistogram_84 {
    public static int largestRectangleAreaFaster(int[] heights) {
        Deque<Integer> stack=new ArrayDeque<>();
        int len=heights.length;
        //某个点左边第一个小于该点的索引
        int[] left=new int[len];
        //右边
        int[] right=new int[len];
        //那些最后还留在栈中的值，表示它们的最右边最小的是len，len充当哨兵
        Arrays.fill(right,len);
        //stack中存放的索引严格递增，对应的数组中的值也严格递增
        for(int i=0;i<len;i++){
            //当当前下标对应的值小于栈顶元素对应的值时
            while (!stack.isEmpty()&&heights[i]<heights[stack.peek()]){
                right[stack.pop()]=i;
            }
            left[i]=stack.isEmpty()?-1:stack.peek();
            stack.push(i);
        }
        int res=0;
        for(int i=0;i<len;i++){
            res=Math.max(res,(right[i]-left[i]-1)*heights[i]);
        }
        return res;
    }
    /**
     * 第一版
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        Deque<Integer> stack=new ArrayDeque<>();
        int res=0;
        int len=heights.length;
        //标志位
        stack.add(-1);
        for(int i=0;i<len;i++){
            while (stack.size()>1){
                int peek=stack.peek()==-1?-1:heights[stack.peek()];
                if(heights[i]<peek){
                    int cur=stack.pop();
                    int left = stack.peek()==-1?-1:stack.peek();
                    res=Math.max(res, (i-left-1)*heights[cur]);
                }else{
                    break;
                }
            }
            stack.push(i);
        }
        while (stack.size()>1){
            int cur=stack.pop();
            int left = stack.peek()==-1?-1:stack.peek();
            res=Math.max(res, (len-left-1)*heights[cur]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] h=new int[]{2,1,2};
        int i = largestRectangleAreaFaster(h);
        System.out.println(i);
    }
}
