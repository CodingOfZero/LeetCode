package Stack_Queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
 * 注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，
 * 例如不会出现像3a或2[4]的输入。
 *
 */
public class DecodeString_394 {
    /**
     * 1.构建辅助栈 stack， 遍历字符串 s 中每个字符 c；
         * 当 c 为数字时，将数字字符转化为数字 multi，用于后续倍数计算；
         * 当 c 为字母时，在 res 尾部添加 c；
         * 当 c 为 [ 时，将当前 multi 和 res 入栈，并分别置空置 00：
             * 记录此 [ 前的临时结果 res 至栈，用于发现对应 ] 后的拼接操作；
             * 记录此 [ 前的倍数 multi 至栈，用于发现对应 ] 后，获取 multi × [...] 字符串。
             * 进入到新 [ 后，res 和 multi 重新记录。
         * 当 c 为 ] 时，stack 出栈，拼接字符串 res = last_res + cur_multi * res，其中:
         * last_res是上个 [ 到当前 [ 的字符串，例如 "3[a2[c]]" 中的 a；
         * cur_multi是当前 [ 到 ] 内字符串的重复倍数，例如 "3[a2[c]]" 中的 2。
     * 2.返回字符串 res。
     * @param s
     * @return
     */
    public String decodeStringFaster(String s) {
        StringBuilder res=new StringBuilder();
        int multi=0;
        LinkedList<Integer> stack_multi=new LinkedList<>();
        LinkedList<String> stack_res=new LinkedList<>();
        for(Character c:s.toCharArray()){
            if(c=='['){
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi=0;
                res=new StringBuilder();
            }else if(c==']'){
                StringBuilder tmp=new StringBuilder();
                //出栈
                int cur_multi=stack_multi.removeLast();
                for(int i=0;i<cur_multi;i++){
                    tmp.append(res);
                }
                res=new StringBuilder(stack_res.removeLast()+tmp);
            }else if(isDigit(c)){
                multi=multi*10+Integer.parseInt(c+"");
            }else{
                res.append(c);
            }
        }
        return res.toString();
    }
    /**
     * 方法一
     */
    public String decodeString(String s) {
        Deque<Character> res=new ArrayDeque<>();
        Deque<Character> temp=new ArrayDeque<>();
        char[] charArray = s.toCharArray();
        for(int i=0;i<charArray.length;i++){
            //当遇到]时
            if(charArray[i]==']'){
                while (!res.isEmpty()){
                    if(res.peek()!='['){
                        temp.push(res.pop());
                    }else{
                        res.pop();
                        break;
                    }
                }

                while (!res.isEmpty()&&isDigit(res.peek())){
                    temp.push(res.pop());
                }
                int n=0;
                StringBuilder sb=new StringBuilder();
                while (!temp.isEmpty()){
                    if(isDigit(temp.peek())){
                        n=10*n+temp.pop()-'0';
                    }else{
                        sb.append(temp.pop());
                    }
                }
                String s1 = sb.toString();
                for(int k=0;k<n;k++){
                    for(int j=0;j<s1.length();j++){
                        res.push(s1.charAt(j));
                    }
                }
                continue;
            }
            //将除了]以外的所有字符压入栈中
            res.push(charArray[i]);
        }
        StringBuilder stringBuilder=new StringBuilder();
        while (!res.isEmpty()){
            stringBuilder.append(res.pollLast());
        }
        return stringBuilder.toString();
    }
    private boolean isDigit(char c){
        return c>='0'&&c<='9';
    }
}
