package SlidingWindow;

/**
 * 替换后最长重复字符
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换k次。
 * 在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意：字符串长度 和 k 不会超过10^4。
 *
 *
 */
public class LongestRepeatingCharacterReplacement_424 {

    public static int characterReplacement(String s, int k) {
        if(s==null||s.length()==0) {
            return 0;
        }
        if(k>=s.length()) {
            return k;
        }
        char[] array = s.toCharArray();
        int right=0,left=0;
        int[] num=new int[26];
        //记录在区间中出现最多的次数
        int maxTime=0;
        for(;right<array.length;right++){
            num[array[right]-'A']++;
            maxTime=Math.max(maxTime,num[array[right]-'A']);

            if(right-left+1-maxTime>k){
                //区间内非最长重复字符超过了k个
                num[array[left]-'A']--;
                left++;
            }
        }
        return right-left;
    }

    public static void main(String[] args) {
        int abab = characterReplacement("ABBB", 2);
        System.out.println(abab);
    }
}
