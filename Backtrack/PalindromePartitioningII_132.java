package Backtrack;

import java.util.Arrays;

/**
 * 分割回文串，求最小切割次数
 * 判断字符串在某个区间内是否为回文串，状态转移方程为
 *
 * g(i,j)=
 *        1.True    while i>=j;
 *        2.g(i+1,j-1)&&(s[i]==s[j]) i<j;
 * 设 f[i] 表示字符串的前缀 s[0..i]的最少分割次数
 *        f[i]=min{f[j]}+1
 *  我们枚举最后一个回文串的起始位置 j+1，保证 s[j+1..i]是一个回文串，
 *  那么 f[i]就可以从 f[j]转移而来，附加1次额外的分割次数。此外当s[0,i]本身是一个回文串时
 *  不需要进行分割，次数为0
 */
public class PalindromePartitioningII_132 {
    public static int minCut(String s) {
        if(s==null||s.length()<2) return 0;
        int len=s.length();
        boolean[][] check=new boolean[len][len];
        for(boolean[] item:check){
            Arrays.fill(item,true);
        }
//        for(int i=0;i<len;i++){
//            for(int j=0;j<=i;j++){
//                check[i][j]=true;
//            }
//        }
        char[] str = s.toCharArray();
        //预处理后就可O(1)时间得到某个区间内是否为回文串
        for(int j=1;j<len;j++){
            for(int i=0;i<j;i++){
                check[i][j]=check[i+1][j-1]&&(str[i]==str[j]);
            }
        }
        int[] f=new int[len];
        Arrays.fill(f,Integer.MAX_VALUE);
        for(int i=0;i<len;i++){
            if(check[0][i]){
                //本身是回文串
                f[i]=0;
            }else{
                for(int j=0;j<i;j++){
                    if(check[j+1][i]){
                        f[i]=Math.min(f[i],f[j]+1);
                    }
                }
            }
        }
        return f[len-1];
    }

    public static void main(String[] args) {
        minCut("aab");
    }
}
