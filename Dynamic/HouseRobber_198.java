package Dynamic;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 你是一名职业劫匪，计划抢劫沿街的房屋。每间房子都藏有一定数量的钱，
 * 阻止你抢劫每间房子的唯一制约因素是相邻的房子都有安全系统连接，
 * 如果两间相邻的房子在同一晚上被闯入，它将自动联系警察。
 *
 * 给定一个非负整数列表，代表每间房子的钱数，确定你今晚可以在不报警的情况下抢劫的最大金额。
 */
public class HouseRobber_198 {
    private int[] dp;
    //递归
    public int rob(int[] nums) {
        return robHelper(nums,nums.length-1);
    }
    private int robHelper(int[] nums,int i){
        if(i<0) {
            return 0;
        }
        return Math.max(robHelper(nums,i-2)+nums[i],robHelper(nums,i-1));
    }
    //DP
    public int robTopDown(int[] nums){
        dp=new int[nums.length];
        Arrays.fill(dp,-1);
        return robTopDownHelper(nums,nums.length-1);
    }
    private int robTopDownHelper(int[] nums, int i) {
        if(i<0) {
            return 0;
        }
        if(dp[i]!=-1) {
            return dp[i];
        }
        dp[i]=Math.max(robTopDownHelper(nums,i-2)+nums[i],robTopDownHelper(nums,i-1));
        return dp[i];
    }


    public int robBottomUP(int[] nums){
        if(nums.length==2){
            return Math.max(nums[0],nums[1]);
        }
        if(nums.length==1) {
            return nums[0];
        }
        if(nums.length==0) {
            return 0;
        }
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }

}
