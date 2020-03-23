package Array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum_15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        int N=nums.length;
        int k=0;
        List<List<Integer>> lists=new LinkedList<>();
        if(N<3) return lists;
        Arrays.sort(nums);
        for(int i=0;i<N;i++){
            if(i!=0&&nums[i]==nums[i-1]) continue;//外层相同第一次执行，其余跳过。从0开始
            for(int j=i+1;j<N-1;j++){
                if((j!=i+1)&&nums[j]==nums[j-1]) continue;//内层相同第一次执行，其余跳过。从1开始
                if((k=BinarySearch(-(nums[i]+nums[j]),nums,j+1,N-1))>j){//判断是否能够找到值为-(nums[i]+nums[j])且序号大于j
                    lists.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
            }
        }
        return lists;
    }
    /*
    二分查找
     */
    public static int BinarySearch(int target,int[] a,int lo,int hi){
        while(lo<=hi){
            int mid=lo+(hi-lo)/2;
            if(a[mid]>target) hi=mid-1;
            else if(a[mid]<target) lo=mid+1;
            else return mid;
        }
        return -1;
    }
    //清晰，高效
    public static List<List<Integer>> threeSumFast(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists=new LinkedList<>();
        for(int i=0;i<nums.length-2;i++){
            if(i>0&&nums[i]==nums[i-1]) continue;
            int l=i+1,r=nums.length-1;
            int target=-nums[i];
            while(l<r){
                if(nums[l]+nums[r]>target) r--;
                else if(nums[l]+nums[r]<target) l++;
                else {
                    lists.add(Arrays.asList(nums[i],nums[l++],nums[r--]));
                    while(l<r&&nums[r]==nums[r+1]) r--;
                    while(l<r&&nums[l]==nums[l-1])l++;
                }
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        int[] num={-1, 0, 1,0,0, 2, -1, -4};
        int[] num1={0,0};
        List<List<Integer>> lists = threeSum(num1);
        for(List<Integer> i:lists)
            System.out.println(i);
    }
}
