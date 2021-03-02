package Dynamic;

/**
 * 给定一个非负整数集，以及一个值和，判断给定集中是否有一个子集的和等于给定的和。
 */
public class SubsetSumProblem {
    /**
     * 对于递归，我们考虑两种情况
     * 1）考虑子集包含最后一个元素，则要求的和=目标值-最后一个元素值，元素个数=总的个数-1
     * 2）子集不包含最后一个元素，则要求的和=目标值，元素个数=总的个数-1
     * 最坏的情况下可能会尝试给定集合的所有子集，时间复杂度是指数级的。
     * 这个问题实际上是NP-Complete（没有已知的多项式时间解）
     */
    public static boolean  isSubsetSum(int[] set,int n,int sum){
        //base case
        if(sum==0){
            return true;
        }
        //n==0&&sum>0
        if(n==0) {
            return false;
        }
        // If last element is greater than
        // sum, then ignore it
        if (set[n - 1] > sum) {
            return isSubsetSum(set, n - 1, sum);
        }
        /*
            (a) including the last element
            (b) excluding the last element
         */
        return isSubsetSum(set,n-1,sum-set[n-1])||isSubsetSum(set,n-1,sum);
    }
    /**
     * 为了在伪多项式时间内解决该问题，使用动态规划
     * 创建一个二维布尔型数组 A[arr.size()+1][target+1]
     * 如果在A[0..i]中存在值为j的子集，则状态方程dp[i][j]为true
     * if (A[i] > j)
     * DP[i][j] = DP[i-1][j]
     * else
     * DP[i][j] = DP[i-1][j] OR DP[i-1][sum-A[i]]
     * 时间空间复杂度均为O(n*sum)
     *例子：
     * set[]={3, 4, 5, 2}
     * target=6
     *
     *     0    1    2    3    4    5    6 sum
     *set
     * 0   T    F    F    F    F    F    F
     *
     * 3   T    F    F    T    F    F    F
     *
     * 4   T    F    F    T    T    F    F
     *
     * 5   T    F    F    T    T    T    F
     *
     * 2   T    F    T    T    T    T    T
     */
    public static boolean isSubset(int[] set,int n,int sum){
        boolean[][] dp=new boolean[n+1][sum+1];

        //如果和为0，则均为true
        for(int i=0;i<=n;i++){
            dp[i][0]=true;
        }
        //n==0&&sum>0
        for(int i=1;i<=sum;i++){
            dp[0][i]=false;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=sum;j++){
                if(set[i-1]>j){
                    dp[i][j]=dp[i-1][j];
                }else{
                    dp[i][j]=dp[i-1][j]||dp[i-1][j-set[i-1]];
                }
            }
        }
        return dp[n][sum];
    }

    /**
     * 通过对上面程序观察，可以发现，实际上我们仅仅需要直到前一行数据即可，故可由O（n*sum）的空间复杂度
     * 优化为O(sum)
     * 我们使用dp[2][sum+1] 其中2行数据，分别保存前一行与当前行,交替下去
     */
    public static boolean isSubsetSum2(int[] set,int n,int sum){
        boolean[][] dp=new boolean[2][sum+1];

        for(int i=0;i<=n;i++){
            for(int j=0;j<=sum;j++){
                //如果和为0，则均为true
                if(j==0){
                    dp[i%2][j]=true;
                }else if(i==0){
                    //n==0&&sum>0
                    dp[i%2][j]=false;
                }else if(set[i-1]>j){
                    dp[i%2][j]=dp[(i+1)%2][j];
                }else{
                    dp[i%2][j]=dp[(i+1)%2][j]||dp[(i+1)%2][j-set[i-1]];
                }
            }
        }
        return dp[n%2][sum];
    }
    public static void main(String[] args)
    {
        int[] set = { 3, 34, 4, 12, 5, 2 };
        int sum = 9;
        int n = set.length;
        if (isSubset(set, n, sum) == true)
            System.out.println("Found a subset"
                    + " with given sum");
        else
            System.out.println("No subset with"
                    + " given sum");

    }
}
