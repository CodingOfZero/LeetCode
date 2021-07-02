package Stack_Queue;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
/**
 * 最小栈
 */

public class MinStack_155 {
    /** initialize your data structure here.
     * 空间为O(n)
     *
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;
    public MinStack_155() {
        dataStack=new Stack<Integer>();
        minStack=new Stack<Integer>();
    }

    public void push(int x) {
        dataStack.push(x);
        if(minStack.isEmpty()||x<(int)minStack.peek())
            minStack.push(x);
        else
            minStack.push((int)minStack.peek());
    }

    public void pop() {
        assert (!dataStack.isEmpty()&&!minStack.isEmpty());
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        assert (!dataStack.isEmpty()&&!minStack.isEmpty());
        return (int)dataStack.peek();
    }

    public int getMin() {
        assert (!dataStack.isEmpty()&&!minStack.isEmpty());
        return (int)minStack.peek();
    }
     */
    /**
     * 空间为O(1)
     * 存放与最小值的差值diff
     *      diff<0:表明元素比先前的最小值还要小,即元素为现在的最小值
     *      diff>0:表明元素比最小值大，最小值加差值即为所求元素
     * 使用Long避免溢出
     * */
    private Deque<Long> stack;
    private long minValue;

    public MinStack_155() {
        stack=new LinkedList<>();
        minValue=0;
    }

    public void push(int x) {
        if(stack.isEmpty()){
            stack.push(0L);
            minValue=x;
        }else{
            long diff=x-minValue;
            stack.push(diff);
            minValue=diff<0?x:minValue;
        }
    }

    public void pop() {
        if(!stack.isEmpty()){
            long res;
            long diff=stack.pop();
            if(diff<0){
                res=minValue;
                minValue=res-diff;
            }else{
                res=minValue+diff;
            }
        }
    }

    public int top() {
        assert(!stack.isEmpty());
        long diff=stack.peek();
        return (int)(diff>0?minValue+diff:minValue);
    }

    public int getMin() {
        assert (!stack.isEmpty());
        return (int)minValue;
    }
}
