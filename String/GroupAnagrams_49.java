package String;


import java.util.*;

/**
 * 字母异或：指的是字母相同，排列不同的字符串
 */
public class GroupAnagrams_49 {
    /**
     * 对每个字符串进行排序，字母异或的字符串，排序后肯定相同，
     * 使用hashmap保存，key为排序后生成的字符串，value为原始字符串
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> hashmap=new HashMap<>();
        for(String str:strs){
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            hashmap.computeIfAbsent(new String(temp),key->new ArrayList<>()).add(str);
        }
        return new ArrayList<List<String>>(hashmap.values());
    }

}
