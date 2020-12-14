package Stack_Queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
 * 如果气温在这之后都不会升高，请在该位置用0 来代替。
 *
 * 例如，给定一个列表temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是[1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 解决办法：单调栈
 * 栈里存放下标，这些下标对应的数字递减，
 */
public class DailyTemperatures_739 {
    public static int[] dailyTemperatures(int[] T) {
        Deque<Integer> stack=new LinkedList<>();
        int[] res=new int[T.length];
        Arrays.fill(res,0);
        for(int i=0;i<T.length;i++){
            int temperature=T[i];
            //如果比栈顶索引对应的数字大，则出栈，直到找到比它大的或者栈空为止
            while (!stack.isEmpty()&&temperature>T[stack.peek()]){
                Integer topIndex = stack.pop();
                res[topIndex]=i-topIndex;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] t=new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] ints = dailyTemperatures(t);
        Arrays.stream(ints).forEach(i-> System.out.printf("%d ",i));
    }
}
