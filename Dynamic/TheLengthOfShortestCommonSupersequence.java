package Dynamic;

/**
 * 找到一个将str1和str2作为子串的最短字符串
 * 与最长公共子串问题相关
 * 示例：
 * Input:   str1 = "geek",  str2 = "eke"    LCS是ek
 * Output: "geeke"
 *
 * Input:   str1 = "AGGTAB",  str2 = "GXTXAYB"   LCS是GTAB
 * Output:  "AGXGTXAYB"
 * 思路一：最短长度=两个字符串长度之和-最长公共子串的长度
 * 思路二：  当最后一个元素相等时，递归比较下一个，并将长度加1，
 *          当不等时，找到min( SCS(X, Y, m - 1, n), SCS(X, Y, m, n - 1) )，然后加1
 *
 */
public class TheLengthOfShortestCommonSupersequence {
    public static int superSeq(String X, String Y,
                        int m, int n)
    {
        int[][] dp = new int[m + 1][n + 1];

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
                else if (X.charAt(i - 1) == Y.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],
                            dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }
}
