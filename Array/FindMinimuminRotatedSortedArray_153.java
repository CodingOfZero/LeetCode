package Array;

/**
 * 在旋转数组中找最小值，无重复
 */
public class FindMinimuminRotatedSortedArray_153 {
    public static int findMin(int[] nums) {
        if(nums==null||nums.length==0) throw new IllegalArgumentException("error");
        if(nums.length==1) return nums[0];//考虑只有一个元素情况
        int p1=0;
        int p2=nums.length-1;
        int mid=p1;//当将排序数组的0个元素搬到后面时，即排序数组本身。此时最小值就是p1，故将mid赋值为p1
//        if(nums[p1]<nums[p2]) return nums[p1];
        while (nums[p1]>=nums[p2]){
            if(p2-p1==1) {mid=p2;break;}
            mid=(p1+p2)/2;
            if(nums[mid]>=nums[p1])
                p1=mid;
            else if(nums[mid]<=nums[p2])
                p2=mid;
        }
        return nums[mid];
    }
}
