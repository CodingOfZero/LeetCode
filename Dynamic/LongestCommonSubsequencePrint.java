package Dynamic;

import java.util.Arrays;

/**
 * 最长公共子序列LCS
 * LCS问题陈述： 给定两个序列，找出两个序列中存在的最长子序列的长度。
 * 子序列是以相同的相对顺序出现，但不一定是连续的序列。
 * 例如，“ abc”，“ abg”，“ bdf”，“ aeg”，“ acefg”等是“ abcdefg”的子序列。
 *
 * 分析如下：由于具有重叠子问题特点，且满足最优子结构，所以可以用动态规划
 * 暴力匹配：
 *      长度为n的字符串的可能不同子序列的数量等于c(n,0)+c(n,1)+c(n,2)+……+c(n,n)=2的n次方
 *      并且判断子序列是否是两个序列共同的子序列需要O(n) 所以时间复杂度为O(n*2^n)
 * 递推公式如下：假设输入序列分别为长度为m和n的X [0..m-1]和Y [0..n-1]。
 *              并令L（X [0..m-1]，Y [0..n-1]）为两个序列X和Y的LCS的长度。以下是L（X [0 ... m-1]，Y [0..n-1]）。
 *
 * 1）如果两个序列的最后一个字符都匹配（或X [m-1] == Y [n-1]），则
 *      L（X [0..m-1]，Y [0..n-1]）= 1 + L（X [0..m-2]，Y [0..n-2]）
 *
 * 2）如果两个序列的最后一个字符都不匹配（或X [m-1]！= Y [n-1]），则
 *      L（X [0..m-1]，Y [0..n-1]）= MAX（L（X [0..m-2]，Y [0..n-1]），L（X [0..m-1]，Y [0..n-2]））
 *
 *  如何打印？
 *  首先根据上述方法求出L[m][n] 里面存放最长公共子序列的长度
 *  从L[m-1][n-1]出发，如果X[m-1]==Y[n-1] 则将该字符加入到字符串中，同时m,n减一
 *  如果不等，比较L[i-1][j]和L[i][j-1]的值，并向较大的方向继续进行。
 *
 */
public class LongestCommonSubsequencePrint {
    public static String longestCommonSubsequence(String text1, String text2) {
        int m=text1.length();
        int n=text2.length();
        if(m==0||n==0) {
            return "";
        }
        char[] seq1 = text1.toCharArray();
        char[] seq2 = text2.toCharArray();
        int[][] L=new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0||j==0){
                    L[i][j]=0;
                }else if(seq1[i-1]==seq2[j-1]){
                    L[i][j]=1+L[i-1][j-1];
                }else{
                    L[i][j]=Math.max(L[i][j-1],L[i-1][j]);
                }
            }
        }
        int len= L[m][n];
        //result
        char[] lcs=new char[len];

        int index1 = m;
        int index2 = n;
        while (index1 > 0 && index2 > 0){
            if(seq1[index1-1]==seq2[index2-1]){
                lcs[--len]=seq1[index1-1];
                index1--;
                index2--;
            }else if(L[index1-1][index2] > L[index1][index2-1]){
                index1--;
            }else{
                index2--;
            }
        }
        return Arrays.toString(lcs);
    }
    public static void main (String[] args)
    {
        String X = "AGGTAB";
        String Y = "GXTXAYB";
        int m = X.length();
        int n = Y.length();
        System.out.println(longestCommonSubsequence(X, Y));
    }
}
