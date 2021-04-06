package DoublePointer;

/**
 * 单词距离
 *
 */
public class WordDistance_11 {
    public int findClosest(String[] words, String word1, String word2) {
        if(words==null||words.length==0||word1==null||word2==null) return 0;
        int p1=-1,p2=-1;
        int res=Integer.MAX_VALUE;
        for(int i=0;i<words.length;i++){
            if(words[i].equals(word1)){
                p1=i;
            }else if(words[i].equals(word2)){
                p2=i;
            }
            if(p1!=-1&&p2!=-1){
                res=Math.min(res,Math.abs(p2-p1));
            }
        }
        return res;
    }
}
