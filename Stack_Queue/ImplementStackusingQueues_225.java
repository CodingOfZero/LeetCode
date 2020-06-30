package Stack_Queue;



import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackusingQueues_225 {
    static class MyStack {
        Queue <Integer> op1,op2;
        /** Initialize your data structure here. */
        public MyStack() {
            op1=new LinkedList<>();
            op2=new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            if(op1.isEmpty()&&op2.isEmpty()) op1.add(x);
            else if(op1.isEmpty())
                op2.add(x);
            else
                op1.add(x);
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if(op1.isEmpty()&&op2.isEmpty()) throw new NullPointerException();
            int data=-1;
            if(op1.isEmpty()){
                int len=op2.size()-1;//先前没有该句，在在循环中改变了栈的大小，条件又与栈大小相关，导致产生错误
                for(int i=0;i<len;i++)
                    op1.add(op2.poll());
                if(op2.size()==1) data=op2.poll();
            }else if(op2.isEmpty()) {
                int len=op1.size()-1;
                for(int i=0;i<len;i++)
                    op2.add(op1.poll());
                if(op1.size()==1) data=op1.poll();
            }
            return data;
        }

        /** Get the top element. */
        public int top() {
            if(op1.isEmpty()&&op2.isEmpty()) throw new NullPointerException();
            int data=-1;
            if(op1.isEmpty()){
                int len=op2.size()-1;
                for(int i=0;i<len;i++)
                    op1.add(op2.poll());
                if(op2.size()==1) data=op2.peek();
                op1.add(op2.poll());
            }else if(op2.isEmpty()) {
                int len=op1.size()-1;
                for(int i=0;i<len;i++)
                    op2.add(op1.poll());
                if(op1.size()==1) data=op1.peek();
                op2.add(op1.poll());
            }
            return data;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return op1.isEmpty()&&op2.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack myStack=new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        int data=myStack.top();
        System.out.println(myStack.top());
    }
}
