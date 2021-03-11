package Stack_Queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 实现基本的计算器
 * + - * /  没有括号
 */
public class BasicCalculator_225 {
    /**
     * 先计算乘除，再计算加减
     * 将乘除计算出来的结果，放回原先位置，最后计算一系列值的加法
     * 比如 2+3*4  --->  +2+3*4
     * 遍历字符串 s，并用变量 preSign记录每个数字之前的运算符，
     * 对于第一个数字，其之前的运算符视为加号。每次遍历到数字末尾时，根据 preSign来决定计算方式：
     *          加号：入栈
     *          减号：相反数入栈
     *          乘除：计算数字与栈顶元素，并将栈顶元素替换为计算结果
     *
     */
    public static int calculateFast(String s){
        if(s==null||s.length()==0) return 0;
        char preSign='+';
        int num=0;
        int len=s.length();
        Deque<Integer> stack=new LinkedList<>();
        for(int i=0;i<len;i++){
            if(Character.isDigit(s.charAt(i))){
                num=10*num+(s.charAt(i)-'0');
            }
            if(!Character.isDigit(s.charAt(i))&&s.charAt(i)!=' '||i==len-1){
                switch (preSign){
                    case '+': stack.push(num);break;
                    case '-': stack.push(-num);break;
                    case '*': stack.push(num*stack.pop());break;
                    default:
                        stack.push(stack.pop()/num);
                }
                preSign=s.charAt(i);
                //复原
                num=0;
            }
        }
        int ans=0;
        while (!stack.isEmpty()){
            ans+=stack.pop();
        }
        return ans;
    }



    public static int calculate(String s) {
        if(s==null||s.length()==0) return 0;
        Deque<Long> dataStack=new LinkedList<>();
        Deque<Character> opeStack=new LinkedList<>();

        int i=0;
        int len=s.length();
        while (i<len){
            if(' '==s.charAt(i)){
                i++;
            }else if('+'==s.charAt(i)||'-'==s.charAt(i)){
                while(!opeStack.isEmpty()){
                    long num2=dataStack.pop();
                    long num1=dataStack.pop();
                    dataStack.push(cal(num1,opeStack.pop(),num2));
                }
                opeStack.push(s.charAt(i));
                i++;
            }else if('*'==s.charAt(i)){
                if(!opeStack.isEmpty()&&(opeStack.peek()=='*'||opeStack.peek()=='/')){
                    long num2=dataStack.pop();
                    long num1=dataStack.pop();
                    dataStack.push(cal(num1,opeStack.pop(),num2));
                }
                opeStack.push(s.charAt(i));
                i++;
            }else if('/'==s.charAt(i)){
                if(!opeStack.isEmpty()&&(opeStack.peek()=='*'||opeStack.peek()=='/')){
                    long num2=dataStack.pop();
                    long num1=dataStack.pop();
                    dataStack.push(cal(num1,opeStack.pop(),num2));
                }
                opeStack.push(s.charAt(i));
                i++;
            }else{
                long tmp=0;
                while(i<len&&Character.isDigit(s.charAt(i))){
                    tmp=10*tmp+(s.charAt(i)-'0');
                    i++;
                }
                dataStack.push(tmp);
            }
        }
        while (!opeStack.isEmpty()){
            long num2=dataStack.pop();
            long num1=dataStack.pop();
            dataStack.push(cal(num1,opeStack.pop(),num2));
        }
        return dataStack.pop().intValue();
    }

    private static long cal(long m,char sym,long n){
        long ans=0;
        switch (sym){
            case '+': ans=m+n; break;
            case '-': ans=m-n; break;
            case '*': ans=m*n; break;
            case '/': ans=m/n; break;
            default:
                break;
        }
        return ans;
    }

    public static void main(String[] args) {
        int calculate = calculate("2*3*4");
        System.out.println(calculate);

        int calculate1 = calculate(" 3/2 ");
        System.out.println(calculate1);

        int calculate2 = calculate(" 3+5 / 2 ");
        System.out.println(calculate2);
    }
}
