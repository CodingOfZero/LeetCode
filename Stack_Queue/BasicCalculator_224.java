package Stack_Queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 实现基本的计算器
 * 只含有加号和减号
 */
public class BasicCalculator_224 {
    public int calculate(String s) {
        Deque<Integer> ops=new LinkedList<>();
        ops.push(1);
        int sign=1;

        int i=0;
        int len=s.length();
        int ret=0;
        while (i<len){
            char c=s.charAt(i);
            if('+'==c){
                sign=ops.peek();
                i++;
            }else if('-'==c){
                sign=-ops.peek();
                i++;
            }else if('('==c){
                ops.push(sign);
                i++;
            }else if(' '==c){
                i++;
            }else if(')'==c){
                ops.pop();
                i++;
            }else{
                //判断是否是数字
                long num=0;
                while (i<len&&Character.isDigit(s.charAt(i))){
                    num=num*10+(s.charAt(i)-'0');
                    i++;
                }
                ret+=sign*num;
            }
        }
        return ret;
    }

}
