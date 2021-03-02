package Dynamic;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 硬币兑换问题  for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}
 * N为要兑换的钱，S为拥有的硬币种类数组
 * 首先要想好它的递推公式
 *  为了统计所有的解决方案，我们将它分为两部分
 *      1）不包含第m个硬币的兑换方案
 *      2）至少包含一个m硬币的兑换方案
 *  给定一个N，假设S={1，2，3}，则总的兑换方案等于S={1,2}的兑换方案加上至少含有一个3的方案，两部分之和
 *      f(S,m,n)=f(S,m-1,n)+f(S,m,n-Sm) 这里Sm在数组里就是S[m-1]
 */
public class CoinChange {
    //递归版
    public static int numberChange(int[] S,int m,int n){
        //钱为0时，方案有一种，不包含任何硬币
        if(n==0) {
            return 1;
        }
        //当钱小于0或者硬币种类小于等于0时，方案为0
        if(n<0||m<=0) {
            return 0;
        }
        return numberChange(S,m-1,n)+numberChange(S,m,n-S[m-1]);
    }
    /**
     * 动态规划  时间O(mn) 空间O(n)
     */
    public static long numberChange1(int[] S, int m, int n){
        //n兑换硬币的方案
        long[] table=new long[n+1];
        Arrays.fill(table,0);
        //Base case
        table[0]=1;
        //bottom up
        //从只有一枚硬币开始，每个兑换方案
        for (int i=0; i<m; i++) {
            for (int j=S[i]; j<=n; j++) {
                table[j] += table[j-S[i]];
            }
        }
        return table[n];
    }
    public static void main(String[] args) {
        int arr[] = {1, 2, 3};
        int m = arr.length;
        System.out.println( numberChange1(arr, m, 4));
    }
}
