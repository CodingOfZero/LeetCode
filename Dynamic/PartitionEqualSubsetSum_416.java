package Dynamic;

/**
 * 分割等和子集
 */
public class PartitionEqualSubsetSum_416 {
    public boolean canPartitionSecond(int[] nums) {
        if(nums==null||nums.length==0) return false;
        int sum=0;
        for(int num:nums){
            sum+=num;
        }
        //和奇数一定不能
        if(sum%2!=0) return false;
        //背包容量，一定要装满
        int target=sum/2;
        int len=nums.length;
        int[] dp=new int[target+1];
        //处理第一件物品
        for(int j=0;j<=target;j++){
            dp[j]=j>=nums[0]?nums[0]:0;
        }
        for(int i=1;i<len;i++){
            for(int j=target;j>=nums[i];j--){
                dp[j]=Math.max(dp[j],dp[j-nums[i]]+nums[i]);
            }
        }

        return dp[target]==target;
    }
    public boolean canPartition(int[] nums) {
        if(nums==null||nums.length==0) return false;
        int sum=0;
        for(int num:nums){
            sum+=num;
        }
        //和奇数一定不能
        if(sum%2!=0) return false;
        //背包容量，一定要装满
        int target=sum/2;
        int len=nums.length;
        int[][] dp=new int[len][target+1];
        //处理第一件物品
        for(int j=0;j<=target;j++){
            dp[0][j]=j>=nums[0]?nums[0]:0;
        }
        for(int i=1;i<len;i++){
            for(int j=0;j<=target;j++){
                int num=j>=nums[i]?dp[i-1][j-nums[i]]+nums[i]:0;
                dp[i][j]=Math.max(dp[i-1][j],num);
            }
        }

        return dp[len-1][target]==target;
    }
}
