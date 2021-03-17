package Dynamic;

import java.util.HashMap;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。
 *
 *
 */
public class LongestPalindromicSubstring_5 {
    /**
     * 动态规划
     * 在头尾字符相等的情况下，里面子串的回文性质决定了整个子串的回文性质
     * 1.定义状态
     *      dp[i][j]表示子串s[i..j]是否为回文
     * 2.确定状态转移方程
     *      dp[i][j]=(s[i]==s[j]) and dp[i+1][j-1]
     *     「动态规划」事实上是在填一张二维表格，由于构成子串，因此 i 和 j 的关系是 i <= j ，
     *     因此，只需要填这张表格对角线以上的部分在 s[i] == s[j] 成立和 j - i < 3 的前提下，直接可以下结论，
     *     dp[i][j] = true，否则才执行状态转移。
     *      看到 dp[i + 1][j - 1] 就得考虑边界情况
     * 3.初始化
     * 4.输出
     *      只要一得到 dp[i][j] = true，就记录子串的长度和起始位置，
     *      没有必要截取，这是因为截取字符串也要消耗性能，
     *      记录此时的回文子串的「起始位置」和「回文长度」即可。
     * 5.空间优化
     * 要注意填表顺序，要无后效性
     */
    public String longestPalindromeDP(String s){
        int len=s.length();
        if(len<2){
            return s;
        }
        boolean[][] dp=new boolean[len][len];

        int maxLen=1;
        int begin=0;
        char[] charArray = s.toCharArray();
        for(int j=1;j<len;j++){
            for(int i=0;i<j;i++){
                if(charArray[i]!=charArray[j]){
                    dp[i][j]=false;
                }else{
                    //表达式 [i + 1, j - 1] 不构成区间，即长度严格小于 2，
                    // 即 j - 1 - (i + 1) + 1 < 2
                    //整理得j-i<3
                    if(j-i<3){
                        dp[i][j]=true;
                    }else{
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }

            }
        }
        return s.substring(begin,begin+maxLen);
    }



    /**
     * 暴力
     */
    public String longestPalindrome(String s) {
        int len=s.length();
        if(len<2){
            return s;
        }
        int maxLen=1;
        int begin=0;
        char[] charArray = s.toCharArray();
        //枚举所有长度大于1的子串
        for(int i=0;i<len-1;i++){
            for(int j=i+1;j<len;j++){
                if(j-i+1>maxLen&&valid(charArray,i,j)){
                    maxLen=j-i+1;
                    begin=i;
                }
            }
        }
        return s.substring(begin,begin+maxLen);
    }
    public boolean valid(char[] charArray,int left,int right){
        while (left<right){
            if(charArray[left]!=charArray[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String babad = new LongestPalindromicSubstring_5().longestPalindrome("b");
        System.out.println(babad);
    }
}
