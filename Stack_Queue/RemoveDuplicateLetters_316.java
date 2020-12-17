package Stack_Queue;

import java.util.*;

/**
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 在保证其他字符至少出现一次的情况下，使第一个字符最小
 * 方式一：贪心
 * 方法二：使用贪心+栈+Set+Map
 */
public class RemoveDuplicateLetters_316 {
    /**
     * 用数组实现栈
     */
    public String removeDuplicateLettersFaster(String s){
        int[] lastAppear=new int[26];
        for(int i=0;i<s.length();i++){
            lastAppear[s.charAt(i)-'a']=i;
        }

        boolean[] exist=new boolean[26];
        Arrays.fill(exist,false);
        //栈
        char[] stack=new char[26];
        int sp=-1;

        for(int i=0;i<s.length();i++){
            //如果存在
            int c=s.charAt(i)-'a';
            if(exist[c]){
                continue;
            }
            while (sp>=0&&s.charAt(i)<stack[sp]&&lastAppear[stack[sp]-'a']>i){
                exist[stack[sp]-'a']=false;
                sp--;
            }
            exist[c]=true;
            stack[++sp]=s.charAt(i);
        }
        StringBuilder sb=new StringBuilder(sp+1);
        for(int i=0;i<=sp;i++){
            sb.append(stack[i]);
        }
        return sb.toString();
    }


    /**
     * 使用贪心+栈+Set+Map
     * 时间复杂度为O(n)  空间O(1)
     * @param s
     * @return
     */
    public String removeDuplicateLettersFast(String s) {
        if(s.length()==0) {
            return "";
        }
        char[] chars = s.toCharArray();
        //字符最后出现的位置
        HashMap<Character,Integer> lastIndex=new HashMap<>();
        for(int i=0;i<s.length();i++){
            lastIndex.put(chars[i],i);
        }
        //在O(1)时间内判断stack中是否已经存在
        HashSet<Character> hashSet=new HashSet<>();
        LinkedList<Character> stack=new LinkedList<>();
        for(int i=0;i<s.length();i++){
            char c=chars[i];
            //如果栈中没有该字符
            if(!hashSet.contains(c)){
                //栈非空，且小于栈顶元素，并且栈顶元素最后出现的位置比i大，表明后面还有
                //则出栈
                while (!stack.isEmpty()&&c<stack.getLast()&&lastIndex.get(stack.getLast())>i){
                    //将该字符从栈中移除，并且从set中移除
                    hashSet.remove(stack.removeLast());
                }
                hashSet.add(c);
                stack.addLast(c);
            }

        }
        //遍历用列表模拟的栈
        StringBuilder sb=new StringBuilder(stack.size());
        for(Character c:stack){
            sb.append(c);
        }
        return sb.toString();
    }






    /**
     * 方法一：每次递归中，在保证其他字符至少出现一次的情况下，确定最小左侧字符。
     * 之后再将未处理的后缀字符串继续递归。
     */
    public String removeDuplicateLetters(String s) {
        if(s.length()==0) {
            return "";
        }
        int[] count=new int[26];
        //统计每个字符出现的次数
        for(int i=0;i<s.length();i++){
            count[s.charAt(i)-'a']++;
        }
        int pos=0;
        for(int i=0;i<s.length();i++){
            //如果pos位置上的字符大于当前字符，则更新最小字符的位置
            if(s.charAt(i)<s.charAt(pos)){
                pos=i;
            }
            //当字符个数为0时，退出
            if(--count[s.charAt(i)-'a']==0){
                break;
            }
        }
        //最后的结果就等于当前最小字符+递归余下字符串的结果
        //在子字符串中要把当前最小字符替换为空
        return s.charAt(pos)+removeDuplicateLetters(s.substring(pos+1).replaceAll(""+s.charAt(pos),""));
    }

    public static void main(String[] args) {
        new RemoveDuplicateLetters_316().removeDuplicateLettersFaster("bcabc");

    }
}
