package DoublePointer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Sum4_18 {
    //回溯会超时
//    public List<List<Integer>> fourSum(int[] nums, int target) {
//        if(nums==null||nums.length<4) return new LinkedList<>();
//        List<List<Integer>> res=new LinkedList<>();
//        int cnt=4;
//        Arrays.sort(nums);
//        sumHelper(nums,target,cnt,res,new LinkedList<>(),0);
//        return res;
//    }
//
//    private void sumHelper(int[] nums, int target, int cnt, List<List<Integer>> res, LinkedList<Integer> tmp,int start) {
//        if(target==0&&cnt==0){
//            res.add(new LinkedList<>(tmp));
//            return;
//        }
//        for(int i=start;i<nums.length;i++){
//            //剪枝，与前一个相同时跳过
//            if(i>start&&nums[i-1]==nums[i]) continue;
//            //当后面个数不够cnt时直接返回
//            if(nums.length-i<cnt) return;
//            tmp.add(nums[i]);
//            sumHelper(nums, target-nums[i], cnt-1, res, tmp, i+1);
//            tmp.removeLast();
//        }
//    }
    //四重循环，依次枚举，时间复杂度为O(n^4)
    //优化：两次for循环+双指针 O(n^3)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums==null||nums.length<4) return new LinkedList<>();
        List<List<Integer>> res=new LinkedList<>();
        Arrays.sort(nums);
        int n=nums.length;
        for(int i=0;i<n-3;i++){
            //保证不取到重复
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            //当大于时，由于数组是递增的，所以表明后面无论取多少都比target大，退出第一重循环
            if(nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target){
                break;
            }
            if(nums[i]+nums[n-1]+nums[n-2]+nums[n-3]<target){
                continue;
            }
            for(int j=i+1;j<n-2;j++){
                //保证不取到重复
                if(j>i+1&&nums[j]==nums[j-1]){
                    continue;
                }
                if(nums[i]+nums[j]+nums[j+1]+nums[j+2]>target){
                    break;
                }
                if(nums[i]+nums[j]+nums[n-1]+nums[n-2]<target){
                    continue;
                }
                int left=j+1,right=n-1;
                while (left<right){
                    int sum=nums[left]+nums[right]+nums[i]+nums[j];
                    if(sum==target){
                        res.add(new LinkedList<>(Arrays.asList(nums[i],nums[j],nums[left],nums[right])));
                        left++;
                        right--;
                        while (left<right&&nums[left-1]==nums[left]) left++;
                        while (left<right&&nums[right+1]==nums[right]) right--;
                    }else if(sum<target){
                        left++;
                    }else{
                        right--;
                    }
                }
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] test=new int[]{-2,-1,-1,1,1,2,2};
        List<List<Integer>> lists = new Sum4_18().fourSum(test, 0);
        for(List<Integer> list:lists){
            list.forEach(k-> System.out.printf("%d ",k));
            System.out.println();
        }
    }
}
