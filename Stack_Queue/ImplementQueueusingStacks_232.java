package Stack_Queue;

import java.util.Stack;

public class ImplementQueueusingStacks_232 {
    static class MyQueue {
        Stack<Integer> op1,op2;
        /** Initialize your data structure here. */
        public MyQueue() {
            op1=new Stack<>();
            op2=new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            op1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if(op2.isEmpty()&&op1.isEmpty()) throw new NullPointerException();
            if(op2.isEmpty()){
                while (!op1.isEmpty()){
                    op2.push(op1.pop());
                }
            }
            return op2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if(op2.isEmpty()&&op1.isEmpty()) throw new NullPointerException();
            if(op2.isEmpty()){
                while (!op1.isEmpty()){
                    op2.push(op1.pop());
                }

            }
            return op2.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return op2.isEmpty()&&op1.isEmpty();
        }
    }
}
