package Dynamic;

/**
 * 求最子序列最大和
 * 规划方程为：dp[i]=max{dp[i-1]+nums[i], nums[i]}  要么当前值加上前面i - 1个数的最大子序和，要么就是nums[i]本身
 */
public class MaximumSubarray_53 {
    public static int maxSubArray(int[] nums) {
        int len=nums.length;
        //dp表示当前位置最大子序和
        int[] dp=new int[len];
        //第0，最大子序和就是第一个数的值
        dp[0]=nums[0];
        for(int i=1;i<len;i++){
            dp[i]=Math.max(dp[i-1]+nums[i],nums[i]);
        }
        int max=dp[0];
        for(int x:dp){
            max=Math.max(max,x);
        }
        return max;
    }
    public static void main(String[] args){
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
