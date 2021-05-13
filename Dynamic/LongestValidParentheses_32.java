package Dynamic;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 最长有效括号
 */
public class LongestValidParentheses_32 {
    /**
     * maxlen[i] 以i结尾的最长有效括号
     * 当s.charAt(i)==')'时才可能是有效括号
     *      1.i-1是‘(’
     *      2.i-1是')'
     */
    public int longestValidParenthesesDP(String s) {
        if(s==null||s.length()==0) return 0;
        char[] str=s.toCharArray();
        int[] maxlen=new int[str.length];
        int ans=0;
        for(int i=1;i<str.length;i++){
            if(str[i]==')'){
                if(str[i-1]=='('){
                    maxlen[i]=(i>=2?maxlen[i-2]:0)+2;

                }else if(i-maxlen[i-1]>0&&str[i-maxlen[i-1]-1]=='('){

                    maxlen[i]=maxlen[i-1]+2+((i-maxlen[i-1]>=2)?maxlen[i-maxlen[i-1]-2]:0);
                }
                ans=Math.max(ans,maxlen[i]);
            }
        }
        return ans;
    }

    public int longestValidParenthesesByStack(String s) {
        if(s==null||s.length()==0) return 0;
        char[] str=s.toCharArray();
        //存放下标
        //始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
        //刚开始栈为空，放入-1保持定义的完整性
        Deque<Integer> stack=new LinkedList<>();
        stack.push(-1);

        int ans=0;
        for(int i=0;i<str.length;i++){
            if(str[i]=='('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    //为空表明没有被匹配
                    stack.push(i);
                }else{
                    ans=Math.max(ans,i-stack.peek());
                }
            }
        }
        return ans;
    }
}
