package Stack_Queue;
import java.util.Stack;
/**
 * 最小栈
 */

public class MinStack_155 {
    /** initialize your data structure here. */
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
}
