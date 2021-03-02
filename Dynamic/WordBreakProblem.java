package Dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个输入字符串和一个单词字典，判断输入字符串是否能够被分割成字典里的单词
 * 示例：
 * { i, like, sam, sung, samsung, mobile, ice,
 *   cream, icecream, man, go, mango}
 * 输入字符串 ilike  返回true 它可以被分割为i like
 */
public class WordBreakProblem {
    private static Set<String> dictionary=new HashSet<>();

    /**
     * recursive implementation
     * @param word
     * @return
     */
    public static boolean wordBreak(String word){
        //base case
        int size=word.length();
        if(size==0){
            return true;
        }
        //注意这里i是小于等于size
        for(int i=1;i<=size;i++){
            if(dictionary.contains(word.substring(0,i))&&wordBreak(word.substring(i,size))){
                return true;
            }
        }
        return false;
    }

    /**
     * 动态规划，除了维护dp表之外，还维护所有匹配的索引，然后检查从那些索引到当前索引的子字符串
     * @param word
     * @return
     */
    public static boolean wordBreakDP(String word){
        int size=word.length();
        if(size==0){
            return true;
        }
        boolean[] dp=new boolean[size];
        //存放匹配的索引
        ArrayList<Integer> index = new ArrayList<>();
        index.add(-1);

        for(int i=0;i<size;i++){
            //index列表大小
            int indexSize=index.size();
            //遍历
            for(int j=indexSize-1;j>=0;j--){
                String comStr=word.substring(index.get(j)+1,i+1);
                if(dictionary.contains(comStr)){
                    dp[i]=true;
                    index.add(i);
                }
            }
        }
        return dp[size-1];
    }


    public static void main(String[] args) {
        String[] tempDictionary = {"mobile","samsung","sam","sung",
                "man","mango","icecream","and",
                "go","i","like","ice","cream"};

        // loop to add all strings in dictionary set
        dictionary.addAll(Arrays.asList(tempDictionary));

        // sample input cases
        System.out.println(wordBreakDP("ilikesamsung"));
        System.out.println(wordBreakDP("iiiiiiii"));
        System.out.println(wordBreakDP(""));
        System.out.println(wordBreakDP("ilikelikeimangoiii"));
        System.out.println(wordBreakDP("samsungandmango"));
        System.out.println(wordBreakDP("samsungandmangok"));
//        System.out.println("hello".substring(0,0));
    }
}
