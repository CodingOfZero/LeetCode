package Stack_Queue;

import java.util.Deque;
import java.util.LinkedList;

public class EvaluateRPN_150 {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack=new LinkedList<>();
        for(String token:tokens){
            if(Character.isDigit(token.charAt(0))||token.charAt(0)=='-'&&token.length()>1){
                stack.push(Integer.parseInt(token));
            }else {
                int n2=stack.pop();
                int n1=stack.pop();
                stack.push(eval(n1,n2,token.charAt(0)));
            }
        }
        return stack.pop();
    }
    private int eval(int n1,int n2,char symbol){
        switch (symbol){
            case '+':return n1+n2;
            case '-':return n1-n2;
            case '*':return n1*n2;
            default:
                return n1/n2;
        }
    }

    public static void main(String[] args) {
        EvaluateRPN_150 rpn150 = new EvaluateRPN_150();
        int i = rpn150.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"});
        System.out.println(i);

    }
}
