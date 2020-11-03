package Dynamic;

import java.util.Arrays;

/**
 * 给定一根长度为n英寸的棍子和一个价格数组，
 * 其中包含了所有尺寸小于n的碎片的价格，
 * 请确定通过切割棍子并出售碎片可以获得的最大价值。
 * 示例
 * length   | 1   2   3   4   5   6   7   8
 * --------------------------------------------
 * price    | 3   5   8   9  10  17  17  20
 * 最大价值24
 */
public class CuttingARod {
    /**
     * 递归  cutRod(n) = max(price[i] + cutRod(n-i-1))  i属于 {0, 1 .. n-1}
     */
    public static int cutRod(int[] price,int n){
        if(n<=0){
            return 0;
        }
        int maxValue=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            maxValue=Math.max(maxValue,price[i]+cutRod(price,n-i-1));
        }
        return maxValue;
    }

    public static int cutRodDP(int[] price,int n){
        int[] value=new int[n+1];
        Arrays.fill(value,0);

        for(int i=1;i<=n;i++){
            int maxValue=Integer.MIN_VALUE;
            //在区间里找到最大价值
            for(int j=0;j<i;j++){
                maxValue=Math.max(maxValue,price[j]+value[i-j-1]);
            }
            value[i]=maxValue;
        }
        return value[n];
    }
    public static void main(String[] args)
    {
        int[] arr = new int[] {1, 5, 8, 9, 10, 17, 17, 20};
        int size = arr.length;
        System.out.println("Maximum Obtainable Value is "+
                cutRodDP(arr, size));

    }
}
