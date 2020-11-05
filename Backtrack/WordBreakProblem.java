package Backtrack;

import java.util.*;

/**
 *
 * 给定一个输入字符串和一个单词字典，输出所有可分割的结果
 * 示例：
 * { i, like, sam, sung, samsung, mobile, ice,
 *   cream, icecream, man, go, mango}
 * 输入字符串 ilike  返回i like
 */
public class WordBreakProblem {
    private static Set<String> dictionary=new HashSet<>();
    private static List<String> results;
    public static void wordBreak(String word){
        results=new LinkedList<>();
        String result="";
        int len=word.length();
        wordBreakHelper(word,len,result);
    }
    //result存放前缀
    private static void wordBreakHelper(String word,int len, String result) {
        for(int i=1;i<=len;i++){
            //前缀
            String prefix=word.substring(0,i);
            if(dictionary.contains(prefix)){
                if(i==len){
                    result+=prefix;
                    results.add(result);
                }
                //寻找后面
                wordBreakHelper(word.substring(i,len), len-i,result+prefix+" ");
            }
        }
    }

    public static void main(String[] args) {
        String[] tempDictionary = {"mobile","samsung","sam","sung",
                "man","mango", "icecream","and",
                "go","i","love","ice","cream"};

        // loop to add all strings in dictionary set
        dictionary.addAll(Arrays.asList(tempDictionary));
        wordBreak("iloveicecreamandmango");
        results.forEach(System.out::println);
    }
}
