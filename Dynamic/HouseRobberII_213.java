package Dynamic;

public class HouseRobberII_213 {
    public int rob(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        int n=nums.length;
        if(n==2) return Math.max(nums[0],nums[1]);
        if(n==1) return nums[0];
        //第一个房间和最后一个房间不能同时被偷
        //只考虑偷不偷第一个房间，去除最后一个房间
        //只考虑偷不偷最后一个房间，去除第一个房间
        //两者取最大
        int[] dp=new int[n];
        //只考虑偷不偷第一个房间
        int first=robHelper(nums,dp,0,n-2);
        //只考虑偷不偷最后一个房间
        int second=robHelper(nums,dp,1,n-1);
        return Math.max(first,second);
    }
    private static int robHelper(int[] nums,int[] dp,int start,int end){

        dp[start]=nums[start];
        dp[start+1]=Math.max(nums[start],nums[start+1]);

        for(int i=start+2;i<=end;i++){
            dp[i]=Math.max(dp[i-2]+nums[i],dp[i-1]);
        }
        return dp[end];
    }

    public int robLessSpace(int[] nums){
        if(nums==null||nums.length==0) return 0;
        int n=nums.length;
        if(n==2) return Math.max(nums[0],nums[1]);
        if(n==1) return nums[0];
        //第一个房间和最后一个房间不能同时被偷
        //只考虑偷不偷第一个房间，去除最后一个房间
        //只考虑偷不偷最后一个房间，去除第一个房间
        //两者取最大

        //只考虑偷不偷第一个房间
        int first=robHelperLessSpace(nums,0,n-2);
        //只考虑偷不偷最后一个房间
        int second=robHelperLessSpace(nums,1,n-1);
        return Math.max(first,second);
    }

    private int robHelperLessSpace(int[] nums, int start, int end) {
        int first=nums[start];
        int second=Math.max(nums[start],nums[start+1]);
        int max=second;
        for(int i=start+2;i<end+1;i++){
            max=Math.max(second,first+nums[i]);
            first=second;
            second=max;
        }
        return max;
    }
}
