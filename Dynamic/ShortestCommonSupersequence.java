package Dynamic;

import java.util.Arrays;

/**
 * 找到一个将str1和str2作为子串的最短字符串,并打印
 * 与最长公共子串问题相关
 * 示例：
 * Input:   str1 = "geek",  str2 = "eke"    LCS是ek
 * Output: "geeke"
 *
 * Input:   str1 = "AGGTAB",  str2 = "GXTXAYB"   LCS是GTAB
 * Output:  "AGXGTXAYB"
 * 思路一：最短长度=两个字符串长度之和-最长公共子串的长度
 *
 */
public class ShortestCommonSupersequence {
    public static String superSeq(String X, String Y)
    {
        int m = X.length();
        int n = Y.length();
        if(m==0||n==0){
            return "";
        }
        int[][] dp = new int[m + 1][n + 1];
        char[] seq1 = X.toCharArray();
        char[] seq2 = Y.toCharArray();
        // Fill table in bottom up manner
        for (int i = 0; i <= m; i++)
        {
            for (int j = 0; j <= n; j++)
            {
                // Below steps follow above recurrence
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (seq1[i - 1] == seq2[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],
                            dp[i][j - 1]);
            }
        }
        int len=dp[m][n];
        //存放结果
        char[] scs=new char[len];
        //从L[m][n]开始
        int i=m,j=n;
        while (i>0&&j>0){
            //如果相等，说明是LCS中的一部分，将它加入到结果数组中去
            if(seq1[i-1]==seq2[j-1]){
                scs[--len]=seq1[i-1];
                i--;
                j--;
                //向较大值方向继续进行
            }else if(dp[i-1][j] < dp[i][j-1]){
                //将较小值对应的字符加入到结果数组当中
                scs[--len]=seq1[i-1];
                i--;
            }else{
                scs[--len]=seq2[j-1];
                j--;
            }
        }
        //复制字符串X剩下的字符
        while (i>0) {
            scs[--len]=seq1[i-1];
            i--;
        }
        //复制字符串Y剩下的字符
        while (j>0){
            scs[--len]=seq2[j-1];
            j--;
        }
        return Arrays.toString(scs);
    }
    public static void main(String args[])
    {
        String a = "algorithm";
        String b = "rhythm";
        System.out.println(superSeq(a, b));

    }
}
