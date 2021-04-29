package SlidingWindow;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 找到字符串中所有字母异位词，异位词指字母相同，排列不同的字符串
 */
public class FindAllAnagramsinaString_438 {
    public List<Integer> findAnagrams(String s, String p) {
        int left=0,right=0;
        int sLen=s.length();
        int pLen=p.length();
        if(sLen<pLen) return new LinkedList<>();
        Map<Character,Integer> freq=new HashMap<>();
        for(char c:p.toCharArray()){
            freq.put(c,freq.getOrDefault(c,0)+1);
        }
        List<Integer> res=new LinkedList<>();
        Map<Character,Integer> tmp=new HashMap<>();
        char[] str = s.toCharArray();
        //使用它来衡量字符串之间的包含关系
        int distance=0;
        //滑动窗口
        while (right<sLen){
            //[left,right)
            char c=str[right];
            if(!freq.containsKey(c)){
                right++;
                continue;
            }
            //如果没有或者频数小于目标值，dis++
            if(!tmp.containsKey(c)||tmp.get(c)<freq.get(c)){
                distance++;
            }
            tmp.put(c,tmp.getOrDefault(c,0)+1);
            right++;

            //开始缩小窗口
            while (distance==pLen){
                if(right-left==pLen){
                    res.add(left);
                }
                char ch=str[left];
                if(!freq.containsKey(ch)){
                    left++;
                    continue;
                }
                if(freq.get(ch).equals(tmp.get(ch))){
                    distance--;
                }
                tmp.put(ch,tmp.get(ch)-1);
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        FindAllAnagramsinaString_438 test = new FindAllAnagramsinaString_438();
        List<Integer> anagrams = test.findAnagrams("abab", "ab");
        anagrams.forEach(System.out::println);
    }
}
