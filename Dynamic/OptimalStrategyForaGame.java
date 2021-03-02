package Dynamic;

/**
 * 给偶数个硬币，有两名玩家，轮流选取硬币，每次选取时，只能选择第一个或最后一个硬币。
 * 确定当我们第一个开始时，可能获取的最大金额
 * 注意：并非第一个选最大，最后的值就一定最大
 * 示例：8, 15, 3, 7  最大为15+7=22
 *      用户选8，对手选15，用户选7，对手选3  8+7=15
 *      用户选7，对手选8，用户选15，对手选3   7+15=22
 * 思路：对手一定会留给自己硬币面值最小的，所以
 *      硬币序列为[i,j]  F代表用户从序列i到序列j能够收集的最大硬币金额
 *      当自己选择第i个时，自己能够获取的最大金额为  Vi+min[F(i+1,j-1),F(i+2,j)]
 *      当自己选择第j个时，自己能够获取的最大金额为  Vj+min[F(i+1,j-1),F(i,j-2)]
 */
public class OptimalStrategyForaGame {
    public static int optimalStrategyOfGame(int[] arr,int n){
        int[][] table=new int[n][n];
        int gap, i, j, x, y, z;
        //制表过程中，先填充对角线
        for(gap=0;gap<n;gap++){
            //i与j是同时增加的
            for(i=0,j=gap;j<n;j++,i++){
                //x=F(i+2,j),y=F(i+1,j-1),z=F(i,j-2)
                x=(i+2)<=j ? table[i+2][j]:0;
                y=(i+1)<=(j-1) ? table[i+1][j-1] : 0;
                z=(i<=(j-2)) ? table[i][j-2] : 0;
                table[i][j]=Math.max(arr[i]+Math.min(x,y),arr[j]+Math.min(y,z));
            }
        }
        return table[0][n-1];
    }
    public static void main(String[] args)
    {
        int arr1[] = { 8, 15, 3, 7 };
        int n = arr1.length;
        System.out.println(
                ""
                        + optimalStrategyOfGame(arr1, n));

        int arr2[] = { 2, 2, 2, 2 };
        n = arr2.length;
        System.out.println(
                ""
                        + optimalStrategyOfGame(arr2, n));

        int arr3[] = { 20, 30, 2, 2, 2, 10 };
        n = arr3.length;
        System.out.println(
                ""
                        + optimalStrategyOfGame(arr3, n));
    }
}
