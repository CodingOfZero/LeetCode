package Stack_Queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 有效的括号，左括号必须用相同类型的右括号闭合。左括号必须以正确的顺序闭合。
 */
public class ValidParentheses_20 {
    public boolean isValid(String s) {
        char[] str=s.toCharArray();
        Deque<Character> queue=new LinkedList<>();
        for(char c:str){
            if('('==c||'['==c||'{'==c){
                queue.push(c);
            }else{
                if(!queue.isEmpty()){
                    char cur=queue.peek();
                    if(c==')'&&cur!='('){
                        return false;
                    }else if(c==']'&&cur!='['){
                        return false;
                    }else if(c=='}'&&cur!='{'){
                        return false;
                    }else{
                        queue.pop();
                    }
                }else{
                    return false;
                }
            }
        }
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses_20 test = new ValidParentheses_20();
        boolean valid = test.isValid("([)]");
        System.out.println(valid);
    }
}
