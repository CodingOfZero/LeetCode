package Dynamic;

import java.util.*;

/**
 * 单词拆分
 */
public class WordBreak_139 {

    public boolean wordBreakBottomUP(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 自顶向下
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> hashSet = new HashSet<>(wordDict);
        int[] state=new int[s.length()];
        Arrays.fill(state,-1);
        return wordBreakHelper(s,hashSet,0,state);
    }

    private boolean wordBreakHelper(String s, Set<String> hashSet, int start,int[] state) {
        if(start==s.length()){
            return true;
        }
        if(state[start]!=-1){
            return state[start] == 1;
        }
        for(int i=start;i<s.length();i++){
            String str=s.substring(start,i+1);
            if(hashSet.contains(str)&&wordBreakHelper(s,hashSet,i+1,state)){
                state[i]=1;
                return true;
            }
        }
        state[start]=0;
        return false;
    }

    public static void main(String[] args) {
        WordBreak_139 wordBreak = new WordBreak_139();
        List<String> wordDict= Arrays.asList("cats", "dog", "sand", "and", "cat");

        boolean applepenapple = wordBreak.wordBreak("catsandog", wordDict);
        System.out.println(applepenapple);
    }
}
