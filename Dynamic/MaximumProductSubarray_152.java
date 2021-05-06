package Dynamic;

import java.util.Arrays;

/**
 * 乘积最大子数组
 * 第i个元素结尾的最大乘积，不仅仅取决于第i-1结尾加入ai的乘积与 ai单独乘积，还可能与第i-1个最小乘积有关，（当ai为负数时）
 * maxF[i]：表示第i个元素结尾的乘积最大子数组的乘积，可以考虑将ai加入第i-1个元素结尾的乘积最大或者乘积最小的子数组的乘积中，二者加上ai,
 * 三者取大，就是第i个元素结尾的乘积最大子数组的乘积
 * minF[i]，同上
 */
public class MaximumProductSubarray_152 {
    /**
     * 时间复杂度为O(n),空间为O(n)
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        int n=nums.length;
        int[] maxF=new int[n];
        int[] minF=new int[n];
        maxF[0]=nums[0];
        minF[0]=nums[0];

        for(int i=1;i<n;i++){
            maxF[i]=Math.max(maxF[i-1]*nums[i],Math.max(nums[i],minF[i-1]*nums[i]));
            minF[i]=Math.min(minF[i-1]*nums[i],Math.min(nums[i],maxF[i-1]*nums[i]));
        }
        return Arrays.stream(maxF).max().getAsInt();
    }
    public int maxProductLessSpace(int[] nums) {
        if(nums==null||nums.length==0) return 0;
        int n=nums.length;
        int maxF=nums[0];
        int minF=nums[0];
        int res=maxF;
        for(int i=1;i<n;i++){
            int nextMaxF=Math.max(maxF*nums[i],Math.max(nums[i],minF*nums[i]));
            minF=Math.min(minF*nums[i],Math.min(nums[i],maxF*nums[i]));
            maxF=nextMaxF;
            res=Math.max(res,maxF);
        }
        return res;
    }
}
