package DoublePointer;

import java.util.Arrays;

/**
 * 最接近的三数之和
 * 双指针
 */
public class SumClosest_16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res=target;
        int dis=Integer.MAX_VALUE;
        for(int i=0;i<nums.length-2;i++){
            int tmp=target-nums[i];
            int left=i+1,right=nums.length-1;

            while(left<right){
                int sum=nums[left]+nums[right];
                if(sum>tmp){
                    if(Math.abs(sum-tmp)<dis){
                        dis=Math.abs(sum-tmp);
                        res=sum+nums[i];
                    }
                    right--;
                    while(left<right&&nums[right]==nums[right+1])right--;
                }else if(sum<tmp){
                    if(Math.abs(sum-tmp)<dis){
                        dis=Math.abs(sum-tmp);
                        res=sum+nums[i];
                    }
                    left++;
                    while(left<right&&nums[left]==nums[left-1]) left++;
                }else{
                    return target;
                }

            }
        }
        return res;
    }
}
