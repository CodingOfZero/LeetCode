package Dynamic;

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
 */
public class LongestCommonSubsequence_1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m=text1.length();
        int n=text2.length();
        if(m==0||n==0) {
            return 0;
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
        return L[m][n];
//先转字符数组，然后判断，测试比直接使用text1.charAt(i-1)要快

//        int m=text1.length();
//        int n=text2.length();
//        if(m==0||n==0) {
//            return 0;
//        }
//        int[][] L=new int[m+1][n+1];
//        for(int i=0;i<=m;i++){
//            for(int j=0;j<=n;j++){
//                if(i==0||j==0){
//                    L[i][j]=0;
//                }else if(text1.charAt(i-1)==text2.charAt(j-1)){
//                    L[i][j]=1+L[i-1][j-1];
//                }else{
//                    L[i][j]=Math.max(L[i][j-1],L[i-1][j]);
//                }
//            }
//        }
//        return L[m][n];
    }
}
