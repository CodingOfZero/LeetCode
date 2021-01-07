package SlidingWindow;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 注意是子串而非子序列，子串必须是紧挨着的，比如abcd  其中abc是子串，abd是子序列
 */
public class LongestSubstringWithoutRepeatingCharacters_3 {
    //滑动窗口
    public static int lengthOfLongestSubstringFaster(String s){
        Set<Character> hashSet=new HashSet<>();
        int right=-1,left=0;
        int res=0;
        char[] c = s.toCharArray();
        for(;left<c.length;left++){
            if(left!=0){
                // 左指针向右移动一格，移除一个字符
                hashSet.remove(c[left-1]);
            }
            while (right+1<c.length&&!hashSet.contains(c[right+1])){
                hashSet.add(c[right+1]);
                right++;
            }
            // 第 left到 right个字符是一个极长的无重复字符子串
            res=Math.max(res,right-left+1);
        }
        return res;
    }

    public static int lengthOfLongestSubstring(String s) {
        //Set存值，Queue顺序存
        Set<Character> hashSet=new HashSet<>();
        Queue<Character> queue=new LinkedList<>();
        char[] str = s.toCharArray();
        int length=0;
        for(char c:str){
            //当遇到set里面存在的数时，记录此时set大小，移除该数前面入队的数以及set里面的数
            if(hashSet.contains(c)){
                length=Math.max(hashSet.size(),length);
                while (queue.peek()!=c){
                    hashSet.remove(queue.poll());
                }
                hashSet.remove(queue.poll());
            }
            hashSet.add(c);
            queue.offer(c);
        }
        //处理全是唯一的情况
        length=Math.max(length,queue.size());
        return length;

  }

    public static void main(String[] args) {
        int len1 = lengthOfLongestSubstring("abcdefg");
        System.out.println(len1);
    }

}
