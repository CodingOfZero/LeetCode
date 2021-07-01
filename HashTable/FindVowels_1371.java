package HashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * 如果子串中包含aeiou中的某个，则它的个数必须是偶数，求符合条件的最长子串
 * 涉及到知识点
 * 异或运算
 * 状态压缩
 * 前缀和以及哈希表
 */
public class FindVowels_1371 {
    private static final String VOWELS="aeiou";

    public int findTheLongestSubstring(String s) {
        //记录状态以及第一次出现的位置
        Map<Integer,Integer> map=new HashMap<>();
        int n=s.length();
        int state=0;//00000
        int maxSize=0;
        map.putIfAbsent(0,-1);
        for(int i=0;i<n;i++){
            for(int k=0;k<VOWELS.length();k++){
                if(s.charAt(i)==VOWELS.charAt(k)){
                    state^=(1<<(VOWELS.length()-k-1));
                    break;
                }
            }
            if(map.containsKey(state)){
                maxSize=Math.max(maxSize,i-map.get(state));
            }
            map.putIfAbsent(state,i);
        }
        return maxSize;
    }
}
