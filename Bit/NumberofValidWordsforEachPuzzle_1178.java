package Bit;

import java.util.*;

/**
 * 猜字谜
 * 步骤：
 *      1.求出words数组每个元素对应的集合bw(b表示二进制)，统计每种集合出现的次数，放入HashMap中，方便存取
 *      2.求出puzzles数组每个元素对应的集合bp
 *      3.对于每个bp，枚举一个二进制数的子集
 *      4.将bp对应的所有子集的频率进行累加，得到结果就是作为谜面的谜底数量
 */
public class NumberofValidWordsforEachPuzzle_1178 {
    /**
     * 时间复杂度分为三部分，计算所有word对应的二进制数，计算所有puzzle对应的二进制数，计算子集
     * 空间复杂度O(m)
     * @param words
     * @param puzzles
     * @return
     */
    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        //1.
        Map<Integer,Integer> freq=new HashMap<>();
        for(String word:words){
            int mask=0;
            for(int i=0;i<word.length();i++){
                char c=word.charAt(i);
                mask|=(1<<(c-'a'));
            }
            //由于puzzles中的每个元素长度都为7，word对应二进制数1的个数必然小于或等于7
            //因此可将大于7的那部分不加入哈希表中
            if(Integer.bitCount(mask)<=7){
                freq.put(mask,freq.getOrDefault(mask,0)+1);
            }
        }

        List<Integer> ans=new LinkedList<>();
        for(String puzzle:puzzles){
            int total=0;
            //2.求对应的二进制数bp
            int mask=0;
            //从1开始，除去第一位
            for(int i=1;i<puzzle.length();i++){
                char c=puzzle.charAt(i);
                mask|=1<<(c-'a');
            }
            //3.枚举二进制数的子集
            int subset=mask;
            do{
                //必须包含第一个字母，subset为bp的子集
                int s=subset |(1<<(puzzle.charAt(0)-'a'));
                if(freq.containsKey(s)){
                    //4.累加
                    total+=freq.get(s);
                }
                //下一个子集
                //当subset为0时，减1变为-1，与mask相与后的结果相同，跳出循环
                subset=(subset-1)&mask;
            }while (subset!=mask);
            ans.add(total);
        }
        return ans;
    }
    public static List<Integer> findNumOfValidWordsBF(String[] words, String[] puzzles) {
        List<Integer> ans=new LinkedList<>();
        int puzzleLen= puzzles.length;
        for(int i=0;i<puzzleLen;i++){
            HashSet<Character> puzzleStore=new HashSet<>();
            char[] array = puzzles[i].toCharArray();
            char headChar=array[0];
            for(char c:array){
                puzzleStore.add(c);
            }
            int count=0;
            for(String word:words){
                char[] wordArray = word.toCharArray();
                boolean haveHead=false;
                boolean isExist=true;
                for(char c:wordArray){
                    if(c==headChar) haveHead=true;
                    if(!puzzleStore.contains(c)){
                        isExist=false;
                        break;
                    }
                }
                if(haveHead&&isExist) count++;
            }
            ans.add(count);
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words={"aaaa","asas","able","ability","actt","actor","access"};
        String[] puzzles={"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};
        List<Integer> numOfValidWords = findNumOfValidWords(words, puzzles);
        numOfValidWords.forEach(System.out::println);
    }
}
