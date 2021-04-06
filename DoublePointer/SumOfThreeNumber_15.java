package DoublePointer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SumOfThreeNumber_15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new LinkedList<>();
        if(nums==null||nums.length<3) return res;
        Arrays.sort(nums);
        int n=nums.length;
        for(int i=0;i<n-2;i++){
            //后面都为正数
            if(nums[i]>0) break;
            //去掉重复情况
            if(i>0&&nums[i]==nums[i-1]) continue;

            int target=-nums[i];
            int left=i+1,right=n-1;
            while(left<right){
                int sum=nums[left]+nums[right];
                if(sum>target){
                    right--;
                }else if(sum<target){
                    left++;
                }else{
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left++;
                    right--;
                    while(left<right&&nums[left]==nums[left+1]){
                        left++;
                    }
                    while(left<right&&nums[right-1]==nums[right]){
                        right--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> lists = threeSum(nums);

    }
}
