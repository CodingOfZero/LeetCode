package SlidingWindow;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 */
public class MinimumWindowSubstring_76 {
    /**
     * 可行解---->局部最优解
     * 首先先向右扩展使得目前窗口内包含t的所有字符，然后固定右边，从左边进行收缩，寻找最小覆盖字串
     * 使用distance来衡量窗口与t的包含关系，当窗口内的字符以及频数与字符串t内的字符与频数相等时，distance不变
     * 通过distance的变化可以判断当前的窗口包含的字符串是否是一个可行解
     */
    public static String minWindow(String s, String t) {
//        if(s==null||t==null||s.length()==0||t.length()==0) {
//            return "";
//        }
//        Map<Character,Integer> hashMap=new HashMap<>();
//        for(int i=0;i<t.length();i++){
//            hashMap.merge(t.charAt(i),1,Integer::sum);
//        }
//        char[] str = s.toCharArray();
//        int left=0,right=-1,count=t.length();
//        //记录位置
//        int[] position=new int[s.length()];
//        int len=0;
//        for(int i=0;i<str.length;i++){
//            if(hashMap.containsKey(str[i])){
//                position[len++]=i;
//            }
//        }
//        int end=-1,minDist=Integer.MAX_VALUE;
//        //对位置数组进行滑动
//        for(left=0;left<len;left++){
//            while (right+1<len){
//                if(hashMap.get(str[position[right+1]])!=0){
//                    hashMap.replace(str[position[right+1]],hashMap.get(str[position[right+1]])-1);
//                    count--;
//                }
//                right++;
//            }
//            if(count==0){
//                if(position[right]-position[left]<minDist){
//                    end=right;
//                    minDist=position[right]-position[left];
//                }
//            }
//            hashMap.replace(str[position[left]],hashMap.get(str[position[left]])+1);
//            count++;
//        }
//        if(end!=-1){
//            return s.substring(position[end-t.length()+1],position[end]+1);
//        }else{
//            return "";
//        }
        int sLen=s.length();
        int tLen=t.length();
        if(sLen==0||tLen==0||sLen<tLen){
            return "";
        }
        //由于charAt()每次都会检查范围，一般会先将转为字符数组
        char[] charArrayS = s.toCharArray();
        char[] charArrayT = t.toCharArray();
        //窗口代表的字符串频数数组  ascii(z)=123，不过一般使用2的整数次
        int[] winFreq=new int[128];
        //t对应的频数数组
        int[] tFreq=new int[128];
        for(char c:charArrayT){
            tFreq[c]++;
        }
        //滑动窗口内部包含多少T中的字符，如果超过不重复计算，，最小覆盖子串，表示只要包含所有字符即可，多了不管
        int distance=0;
        int begin=0;
        int minLen=sLen+1;

        //窗口指针
        int left=0,right=0;
        //[left,right)
        while (right<sLen){
            if(tFreq[charArrayS[right]]==0){
                right++;
                continue;
            }
            if(winFreq[charArrayS[right]] < tFreq[charArrayS[right]]){
                distance++;
            }
            //字符对应频数加1，向右移动
            winFreq[charArrayS[right]]++;
            right++;
            //当以及包含字符串t中的所有字符时，开始收缩，寻找局部最优解
            while (distance==tLen){
                if(right-left<minLen){
                    begin=left;
                    minLen=right-left;
                }
                if(tFreq[charArrayS[left]]==0){
                    left++;
                    continue;
                }
                if(winFreq[charArrayS[left]] == tFreq[charArrayS[left]]){
                    distance--;
                }
                winFreq[charArrayS[left]]--;
                left++;
            }
        }
        if(minLen==sLen+1){
            return "";
        }else{
            return s.substring(begin,begin+minLen);
        }
    }

    public static void main(String[] args) {
        String s = minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd");
        System.out.println(s);

    }
}
