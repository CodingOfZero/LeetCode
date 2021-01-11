package HashTable;



/**
 * 给一个字符串，找到它第一个不重复的字符返回它的索引
 */
public class FirstUniqueCharacterinaString_387 {
    public int firstUniqChar(String s) {
        char[] charArray = s.toCharArray();
        int[] freq=new int[26];
        for(char c:charArray){
            freq[c-'a']++;
        }
        for(int i=0;i<charArray.length;i++){
            if(freq[charArray[i]-'a']==1){
                return i;
            }
        }
        return -1;
    }
}
