package SlidingWindow;

public class MinimumSizeSubarraySum_209 {
    public int minSubArrayLen(int s, int[] nums) {
        int n=nums.length;
        int left=0,right=0;
        int windowSum=0;
        int winLen=0;
        int res=n+1;
        while(right<n){
            windowSum=windowSum+nums[right++];
            winLen++;
            while(windowSum>=s){
                res=Math.min(res,winLen);
                windowSum=windowSum-nums[left++];
                winLen--;
            }
        }
        return res==(n+1)?0:res;
    }

    public static void main(String[] args) {
        MinimumSizeSubarraySum_209 test = new MinimumSizeSubarraySum_209();
        int i = test.minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1});
        System.out.println(i);
    }
}
