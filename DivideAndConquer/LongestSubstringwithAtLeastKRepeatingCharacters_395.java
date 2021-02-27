package DivideAndConquer;

/**
 * 求重复K次最长子串
 * 当字符出现的次数小于K时，该字符肯定不会包含在结果子串内，故可以将这些字符作为分割点，进行分治
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters_395 {
    public int longestSubstring(String s, int k) {
        char[] array = s.toCharArray();
        return longestSubstringHelper(array,0,array.length-1,k);
    }

    private int longestSubstringHelper(char[] array, int left, int right, int k) {
        int[] count=new int[26];
        for(int i=left;i<=right;i++){
            count[array[i]-'a']++;
        }
        char split=0;
        for(int i=0;i<count.length;i++){
            if(count[i]>0&&count[i]<k){
                split=(char)(i+'a');
                break;
            }
        }
        if(split==0){
            return right-left+1;
        }
        int i=left;
        int ret=0;
        while(i<=right){
            while(i<=right && array[i]==split) i++;
            if(i>right) break;
            int start=i;
            while (i<=right&&array[i]!=split) i++;
            int length=longestSubstringHelper(array, start, i-1, k);
            ret=Math.max(ret,length);
        }
        return ret;
    }
}
