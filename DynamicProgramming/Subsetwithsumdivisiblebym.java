package Dynamic;

import java.util.Arrays;

/**
 * 判断是否存在一个子集，它的和可以被给定的数m整除
 */
public class Subsetwithsumdivisiblebym {
    /**
     *
     * @param arr   集合
     * @param n 集合长度
     * @param m 除数
     * @return 是否被m整除
     */
    public static boolean modularSum(int[] arr,int n,int m){
        //利用鸽巢原理，当n>m时，一定存在这样一个子集，它的和被m整除
        if(n>m) {
            return true;
        }
        //存储sum%m的状态，某个数对m取余一定在0到m-1之间
        boolean[] DP=new boolean[m];
        Arrays.fill(DP,false);

        for(int i=0;i<n;i++){
            //如果余数为0，说明存在子集被m整除
            if(DP[0]) {
                return true;
            }
            //创建一个临时空间
            boolean[] temp=new boolean[m];
            Arrays.fill(temp,false);

            for(int j=0;j<m;j++){
                //如果DP[j]为true，说明前面有对m取余等于j的子集
                if(DP[j]){
                    //判断下j+集合中当前处理的数它们的和对m取余得到的值，以前有没有过
                    //没有，则将辅助空间对应位置设置为true
                    if(!DP[(j+arr[i])%m]) {
                        temp[(j+arr[i])%m]=true;
                    }
                }
            }
            //遍历辅助空间，将temp的状态同步到DP中去
            for (int j=0;j<m;j++){
                if(temp[j]) {
                    DP[j]=true;
                }
            }
            //单个元素
            DP[arr[i]%m]=true;
        }
        return DP[0];
    }
    public static void main(String arg[])
    {
        int arr[] = {3, 1, 7, 5};
        int n = arr.length;
        int m = 6;

        if(modularSum(arr, n, m)) {
            System.out.print("YES\n");
        } else {
            System.out.print("NO\n");
        }
    }
}
