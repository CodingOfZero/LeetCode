package Dynamic;

/**
 * 给定一个集合，判断是否能够将其划分为两个和相等的子集
 */
public class PartitionProblem {
    /**
     * 递归思路
     * @param arr
     * @param n
     * @param sum
     * @return
     */
    public static boolean isSubsetSum(int[] arr, int n, int sum){
        if(sum==0){
            return true;
        }
        if(n==0){
            return false;
        }
        if(arr[n-1]>sum){
            return isSubsetSum(arr,n-1,sum);
        }
        return isSubsetSum(arr,n-1,sum)||isSubsetSum(arr,n-1,sum-arr[n-1]);
    }
    public static boolean findPartition(int[] arr, int n){
        int sum=0;
        for(int item:arr){
            sum+=item;
        }
        //如果为和奇数，则不可能分为两个相等的子集
        if(sum%2!=0){
            return false;
        }
        return isSubsetSum(arr,n,sum/2);
    }

    /**
     * 动态规划，时间空间复杂度为O(sum*n)
     * @param arr
     * @param n
     * @return
     */
    public static boolean findPartitionDP(int[] arr,int n){
        int sum=0;
        for(int item:arr){
            sum+=item;
        }
        //如果为和奇数，则不可能分为两个相等的子集
        if(sum%2!=0){
            return false;
        }
        boolean[][] table=new boolean[sum/2+1][n+1];

        //对应于递归函数中的 if(sum==0) return true;
        for(int i=0;i<=n;i++){
            table[0][i]=true;
        }
        //对应于递归函数中的 if(n==0) return false;
        for(int i=1;i<=sum/2;i++){
            table[i][0]=false;
        }
        int i,j;
        for (i = 1; i <= sum / 2; i++) {
            for (j = 1; j <= n; j++) {
                table[i][j] = table[i][j - 1];
                if (i >= arr[j - 1]) {
                    table[i][j]
                            = table[i][j]
                            || table[i - arr[j - 1]][j - 1];
                }
            }
        }
        return table[sum/2][n];
    }


    public static void main(String[] args)
    {

        int[] arr = { 3, 1, 5, 9, 12 };
        int n = arr.length;

        // Function call
        if (findPartitionDP(arr, n))
            System.out.println("Can be divided into two "
                    + "subsets of equal sum");
        else
            System.out.println(
                    "Can not be divided into "
                            + "two subsets of equal sum");
    }
}
