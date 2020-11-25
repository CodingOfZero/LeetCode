package Backtrack;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给一个表达式，它可以包含开括号和闭括号以及一些可选的字符，字符串中不会有其他的运算符。
 * 我们需要去掉最少的小括号以使输入字符串有效。如果有多个有效的输出，那么去掉相同数量的小括号，然后打印所有这样的输出。
 * 思路：
 *      搜索所有可能的情况，判断是否为有效字符串。
 *      使用BFS来移动状态，使用BFS将保证去除最少的括号，因为我们逐级进入状态，每一级对应一个额外的括号去除
 *      示例： ()())   --->视为根结点  将其看成一棵树
 *      )())  ( ()) ())) ()() ()()  -->第二层  每层去掉一个括号
 *
 *
 */
public class RemoveInvalidParenthesis {
    /**
     * 判断是否为括号
     * @param c 字符
     * @return 判断结果
     */
    private static boolean isParenthesis(char c){
        return (c=='(')||(c==')');
    }

    /**
     * 判断表达式是否有效
     * 如果左括号则加1，右括号则减1，如果右括号的个数大于左括号，则无效
     * 当左括号的个数小于右括号时，由于还有字符串，所以继续进行，最后判断cnt是否为0
     * 为0表示个数相同
     * @param str 表达式
     */
    private static boolean isValidString(String str){
        int cnt=0;
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if ('(' == aChar) {
                cnt++;
            } else if (')' == aChar) {
                cnt--;
            }
            if (cnt < 0) {
                return false;
            }
        }
        return cnt==0;
    }

    public static void removeInvalidParenthesis(String str){
        if(str.length()==0){
           return;
        }
        //访问集合，用于忽略已访问过的字符串
        HashSet<String> visit=new HashSet<>();
        //BFS
        Queue<String> queue=new LinkedList<>();
        //当某层有有效表达式， 设置为true，将该层全部处理完后，不再向下继续进行
        boolean level=false;
        queue.add(str);
        while (!queue.isEmpty()){
            String peek = queue.poll();
            if(isValidString(peek)){
                System.out.println(peek);
                level=true;
            }
            if(level){
                continue;
            }
            for(int i=0;i<str.length();i++){
                //判断是否为括号
                if(!isParenthesis(str.charAt(i))){
                    continue;
                }
                //如果是，去掉该下标的括号，组成新的字符串
                //str.substring(0,0)如果范围相同，返回为空
                String temp=str.substring(0,i)+str.substring(i+1);
                if(!visit.contains(temp)){
                    queue.add(temp);
                    visit.add(temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        String expression = "()())()";
        removeInvalidParenthesis(expression);

        expression = "()v)";
        removeInvalidParenthesis(expression);
    }
}
